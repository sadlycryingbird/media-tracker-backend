package com.uk.weird.media.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DramaWriteDTO (

        @NotNull
        Long externalId,

        @Min(0)
        @Max(10)
        Integer rating,

        @NotBlank
        String status,

        @Min(0)
        Integer progress
) {}