package org.example.springbootdev.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdev.domain.Article;

//컨트롤러에서 요청한 본문을 받을 객체
//DAO(데이터베이스와 연결되고 데이터를 조회하고 수정하는데 사용하는 객체)
//DTO(단순하게 데이터를 옮기기 위해 사용하는 전달자 역할- 별도의 비즈니스 로직을 포함하지 않는다.)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){ //생성자 사용해 객체 생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
