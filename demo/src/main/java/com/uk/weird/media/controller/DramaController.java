package com.uk.weird.media.controller;

import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.repository.DramaRepository;
import com.uk.weird.media.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drama")
@RequiredArgsConstructor
public class DramaController {

    private final DramaRepository dramaRepository;
    private final DramaService dramaService;

    @PostMapping
    public DramaReadDTO createSingleDrama(@RequestBody DramaWriteDTO request) {
        return dramaService.createDrama(request);
    }

    @GetMapping
    public List<DramaReadDTO> returnAllDrama() {
        return dramaService.getAllDrama();
    }

    @GetMapping("/{id}")
    public DramaReadDTO getSingleDramaById(@PathVariable Long id) {
        return dramaService.getDramaById(id);
    }

    @PutMapping("/{id}")
    public DramaReadDTO updateSingleDrama(
            @PathVariable Long id,
            @RequestBody DramaWriteDTO request) {
        return dramaService.updateDrama(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSingleDrama(@PathVariable Long id) {
        dramaService.deleteDrama(id);
    }
}