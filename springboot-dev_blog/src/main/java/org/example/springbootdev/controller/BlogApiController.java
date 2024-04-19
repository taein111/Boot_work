package org.example.springbootdev.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.Article;
import org.example.springbootdev.dto.AddArticleRequest;
import org.example.springbootdev.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // http response body에 객체 데이터를 json 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    //http 메서드가 post일 때 전달받은 url과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //RequestBody로 요청 본문 값 매핑                       //컨트롤러에서 요청한 본문을 받을 객체 매개변수
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }




}
