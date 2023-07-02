package com.dodo.book.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileContorller {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles()); // 현재 실행 중인 ActiveProfile을 모두 가져오기
        // -> real, ouath, real-db 등이 활성화되어 있다면(active) 3개가 모두 담겨 있음.

        List<String> realProfiles = Arrays.asList("real", "real1", "real2"); // real, real1, real2는 모두 배포에 사용될 profile이다.
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);
        // prifile이 없는 경우 "default"는 application.properties를 가져온다.

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
