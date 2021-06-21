package com.spideo.test.videorecommendationapi.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TitleData {

    MATRIX("matrix"),
    MATRIX_2("matrix 2"),
    INDIANA_JONES("Indiana Jones : Raiders of the Lost Ark"),
    BREAKING_BAD("Breaking Bad");

    private final String title;

}
