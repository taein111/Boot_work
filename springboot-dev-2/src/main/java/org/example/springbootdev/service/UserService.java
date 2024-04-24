package org.example.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.User;
import org.example.springbootdev.dto.AdduserRequest;
import org.example.springbootdev.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


//AddUserRequest 객체를 인수로 받는 회원정보 추가 메서드를 작성한다.
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long save(AdduserRequest dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return  userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build()).getId();
    }
    //메서드 추가
    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
