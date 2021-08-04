package com.spideo.test.videorecommendationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface VideoType {

    String id();

    String title();

    List<String> labels();

    @JsonIgnore
    boolean isDeleted();

}
