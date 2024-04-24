package org.example.springbootdev.repository;

import org.example.springbootdev.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //email로 사용자 정보 가져옴 / 값이 존재하는 상태, 존재하지 않는 상태  둘 다 가능


}
