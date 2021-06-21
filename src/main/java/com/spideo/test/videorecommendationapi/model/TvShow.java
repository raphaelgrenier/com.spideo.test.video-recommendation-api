package com.spideo.test.videorecommendationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public record TvShow(@NotNull Video video,
                     @Min(1) Integer numberOfEpisodes) implements VideoType, TvShowType {

    @Override
    @JsonIgnore
    public String getId() {
        return video.id();
    }

    @Override
    @JsonIgnore
    public String getTitle() {
        return video.title();
    }

    @Override
    @JsonIgnore
    public List<String> getLabels() {
        return video.getLabels();
    }

    @Override
    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
}
