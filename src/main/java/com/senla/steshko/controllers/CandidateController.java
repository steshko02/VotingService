package com.senla.steshko.controllers;

import com.senla.steshko.api.CandidateService;
import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private Mapper<Candidate, CandidateDto> modelMapper;

    @GetMapping("/get")
    public CandidateDto getById(@RequestParam("id") Long id) {
        return modelMapper.toDto(candidateService.getById(id));
    }

    @PostMapping("/save")
    public Long save(@RequestBody CandidateDto candidate) {
        return candidateService.save(modelMapper.toEntity(candidate));
    }

    @PutMapping("/update")
    public CandidateDto update(@RequestBody CandidateDto candidate, @RequestParam Long id) {
        return modelMapper.toDto(candidateService.update(modelMapper.toEntity(candidate), id));
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return candidateService.delete(id);
    }
}
