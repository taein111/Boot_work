package org.example.springbootdev.dto;

import lombok.Getter;
import lombok.Setter;

//사용자 정보를 담고있는 객체
@Getter
@Setter
public class AdduserRequest {
    private String email;
    private String password;
}
