package com.uk.weird.media.service;

import com.uk.weird.media.dto.DramaSearchResultDTO;
import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.entity.Drama;
import com.uk.weird.media.entity.Media;
import com.uk.weird.media.entity.MediaType;
import com.uk.weird.media.external.tmdb.*;
import com.uk.weird.media.mapper.DramaMapper;
import com.uk.weird.media.repository.DramaRepository;
import com.uk.weird.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaRepository dramaRepository;
    private final DramaMapper dramaMapper;
    private final MediaRepository mediaRepository;
    private final WebClient tmdbClient;

    public DramaReadDTO createDrama(DramaWriteDTO request) {

        // 1. Create Media with user fields
        Media media = new Media();
        media.setRating(request.rating());
        media.setStatus(request.status());
        media.setProgress(request.progress());
        media.setMediaType(MediaType.DRAMA);

        // 2. Fetch metadata
        TmdbDramaDetailsDTO meta = fetchMetadata(request.externalId());

        // 3. Map metadata → Media
        mapMetadataToMedia(meta, media);

        Media savedMedia = mediaRepository.save(media);

        // 4. Create Drama
        Drama drama = dramaMapper.toEntity(request);
        drama.setMedia(savedMedia);

        // 5. Map metadata → Drama
        mapMetadatatoEntity(meta, drama);
        List<String> cast = fetchCast(request.externalId());
        drama.setCast(cast);

        Drama savedDrama = dramaRepository.save(drama);

        return dramaMapper.toReadDTO(savedDrama);
    }

    public List<DramaReadDTO> getAllDrama() {
        return dramaRepository.findAll()
                .stream()
                .map(dramaMapper::toReadDTO)
                .toList();
    }

    public DramaReadDTO getDramaById(Long id) {
        Drama drama = dramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("drama not found"));

        return dramaMapper.toReadDTO(drama);
    }

    private Integer extractYear(String date) {
        if (date == null || date.isBlank()) return null;
        return Integer.parseInt(date.substring(0, 4));
    }

    public List<DramaSearchResultDTO> searchExternal(String query) {

        TmdbSearchResponse response = tmdbClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("search", "tv")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(TmdbSearchResponse.class)
                .block();

        return response.results().stream()
                .map(r -> new DramaSearchResultDTO(
                        r.id(),
                        r.name(),
                        r.overview(),
                        r.posterPath() != null
                                ? "https://image.tmdb.org/t/p/w500" + r.posterPath()
                                : null,
                        extractYear(r.firstAirDate())
                ))
                .toList();
    }

    public TmdbDramaDetailsDTO fetchMetadata(Long externalId) {
        return tmdbClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("tv", externalId.toString())
                        .build())
                .retrieve()
                .bodyToMono(TmdbDramaDetailsDTO.class)
                .block();
    }

    private void mapMetadatatoEntity(TmdbDramaDetailsDTO meta, Drama drama) {

        drama.setNumberOfEpisodes(meta.numberOfEpisodes());
        drama.setCountry(meta.originCountry() != null && !meta.originCountry().isEmpty()
                ? meta.originCountry().get(0)
                : null);

        drama.setNetwork(meta.networks() != null && !meta.networks().isEmpty()
                ? meta.networks().get(0).name()
                : null);

        drama.setAiringStatus(meta.status());

        drama.setStartDate(meta.firstAirDate() != null
                ? LocalDate.parse(meta.firstAirDate())
                : null);

        drama.setEndDate(meta.lastAirDate() != null
                ? LocalDate.parse(meta.lastAirDate())
                : null);

        if (meta.genres() != null) {
            drama.setGenres(
                    meta.genres().stream()
                            .map(TmdbGenreDTO::name)
                            .toList()
            );
        }
    }

    private void mapMetadataToMedia(TmdbDramaDetailsDTO meta, Media media) {

        media.setTitle(meta.name());
        media.setDescription(meta.overview());
        media.setReleaseYear(extractYear(meta.firstAirDate()));

        media.setCoverImageUrl(meta.posterPath() != null
                ? "https://image.tmdb.org/t/p/w500" + meta.posterPath()
                : null);
    }

    public List<String> fetchCast(Long externalId) {
        TmdbCreditsResponse response = tmdbClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("tv", externalId.toString(), "credits")
                        .build())
                .retrieve()
                .bodyToMono(TmdbCreditsResponse.class)
                .block();

        if (response == null || response.cast() == null) return List.of();

        return response.cast().stream()
                .map(TmdbCastDTO::name)
                .toList();
    }

    public DramaReadDTO updateDrama(Long id, DramaWriteDTO dto) {
        Drama existing = dramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("drama not found"));

        Media media = existing.getMedia();
        media.setRating(dto.rating());
        media.setStatus(dto.status());
        media.setProgress(dto.progress());
        mediaRepository.save(media);

        existing.setExternalId(dto.externalId());

        Drama saved = dramaRepository.save(existing);
        return dramaMapper.toReadDTO(saved);
    }

    public void deleteDrama(Long id) {
        dramaRepository.deleteById(id);
    }

}