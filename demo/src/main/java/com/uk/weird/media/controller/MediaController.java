package com.uk.weird.media.controller;

import com.uk.weird.media.dto.MediaReadDTO;
import com.uk.weird.media.dto.MediaWriteDTO;
import com.uk.weird.media.repository.MediaRepository;
import com.uk.weird.media.entity.Media;
import com.uk.weird.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaRepository mediaRepository;
    private final MediaService mediaService;

    @PostMapping
    public MediaReadDTO createSingleMedia(@RequestBody MediaWriteDTO request) {
        return mediaService.createMedia(request);
    }

    @GetMapping
    public List<MediaReadDTO> returnAllMedia() {
        return mediaService.getAllMedia();
    }

    @GetMapping("/{id}")
    public MediaReadDTO getSingleMediaById(@PathVariable Long id) {
        return mediaService.getMediaById(id);
    }

    @PutMapping("/{id}")
    public MediaReadDTO updateSingleMedia(
            @PathVariable Long id,
            @RequestBody MediaWriteDTO request) {
        return mediaService.updateMedia(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSingleMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
    }


}
