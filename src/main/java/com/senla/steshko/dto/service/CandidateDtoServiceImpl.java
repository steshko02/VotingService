package com.senla.steshko.dto.service;

import com.senla.steshko.api.CandidateService;
import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.dtoapi.CandidateDtoService;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateDtoServiceImpl implements CandidateDtoService {

    private final CandidateService candidateService;

    private final Mapper<Candidate, CandidateDto> modelMapper;

    @Override
    public Long save(CandidateDto entity) {
        return candidateService.save(modelMapper.toEntity(entity));
    }

    @Override
    public Long delete(Long id) {
        return candidateService.delete(id);
    }

    @Override
    public CandidateDto getById(Long id) {
        return modelMapper.toDto(candidateService.getById(id));
    }

    @Override
    public CandidateDto update(CandidateDto newEntity, Long id) {
        return modelMapper.toDto(candidateService.update(modelMapper.toEntity(newEntity), id));
    }
}
