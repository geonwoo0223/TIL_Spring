package practice.jpapostgresql.member.dto;

import lombok.*;


@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private int age;

    @Builder
    public MemberResponseDto(Long id, String email, String name, int age) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
    }
}
