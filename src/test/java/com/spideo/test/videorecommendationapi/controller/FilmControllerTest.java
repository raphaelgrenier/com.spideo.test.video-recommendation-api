package com.spideo.test.videorecommendationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spideo.test.videorecommendationapi.data.FilmData;
import com.spideo.test.videorecommendationapi.model.Film;
import com.spideo.test.videorecommendationapi.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.spideo.test.videorecommendationapi.controller.FilmController.FILMS_PATH;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    VideoService videoService;

    @Test
    void get_all_films_should_respond_success() throws Exception {
        List<Film> indianaJonesSingleton = singletonList(FilmData.INDIANA_JONES.toFilm());
        when(videoService.allFilms()).thenReturn(indianaJonesSingleton);
        this.mockMvc.perform(get(FILMS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(indianaJonesSingleton)));
    }

    @Test
    void get_all_films_should_respond_not_found() throws Exception {
        this.mockMvc.perform(get(FILMS_PATH))
                .andExpect(status().isNotFound());
    }

}