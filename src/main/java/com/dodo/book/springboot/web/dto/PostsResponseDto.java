package com.dodo.book.springboot.web.dto;

import com.dodo.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    // PostsResponseDto는 Entity의 필드 중 일부만 사용하기 때문에 생성자로 Entity를 받아 필드값에 넣는다.
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
