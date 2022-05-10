package practice.jpapostgresql.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPutRequestDto {

    @NotNull
    private String name;

    @NotNull
    private int age;

    @Builder
    public MemberPutRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
