package com.uk.weird.media.controller;

import com.uk.weird.media.repository.MediaRepository;
import com.uk.weird.media.entity.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaRepository mediaRepository;

    @PostMapping
    public Media createSingleMedia(@RequestBody Media media) {
        return mediaRepository.save(media);
    }

    @GetMapping
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    @PutMapping
    public Media updateSingleMedia(@RequestBody Media media) {
        return mediaRepository.save(media);
    }

    @DeleteMapping
    public void deleteSingleMedia(@PathVariable Long id) {
        mediaRepository.deleteById(id);
    }

}
