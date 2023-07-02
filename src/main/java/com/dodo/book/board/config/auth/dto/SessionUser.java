package com.dodo.book.board.config.auth.dto;

import com.dodo.book.board.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // SessionUser에는 인증된 사용자 정보만 필요하다.
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

    // User class를 그대로 사용하게 되면 직렬화를 구현하지 않았다는 에러를 맞닥뜨린다.
    // User class는 엔티티이기 때문에 직렬화를 구현하지 않고, 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수에 좋다.
}