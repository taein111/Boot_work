package com.example.test1.service;

import com.example.test1.dto.CommentDto;
import com.example.test1.entity.Article;
import com.example.test1.entity.Comment;
import com.example.test1.repository.ArticleRepository;
import com.example.test1.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) { //게시글 번호를 받는 comments 메서드, 받아서 댓글을 조회한다.
        //1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId); // articleId번 게시글의 모든 댓글을 가져온다
        //2. 엔티티 -> dto 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>(); //비어있는 리스트 타입 dto 만들기
        for(int i = 0; i<comments.size(); i++){ //조회한 댓글 엔티티 수만큼 반복하기
            Comment c = comments.get(i); // 조회한 댓글 엔티티 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); //엔티티를 dto로 변환
            dtos.add(dto); //변환한 dto를 dtos리스트에 삽입
        }
        //3. 결과 반환
        return dtos;
    }

    @Transactional //create 메서드는 db내용을 바꾸기 때문에 트랜잭션 처리해야한다.
    public CommentDto create(Long articleId, CommentDto dto) {
        //1 . 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" + "대상 게시글이 없습니다."));
        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        //3. 댓글 엔티티를 db에 저장
        Comment created = commentRepository.save(comment);
        //4, dto로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}
