package com.example.test1.api;


import com.example.test1.dto.ArticleForm;
import com.example.test1.entity.Article;
import com.example.test1.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;
    //GET
    //전체조회하기
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    //단일 조회하기
    @GetMapping("api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }


    //PATCH
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){ //요청 메시지의 본문을 가져오기 위한 Requestbody
        //1. dto -> 엔티티 변환하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        //2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if(target ==null || id != article.getId()){
            //400, 잘못된 요청 응답!
            log.info("잘못된 요청 ! id: {}, article: {}", id, article.toString());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. 업데이트 및 정상 응답(200)하기
        target.patch(article); //기존 데이터 target에  patch 메서드로 수정할 데이터 article로 바꿔준다
        Article updated = articleRepository.save(target);
        return  ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //1.대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2.잘못된 요청 처리하기
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //3. 대상 삭제하기
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}