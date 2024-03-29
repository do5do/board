package com.dodo.book.board.domain.user;

import com.dodo.book.board.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) // @Enumerated Enum 값을 어떤 형태로 저장할지를 결정.
    // 기본적으로는 int로 저장되는데, 숫자로 저장되면 db 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없다. 그래서 문자열(EnumType.STRING)로 저장될 수 있도록 선언한다.
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this; // 현재 객체의 인스턴스를 반환
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
