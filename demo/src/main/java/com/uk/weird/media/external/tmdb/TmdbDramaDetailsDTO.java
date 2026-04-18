package com.uk.weird.media.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TmdbDramaDetailsDTO(

        @JsonProperty("name")
        String name,

        @JsonProperty("overview")
        String overview,

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("first_air_date")
        String firstAirDate,

        @JsonProperty("last_air_date")
        String lastAirDate,

        @JsonProperty("number_of_episodes")
        Integer numberOfEpisodes,

        @JsonProperty("number_of_seasons")
        Integer numberOfSeasons,

        @JsonProperty("genres")
        List<TmdbGenreDTO> genres,

        @JsonProperty("origin_country")
        List<String> originCountry,

        @JsonProperty("networks")
        List<TmdbNetworkDTO> networks,

        @JsonProperty("status")
        String status
) {}
