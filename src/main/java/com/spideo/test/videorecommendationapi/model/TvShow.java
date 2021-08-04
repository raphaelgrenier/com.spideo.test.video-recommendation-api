package com.spideo.test.videorecommendationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public record TvShow(@NotNull Video video,
                     @Min(1) Integer numberOfEpisodes) implements VideoType, TvShowType {

    @Override
    @JsonIgnore
    public String id() {
        return video.id();
    }

    @Override
    @JsonIgnore
    public String title() {
        return video.title();
    }

    @Override
    @JsonIgnore
    public List<String> labels() {
        return video.labels();
    }

    @Override
    public boolean isDeleted() {
        return video().isDeleted();
    }

    @Override
    public Integer numberOfEpisodes() {
        return numberOfEpisodes;
    }
}
