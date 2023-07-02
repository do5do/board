package com.dodo.book.board.config.auth;

import com.dodo.book.board.config.auth.CustomOAuth2UserService;
import com.dodo.book.board.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                // h2-console 화면을 사용하기위해 해당 옵션들을 disable 함.
                .and()
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션의 시작점. authorizeRequests가 선언되어야만 andMatchers 옵션을 사용할 수 있음.
                .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                // antMatchers() : 권한 관리 대상을 지정하는 옵션
                // permitAll() : 모든 사용자가 접근할 수 있음.
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // hasRole() : 특정 권한만 가진 사람만이 접근 가능.
                // "/api/v1/**" 주소를 가진 api는 USER 권한을 가진 사람만 가능하도록 설정함
                .anyRequest().authenticated()
                // anyRequest() : 설정된 값들 이외 나머지 url들을 나타냄.
                // authenticated()를 추가하여 나머지 url들은 모두 인증된 사용자들에게만 허용. (인증된 사용자 -> 로그인한 사용자)
                .and()
                .logout().logoutSuccessUrl("/")
                // logoutSuccessUrl() : logout 성공시 해당 주소("/")로 이동.
                .and()
                .oauth2Login()
                // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint().userService(customOAuth2UserService);
                // userInfoEndpoint() : Oauth 2 로그인 성공 이후 사용자 정보를 가저올 때의 설정들을 담당.
                // userService() : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
