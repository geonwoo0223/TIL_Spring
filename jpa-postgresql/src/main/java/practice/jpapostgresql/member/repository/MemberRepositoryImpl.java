package practice.jpapostgresql.member.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.jpapostgresql.member.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public List<Member> findAll() {
        Iterable<Member> members = memberJpaRepository.findAll();
        List<Member> newMembers = new ArrayList<>();
        for (Member member : members) {
            newMembers.add(member);
        }
        return newMembers;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        memberJpaRepository.delete(member);
    }
}
