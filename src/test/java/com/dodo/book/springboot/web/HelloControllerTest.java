package com.dodo.book.springboot.web;

import com.dodo.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class) // controller test
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) // /hello라는 url로 get 요청
                .andExpect(status().isOk()) // 위 요청 결과에 따라 상태는 200이며
                .andExpect(content().string(hello)); // response body에 hello가 있는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) // API 테스트할 때 사용될 요청 파라미터 설정.
                .param("amount", String.valueOf(amount))) // 단 값은 String만 허용된다.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // json 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}