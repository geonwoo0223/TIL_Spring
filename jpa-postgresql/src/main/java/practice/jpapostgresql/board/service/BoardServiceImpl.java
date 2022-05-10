package practice.jpapostgresql.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.jpapostgresql.board.dto.BoardMemberDto;
import practice.jpapostgresql.board.dto.BoardPostRequestDto;
import practice.jpapostgresql.board.dto.BoardPutRequestDto;
import practice.jpapostgresql.board.dto.BoardResponseDto;
import practice.jpapostgresql.board.entity.Board;
import practice.jpapostgresql.board.repository.BoardRepositoryImpl;
import practice.jpapostgresql.member.entity.Member;
import practice.jpapostgresql.member.repository.MemberRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepositoryImpl boardRepository;

    // MSA향으로 수정시 member에 요청보내는것으로 수정
    private final MemberRepositoryImpl memberRepository;

    @Override
    public String post(BoardPostRequestDto boardPostRequestDto) throws Exception {

        Board board = Board.builder()
                        .title(boardPostRequestDto.getTitle())
                        .content(boardPostRequestDto.getContent())
                        .memberId(boardPostRequestDto.getMemberId())
                        .build();

        return boardRepository.save(board).getId().toString();
    }

    @Override
    public BoardResponseDto get(Long id) throws Exception {
        Optional<Board> boardOptional = boardRepository.findById(id);

        if ( boardOptional.isEmpty() ) {
            return null;
        }


        Board board = boardOptional.get();

        // MSA향으로 수정시 member에 요청보내는것으로 수정
        Optional<Member> memberOptional = memberRepository.findById(board.getMemberId());

        BoardMemberDto boardMemberDto = BoardMemberDto.builder().build();

        if (memberOptional.isEmpty()) {
            boardMemberDto = null;
        }
        // 여기까지 MSA로 바꾸면 수정할 코드

        Member member = memberOptional.get();

        boardMemberDto.setId(member.getId());
        boardMemberDto.setAge(member.getAge());
        boardMemberDto.setEmail(member.getEmail());
        boardMemberDto.setName(member.getName());


        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .memberInfo(boardMemberDto)
                .build();
    }

    @Override
    public List<BoardResponseDto> getAll() throws Exception {

        List<Board> oldBoards = boardRepository.findAll();

        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        for (Board board : oldBoards) {

            // MSA향으로 수정시 member에 요청보내는것으로 수정
            Optional<Member> memberOptional = memberRepository.findById(board.getMemberId());

            BoardMemberDto boardMemberDto = BoardMemberDto.builder().build();

            if (memberOptional.isEmpty()) {
                boardMemberDto = null;
            }
            // 여기까지 MSA로 바꾸면 수정할 코드

            Member member = memberOptional.get();

            boardMemberDto.setId(member.getId());
            boardMemberDto.setAge(member.getAge());
            boardMemberDto.setEmail(member.getEmail());
            boardMemberDto.setName(member.getName());

            boardResponseDtos.add(BoardResponseDto.builder()
                                    .id(board.getId())
                                    .title(board.getTitle())
                                    .content(board.getContent())
                                    .createdDate(board.getCreatedDate())
                                    .memberInfo(boardMemberDto)
                                    .lastModifiedDate(board.getLastModifiedDate())
                                    .build());
        }

        return boardResponseDtos;
    }

    @Override
    public List<BoardResponseDto> getAllByTitle(String title) throws Exception {
        return null;
    }

    @Override
    public List<BoardResponseDto> getAllByContent(String content) throws Exception {
        return null;
    }

    @Override
    public List<BoardResponseDto> getAllByMember(String memberName) throws Exception {
        return null;
    }

    @Override
    public String update(BoardPutRequestDto boardPutRequestDto) throws Exception {
        return null;
    }

    @Override
    public void delete(Board board) throws Exception {

    }

}
