package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.dtoapi.VoteDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/votes")
@RequiredArgsConstructor
public class VoteController{

    private final VoteDtoService voteService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public VoteDto getById(@PathVariable("id") Long id) {
        return voteService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/countByCandidateAndEvent")
    public Long getCountByCandidateAndEvent(@RequestParam("c_id") Long candidateId, @RequestParam("e_id") Long eventId) {
        return voteService.countByCandidateAndEvent(candidateId,eventId);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    public Long save(@RequestBody VoteDto vote) {
        return voteService.save(vote);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping
    public VoteDto update(@RequestBody VoteDto vote) {
        return voteService.update(vote, vote.getId());
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return voteService.delete(id);
    }
}
