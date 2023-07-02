package com.dodo.book.board.domain.posts;

import com.dodo.book.board.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// @Repository를 추가할 필요가 없다.
// Posts 클래스로 Database를 접근하게 해줄 JpaRepository 생성 (Dao)
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();
}
