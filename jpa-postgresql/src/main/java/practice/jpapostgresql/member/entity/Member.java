package practice.jpapostgresql.member.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import practice.jpapostgresql.common.entity.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Builder
    public Member(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
