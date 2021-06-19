package com.spideo.test.videorecommendationapi.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum IdData {

    UNKNOWN("unknown"),
    MATRIX("97e343ac-3141-45d1-aff6-68a7465d55ec");

    private final String id;

}
