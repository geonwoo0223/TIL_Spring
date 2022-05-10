package practice.jpapostgresql.member.entity;

import lombok.*;
import practice.jpapostgresql.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity(name = "tb_member")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String password;

    @Builder
    public Member(Long id, String email, String name, int age, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public void update(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
