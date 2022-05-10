package practice.jpapostgresql.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import practice.jpapostgresql.board.entity.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    @Override
    public Board save(Board board) {
        return boardJpaRepository.save(board);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardJpaRepository.findById(id);
    }

    @Override
    public List<Board> findAll() {
        Iterable<Board> boards = boardJpaRepository.findAll();
        List<Board> newBoards = new ArrayList<>();
        boards.forEach(newBoards::add);
        return newBoards;
    }

    @Override
    public List<Board> findAllByTitle(String title) {
        Iterable<Board> boards = boardJpaRepository.findAllByTitleContaining(title);
        List<Board> newBoards = new ArrayList<>();
        boards.forEach(newBoards::add);
        return newBoards;
    }

    @Override
    public List<Board> findAllByContent(String content) {
        Iterable<Board> boards = boardJpaRepository.findAllByContentContaining(content);
        List<Board> newBoards = new ArrayList<>();
        boards.forEach(newBoards::add);
        return newBoards;
    }

    @Override
    public List<Board> findAllByMemberId(Long memberId) {
        Iterable<Board> boards = boardJpaRepository.findAllByMemberId(memberId);
        List<Board> newBoards = new ArrayList<>();
        boards.forEach(newBoards::add);
        return newBoards;
    }

    @Override
    public void delete(Board board) {
        boardJpaRepository.delete(board);
    }
}
