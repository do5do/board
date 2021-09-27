package com.dodo.book.springboot.web;

import com.dodo.book.springboot.service.posts.PostsService;
import com.dodo.book.springboot.web.dto.PostsResponseDto;
import com.dodo.book.springboot.web.dto.PostsSaveRequestDto;
import com.dodo.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다.
@RestController
public class PostsApiController {
    // @Autowired가 없음. -> 생성자로 Bean을 주입받는 방식을 권장하기때문.
    private final PostsService postsService;

    @PostMapping("/api/v1/posts") // insert
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}") // update
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}") // select
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id); // findById JPA의 id값으로 엔티티를 가져오는 메서드
    }
}
