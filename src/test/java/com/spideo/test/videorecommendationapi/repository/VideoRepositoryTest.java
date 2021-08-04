package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.data.FilmData;
import com.spideo.test.videorecommendationapi.data.IdData;
import com.spideo.test.videorecommendationapi.data.LabelData;
import com.spideo.test.videorecommendationapi.data.TitleData;
import com.spideo.test.videorecommendationapi.data.TvShowData;
import com.spideo.test.videorecommendationapi.data.VideoData;
import com.spideo.test.videorecommendationapi.model.Film;
import com.spideo.test.videorecommendationapi.model.TvShow;
import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.model.VideoType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        assertThat(videoRepository.allVideos()).containsOnly(matrix);
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
        assertThat(videoRepository.allVideos()).containsOnly(updated);
    }

    @Test
    void should_not_find_any_video_from_an_unknown_id() {
        // GIVEN
        String id = IdData.UNKNOWN.getId();
        // WHEN
        Optional<VideoType> actual = videoRepository.find(id);
        // THEN
        assertThat(actual).isEmpty();
    }

    @Test
    void should_find_a_video_from_its_id() {
        // GIVEN
        Video matrix = VideoData.MATRIX.toVideo();
        videoRepository.createOrUpdate(matrix);
        // WHEN
        Optional<VideoType> actual = videoRepository.find(matrix.id());
        // THEN
        assertThat(actual).isEqualTo(of(matrix));
    }

    @Test
    void should_get_all_films() {
        // GIVEN
        Film indianaJones = FilmData.INDIANA_JONES.toFilm();
        videoRepository.createOrUpdate(indianaJones);
        // WHEN
        List<Film> actual = videoRepository.allFilms();
        // THEN
        assertThat(actual).isEqualTo(singletonList(indianaJones));
    }

    @Test
    void should_get_all_tv_shows() {
        // GIVEN
        TvShow stevenSpielberg = TvShowData.BREAKING_BAD.toTvShow();
        videoRepository.createOrUpdate(stevenSpielberg);
        // WHEN
        List<TvShow> actual = videoRepository.allTvShows();
        // THEN
        assertThat(actual).isEqualTo(singletonList(stevenSpielberg));
    }

    @Test
    void should_get_all_deleted_videos() {
        // GIVEN
        Video matrix2 = VideoData.MATRIX_2.toVideo();
        Video matrixDeleted = VideoData.MATRIX.toVideoDeleted();
        videoRepository.createOrUpdate(matrix2);
        videoRepository.createOrUpdate(matrixDeleted);
        // WHEN
        List<VideoType> allDeleted = videoRepository.allDeleted();
        // THEN
        assertThat(allDeleted).containsOnly(matrixDeleted);
    }

}