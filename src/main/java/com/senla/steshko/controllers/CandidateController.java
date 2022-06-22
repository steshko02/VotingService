package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.dtoapi.CandidateDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidates")
public class CandidateController {

    @Autowired
    private CandidateDtoService candidateService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get")
    public CandidateDto getById(@RequestParam("id") Long id) {
        return candidateService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public Long save(@RequestBody CandidateDto candidate) {
        return candidateService.save(candidate);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public CandidateDto update(@RequestBody CandidateDto candidate, @RequestParam Long id) {
        return candidateService.update(candidate, id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return candidateService.delete(id);
    }
}
