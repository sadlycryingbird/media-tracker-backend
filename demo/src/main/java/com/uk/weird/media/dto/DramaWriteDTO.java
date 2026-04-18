package com.uk.weird.media.dto;

public record DramaWriteDTO (
        String externalId,
        Integer rating,
        String status,
        Integer progress
) {}