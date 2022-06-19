package com.senla.steshko.service;

import com.senla.steshko.api.VoteService;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.User;
import com.senla.steshko.entities.Vote;
import com.senla.steshko.exception.EntityNotFoundException;
import com.senla.steshko.repositories.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    @Override
    public Long save(Vote entity) {
        if(entity == null) {
            log.error("Entity of {} - NULL.", Vote.class);
            throw new NullPointerException("entity for saving is null");
        }
        return voteRepository.save(entity).getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        if(!voteRepository.existsById(id)) {
            log.error("Entity not found exception {}.", Vote.class);
            throw new EntityNotFoundException(Vote.class, "id", id.toString());
        }
         voteRepository.deleteById(id);
         return id;
    }

    @Transactional
    @Override
    public Long countByCandidateAndEvent(Long candidateId, Long eventId) {
        return  voteRepository.countByCandidateAndEvent(candidateId, eventId);
    }

    @Transactional
    @Override
    public Vote getById(Long id) {
        return voteRepository.findVoteById(id);
    }

    @Transactional
    @Override
    public Vote update(Vote newEntity, Long id) {
        if(newEntity == null) {
            log.error("Entity of {} - NULL.", Vote.class);
            throw new NullPointerException("Entity of "+ Vote.class+ " - NULL");
        }
        Vote entityFromDB = getById(id);
        if(entityFromDB == null) {
            log.error("Entity not found exception {}.",Vote.class);
            throw new EntityNotFoundException(Vote.class,"id", id.toString());
        }
        newEntity.setId(entityFromDB.getId());
        entityFromDB = newEntity;
        return voteRepository.save(entityFromDB);
    }
}
