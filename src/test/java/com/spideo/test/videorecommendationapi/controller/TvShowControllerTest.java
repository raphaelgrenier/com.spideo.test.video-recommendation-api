package com.spideo.test.videorecommendationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.data.TvShowData;
import com.spideo.test.videorecommendationapi.model.TvShow;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.spideo.test.videorecommendationapi.controller.TvShowController.TV_SHOWS_PATH;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TvShowController.class)
class TvShowControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    VideoService videoService;

    @Test
    void post_tv_show_should_respond_bad_request_when_number_of_episodes_is_0() throws Exception {
        TvShow tvShow = new TvShow(new Video(IdData.BREAKING_BAD.getId(), TitleData.BREAKING_BAD.getTitle(),
                asList(LabelData.CHEMISTRY.getLabel(), LabelData.DRUG.getLabel(),
                        LabelData.DESERT.getLabel(), LabelData.CANCER.getLabel())),
                0);
        this.mockMvc.perform(post(TV_SHOWS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(tvShow)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void get_all_tv_shows_should_respond_success() throws Exception {
        List<TvShow> stevenSpielbergSingleton = singletonList(TvShowData.BREAKING_BAD.toTvShow());
        when(videoService.allTvShows()).thenReturn(stevenSpielbergSingleton);
        this.mockMvc.perform(get(TV_SHOWS_PATH))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(stevenSpielbergSingleton)));
    }

    @Test
    void get_all_tv_shows_should_respond_not_found() throws Exception {
        this.mockMvc.perform(get(TV_SHOWS_PATH))
                .andExpect(status().isNotFound());
    }

}