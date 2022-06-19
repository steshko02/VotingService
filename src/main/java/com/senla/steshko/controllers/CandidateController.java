package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.dtoapi.CandidateDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidate")
public class CandidateController {

    @Autowired
    private CandidateDtoService candidateService;


    @GetMapping("/get")
    public CandidateDto getById(@RequestParam("id") Long id) {
        return candidateService.getById(id);
    }

    @PostMapping("/save")
    public Long save(@RequestBody CandidateDto candidate) {
        return candidateService.save(candidate);
    }

    @PutMapping("/update")
    public CandidateDto update(@RequestBody CandidateDto candidate, @RequestParam Long id) {
        return candidateService.update(candidate, id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return candidateService.delete(id);
    }
}
