package com.example.test1.dto;

import com.example.test1.entity.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;



    public Article toEntity() {
        return new Article(id,title,content);
    }
}




