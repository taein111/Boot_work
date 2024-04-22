package org.example.springbootdev.service;


import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.Article;
import org.example.springbootdev.dto.AddArticleRequest;
import org.example.springbootdev.dto.UpdateArticleRequest;
import org.example.springbootdev.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request){ //dto를 받아온다
        return blogRepository.save(request.toEntity()); //dto에서 엔티티로 변환하여 저장
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id)) ;
    }

    public void deleteById(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request){ //엔티티 타입을반환하는 update 메서드.
        Article article = blogRepository.findById(id) //레포지터리의 findbyid 메서드를 통해 article에 저장된 정보를 가져온다.
                .orElseThrow(()-> new IllegalArgumentException("not found" + id)); //없으면 exception 반환

        article.update(request.getTitle(), request.getContent()); // article로 가져온 정보를 Article 클래스의 update메서드를 통해 수정한다.
                                                                    // 이때 받는 매개변수는 UpdateArticleRequest클래스(요청 클래스)의 필드
        return article; //수정된 엔티티를 반환한다.
    }

}
