package com.spideo.test.videorecommendationapi;

import com.spideo.test.videorecommendationapi.controller.VideoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VideoRecommendationApiApplicationTests {

    @Autowired
    private VideoController videoController;

    @Test
    void contextLoads() {
        assertThat(videoController).isNotNull();
    }

}
