package com.uk.weird.media.provider;

public record MediaSummary(
        String externalId,
        String title,
        String description,
        String posterUrl,
        Integer releaseYear
) {}
