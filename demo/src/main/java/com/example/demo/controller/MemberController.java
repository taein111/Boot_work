package com.example.demo.controller;

import com.example.demo.dto.MemberForm;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/email")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form){
        //System.out.println(form.toString());
//        log.info(form.toString());
        //1. dto를 엔티티로 변환
        Member member = form.toEntity();
        //System.out.println(member.toString());
//        log.info(member.toString());
        //2. 레퍼지토리로 엔티티를 db에 저장
        Member saved = memberRepository.save(member);
        //System.out.println(saved.toString());
//        log.info(saved.toString());
        return "";
    }
    //특정 회원 조회
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
//        log.info("id =" +id);
        //1.id를 조회해서 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        //2.모델에 데이터 등록하기
        model.addAttribute("member",memberEntity);
        //3. 뷰 페이지 반환하기
        return "members/show";

    }

    //모든 회원 조회
    @GetMapping("/members")
    public String index(Model model){
        List<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList", memberEntityList);
        return "members/index";
    }
}