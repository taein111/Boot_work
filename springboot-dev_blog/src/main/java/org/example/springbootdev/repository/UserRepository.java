package org.example.springbootdev.repository;

import org.example.springbootdev.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //email로 사용자 정보를 가져옴
}
//이메일로 사용자를 식별할 수 있다. 즉, 사용자 이름으로 봐도 된다. 따라서 사용자 정보를 가져오기 위해 스프링 시큐리티가 이메일을 전달받아야 한다.
//스프링 데이터 jpa는 메서드 규칙에 맞춰 메서드를 선언하면 이름을 분석해 자동으로 쿼리를 생성해준다.
// Optional<User> findByEmail(String email); = From users where email = #{email}
