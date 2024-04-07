package com.example.test1.entity;


import com.example.test1.dto.ArticleForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;


@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString

public class Article {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//db가 id 자동생성
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.title;
    }
}

