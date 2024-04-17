package org.example.springbootdev;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @Sql("/insert-members.sql")
    @Test
    void getAllMembers(){ //select * from member
        //when
        List<Member> members = memberRepository.findAll();
        //select * from member

        //then
        assertThat(members.size()).isEqualTo(3);
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberById(){ //select * from member where id = 2;
        //when
        Member member = memberRepository.findById(2L).get();

        //then
        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberByName(){ //select * from memeber where name = "C";
        //when
        Member member = memberRepository.findByName("C").get();

        //then
        assertThat(member.getId()).isEqualTo(3);

    }

    @Test
    void saveMember(){ //insert into (id, name) values(1,"A")
        //given
        Member member = new Member(1L, "A");

        //when
        memberRepository.save(member);

        //then
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }


    @Test
    void saveAllMembers(){
        List<Member> members = List.of(new Member(3L, "C"), new Member(2L, "B"));

        //when
        memberRepository.saveAll(members);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById() {  //delete from member where id=2;
        //when
        memberRepository.deleteById(2L);
        //then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    @Sql("/insert-members.sql")
    @Test
    void deleteAllMembers() {
        //when
        memberRepository.deleteAll();

        //then
        assertThat(memberRepository.findAll().size()).isZero();

    }

    @Sql("/insert-members.sql")
    @Test
    void update(){
        //given
        Member member = memberRepository.findById(2L).get();

        //when
        member.changeName("BC");

        //then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }

}