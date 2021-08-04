package com.spideo.test.videorecommendationapi.controller;

import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.model.VideoMatch;
import com.spideo.test.videorecommendationapi.model.VideoType;
import com.spideo.test.videorecommendationapi.service.VideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

import static com.spideo.test.videorecommendationapi.controller.VideoController.VIDEOS_PATH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(VIDEOS_PATH)
@Validated
public class VideoController {

    static final String VIDEOS_PATH = "/videos";
    static final String MATCH_PATH = "/match";
    static final String DELETED_PATH = "/deleted";
    static final String ID_PATH_PARAM = "/{id}";
    static final String TITLE_QUERY_PARAM = "title";
    static final String MIN_COMMON_LABELS = "min-common-labels";

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("Adds or fully updates a video in the repository")
    @ApiResponses({
            @ApiResponse(code = SC_NO_CONTENT, message = "The video posted in the request body was created or updated"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "The video posted in the request body was not formed properly")
    })
    @PostMapping
    @ResponseStatus(NO_CONTENT)
    public void createOrUpdate(@RequestBody @Validated Video video) {
        videoService.createOrUpdate(video);
    }

    @ApiOperation("Retrieves a video in the repository")
    @ApiResponse(code = SC_NOT_FOUND, message = "The requested video was not found in the repository")
    @GetMapping(ID_PATH_PARAM)
    public VideoType find(@ApiParam("id of the video to create or update") @PathVariable String id) {
        return videoService.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ApiOperation("Search videos by a title keyword")
    @ApiResponse(code = SC_NOT_FOUND, message = "No video matching given title keyword was found")
    @GetMapping
    public List<VideoType> searchByTitleKeyword(
            @ApiParam("Title keyword to search (at least 3 chars)")
            @RequestParam(TITLE_QUERY_PARAM) @Size(min = 3) String titleKeyword) {
        var result = videoService.searchByTitleKeyword(titleKeyword);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @ApiOperation("Search other videos matching labels from the requested one")
    @ApiResponse(code = SC_NOT_FOUND, message = "No matching video was found")
    @PostMapping(MATCH_PATH)
    public List<VideoType> searchByVideoMatch(@RequestBody @Validated VideoMatch videoMatch,
                                              @ApiParam("The minimum common labels to match the requested one's")
                                              @RequestParam(value = MIN_COMMON_LABELS) @Min(1) int minCommonLabels) {
        var result = videoService.searchByVideoMatch(videoMatch, minCommonLabels);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @ApiOperation("Deletes logically a video from its id if it exists")
    @ApiResponse(code = SC_NOT_FOUND, message = "")
    @ApiResponses({
            @ApiResponse(code = SC_NO_CONTENT, message = "The video was successfully logically deleted"),
            @ApiResponse(code = SC_NOT_FOUND, message = "No video with this id was found")
    })
    @DeleteMapping(ID_PATH_PARAM)
    @ResponseStatus(NO_CONTENT)
    public void delete(@ApiParam("id of the video to delete") @PathVariable String id) {
        videoService.delete(id);
    }

    @ApiOperation("Retrieves all logically deleted videos in the repository")
    @ApiResponse(code = SC_NOT_FOUND, message = "No deleted video was found")
    @GetMapping(DELETED_PATH)
    public List<VideoType> allDeleted() {
        var result = videoService.allDeleted();
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

}
