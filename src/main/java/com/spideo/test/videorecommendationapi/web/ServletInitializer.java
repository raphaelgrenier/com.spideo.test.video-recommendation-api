package com.spideo.test.videorecommendationapi.web;

import com.spideo.test.videorecommendationapi.VideoRecommendationApiApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VideoRecommendationApiApplication.class);
    }

}
