package com.uk.weird.media.dto;

import com.uk.weird.media.entity.MediaType;

public record MediaWriteDTO(
        String title,
        MediaType mediaType,
        Integer releaseYear,
        String coverImageUrl,
        Integer rating,
        String status,
        Integer progress
) {}
