package com.uk.weird.media.controller;

import com.uk.weird.media.dto.DramaReadDTO;
import com.uk.weird.media.dto.DramaSearchResultDTO;
import com.uk.weird.media.dto.DramaWriteDTO;
import com.uk.weird.media.repository.DramaRepository;
import com.uk.weird.media.service.DramaService;
import jakarta.validation.Valid;
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
    public DramaReadDTO createDrama(@Valid @RequestBody DramaWriteDTO dto) {
        return dramaService.createDrama(dto);
    }

    @GetMapping
    public List<DramaReadDTO> returnAllDrama() {
        return dramaService.getAllDrama();
    }

    @GetMapping("/{id}")
    public DramaReadDTO getSingleDramaById(@PathVariable Long id) {
        return dramaService.getDramaById(id);
    }

    @GetMapping("/search")
    public List<DramaSearchResultDTO> searchDrama(@RequestParam String query) {
        return dramaService.searchExternal(query);
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