package practice.jpapostgresql.member.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpapostgresql.member.entity.Member;

import java.util.Optional;

public interface MemberJpaRepository extends CrudRepository<Member, Long> {

    Optional<Member> findMemberByEmail(String email);

}
