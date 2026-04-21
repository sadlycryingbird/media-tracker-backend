package com.uk.weird.media.controller;

import com.uk.weird.media.dto.ManhwaReadDTO;
import com.uk.weird.media.dto.ManhwaWriteDTO;
import com.uk.weird.media.repository.ManhwaRepository;
import com.uk.weird.media.service.ManhwaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manhwa")
@RequiredArgsConstructor
public class ManhwaController {

    private final ManhwaRepository manhwaRepository;
    private final ManhwaService manhwaService;

    @GetMapping
    public List<ManhwaReadDTO> getAllManhwa() {
        return manhwaService.getAllManhwa();
    }

    @GetMapping("/{id}")
    public ManhwaReadDTO getManhwaById(@PathVariable Long id) {
        return manhwaService.getManhwaById(id);
    }

    @PutMapping("/{id}")
    public ManhwaReadDTO updateManhwa(
            @PathVariable Long id,
            @RequestBody ManhwaWriteDTO request) {
        return manhwaService.updateManhwa(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteManhwa(@PathVariable Long id) {
        manhwaService.deleteManhwa(id);
    }

}
