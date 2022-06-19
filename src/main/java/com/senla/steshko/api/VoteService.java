package com.senla.steshko.api;

import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.Vote;

public interface VoteService {
    Long save(Vote entity);
    Long delete(Long id);

    Long countByCandidateAndEvent(Long candidateId, Long eventId);

    Vote getById(Long id);
    Vote update(Vote newEntity, Long id);
}
