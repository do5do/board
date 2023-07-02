package com.dodo.book.board.web.dto;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when (위 변수 값 전달)
        HelloResponseDto hrd = new HelloResponseDto(name, amount);
        // then (예상값과 같은지 확인)
        assertThat(hrd.getName()).isEqualTo(name);
        assertThat(hrd.getAmount()).isEqualTo(amount);
    }
}
