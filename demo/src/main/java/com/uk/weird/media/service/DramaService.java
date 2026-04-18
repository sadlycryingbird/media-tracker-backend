package com.uk.weird.media.service;

import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.entity.Drama;
import com.uk.weird.media.entity.Media;
import com.uk.weird.media.entity.MediaType;
import com.uk.weird.media.mapper.DramaMapper;
import com.uk.weird.media.repository.DramaRepository;
import com.uk.weird.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaRepository dramaRepository;
    private final DramaMapper dramaMapper;
    private final MediaRepository mediaRepository;

    public DramaReadDTO createDrama(DramaWriteDTO request) {

        Media media = new Media();
        media.setRating(request.rating());
        media.setStatus(request.status());
        media.setProgress(request.progress());
        media.setMediaType(MediaType.DRAMA);

        Media savedMedia = mediaRepository.save(media);

        Drama drama = dramaMapper.toEntity(request);
        drama.setMedia(savedMedia);
        drama.setExternalId(request.externalId());

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