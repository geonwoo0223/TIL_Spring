package practice.jpapostgresql.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.jpapostgresql.member.dto.MemberPostRequestDto;
import practice.jpapostgresql.member.dto.MemberResponseDto;
import practice.jpapostgresql.member.entity.Member;
import practice.jpapostgresql.member.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() throws Exception {

        List<MemberResponseDto> members = memberService.getAllMembers();

        if ( members.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(members);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long id) throws Exception {

        MemberResponseDto member = memberService.getMember(id);

        if ( member == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(member);
        }

    }

    @PostMapping("/join")
    public ResponseEntity<Member> join(@Valid MemberPostRequestDto memberPostRequestDto) throws Exception {

        System.out.println("memberPostRequestDto = " + memberPostRequestDto);

        Member savedMember = memberService.save(memberPostRequestDto);

        return ResponseEntity.ok(savedMember);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {

        memberService.delete(id);
        return ResponseEntity.ok("삭제 성공");

    }


}
