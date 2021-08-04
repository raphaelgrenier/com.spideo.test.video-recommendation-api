package com.spideo.test.videorecommendationapi.controller;

import com.spideo.test.videorecommendationapi.model.TvShow;
import com.spideo.test.videorecommendationapi.service.VideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.spideo.test.videorecommendationapi.controller.TvShowController.TV_SHOWS_PATH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(TV_SHOWS_PATH)
@Validated
public class TvShowController {

    static final String TV_SHOWS_PATH = "/tv-shows";

    private final VideoService videoService;

    @Autowired
    public TvShowController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("Adds or fully updates a tv-show in the repository")
    @ApiResponses({
            @ApiResponse(code = SC_NO_CONTENT, message = "The tv-show posted in the request body was created or updated"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "The tv-show posted in the request body was not formed properly")
    })
    @PostMapping
    @ResponseStatus(value = NO_CONTENT)
    public void createOrUpdate(@RequestBody @Validated TvShow tvShow) {
        videoService.createOrUpdate(tvShow);
    }

    @ApiOperation("Retrieves all tv-shows in the repository")
    @ApiResponse(code = SC_NOT_FOUND, message = "No tv-show was found in the repository")
    @GetMapping
    public List<TvShow> allTvShows() {
        List<TvShow> tvShows = videoService.allTvShows();
        if (tvShows.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return tvShows;
    }

}
