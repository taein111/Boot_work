package org.example.springbootdev.dto;

import lombok.Getter;
import org.example.springbootdev.domain.Article;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();

    }
    //해당 필드를 가지는 클래스를 만든 다음, 엔티티를 인수로 받는 생성자 추가(엔티티를 받아와서 dto로 만들고, 응답하기 위해)

}
