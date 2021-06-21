package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.data.VideoData;
import com.spideo.test.videorecommendationapi.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.spideo.test.videorecommendationapi.repository.VideoRepository.allVideos;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    void should_create_a_video() {
        // GIVEN
        Video matrix = VideoData.MATRIX.toVideo();
        // WHEN
        videoRepository.createOrUpdate(matrix);
        // THEN
        assertThat(allVideos()).containsOnly(matrix);
    }

    @Test
    void should_update_a_video() {
        // GIVEN
        Video matrix = VideoData.MATRIX.toVideo();
        Video updated = new Video(matrix.id(), TitleData.MATRIX.getTitle(),
                singletonList(LabelData.SCI_FI.getLabel()));
        // WHEN
        videoRepository.createOrUpdate(matrix);
        videoRepository.createOrUpdate(updated);
        // THEN
        assertThat(allVideos()).containsOnly(updated);
    }

    @Test
    void should_not_find_any_video_from_an_unknown_id() {
        // GIVEN
        String id = IdData.UNKNOWN.getId();
        // WHEN
        Optional<Video> actual = videoRepository.find(id);
        // THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void should_find_a_video_from_its_id() {
        // GIVEN
        Video matrix = VideoData.MATRIX.toVideo();
        videoRepository.createOrUpdate(matrix);
        // WHEN
        Optional<Video> actual = videoRepository.find(matrix.id());
        // THEN
        assertThat(actual).isEqualTo(of(matrix));
    }

    @Test
    void should_find_videos_from_a_title_keyword() {
        // GIVEN
        Video matrix = VideoData.MATRIX.toVideo();
        Video matrix2 = VideoData.MATRIX_2.toVideo();
        videoRepository.createOrUpdate(matrix);
        videoRepository.createOrUpdate(matrix2);
        // WHEN
        List<Video> actual = videoRepository.searchByTitleKeyword("matrix");
        // THEN
        assertThat(actual).isEqualTo(asList(matrix, matrix2));
    }

}