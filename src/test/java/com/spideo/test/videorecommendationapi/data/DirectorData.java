package com.spideo.test.videorecommendationapi.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum DirectorData {

    STEVEN_SPIELBERG("Steven Spielberg");

    private final String director;

}
