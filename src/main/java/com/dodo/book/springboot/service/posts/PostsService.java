package com.dodo.book.springboot.service.posts;

import com.dodo.book.springboot.domain.posts.Posts;
import com.dodo.book.springboot.domain.posts.PostsRepository;
import com.dodo.book.springboot.web.dto.PostsListResponseDto;
import com.dodo.book.springboot.web.dto.PostsResponseDto;
import com.dodo.book.springboot.web.dto.PostsSaveRequestDto;
import com.dodo.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다.
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

    @Transactional(readOnly = true) // readOnly option을 주면 조회 기능만 남겨두어 조회 속도가 개선된다.
    // transactional에 option이 들어가지 않는다면, import 확인
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // 람다식으로 .map(posts -> new PostsListResponseDto(posts)와 같다.
                .collect(Collectors.toList());
        // postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드이다.
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
