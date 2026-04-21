package com.uk.weird.media.dto;

import java.time.LocalDate;
import java.util.List;

public record ManhwaReadDTO(
        Long id,
        Long externalId,
        String title,
        String description,
        Integer releaseYear,
        String coverImageUrl,
        String author,
        String artist,
        Integer rating,
        String status,
        Integer progress,
        Integer numberOfChapters,
        List<String> genres,
        String airingStatus,
        LocalDate startDate,
        LocalDate endDate
){
}
