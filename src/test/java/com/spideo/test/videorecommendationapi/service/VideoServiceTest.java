package com.spideo.test.videorecommendationapi.service;

import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.VideoData;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.model.VideoMatch;
import com.spideo.test.videorecommendationapi.model.VideoType;
import com.spideo.test.videorecommendationapi.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        String keyword = "matrix";
        Video matrix = VideoData.MATRIX.toVideo();
        Video matrix2 = VideoData.MATRIX_2.toVideo();
        when(videoRepository.allVideos()).thenReturn(asList(matrix, matrix2));
        // WHEN
        List<VideoType> actual = videoService.searchByTitleKeyword(keyword);
        // THEN
        assertThat(actual).isEqualTo(asList(matrix, matrix2));
    }

    @Test
    void should_find_other_videos_from_a_video_sufficient_double_match() {
        // GIVEN
        VideoMatch videoMatch = VideoData.UNKNOWN.toVideoMatch();
        Video matrix = VideoData.MATRIX.toVideo();
        Video matrix2 = VideoData.MATRIX_2.toVideo();
        when(videoRepository.allVideos()).thenReturn(asList(matrix, matrix2));
        // WHEN
        List<VideoType> actual = videoService.searchByVideoMatch(videoMatch, 2);
        // THEN
        assertThat(actual).isEqualTo(asList(matrix, matrix2));
    }

    @Test
    void should_find_other_videos_from_a_video_insufficient_double_match() {
        // GIVEN
        VideoMatch videoMatch = VideoData.UNKNOWN.toVideoMatch();
        Video matrix = VideoData.MATRIX.toVideo();
        Video matrix2 = VideoData.MATRIX_2.toVideo();
        when(videoRepository.allVideos()).thenReturn(asList(matrix, matrix2));
        // WHEN
        List<VideoType> actual = videoService.searchByVideoMatch(videoMatch, 3);
        // THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void should_get_all_films() {
        // WHEN
        videoService.allFilms();
        // THEN
        verify(videoRepository).allFilms();
    }

    @Test
    void should_get_all_tv_shows() {
        // WHEN
        videoService.allTvShows();
        // THEN
        verify(videoRepository).allTvShows();
    }

}