package com.dodo.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // User의 CRUD 용도
    // 소셜 로그인으로 반환되는 겂 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드.
    Optional<User> findByEmail(String email); // Optional class : 있으면 true, 없으면 false return
}
