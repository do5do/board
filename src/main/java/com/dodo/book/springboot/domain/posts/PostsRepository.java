package com.dodo.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository를 추가할 필요가 없다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // Posts 클래스로 Database를 접근하게 해줄 JpaRepository 생성 (Dao)

}