package com.spideo.test.videorecommendationapi.repository;

import com.spideo.test.videorecommendationapi.model.Video;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VideoRepository {

    private static final Map<String, Video> VIDEO_CACHE = new HashMap<>();

    public void add(@NonNull Video video) {
        VIDEO_CACHE.put(video.id(), video);
    }

    public static List<Video> allVideos() {
        return VIDEO_CACHE.values().stream().toList();
    }

}
