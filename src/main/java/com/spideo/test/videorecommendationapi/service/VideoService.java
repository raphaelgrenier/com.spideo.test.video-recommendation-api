package com.spideo.test.videorecommendationapi.service;

import com.spideo.test.videorecommendationapi.model.Film;
import com.spideo.test.videorecommendationapi.model.TvShow;
import com.spideo.test.videorecommendationapi.model.VideoMatch;
import com.spideo.test.videorecommendationapi.model.VideoType;
import com.spideo.test.videorecommendationapi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void createOrUpdate(@NonNull VideoType video) {
        videoRepository.createOrUpdate(video);
    }

    public Optional<VideoType> find(@NonNull String id) {
        return videoRepository.find(id);
    }

    public List<VideoType> searchByTitleKeyword(@NonNull String titleKeyword) {
        return videoRepository.allVideos().stream()
                .filter(video -> video.getTitle().contains(titleKeyword))
                .sorted(comparing(VideoType::getTitle))
                .collect(Collectors.toList());
    }

    public List<VideoType> searchByVideoMatch(@NonNull VideoMatch videoMatch, int minCommonLabels) {
        return videoRepository.allVideos().stream()
                .filter(video -> !videoMatch.id().equals(video.getId()))
                .filter(video -> video.getLabels().stream()
                        .filter(label -> videoMatch.labels().contains(label))
                        .count() >= minCommonLabels)
                .sorted(comparing(VideoType::getTitle))
                .collect(Collectors.toList());
    }

    public List<Film> allFilms() {
        return videoRepository.allFilms();
    }

    public List<TvShow> allTvShows() {
        return videoRepository.allTvShows();
    }

}
