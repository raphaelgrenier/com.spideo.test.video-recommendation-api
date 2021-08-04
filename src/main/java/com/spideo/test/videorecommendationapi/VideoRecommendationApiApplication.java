package com.spideo.test.videorecommendationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class,
        springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class
})
public class VideoRecommendationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoRecommendationApiApplication.class, args);
    }

}
