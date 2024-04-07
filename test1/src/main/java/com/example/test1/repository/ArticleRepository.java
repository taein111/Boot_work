package com.example.test1.repository;

import com.example.test1.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    List<Article> findAll();
}
