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

import java.util.*;


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

        // MSA향으로 수정시 member에 요청보내는것으로 수정 (redis 적용하기 좋은 곳)
        List<Member> allMembers = memberRepository.findAll();
        Map<Long, Member> membersHashMap= new HashMap<>();

        for (Member member : allMembers) {
            membersHashMap.put(member.getId(), member);
        }


        for (Board board : oldBoards) {

            Long memberId = board.getMemberId();
            Member member = membersHashMap.get(memberId);

            BoardMemberDto boardMemberDto = BoardMemberDto.builder()
                                                .id(memberId)
                                                .email(member.getEmail())
                                                .age(member.getAge())
                                                .name(member.getName()).build();

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
        List<Board> oldBoards = boardRepository.findAllByTitle(title);

        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        // MSA향으로 수정시 member에 요청보내는것으로 수정 (redis 적용하기 좋은 곳)
        List<Member> allMembers = memberRepository.findAll();
        Map<Long, Member> membersHashMap= new HashMap<>();

        for (Member member : allMembers) {
            membersHashMap.put(member.getId(), member);
        }


        for (Board board : oldBoards) {

            Long memberId = board.getMemberId();
            Member member = membersHashMap.get(memberId);

            BoardMemberDto boardMemberDto = BoardMemberDto.builder()
                    .id(memberId)
                    .email(member.getEmail())
                    .age(member.getAge())
                    .name(member.getName()).build();

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
    public List<BoardResponseDto> getAllByContent(String content) throws Exception {
        List<Board> oldBoards = boardRepository.findAllByContent(content);

        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        // MSA향으로 수정시 member에 요청보내는것으로 수정 (redis 적용하기 좋은 곳)
        List<Member> allMembers = memberRepository.findAll();
        Map<Long, Member> membersHashMap= new HashMap<>();

        for (Member member : allMembers) {
            membersHashMap.put(member.getId(), member);
        }


        for (Board board : oldBoards) {

            Long memberId = board.getMemberId();
            Member member = membersHashMap.get(memberId);

            BoardMemberDto boardMemberDto = BoardMemberDto.builder()
                    .id(memberId)
                    .email(member.getEmail())
                    .age(member.getAge())
                    .name(member.getName()).build();

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
    public List<BoardResponseDto> getAllByMember(String memberName) throws Exception {

        // MSA향으로 수정시 member에 요청보내는것으로 수정 (redis 적용하기 좋은 곳)
        List<Member> allMembers = memberRepository.findAll();
        Map<String, Member> membersHashMap= new HashMap<>();

        for (Member member : allMembers) {
            membersHashMap.put(member.getName(), member);
        }

        List<Board> oldBoards = boardRepository.findAllByMemberId();

        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();



        for (Board board : oldBoards) {

            Long memberId = board.getMemberId();
            Member member = membersHashMap.get(memberId);

            BoardMemberDto boardMemberDto = BoardMemberDto.builder()
                    .id(memberId)
                    .email(member.getEmail())
                    .age(member.getAge())
                    .name(member.getName()).build();

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
    public String update(BoardPutRequestDto boardPutRequestDto) throws Exception {
        return null;
    }

    @Override
    public void delete(Board board) throws Exception {

    }

}
