package org.example.springbootdev.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdev.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article){ //article 엔티티를 받아와서 뷰에 넘겨주기 위해 articleviewresponse(dto)로 반환한다.
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
