package practice.jpapostgresql.board.repository;

import org.springframework.data.repository.CrudRepository;
import practice.jpapostgresql.board.entity.Board;

import java.util.List;

public interface BoardJpaRepository extends CrudRepository<Board, Long> {

    List<Board> findAllByMemberId(Long memberId);
    List<Board> findAllByTitleContaining(String title);
    List<Board> findAllByContentContaining(String content);

}
