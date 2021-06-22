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
        return video.isDeleted();
    }

    @Override
    public String director() {
        return director;
    }

    @Override
    public ZonedDateTime releaseDate() {
        return releaseDate;
    }

}
