package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.model.Film;
import com.spideo.test.videorecommendationapi.model.TvShow;
import com.spideo.test.videorecommendationapi.model.VideoType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Repository
public class VideoRepository {

    private final Map<String, VideoType> videoCache = new HashMap<>();

    public List<VideoType> allVideos() {
        return videoCache.values().stream()
                .filter(videoType -> !videoType.isDeleted())
                .toList();
    }

    public List<Film> allFilms() {
        return allVideos().stream()
                .filter(Film.class::isInstance)
                .map(Film.class::cast)
                .toList();
    }

    public List<TvShow> allTvShows() {
        return allVideos().stream()
                .filter(TvShow.class::isInstance)
                .map(TvShow.class::cast)
                .toList();
    }

    public void createOrUpdate(@NonNull VideoType video) {
        videoCache.put(video.id(), video);
    }

    public Optional<VideoType> find(@NonNull String id) {
        return ofNullable(videoCache.get(id));
    }

    public List<VideoType> allDeleted() {
        return videoCache.values().stream()
                .filter(VideoType::isDeleted)
                .collect(Collectors.toList());
    }
}
