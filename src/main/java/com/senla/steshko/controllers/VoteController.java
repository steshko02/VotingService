package com.senla.steshko.controllers;

import com.senla.steshko.api.VoteService;
import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.entities.Vote;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vote")
public class VoteController{

    @Autowired
    private VoteService voteService;

    @Autowired
    private Mapper<Vote,VoteDto> modelMapper;

    @GetMapping("/get")
    public VoteDto getById(@RequestParam("id") Long id) {
        return modelMapper.toDto(voteService.getById(id));
    }

    @GetMapping("/countByCandidateAndEvent")
    public Long getCountByCandidateAndEvent(@RequestParam("c_id") Long candidateId, @RequestParam("e_id") Long eventId) {
        return voteService.countByCandidateAndEvent(candidateId,eventId);
    }

    @PostMapping("/save")
    public Long save(@RequestBody VoteDto vote) {
        return voteService.save(modelMapper.toEntity(vote));
    }

    @PutMapping("/update")
    public VoteDto update(@RequestBody Vote vote, @RequestParam Long id) {
        return modelMapper.toDto(voteService.update(vote, id));
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return voteService.delete(id);
    }
}
