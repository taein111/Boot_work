package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
public class MemberForm {
    private String email;
    private String password;



    public Member toEntity(){
        return new Member(null,email,password);

    }
}
