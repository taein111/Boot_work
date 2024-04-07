package com.example.test1.dto;

import com.example.test1.entity.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;



    public Article toEntity() {
        return new Article(id,title,content);
    }
}




