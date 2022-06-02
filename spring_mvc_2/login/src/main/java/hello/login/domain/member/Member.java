package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Member {

    private Long id;

    @NotBlank
    private String LoginId;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

}
