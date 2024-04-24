package org.example.springbootdev.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // 뷰의 이름을 반환한다. - 반환하는 값의 이름을 가진 뷰의 파일을 찾으라는 것.
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){ //뷰로 데이터 넘겨주는 모델 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(18);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson); //person 객체 저장
        model.addAttribute("today", LocalDate.now());

        return "example"; //example.html 이라는 뷰 조회
    }



    @Setter
    @Getter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
