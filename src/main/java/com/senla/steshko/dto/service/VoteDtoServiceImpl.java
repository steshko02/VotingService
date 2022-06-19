package com.senla.steshko.dto.service;

import com.senla.steshko.api.VoteService;
import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.dtoapi.VoteDtoService;
import com.senla.steshko.entities.Vote;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteDtoServiceImpl implements VoteDtoService {

    @Autowired
    private VoteService voteService;

    @Autowired
    private Mapper<Vote,VoteDto> modelMapper;

    @Override
    public Long save(VoteDto entity) {
        return voteService.save(modelMapper.toEntity(entity));
    }

    @Override
    public Long delete(Long id) {
        return voteService.delete(id);
    }

    @Override
    public Long countByCandidateAndEvent(Long candidateId, Long eventId) {
        return voteService.countByCandidateAndEvent(candidateId,eventId);
    }

    @Override
    public VoteDto getById(Long id) {
        return modelMapper.toDto(voteService.getById(id));
    }

    @Override
    public VoteDto update(VoteDto newEntity, Long id) {
        return modelMapper.toDto(voteService.update(modelMapper.toEntity(newEntity), id));

    }
}
