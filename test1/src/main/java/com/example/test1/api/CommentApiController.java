package com.example.test1.api;

import com.example.test1.dto.CommentDto;
import com.example.test1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
    //1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        //서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments") //댓글 생성 요청 접수
    //create 메서드 생성, pathvaruable로 요청 url 의 articleId를 가져오고, http요청의 body로부터 json 데이터를 받아오므로 requestBody를 이용해 CommentDto dto로 받는다.
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);

    }

    //3. 댓글 수정
    //4. 댓글 삭제
}
