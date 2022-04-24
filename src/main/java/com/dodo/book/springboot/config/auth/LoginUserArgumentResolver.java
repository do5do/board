package com.dodo.book.springboot.config.auth;

import com.dodo.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
// @Component : @Bean처럼 spring container에 Bean을 등록하도록 하는 메타데이터를 기입하는 어노테이션이다.
// @Component의 경우 개발자가 직접 작성한 class를 Bean으로 등록하기위한 어노테이션이다. (@Bean은 반대로 개발자가 직접 제어할 수 없는 외부 class를 등록하기 위함)
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;

    @Override // 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단한다.
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass; // @LoginUser 어노테이션이 붙어있고, 파라미터 클래스 타입이 SessionUser.class인 경우 true를 반환한다.
    }

    @Override // 파라미터에 전달할 객체를 생성한다.
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user"); // session에서 객체를 가져온다.
    }
}
