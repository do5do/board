package com.dodo.book.springboot.service.posts;

import com.dodo.book.springboot.domain.posts.Posts;
import com.dodo.book.springboot.domain.posts.PostsRepository;
import com.dodo.book.springboot.web.dto.PostsResponseDto;
import com.dodo.book.springboot.web.dto.PostsSaveRequestDto;
import com.dodo.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional // CUD시 사용. begin, commit을 자동 수행, 예외 발생(Unchecked Error)시 rollback 수행
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) { // read작업이라 Transactional 어노테이션 사용하지 않음
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }
}
