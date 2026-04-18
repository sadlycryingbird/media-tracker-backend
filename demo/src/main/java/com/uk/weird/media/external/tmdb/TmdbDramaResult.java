package com.uk.weird.media.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbDramaResult(
        Long id,
        String name,
        String overview,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("first_air_date") String firstAirDate
) {}
