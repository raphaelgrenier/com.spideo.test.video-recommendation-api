package com.spideo.test.videorecommendationapi.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum IdData {

    UNKNOWN("unknown"),
    MATRIX("97e343ac-3141-45d1-aff6-68a7465d55ec"),
    MATRIX_2("97e343ac-3141-45d1-aff6-68a7465d55ed"),
    INDIANA_JONES("86be99d4-ba36-11eb-8529-0242ac130003"),
    BREAKING_BAD("4544e617-84ab-4934-9bda-4d14c7ebcc19");

    private final String id;

}
