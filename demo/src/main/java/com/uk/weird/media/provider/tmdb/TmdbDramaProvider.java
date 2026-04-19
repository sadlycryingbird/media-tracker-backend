package com.uk.weird.media.provider.tmdb;

import com.uk.weird.media.entity.MediaType;
import com.uk.weird.media.external.tmdb.TmdbDramaDetailsDTO;
import com.uk.weird.media.external.tmdb.TmdbSearchResponse;
import com.uk.weird.media.external.tmdb.TmdbCreditsResponse;
import com.uk.weird.media.external.tmdb.TmdbCastDTO;
import com.uk.weird.media.external.tmdb.TmdbGenreDTO;
import com.uk.weird.media.provider.MediaDetails;
import com.uk.weird.media.provider.MediaProvider;
import com.uk.weird.media.provider.MediaSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TmdbDramaProvider implements MediaProvider {

    private final WebClient tmdbClient;

    @Override
    public MediaType type() {
        return MediaType.DRAMA;
    }

    @Override
    public List<MediaSummary> search(String query) {
        TmdbSearchResponse response = tmdbClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("search", "tv")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(TmdbSearchResponse.class)
                .block();

        if (response == null || response.results() == null) return List.of();

        return response.results().stream()
                .map(r -> new MediaSummary(
                        String.valueOf(r.id()),
                        r.name(),
                        r.overview(),
                        r.posterPath() != null
                                ? "https://image.tmdb.org/t/p/w500" + r.posterPath()
                                : null,
                        extractYear(r.firstAirDate())
                ))
                .toList();
    }

    @Override
    public MediaDetails getByExternalId(String externalId) {
        TmdbDramaDetailsDTO meta = tmdbClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("tv", externalId)
                        .build())
                .retrieve()
                .bodyToMono(TmdbDramaDetailsDTO.class)
                .block();

        TmdbCreditsResponse credits = tmdbClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("tv", externalId, "credits")
                        .build())
                .retrieve()
                .bodyToMono(TmdbCreditsResponse.class)
                .block();

        List<String> genres = meta.genres() != null
                ? meta.genres().stream().map(TmdbGenreDTO::name).toList()
                : List.of();

        List<String> cast = (credits != null && credits.cast() != null)
                ? credits.cast().stream().map(TmdbCastDTO::name).toList()
                : List.of();

        return new MediaDetails(
                meta.name(),
                meta.overview(),
                extractYear(meta.firstAirDate()),
                meta.posterPath() != null
                        ? "https://image.tmdb.org/t/p/w500" + meta.posterPath()
                        : null,
                meta.numberOfEpisodes(),
                meta.originCountry() != null && !meta.originCountry().isEmpty()
                        ? meta.originCountry().get(0)
                        : null,
                meta.networks() != null && !meta.networks().isEmpty()
                        ? meta.networks().get(0).name()
                        : null,
                meta.status(),
                meta.firstAirDate() != null ? LocalDate.parse(meta.firstAirDate()) : null,
                meta.lastAirDate() != null ? LocalDate.parse(meta.lastAirDate()) : null,
                genres,
                cast
        );
    }

    private Integer extractYear(String date) {
        if (date == null || date.isBlank()) return null;
        return Integer.parseInt(date.substring(0, 4));
    }
}