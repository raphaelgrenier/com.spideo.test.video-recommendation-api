package com.spideo.test.videorecommendationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.data.VideoData;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.service.VideoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.spideo.test.videorecommendationapi.controller.VideoController.ID_PATH_PARAM;
import static com.spideo.test.videorecommendationapi.controller.VideoController.TITLE_QUERY_PARAM;
import static com.spideo.test.videorecommendationapi.controller.VideoController.VIDEOS_PATH;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void post_malformed_id_video_should_respond_bad_request() throws Exception {
        Video video = new Video("", TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void post_malformed_title_video_should_respond_bad_request() throws Exception {
        Video video = new Video(IdData.MATRIX.getId(), "",
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void post_malformed_labels_video_should_respond_bad_request() throws Exception {
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(), emptyList());
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void post_matrix_video_should_respond_success() throws Exception {
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        this.mockMvc.perform(post(VIDEOS_PATH)
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(video)))
                .andExpect(status().isNoContent());
    }

    @Test
    void get_matrix_video_should_respond_success() throws Exception {
        String id = IdData.MATRIX.getId();
        Video video = new Video(id, TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        Mockito.when(videoService.find(id)).thenReturn(of(video));
        this.mockMvc.perform(get(VIDEOS_PATH + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(video), true));
    }

    @Test
    void get_unknown_video_should_respond_not_found() throws Exception {
        String id = IdData.UNKNOWN.getId();
        Mockito.when(videoService.find(id)).thenReturn(empty());
        this.mockMvc.perform(get(VIDEOS_PATH + ID_PATH_PARAM, id))
                .andExpect(status().isNotFound());
    }

    @Test
    void get_videos_from_a_title_keyword_should_respond_success() throws Exception {
        Video matrix = VideoData.MATRIX.toVideo();
        Video matrix2 = VideoData.MATRIX_2.toVideo();
        String titleKeyword = "tit";
        Mockito.when(videoService.searchByTitleKeyword(titleKeyword)).thenReturn(asList(matrix, matrix2));
        this.mockMvc.perform(get(VIDEOS_PATH).param(TITLE_QUERY_PARAM, titleKeyword))
                .andExpect(status().isOk());
    }

    @Test
    void get_videos_from_a_too_short_title_keyword_should_respond_bad_request() throws Exception {
        String titleKeyword = "ti";
        this.mockMvc.perform(get(VIDEOS_PATH).param(TITLE_QUERY_PARAM, titleKeyword))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.containsString(BAD_REQUEST.getReasonPhrase())));
    }

}