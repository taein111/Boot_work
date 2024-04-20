package org.example.springbootdev.dto;

import lombok.Getter;
import org.example.springbootdev.domain.Article;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;



    //Article(엔티티)에담겨있는 데이터를 가져와 ArticleListViewResponse 객체로 만든다. (뷰에게 데이터를 전달하기 위한 객체)
    public ArticleListViewResponse(Article article){
        this.id = article.getId();
        this.title =article.getTitle();
        this.content = article.getContent();
    }
}
