package com.example.test1.api;

import com.example.test1.dto.CommentDto;
import com.example.test1.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto updateDto = commentService.update(id, dto);//몇번 댓글을 어떤내용으로 수정해야할지 알아야 하기때문에 id 와 dto를 받는다.
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    //4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        //서비스에 위임
        CommentDto deleteDto = commentService.delete(id); // id(번호)를 받아와 삭제한다, 결과를 CommentDto 타입의 deleteDto 변수에 저장한다
        log.info(deleteDto.toString());
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}
