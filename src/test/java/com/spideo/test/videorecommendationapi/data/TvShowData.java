package com.spideo.test.videorecommendationapi.data;

import com.spideo.test.videorecommendationapi.model.TvShow;
import com.spideo.test.videorecommendationapi.model.Video;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TvShowData {

    BREAKING_BAD(IdData.BREAKING_BAD, TitleData.BREAKING_BAD, 62,
            asList(LabelData.CHEMISTRY, LabelData.DRUG, LabelData.DESERT, LabelData.CANCER));

    private final IdData id;
    private final TitleData title;
    private final int numberOfEpisodes;
    private final List<LabelData> labels;

    public final TvShow toTvShow() {
        return new TvShow(new Video(id.getId(), title.getTitle(),
                labels.stream()
                        .map(LabelData::getLabel)
                        .collect(Collectors.toList())),
                numberOfEpisodes);
    }

}
