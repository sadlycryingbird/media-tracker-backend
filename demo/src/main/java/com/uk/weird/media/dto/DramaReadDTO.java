package com.uk.weird.media.dto;

import java.time.LocalDate;
import java.util.List;

public record DramaReadDTO(
        Long id,
        Long externalId,
        String title,
        String description,
        Integer releaseYear,
        String coverImageUrl,
        Integer rating,
        String status,
        Integer progress,
        Integer numberOfEpisodes,
        String country,
        String network,
        List<String> genres,
        List<String> cast,
        String airingStatus,
        LocalDate startDate,
        LocalDate endDate
) {}
