package com.example.test1.controller;

import com.example.test1.dto.ArticleForm;
import com.example.test1.dto.CommentDto;
import com.example.test1.entity.Article;
import com.example.test1.repository.ArticleRepository;
import com.example.test1.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        //1. dto를 엔티티로 변환하기
        Article article = form.toEntity();
        log.info(article.toString());
        //2. 레퍼지토리를 통해 엔티티 디비에 저장하기
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " +id);
        //1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id); //CommentService 의 comments(id) 메서드를 호출해 조회한 댓글 목록을 commentDtos 변수에 저장한다.
        log.info(articleEntity.toString());
        //2. 모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos); //댓글 목록 모델에 등록
        //3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        List<Article> articleList = articleRepository.findAll();
        log.info(articleList.toString());
        //2. 모델에 데이터 등록하기
        model.addAttribute("article",articleList);
        log.info(model.toString());
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }

    //수정 페이지 만들고 기존 데이터 불러오기
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1. 수정할 데이터 가져오기
        Article articleEntity =articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    //불러온 데이터를 수정에 db에 반영하게 뷰 페이지에 뿌리기
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. dto를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. 엔티티를 db에 저장(레퍼지토리)
        //2-1 . db에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값 갱신하기
        if(target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        //1.삭제할 데이터 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다");
        }
        //3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
