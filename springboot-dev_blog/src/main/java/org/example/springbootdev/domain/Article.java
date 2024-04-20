package org.example.springbootdev.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", updatable = false)
    private  Long id;

    @Column(name = "title", nullable = false)//title 이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder //빌더 패턴으로 객체 생성
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }


    //엔티티에 요청받은 내용으로 값을 수정하는 메서드.  ex - (Article.update("aaa","ccc"))
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
