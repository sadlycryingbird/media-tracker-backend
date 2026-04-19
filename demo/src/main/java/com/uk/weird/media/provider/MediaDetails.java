package com.uk.weird.media.provider;

import java.time.LocalDate;
import java.util.List;

public record MediaDetails(
        String title,
        String description,
        Integer releaseYear,
        String posterUrl,
        Integer numberOfEpisodes,
        String country,
        String network,
        String airingStatus,
        LocalDate startDate,
        LocalDate endDate,
        List<String> genres,
        List<String> cast
) {}
