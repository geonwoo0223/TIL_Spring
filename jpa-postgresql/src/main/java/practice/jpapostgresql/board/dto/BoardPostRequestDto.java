package practice.jpapostgresql.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardPostRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    //우선은 멤버id도 그냥 받아오자
    @NotNull
    private Long memberId;


    @Builder
    public BoardPostRequestDto(String title, String content, Long memberId) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }
}
