package com.spideo.test.videorecommendationapi.controller;

import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.service.VideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.spideo.test.videorecommendationapi.controller.VideoController.VIDEOS_PATH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@RestController
@RequestMapping(VIDEOS_PATH)
public class VideoController {

    static final String VIDEOS_PATH = "/videos";

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("Adds or fully update a video in the repository")
    @ApiResponse(code = SC_BAD_REQUEST, message = "The video posted in the request body was not formed properly")
    @PostMapping
    public void add(@RequestBody @Validated Video video) {
        videoService.add(video);
    }

}
