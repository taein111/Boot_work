package org.example.springbootdev.repository;

import org.example.springbootdev.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

//jparepositoy를 상속 받을 때 엔티티 Article 과 엔티티의 PK 타입 Long 을 인수로 넣는다.
public interface BlogRepository extends JpaRepository<Article, Long> {

}
