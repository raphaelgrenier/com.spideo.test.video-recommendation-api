package com.spideo.test.videorecommendationapi.service;

import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Arrays.asList;
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
        Video video = new Video(IdData.MATRIX.getId(), TitleData.MATRIX.getTitle(),
                asList(LabelData.SCI_FI.getLabel(), LabelData.DYSTOPIA.getLabel()));
        // WHEN
        videoService.add(video);
        // THEN
        verify(videoRepository).add(video);
    }

}