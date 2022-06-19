package com.senla.steshko.api;

import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;

public interface CandidateService {
    Long save(Candidate entity);
    Long delete(Long id);
    Candidate getById(Long id);
    Candidate update(Candidate newEntity, Long id);
}
