package com.spideo.test.videorecommendationapi.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum LabelData {

    SCI_FI("sci-fi"),
    DYSTOPIA("dystopia"),
    ADVENTURE("adventure"),
    WHIP("whip"),
    ARCHEOLOGY("archeology"),
    CHEMISTRY("chemistry"),
    DRUG("drug"),
    DESERT("desert"),
    CANCER("cancer");

    private final String label;

}
