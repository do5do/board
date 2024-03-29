package com.dodo.book.board.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 어노테이션이 생성될 수 있는 위치를 지정.
// PARAMETER로 지정했으니 메소드 파라미터로 선언된 객체에서만 사용할 수 있다.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // annotation class. LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 된다.
}
