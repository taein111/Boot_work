package org.example.springbootdev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springbootdev.domain.Article;
import org.example.springbootdev.domain.User;
import org.example.springbootdev.dto.AddArticleRequest;
import org.example.springbootdev.dto.UpdateArticleRequest;
import org.example.springbootdev.repository.BlogRepository;
import org.example.springbootdev.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc //MockMvc 생성 및 자동 구성
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; //직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setSecurityContext(){
        userRepository.deleteAll();
        user = userRepository.save(user.builder()
                .email("user@email.com")
                .password("test")
                .build());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공한다")
    @Test
    public void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title,content);

        //객체 json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest); //객체를 JSON 으로 직렬화해준다.

        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.getName()).thenReturn("username");

        //when
        //mockMvc http 메서드, url, 요청 본문, 요청 타입 등 설정한뒤 설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)  //url
                .contentType(MediaType.APPLICATION_JSON_VALUE)//요청 타입
                .principal(principal)
                .content(requestBody)); // 요청 본문

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllArticles : 블로그 글 목록 조회에 성공한다")
    @Test
    public void findAllArticles() throws Exception {
        //given 블로그 글을 저장한다
        final String url = "/api/articles";
        Article savedArticle = createDefaultArticle();
        //when 목록 조회 api를 호출한다
        //mockMvc http 메서드, url, 요청 본문, 요청 타입 등 설정한뒤 설정한 내용을 바탕으로 요청 전송

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then 응답 코드가 200 ok 이고, 반환받은 값 중에 0번째 요소의 content와 title이 저장된 값과 같은지 확인한다.
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(savedArticle.getContent()))
                .andExpect(jsonPath("$[0].title").value(savedArticle.getTitle()));

    }

    @DisplayName("findArticle : 블로그 글 조회에 성공한다")
    @Test
    public void findArticle() throws Exception {
        //given 블로그 글을 저장한다
        final String url = "/api/articles/{id}";
        Article savedArticle = createDefaultArticle();
        //when 저장한 블로그 글의 id 값으로 api 를 호출한다.
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        //then 응답 코드가 200 ok 이고, 반환받은 content와 title이 저장된 값과 같은지 확인
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(savedArticle.getContent()))
                .andExpect(jsonPath("$.title").value(savedArticle.getTitle()));

    }

    @DisplayName("deleteArticle : 블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception {
        //given : 블로그 글을 저장한다.
        final String url = "/api/articles/{id}";
        Article savedArticle = createDefaultArticle();

        //when : 저장한 블로그 글의 id 값으로 삭제 api를 호출한다.
        mockMvc.perform(delete(url, savedArticle.getId()))
                .andExpect(status().isOk());

        //then 응답 코드가 200 ok 이고, 블로그 글 리스트를 전체 조회 해 조회한 배열 크기가 0인지 확인한다.
        List<Article> articles=blogRepository.findAll();
//        assertThat(articles).isEmpty();
        assertThat(blogRepository.findById(savedArticle.getId())).isEmpty(); // 레코드 하나의 id가 잇는지 검사하는 것.
    }

    @DisplayName("updateArticle : 블로그 글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception {
        //given : 블로그 글을 저장한다.
        final String url = "/api/articles/{id}";
        Article savedArticle = createDefaultArticle();

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle,newContent);

        //when : update api로 수정 요청을 보낸다. 이 때 요청 타입은 json 이며, given 절에서 미리 만들어둔 객체를 요청 본문으로 함께 보낸다.
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        //then 응답 코드가 200 ok 인지 확인한다. 블로그 글 id로 조회한 후 값이 수정되는지 확인한다.
        result.andExpect(status().isOk());
        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }

    private Article createDefaultArticle() {
        return blogRepository.save(Article.builder()
                .title("title")
                .author(user.getUsername())
                .content("content")
                .build());
    }

}