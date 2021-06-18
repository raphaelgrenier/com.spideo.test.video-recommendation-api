package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.model.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spideo.test.videorecommendationapi.repository.VideoRepository.allVideos;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VideoRepositoryTest {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    void should_add_a_video_to_the_repository() {
        // GIVEN
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        // WHEN
        videoRepository.add(video);
        // THEN
        assertThat(allVideos()).containsOnly(video);
    }

}