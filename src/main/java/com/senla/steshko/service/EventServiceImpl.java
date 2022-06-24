package com.senla.steshko.service;

import com.senla.steshko.api.EventService;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.exception.EntityNotFoundException;
import com.senla.steshko.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService  {

    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long save(Event entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return eventRepository.save(entity).getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        if(!eventRepository.existsById(id)) {
            log.error("Entity not found exception {}.",Event.class);
            throw new EntityNotFoundException(Event.class, "id", id.toString());
        }
         eventRepository.deleteById(id);
         return id;
    }

    @Transactional(readOnly = true)
    @Override
    public Event getById(Long id) {
        Event event =eventRepository.findEventById(id);
        if(event == null){
            log.error("Entity not found exception {}.",Event.class);
            throw new EntityNotFoundException(Event.class,"id", id.toString());
        }
        return event;
    }

    @Transactional
    @Override
    public Event update(Event newEntity, Long id) {
        Event entityFromDB = getById(id);
        if(entityFromDB == null) {
            log.error("Entity not found exception {}.",Event.class);
            throw new EntityNotFoundException(Event.class,"id", id.toString());
        }
        newEntity.setId(entityFromDB.getId());
        entityFromDB = newEntity;
        return eventRepository.save(entityFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean eventSingIn(Long eventId, String password) {
        Event event = getById(eventId);
        return passwordEncoder.matches(password, event.getPassword());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Event> getActualSortedEvents(Date start, String attribute) {
        return eventRepository.findByStartGreaterThanEqual(start, Sort.by(attribute));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Event> getPaginationSortedEvents(int pageNum,int pageSize,String attribute) {
        Pageable sortedByName = PageRequest.of(pageNum, pageSize, Sort.by(attribute));
        return eventRepository.findAll(sortedByName).get().collect(Collectors.toList());
    }
}
