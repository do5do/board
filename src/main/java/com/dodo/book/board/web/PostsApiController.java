package com.dodo.book.board.web;

import com.dodo.book.board.service.posts.PostsService;
import com.dodo.book.board.web.dto.PostsResponseDto;
import com.dodo.book.board.web.dto.PostsSaveRequestDto;
import com.dodo.book.board.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다.
@RestController
public class PostsApiController {
    // @Autowired가 없음. -> 생성자로 Bean을 주입받는 방식을 권장하기때문.
    // 생성자 주입 방식은 final선언이 가능하기때문에 임의로 빈 객체를 변경하는 것을 막을 수 있다.
    private final PostsService postsService;

    @PostMapping("/api/v1/posts") // create
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}") // update
    // json의 기본 반환형은 long형이다.
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        /*@RequestBody : 클라이언트가 전송하는 HTTP 요청의 body 내용을 java Object로 변환시켜주는 역할.
        * 즉, POST 방식의 HTTP body안에 json으로 넘어온 값을 VO(데이터 객체)에 바인딩한다.*/
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}") // read
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id); // findById JPA의 id값으로 엔티티를 가져오는 메서드
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
