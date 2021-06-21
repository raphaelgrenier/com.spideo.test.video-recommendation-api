package com.spideo.test.videorecommendationapi.data;

import com.spideo.test.videorecommendationapi.model.Video;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum VideoData {

    MATRIX(IdData.MATRIX, TitleData.MATRIX, asList(LabelData.SCI_FI, LabelData.DYSTOPIA)),
    MATRIX_2(IdData.MATRIX_2, TitleData.MATRIX_2, asList(LabelData.SCI_FI, LabelData.DYSTOPIA));

    private final IdData id;
    private final TitleData title;
    private final List<LabelData> labels;

    public final Video toVideo() {
        return new Video(id.getId(), title.getTitle(),
                this.labels.stream()
                        .map(LabelData::getLabel)
                        .collect(Collectors.toList()));
    }

}
