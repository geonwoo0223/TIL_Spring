package practice.jpapostgresql.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import practice.jpapostgresql.member.dto.MemberPostRequestDto;
import practice.jpapostgresql.member.dto.MemberPutRequestDto;
import practice.jpapostgresql.member.dto.MemberResponseDto;
import practice.jpapostgresql.member.entity.Member;
import practice.jpapostgresql.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 유닛테스트")
   void saveMemberTest() throws Exception {
        //given
        MemberPostRequestDto memberPostRequestDto = MemberPostRequestDto.builder()
                .email("testemail1@test.com")
                .name("test1")
                .password("0000")
                .age(20)
                .build();

        Member saveMember = createNewMember();

        //mocking
        when(memberRepository.save(any(Member.class))).thenReturn(saveMember);

        //when
        MemberService memberService = new MemberService(memberRepository);
        String savedMember = memberService.save(memberPostRequestDto);

        //then
        assertThat(Long.parseLong(savedMember)).isEqualTo(saveMember.getId());

    }

    @Test
    @DisplayName("모든멤버 조회 유닛테스트")
    void getAllMembersTest() throws Exception {
        //given
        Member member1 = Member.builder()
                .id(1L)
                .email("test1@test.com")
                .password("0000")
                .age(20)
                .name("test1")
                .build();
        Member member2 = Member.builder()
                .id(2L)
                .email("test2@test.com")
                .password("0000")
                .age(20)
                .name("test2")
                .build();
        List<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);

        //mocking
        when(memberRepository.findAll()).thenReturn(members);

        //when
        MemberService memberService = new MemberService(memberRepository);

        //then
        assertThat(memberService.getAllMembers().size()).isEqualTo(2);

    }

    @Test
    @DisplayName("단일멤버 조회 성공 유닛테스트")
    void getMemberTest() throws Exception {
        //given
        Member member = createNewMember();

        //mocking
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        MemberService memberService = new MemberService(memberRepository);
        MemberResponseDto getMember = memberService.getMember(1L);

        //then
        assertThat(getMember.getId()).isEqualTo(1L);

    }

    @Test
    @DisplayName("단일멤버 조회 실패 유닛테스트")
    void getMemberFailTest() throws  Exception {
        //mocking
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        MemberService memberService = new MemberService(memberRepository);

        //then
        assertThat(memberService.getMember(1L)).isNull();

    }

    @Test
    @DisplayName("단일멤버 삭제 유닛테스트")
    void deleteMemberTest() throws Exception {
        //given
        Member member = createNewMember();

        //mocking
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        MemberService memberService = new MemberService(memberRepository);
        memberService.delete(1L);

        //then
        verify(memberRepository).delete(member);

    }

    @Test
    @DisplayName("회원 수정 유닛테스트")
    void updateMemberTest() throws Exception{
        //given
        Member member = createNewMember();

        MemberPutRequestDto memberPutRequestDto = MemberPutRequestDto.builder()
                .age(30)
                .name("lgcns")
                .build();

        //mocking
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        MemberService memberService = new MemberService(memberRepository);
        memberService.update(1L, memberPutRequestDto);

        //then
        assertThat(memberService.getMember(1L).getName()).isEqualTo("lgcns");


    }


    private Member createNewMember() {
        return Member.builder()
                .id(1L)
                .email("lguplus@lguplus.co.kr")
                .name("lguplus")
                .password("0000")
                .age(20)
                .build();
    }

}