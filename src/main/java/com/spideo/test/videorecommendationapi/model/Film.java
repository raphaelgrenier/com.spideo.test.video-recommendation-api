package com.spideo.test.videorecommendationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

public record Film(@NotNull Video video,
                   @NotBlank String director,
                   @NotNull ZonedDateTime releaseDate) implements VideoType, FilmType {

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
        return video.labels();
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

}
