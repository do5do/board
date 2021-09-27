package com.dodo.book.springboot.web;

import com.dodo.book.springboot.domain.posts.Posts;
import com.dodo.book.springboot.domain.posts.PostsRepository;
import com.dodo.book.springboot.web.dto.PostsSaveRequestDto;
import com.dodo.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
// @WebMvcTest를 사용하지 않음. -> JPA 기능이 작동하지 않기 때문.
// 지금 같이 JPA 기능까지 한번에 테스트할때는 @SpringBootTest와 TestRestTemplate를 사용한다.
// @SpringBootTest는 반드시 @RunWith(SpringRunner.class)와 함께 사용해야한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; // REST 방식으로 개발한 API의 test를 최적화하는 클래스
    // HTTP 요청 후 데이터를 응답받을 수 있는 객체이며 ResponseEntity와 함께 자주 사용된다.

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build(); // .build() -> return new PostSaveRequestDto(this);

        String url = "http://localhost:"+port+"/api/v1/posts";

        // when
        // TestRestTemplate.postForEntity() : http 헤더를 사용하여 결과를 ResponseEntity로 반환받는다.
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // isEqualTo : 같은 경우 true
        assertThat(responseEntity.getBody()).isGreaterThan(0L); // isGreaterThan : 큰 경우 true
        // 0L은 Long형 값을 비교할때 0보다 0L이라고 사용한다. (L이라고 뒤에 붙은 것은 명시적으로 Long형 값이란 의미)

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        // HttpEntity는 Http 통신(요청 및 응답)이 이루어질때 header와 body관련 정보를 저장할 수 있도록 하는 클래스.
        // HttpEntity를 사용하여 요청을 빌드한다.
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        // .exchage() : update할때 사용. 결과를 ResponseEntity로 반환 받음. Http Header를 변경할 수 있음.
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url,
                HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
