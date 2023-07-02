package com.dodo.book.board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/* 해당 클래스는 모든 Entity의 상위 클래스가 되어
    Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할 => 자식 클래스가 상속받도록 해줘야함. */
@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 하는 어노테이션
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing(감시, 감사) 기능을 포함시킴.
public abstract class BaseTimeEntity { // abstract class : 추상 클래스
    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    private LocalDateTime createdDate;
    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;

// java8 부터는 LocalDate와 LocalDateTime을 사용한다.(기존의 Date의 문제점을 제대로 고친 타입)
}
