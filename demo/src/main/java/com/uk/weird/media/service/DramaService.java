package com.uk.weird.media.service;

import com.uk.weird.media.dto.DramaSearchResultDTO;
import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.entity.Drama;
import com.uk.weird.media.entity.Media;
import com.uk.weird.media.entity.MediaType;
import com.uk.weird.media.mapper.DramaMapper;
import com.uk.weird.media.provider.MediaDetails;
import com.uk.weird.media.provider.MediaProvider;
import com.uk.weird.media.repository.DramaRepository;
import com.uk.weird.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaRepository dramaRepository;
    private final DramaMapper dramaMapper;
    private final MediaRepository mediaRepository;
    private final MediaProvider mediaProvider;

    public DramaReadDTO createDrama(DramaWriteDTO request) {

        MediaDetails details = mediaProvider.getByExternalId(String.valueOf(request.externalId()));

        Media media = buildMedia(details, request);
        Media savedMedia = mediaRepository.save(media);

        Drama drama = buildDrama(details, savedMedia, request);
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

    public List<DramaSearchResultDTO> searchExternal(String query) {

        return mediaProvider.search(query).stream()
                .map(s -> new DramaSearchResultDTO(
                        Long.valueOf(s.externalId()),
                        s.title(),
                        s.description(),
                        s.posterUrl(),
                        s.releaseYear()
                ))
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

        return dramaMapper.toReadDTO(existing);
    }

    public void deleteDrama(Long id) {
        dramaRepository.deleteById(id);
    }

    private Media buildMedia(MediaDetails details, DramaWriteDTO request) {
        Media media = new Media();
        media.setMediaType(MediaType.DRAMA);
        media.setTitle(details.title());
        media.setDescription(details.description());
        media.setReleaseYear(details.releaseYear());
        media.setCoverImageUrl(details.posterUrl());
        media.setRating(request.rating());
        media.setStatus(request.status());
        media.setProgress(request.progress());
        return media;
    }

    private Drama buildDrama(MediaDetails details, Media savedMedia, DramaWriteDTO request) {
        Drama drama = dramaMapper.toEntity(request);
        drama.setMedia(savedMedia);
        drama.setNumberOfEpisodes(details.numberOfEpisodes());
        drama.setCountry(details.country());
        drama.setNetwork(details.network());
        drama.setGenres(details.genres());
        drama.setCast(details.cast());
        drama.setAiringStatus(details.airingStatus());
        drama.setStartDate(details.startDate());
        drama.setEndDate(details.endDate());
        return drama;
    }

}