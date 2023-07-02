package com.dodo.book.board.web;

import com.dodo.book.board.config.auth.LoginUser;
import com.dodo.book.board.config.auth.dto.SessionUser;
import com.dodo.book.board.service.posts.PostsService;
import com.dodo.book.board.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { // mustache에 url mapping
    private final PostsService postsService;
//    private final HttpSession httpSession;

    @GetMapping("/") // 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있음.
    public String index(Model model, @LoginUser SessionUser user) { // Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
        // 여기서 바로 세션에 접근하여 세션값을 가져오던 것을 개선
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index"; // 의존성 덕분에 앞의 경로(/scr/main/resources/templates/)와 파일 확장자(.mustache)는 자동으로 지정 된다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
