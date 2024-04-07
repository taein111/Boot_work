package com.example.test1.api;


import com.example.test1.dto.ArticleForm;
import com.example.test1.entity.Article;
import com.example.test1.repository.ArticleRepository;
import com.example.test1.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;
    //GET
    //전체조회하기
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    //단일 조회하기
    @GetMapping("api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //PATCH
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){ //요청 메시지의 본문을 가져오기 위한 Requestbody
       Article updated = articleService.update(id, dto);
       return (updated != null) ?
               ResponseEntity.status(HttpStatus.OK).body(updated) :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createList = articleService.createArticles(dtos);
        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
     }

}
