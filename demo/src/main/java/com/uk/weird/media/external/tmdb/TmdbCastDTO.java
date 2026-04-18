package com.uk.weird.media.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbCastDTO(

        @JsonProperty("name")
        String name,

        @JsonProperty("character")
        String character,

        @JsonProperty("profile_path")
        String profilePath
) {}