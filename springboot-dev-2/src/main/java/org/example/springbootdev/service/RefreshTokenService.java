package org.example.springbootdev.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdev.domain.RefreshToken;
import org.example.springbootdev.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;


//전달받은 리프레시 토큰으로 리프레시 토큰 객체를 검색해서 전달하는 findByRefreshToken 메서드를 구현한다.
@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new IllegalArgumentException("Unexpected token"));

    }
}
