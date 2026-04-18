package com.uk.weird.media.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbNetworkDTO(
        Long id,
        String name,

        @JsonProperty("origin_country")
        String originCountry
) {}
