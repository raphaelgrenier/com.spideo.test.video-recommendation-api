package com.spideo.test.videorecommendationapi.controller;

import com.spideo.test.videorecommendationapi.model.Film;
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

import static com.spideo.test.videorecommendationapi.controller.FilmController.FILMS_PATH;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(FILMS_PATH)
@Validated
public class FilmController {

    static final String FILMS_PATH = "/films";

    private final VideoService videoService;

    @Autowired
    public FilmController(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation("Adds or fully updates a film in the repository")
    @ApiResponses({
            @ApiResponse(code = SC_NO_CONTENT, message = "The film posted in the request body was created or updated"),
            @ApiResponse(code = SC_BAD_REQUEST, message = "The film posted in the request body was not formed properly")
    })
    @PostMapping
    @ResponseStatus(value = NO_CONTENT)
    public void createOrUpdate(@RequestBody @Validated Film film) {
        videoService.createOrUpdate(film);
    }

    @ApiOperation("Retrieves all films in the repository")
    @ApiResponse(code = SC_NOT_FOUND, message = "No film was found in the repository")
    @GetMapping
    public List<Film> allFilms() {
        List<Film> films = videoService.allFilms();
        if (films.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return films;
    }

}
