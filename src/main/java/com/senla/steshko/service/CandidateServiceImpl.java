package com.senla.steshko.service;

import com.senla.steshko.api.CandidateService;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.exception.EntityNotFoundException;
import com.senla.steshko.repositories.CandidateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    @Transactional
    public Long save(Candidate entity) {
        if(entity == null) {
            log.error("Entity of {} - NULL.",Candidate.class);
            throw new NullPointerException("Entity of "+ Candidate.class+ " - NULL");
        }
        return candidateRepository.save(entity).getId();
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        if(!candidateRepository.existsById(id)) {
            log.error("Entity not found exception {}.",Candidate.class);
            throw new EntityNotFoundException(Candidate.class, "id", id.toString());
        }
        candidateRepository.deleteById(id);
        return id;
    }

    @Transactional
    @Override
    public Candidate getById(Long id) {
        Candidate candidate = candidateRepository.findCandidateById(id);
        if(candidate==null){
                log.error("Entity not found exception {}.",Candidate.class);
                throw new EntityNotFoundException(Candidate.class, "id", id.toString());
        }
        return candidate;
    }

    @Transactional
    @Override
    public Candidate update(Candidate newEntity, Long id) {
        if(newEntity == null) {
            log.error("Entity of {} - NULL.",Candidate.class);
            throw new NullPointerException("Entity of "+ Candidate.class+ " - NULL");
        }
        Candidate entityFromDB = getById(id);
        newEntity.setId(entityFromDB.getId());
        entityFromDB = newEntity;
        return candidateRepository.save(entityFromDB);
    }
}
