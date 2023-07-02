package com.dodo.book.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing // JPA Auditing 활성화 => 삭제 이유 : MockMvcTest에서 entity가 없어서 나는 에러 방지를 위함
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정하는 어노테이션 -> 항상 프로젝트의 최상단에 위치해야한다.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
