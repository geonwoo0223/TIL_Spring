package practice.jpapostgresql.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPostRequestDto {

    @Email
    private String email;

    @NotNull
    private String name;

}
