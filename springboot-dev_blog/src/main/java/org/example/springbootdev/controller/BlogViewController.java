package org.example.springbootdev.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.Article;
import org.example.springbootdev.dto.ArticleListViewResponse;
import org.example.springbootdev.dto.ArticleViewResponse;
import org.example.springbootdev.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogservice;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogservice.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogservice.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }
}
