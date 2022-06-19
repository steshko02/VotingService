package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.dtoapi.VoteDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vote")
public class VoteController{

    @Autowired
    private VoteDtoService voteService;

    @GetMapping("/get")
    public VoteDto getById(@RequestParam("id") Long id) {
        return voteService.getById(id);
    }

    @GetMapping("/countByCandidateAndEvent")
    public Long getCountByCandidateAndEvent(@RequestParam("c_id") Long candidateId, @RequestParam("e_id") Long eventId) {
        return voteService.countByCandidateAndEvent(candidateId,eventId);
    }

    @PostMapping("/save")
    public Long save(@RequestBody VoteDto vote) {
        return voteService.save(vote);
    }

    @PutMapping("/update")
    public VoteDto update(@RequestBody VoteDto vote, @RequestParam Long id) {
        return voteService.update(vote, id);
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return voteService.delete(id);
    }
}
