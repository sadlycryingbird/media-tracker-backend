package com.uk.weird.media.service;

import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.dto.ManhwaWriteDTO;
import com.uk.weird.media.dto.ManhwaReadDTO;
import com.uk.weird.media.entity.Drama;
import com.uk.weird.media.entity.Manhwa;
import com.uk.weird.media.entity.Media;
import com.uk.weird.media.entity.MediaType;
import com.uk.weird.media.mapper.ManhwaMapper;
import com.uk.weird.media.provider.MediaDetails;
import com.uk.weird.media.provider.MediaProvider;
import com.uk.weird.media.repository.ManhwaRepository;
import com.uk.weird.media.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManhwaService {

    private final ManhwaRepository manhwaRepository;
    private final ManhwaMapper manhwaMapper;
    private final MediaRepository mediaRepository;

    public List<ManhwaReadDTO> getAllManhwa() {

        return manhwaRepository.findAll()
                .stream()
                .map(manhwaMapper::toReadDTO)
                .toList();

    }

    public ManhwaReadDTO getManhwaById(Long id) {
        Manhwa manhwa = manhwaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("manhwa not found"));

        return manhwaMapper.toReadDTO(manhwa);
    }

    public ManhwaReadDTO updateManhwa(Long id, ManhwaWriteDTO dto) {
        Manhwa existingManhwa = manhwaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("manhwa not found"));

        Media media = existingManhwa.getMedia();
        media.setRating(dto.rating());
        media.setStatus(dto.status());
        media.setProgress(dto.progress());
        mediaRepository.save(media);

        return manhwaMapper.toReadDTO(existingManhwa);
    }

    public void deleteManhwa(Long id) {
        manhwaRepository.deleteById(id);
    }
}
