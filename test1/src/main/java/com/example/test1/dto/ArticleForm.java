package com.example.test1.dto;

import com.example.test1.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private String title;
    private String content;



    public Article toEntity() {
        return new Article(null,title,content);
    }
}




