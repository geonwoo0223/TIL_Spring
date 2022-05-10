package practice.jpapostgresql.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import practice.jpapostgresql.member.dto.MemberPostRequestDto;
import practice.jpapostgresql.member.dto.MemberResponseDto;
import practice.jpapostgresql.member.entity.Member;
import practice.jpapostgresql.member.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 성공 유닛테스트")
    void joinSuccessTest() throws Exception {
        String content = objectMapper.writeValueAsString(new MemberPostRequestDto("test@test.com", "lguplus", 20, "0000"));

        mockMvc.perform(post("/v1/members/join")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


    @Test
    @DisplayName("이메일 형식 유닛테스트")
    void emailFormatFailTest() throws Exception {
        String content = objectMapper.writeValueAsString(new MemberPostRequestDto("gege", "lguplus", 20, "0000"));

        mockMvc.perform(post("/v1/members/join")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("이메일 중복 유닛테스트")
    void emailUniqueFailTest() throws Exception {

        MemberPostRequestDto memberPostRequestDto = MemberPostRequestDto.builder()
                .email("lguplus@lguplus.co.kr")
                .name("test1")
                .age(20)
                .password("0000")
                .build();

        Mockito.doReturn(createNewMemberResponseDto()).when(memberService).getMemberEmail(memberPostRequestDto.getEmail());




        String content = objectMapper.writeValueAsString(new MemberPostRequestDto("lguplus@lguplus.co.kr", "test2", 20, "0000"));
        mockMvc.perform(post("/v1/members/join")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError())
                    .andDo(print());
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

    private MemberResponseDto createNewMemberResponseDto() {
        return MemberResponseDto.builder()
                .name("lguplus")
                .email("lguplus@lguplus.co.kr")
                .age(20)
                .build();
    }

}