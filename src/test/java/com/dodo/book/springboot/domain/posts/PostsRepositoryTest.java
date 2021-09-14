package com.dodo.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 자동으로 H2 데이터베이스를 실행해준다.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // 단위 테스트가 끝날 때 수행
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 남아있어 다음 테스트 실행 시 테스트가 실패 할 수 있다.
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // posts 테이블에 insert/update 쿼리를 실행 => id가 있으면 update, 없으면 insert
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("dodo@gamil.com")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // posts 테이블에서 모든 데이터 조회

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
