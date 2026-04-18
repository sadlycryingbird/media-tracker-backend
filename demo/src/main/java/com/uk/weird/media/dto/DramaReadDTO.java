package com.uk.weird.media.dto;

import java.time.LocalDate;
import java.util.List;

public record DramaReadDTO (
        Long id,
        MediaReadDTO media,
        Integer numberOfEpisodes,
        String country,
        String network,
        List<String> genres,
        List<String> cast,
        String airingStatus,
        LocalDate startDate,
        LocalDate endDate
) {}