package com.senla.steshko.dtoapi;

import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.entities.Candidate;

public interface CandidateDtoService {
    Long save(CandidateDto entity);
    Long delete(Long id);
    CandidateDto getById(Long id);
    CandidateDto update(CandidateDto newEntity, Long id);
}
