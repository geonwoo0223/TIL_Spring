package practice.jpapostgresql.member.dto;

import lombok.*;


@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;

    @Builder
    public MemberResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
