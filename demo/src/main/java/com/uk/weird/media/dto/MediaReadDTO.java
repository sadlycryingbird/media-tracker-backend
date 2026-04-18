package com.uk.weird.media.dto;

import com.uk.weird.media.entity.MediaType;

public record MediaReadDTO(
        Long id,
        MediaType mediaType,
        String title,
        String description,
        Integer releaseYear,
        Integer rating,
        String status,
        String coverImageUrl,
        Integer progress,
        String createdAt,
        String updatedAt
) {}
