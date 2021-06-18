package com.spideo.test.videorecommendationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.spideo.test.videorecommendationapi.controller.VideoController.VIDEOS_PATH;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideoController.class)
class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    VideoService videoService;

    @Test
    void add_malformed_id_video_should_respond_bad_request() throws Exception {
        Video video = new Video("", TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_malformed_title_video_should_respond_bad_request() throws Exception {
        Video video = new Video(IdData.MATRIX.getId(), "",
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_malformed_labels_video_should_respond_bad_request() throws Exception {
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(), emptyList());
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void add_matrix_video_should_respond_success() throws Exception {
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andDo(print())
                .andExpect(status().isOk());
    }

}