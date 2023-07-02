package com.dodo.book.board.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest { // 해당 controller는 특별히 스프링 환경이 필요하지 않음
    @Test
    public void real_profile이_조회된다() {
        // given
        String expectedProfile = "real";

        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileContorller contorller = new ProfileContorller(env);
        // when
        String profile = contorller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없으면_첫번째가_조회된다() {
        // given
        String expectedProfile = "oauth";

        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileContorller controller = new ProfileContorller(env);
        // when
        String profile = controller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void real_profile이_없으면_default가_조회된다() {
        // given
        String expectedProfile = "default";

        MockEnvironment env = new MockEnvironment();

        ProfileContorller controller = new ProfileContorller(env);
        // when
        String profile = controller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}
