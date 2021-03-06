package practice.jpapostgresql.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberPostRequestDto {

    @Email(message = "not an email")
    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private int age;

    @NotNull
    private String password;

    @Builder
    public MemberPostRequestDto(String email, String name, int age, String password) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
