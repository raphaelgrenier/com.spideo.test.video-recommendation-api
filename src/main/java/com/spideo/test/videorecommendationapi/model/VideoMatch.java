package com.spideo.test.videorecommendationapi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public record VideoMatch(@NotBlank String id,
                         @NotEmpty List<String> labels) {
}
