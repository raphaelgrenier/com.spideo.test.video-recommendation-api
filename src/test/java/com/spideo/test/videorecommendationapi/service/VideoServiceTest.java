package com.spideo.test.videorecommendationapi.service;

import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.VideoData;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    VideoRepository videoRepository;

    @Test
    void should_add_a_video_through_the_repository() {
        // GIVEN
        Video matrix = VideoData.MATRIX.toVideo();
        // WHEN
        videoService.createOrUpdate(matrix);
        // THEN
        verify(videoRepository).createOrUpdate(matrix);
    }

    @Test
    void should_find_a_video_from_its_id() {
        // GIVEN
        String id = IdData.MATRIX.getId();
        // WHEN
        videoService.find(id);
        // THEN
        verify(videoRepository).find(id);
    }

    @Test
    void should_not_find_a_video_from_an_unknown_id() {
        // GIVEN
        String id = IdData.UNKNOWN.getId();
        // WHEN
        videoService.find(id);
        // THEN
        verify(videoRepository).find(id);
    }

    @Test
    void should_find_videos_from_a_title_keyword() {
        // GIVEN
        String keyword = "keyword";
        // WHEN
        videoService.searchByTitleKeyword(keyword);
        // THEN
        verify(videoRepository).searchByTitleKeyword(keyword);
    }

}