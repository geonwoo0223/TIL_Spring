package practice.jpapostgresql.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.jpapostgresql.member.dto.MemberPostRequestDto;
import practice.jpapostgresql.member.dto.MemberPutRequestDto;
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

    @Transactional
    public String save(MemberPostRequestDto memberPostRequestDto)throws Exception {

        Optional<Member> memberOptional = memberRepository.findByEmail(memberPostRequestDto.getEmail());

        if ( memberOptional.isPresent() ) {
            return "중복 이메일";
        }

        Member savedMember = memberRepository.save(Member.builder()
                .name(memberPostRequestDto.getName())
                .email(memberPostRequestDto.getEmail())
                .password(memberPostRequestDto.getPassword())
                .age(memberPostRequestDto.getAge()).build());

        return savedMember.getId().toString();
    }

    public List<MemberResponseDto> getAllMembers() throws  Exception {

        List<Member> oldMembers = memberRepository.findAll();
        List<MemberResponseDto> newMembers = new ArrayList<>();
        for (Member oldMember : oldMembers) {
            newMembers.add(MemberResponseDto.builder()
                    .id(oldMember.getId())
                    .name(oldMember.getName())
                    .email(oldMember.getEmail())
                    .age(oldMember.getAge()).build());
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
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge()).build();
    }

    public MemberResponseDto getMemberEmail(String email) throws Exception {

        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if ( memberOptional.isEmpty() ) {
            return null;
        }

        Member member = memberOptional.get();

        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge()).build();
    }

    @Transactional
    public void delete(Long id) throws Exception {

        Optional<Member> memberOptional = memberRepository.findById(id);

        memberOptional.ifPresent( member -> memberRepository.delete(member) );

    }

    @Transactional
    public String update(Long id, MemberPutRequestDto memberPutRequestDto) throws Exception {

        Optional<Member> memberOptional = memberRepository.findById(id);

        memberOptional.ifPresent(member -> {
            member.update(memberPutRequestDto.getName(), memberPutRequestDto.getAge());
//            memberRepository.save(member);
        });

        return id.toString();

    }
}
