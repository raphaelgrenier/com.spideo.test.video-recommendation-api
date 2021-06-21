package com.spideo.test.videorecommendationapi.data;

import com.spideo.test.videorecommendationapi.model.Film;
import com.spideo.test.videorecommendationapi.model.Video;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public enum FilmData {

    INDIANA_JONES(IdData.INDIANA_JONES, TitleData.INDIANA_JONES, DirectorData.STEVEN_SPIELBERG,
            ZonedDateTime.parse("1982-03-18T12:00:00Z"),
            asList(LabelData.WHIP, LabelData.ARCHEOLOGY));

    private final IdData id;
    private final TitleData title;
    private final DirectorData directorData;
    private final ZonedDateTime releaseDate;
    private final List<LabelData> labels;

    public final Film toFilm() {
        return new Film(new Video(id.getId(), title.getTitle(),
                labels.stream()
                        .map(LabelData::getLabel)
                        .collect(Collectors.toList())),
                directorData.getDirector(),
                releaseDate);
    }

}
