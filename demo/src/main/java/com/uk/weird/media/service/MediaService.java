package com.uk.weird.media.service;

import com.uk.weird.media.dto.MediaWriteDTO;
import com.uk.weird.media.dto.MediaReadDTO;
import com.uk.weird.media.entity.Media;
import com.uk.weird.media.mapper.MediaMapper;
import com.uk.weird.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    public MediaReadDTO createMedia(MediaWriteDTO request) {
        Media media = mediaMapper.toEntity(request);
        Media saved = mediaRepository.save(media);
        return mediaMapper.toReadDTO(saved);
    }

    public List<MediaReadDTO> getAllMedia() {
        return mediaRepository.findAll()
                .stream()
                .map(mediaMapper::toReadDTO)
                .toList();
    }

    public MediaReadDTO getMediaById(Long id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        return mediaMapper.toReadDTO(media);
    }

    public MediaReadDTO updateMedia(Long id, MediaWriteDTO dto) {
        Media existing = mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        mediaMapper.updateEntity(existing, dto);
        Media saved = mediaRepository.save(existing);
        return mediaMapper.toReadDTO(saved);
    }

    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }

}