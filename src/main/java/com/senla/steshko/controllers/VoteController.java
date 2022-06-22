package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.dtoapi.VoteDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vote")
public class VoteController{

    @Autowired
    private VoteDtoService voteService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public VoteDto getById(@RequestParam("id") Long id) {
        return voteService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/countByCandidateAndEvent")
    public Long getCountByCandidateAndEvent(@RequestParam("c_id") Long candidateId, @RequestParam("e_id") Long eventId) {
        return voteService.countByCandidateAndEvent(candidateId,eventId);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/save")
    public Long save(@RequestBody VoteDto vote) {
        return voteService.save(vote);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/update")
    public VoteDto update(@RequestBody VoteDto vote, @RequestParam Long id) {
        return voteService.update(vote, id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return voteService.delete(id);
    }
}
