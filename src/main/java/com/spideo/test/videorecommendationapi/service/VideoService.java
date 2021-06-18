package com.spideo.test.videorecommendationapi.service;

import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void add(@NonNull Video video) {
        videoRepository.add(video);
    }

}
