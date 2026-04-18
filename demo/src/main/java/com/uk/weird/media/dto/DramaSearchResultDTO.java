package com.uk.weird.media.dto;

public record DramaSearchResultDTO(
        Long externalId,
        String title,
        String description,
        String posterUrl,
        Integer releaseYear
) {}
