package com.senla.steshko.service;

import com.senla.steshko.api.EventService;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.exception.EntityNotFoundException;
import com.senla.steshko.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService  {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    @Override
    public Long save(Event entity) {
        //хуета, убрать
         if(entity == null) {
             log.error("Entity of {} - NULL.", Event.class);
             throw new NullPointerException("entity for saving is null");
         }
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

    //Transactional(readonly - true) или убрать
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

    @Override
    public boolean eventSingIn(Long eventId, String password) {
        return eventRepository.checkByPassword(eventId, password);
    }

    @Transactional
    @Override
    public List<Event> getActualSortedEvents(Date start, String attribute) {
        return eventRepository.findByStartGreaterThanEqual(start, Sort.by(attribute));
    }

    @Transactional
    @Override
    public List<Event> getPaginationSortedEvents(int pageNum,int pageSize,String attribute) {
        Pageable sortedByName = PageRequest.of(pageNum, pageSize, Sort.by(attribute));
        return eventRepository.findAll(sortedByName).get().collect(Collectors.toList());
    }
}
