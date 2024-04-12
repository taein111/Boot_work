package com.example.test1.dto;

import com.example.test1.entity.Article;
import com.example.test1.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id; //댓글의 id
    private Long articleId; // 댓글의 부모 id
    private String nickname; // 댓글의 작성자
    private String body; // 댓글의 본문


    //메서드의 반환값이 댓글 dto가 되도록 CommentDto 생성자 호출한다.
    //댓글 dto와 엔티티 차이가 거의 없으므로 매개변수로 받은 엔티티(comment)의 getter 메서드로 입력값을 가져온다.
    //생성자에 댓글의 id, 부모게시글 id, nickname, body를 차례로 생성자에 입력해서 댓글 엔티티가 댓글 dto로 변환된 후 반환시킨다.
    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), //댓글 엔티티의 id
                comment.getArticle().getId(), // 댓글 엔티티가 속한 부모 게시글의 id
                comment.getNickname(), //댓글 엔티티의 nickname
                comment.getBody() // 댓글 엔티티의 body
        );
    }
}
