package com.uk.weird.media.external.tmdb;

import java.util.List;

public record TmdbCreditsResponse(
        List<TmdbCastDTO> cast
) {}
