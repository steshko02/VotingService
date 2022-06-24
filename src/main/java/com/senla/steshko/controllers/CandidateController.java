package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.dtoapi.CandidateDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateDtoService candidateService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public CandidateDto getById(@PathVariable("id") Long id) {
        return candidateService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public Long save(@RequestBody CandidateDto candidate) {
        return candidateService.save(candidate);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/")
    public CandidateDto update(@RequestBody CandidateDto candidate) {
        return candidateService.update(candidate, candidate.getId());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return candidateService.delete(id);
    }
}
