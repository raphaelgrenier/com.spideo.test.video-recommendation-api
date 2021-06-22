package com.spideo.test.videorecommendationapi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public record Video(@NotBlank String id,
                    @NotBlank String title,
                    @NotEmpty List<String> labels,
                    boolean deleted) implements VideoType {

    public Video(@NotBlank String id,
                 @NotBlank String title,
                 @NotEmpty List<String> labels) {
        this(id, title, labels, false);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public List<String> labels() {
        return labels;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

}
