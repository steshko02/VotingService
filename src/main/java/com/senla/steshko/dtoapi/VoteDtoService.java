package com.senla.steshko.dtoapi;

import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.entities.Vote;

public interface VoteDtoService {
    Long save(VoteDto entity);
    Long delete(Long id);

    Long countByCandidateAndEvent(Long candidateId, Long eventId);

    VoteDto getById(Long id);
    VoteDto update(VoteDto newEntity, Long id);
}
