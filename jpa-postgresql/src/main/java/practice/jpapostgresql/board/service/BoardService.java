package practice.jpapostgresql.board.service;

import practice.jpapostgresql.board.dto.BoardPostRequestDto;
import practice.jpapostgresql.board.dto.BoardPutRequestDto;
import practice.jpapostgresql.board.dto.BoardResponseDto;
import practice.jpapostgresql.board.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    String post(BoardPostRequestDto boardPostRequestDto) throws Exception;
    BoardResponseDto get(Long id) throws Exception;
    List<BoardResponseDto> getAll() throws Exception;
    List<BoardResponseDto> getAllByTitle(String title) throws Exception;
    List<BoardResponseDto> getAllByContent(String content) throws Exception;
    List<BoardResponseDto> getAllByMember(String memberName) throws Exception;
    String update(BoardPutRequestDto boardPutRequestDto) throws Exception;
    void delete(Board board) throws Exception;

}

