package com.dodo.book.springboot.domain.user;

import com.dodo.book.springboot.domain.BaseTimeEntity;
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
}
