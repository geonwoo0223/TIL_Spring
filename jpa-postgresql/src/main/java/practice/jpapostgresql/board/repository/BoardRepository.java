package practice.jpapostgresql.board.repository;

import practice.jpapostgresql.board.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);
    Optional<Board> findById(Long id);
    List<Board> findAll();
    List<Board> findAllByTitle(String title);
    List<Board> findAllByContent(String content);
    List<Board> findAllByMemberId(Long memberId);
    void delete(Board board);

}
