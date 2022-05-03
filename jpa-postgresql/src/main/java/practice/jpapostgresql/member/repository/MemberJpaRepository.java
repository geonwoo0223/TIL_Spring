package practice.jpapostgresql.member.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpapostgresql.member.entity.Member;

public interface MemberJpaRepository extends CrudRepository<Member, Long> {


}
