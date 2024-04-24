package org.example.springbootdev.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.Article;
import org.example.springbootdev.dto.ArticleListViewResponse;
import org.example.springbootdev.dto.ArticleViewResponse;
import org.example.springbootdev.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();  // 스트림을 통해서 엔티티를 dto로 변환한다.
        model.addAttribute("articles", articles); //블로그 글 리스트 저장

        return "articleList"; //articleList.html라는 뷰 조회

    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }

    @GetMapping("/new-article")
    //id 키를 가진 쿼리 파라미터 값을 id 변수에 매핑 (id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false)Long id, Model model){
    if(id == null){ //id가 없으면 생성
    model.addAttribute("article",new ArticleViewResponse());
    } else{ //id가 있으면 수정
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
    }
    //쿼리 파라미터로 넘어온 id 값은 newArticle메서드의 Long 타입 id 인자에 매핑한다.
    return "newArticle";
    }
}
