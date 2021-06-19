package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        // WHEN
        videoRepository.createOrUpdate(video);
        // THEN
        assertThat(allVideos()).containsOnly(video);
    }

    @Test
    void should_update_a_video() {
        // GIVEN
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        Video updated = new Video(video.id(), TitleData.MATRIX.getTitle(),
                singletonList(LabelData.SCI_FI.getLabel()));
        // WHEN
        videoRepository.createOrUpdate(video);
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
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        videoRepository.createOrUpdate(video);
        // WHEN
        Optional<Video> actual = videoRepository.find(video.id());
        // THEN
        assertThat(actual).isEqualTo(of(video));
    }

}