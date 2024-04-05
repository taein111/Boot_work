package com.example.test1.entity;


import com.example.test1.dto.ArticleForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Controller;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String content;



}