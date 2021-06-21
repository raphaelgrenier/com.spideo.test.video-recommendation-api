package com.spideo.test.videorecommendationapi.service;

import com.spideo.test.videorecommendationapi.model.Video;
import com.spideo.test.videorecommendationapi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void createOrUpdate(@NonNull Video video) {
        videoRepository.createOrUpdate(video);
    }

    public Optional<Video> find(@NonNull String id) {
        return videoRepository.find(id);
    }

    public List<Video> searchByTitleKeyword(@NonNull String titleKeyword) {
        return videoRepository.searchByTitleKeyword(titleKeyword);
    }

}
