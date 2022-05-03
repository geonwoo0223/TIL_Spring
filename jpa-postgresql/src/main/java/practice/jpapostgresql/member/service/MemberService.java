package practice.jpapostgresql.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.jpapostgresql.member.dto.MemberPostRequestDto;
import practice.jpapostgresql.member.dto.MemberResponseDto;
import practice.jpapostgresql.member.entity.Member;
import practice.jpapostgresql.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberPostRequestDto memberPostRequestDto)throws Exception {
        Member savedMember = memberRepository.save(Member.builder()
                .name(memberPostRequestDto.getName())
                .email(memberPostRequestDto.getEmail()).build());

        return savedMember;
    }

    public List<MemberResponseDto> getAllMembers() throws  Exception {

        List<Member> oldMembers = memberRepository.findAll();
        List<MemberResponseDto> newMembers = new ArrayList<>();
        for (Member oldMember : oldMembers) {
            newMembers.add(MemberResponseDto.builder().id(oldMember.getId())
                    .name(oldMember.getName()).email(oldMember.getEmail()).build());
        }

        return newMembers;
    }

    public MemberResponseDto getMember(Long id) throws Exception {

        Optional<Member> memberOptional = memberRepository.findById(id);

        if ( memberOptional.isEmpty() ) {
            return null;
        }

        Member member = memberOptional.get();


        return MemberResponseDto.builder()
                .id(member.getId()).name(member.getName()).email(member.getEmail()).build();
    }

    public void delete(Long id) throws Exception {

        Optional<Member> memberOptional = memberRepository.findById(id);

        memberOptional.ifPresent( member -> memberRepository.delete(member) );


    }

}
