package practice.jpapostgresql.board.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private BoardMemberDto memberInfo;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public BoardResponseDto(Long id, String title, String content, BoardMemberDto memberInfo, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberInfo = memberInfo;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
