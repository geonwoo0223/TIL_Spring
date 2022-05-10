package practice.jpapostgresql.board.dto;


import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardMemberDto {

    private Long id;
    private String name;
    private String email;
    private int age;

    @Builder
    public BoardMemberDto(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
