package practice.jpapostgresql.member.repository;


import practice.jpapostgresql.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    List<Member> findAll();
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    Member save(Member member);
    void delete(Member member);

}
