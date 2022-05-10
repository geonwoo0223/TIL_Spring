package practice.jpapostgresql.board.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardPutRequestDto {

    @NotNull
    private String title;

    private String subtitle;

    @NotNull
    private String content;

    @Builder
    public BoardPutRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
