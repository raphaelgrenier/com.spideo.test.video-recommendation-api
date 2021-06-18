package com.spideo.test.videorecommendationapi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public record Video(@NotBlank String id,
                    @NotBlank String title,
                    @NotEmpty List<String> labels) {
}
