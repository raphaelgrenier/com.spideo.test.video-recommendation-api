package com.spideo.test.videorecommendationapi.controller;

import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.service.VideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.spideo.test.videorecommendationapi.controller.VideoController.VIDEOS_PATH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(VIDEOS_PATH)
public class VideoController {

    static final String VIDEOS_PATH = "/videos";
    static final String ID_PATH_PARAM = "/{id}";

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("Adds or fully update a video in the repository")
    @ApiResponses({
            @ApiResponse(code = SC_NO_CONTENT, message = "The video posted in the request body was created or updated"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "The video posted in the request body was not formed properly")
    })
    @PostMapping
    @ResponseStatus(value = NO_CONTENT)
    public void createOrUpdate(@RequestBody @Validated Video video) {
        videoService.createOrUpdate(video);
    }

    @ApiOperation("Retrieves a video in the repository")
    @ApiResponse(code = SC_NOT_FOUND, message = "The requested video was not found in the repository")
    @GetMapping(value = ID_PATH_PARAM)
    public Video find(@PathVariable String id) {
        return videoService.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
