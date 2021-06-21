package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.model.Video;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Repository
public class VideoRepository {

    private static final Map<String, Video> VIDEO_CACHE = new HashMap<>();

    public static List<Video> allVideos() {
        return VIDEO_CACHE.values().stream().toList();
    }

    public void createOrUpdate(@NonNull Video video) {
        VIDEO_CACHE.put(video.id(), video);
    }

    public Optional<Video> find(@NonNull String id) {
        return Optional.ofNullable(VIDEO_CACHE.get(id));
    }

    public List<Video> searchByTitleKeyword(@NonNull String titleKeyword) {
        return allVideos().stream()
                .filter(video -> video.title().contains(titleKeyword))
                .sorted(comparing(Video::title))
                .collect(Collectors.toList());
    }

}
