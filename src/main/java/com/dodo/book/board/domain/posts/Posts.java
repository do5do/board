package com.dodo.book.board.domain.posts;

import com.dodo.book.board.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자 자동 추가 (public Posts() {}와 같은 효과)
@Entity // JPA의 어노테이션으로 테이블과 링크될 클래스임을 나타냄.
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스 -> Entity 클래스
    // JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는, 이 Entity 클래스의 수정을 통해 작업한다.
    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙을 나타냄. GenerationType.IDENTITY 옵션을 추가해야 auto_increment가 된다.
    private Long id; // 웬만하면 Entity의 PK는 Long타입의 Auto_increment를 추천한다. (null 판단을 위해)

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
    private String content;

    private String author; // @Column을 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다.

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // update method
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
