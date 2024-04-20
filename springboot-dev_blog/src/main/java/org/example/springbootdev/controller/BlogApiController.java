package org.example.springbootdev.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.Article;
import org.example.springbootdev.dto.AddArticleRequest;
import org.example.springbootdev.dto.ArticleResponse;
import org.example.springbootdev.dto.UpdateArticleRequest;
import org.example.springbootdev.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // http response body에 객체 데이터를 json 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    //http 메서드가 post일 때 전달받은 url과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //RequestBody로 요청 본문 값 매핑                       //컨트롤러에서 요청한 본문을 받을 객체 매개변수
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")//응답하기 위해 응답dto를 가져와서 뿌려준다.
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article UpdatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(UpdatedArticle);
    }
}
