package com.senla.steshko.dto.service;

import com.senla.steshko.api.EventService;
import com.senla.steshko.dto.entities.EventDto;
import com.senla.steshko.dtoapi.EventDtoService;
import com.senla.steshko.entities.Event;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventDtoServiceImpl implements EventDtoService {

    @Autowired
    private EventService eventService;

    @Autowired
    private Mapper<Event, EventDto> modelMapper;


    @Override
    public Long save(Event entity) {
        return eventService.save(entity);
    }

    @Override
    public Long delete(Long id) {
        return eventService.delete(id);
    }

    @Override
    public EventDto getById(Long id) {
        return modelMapper.toDto(eventService.getById(id));
    }

    @Override
    public EventDto update(EventDto newEntity, Long id) {
        return modelMapper.toDto(eventService.update(modelMapper.toEntity(newEntity), id));
    }

    @Override
    public boolean eventSingIn(Long eventId, String password) {
        return eventService.eventSingIn(eventId,password);
    }

    @Override
    public List<EventDto> getActualSortedEvents(Date start, String attribute) {
        return eventService.getActualSortedEvents(start,attribute).stream().
                map(e->modelMapper.toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getPaginationSortedEvents(int pageNum, int pageSize, String attribute) {
        return eventService.getPaginationSortedEvents(pageNum,pageSize,attribute).stream().
                map(e->modelMapper.toDto(e)).collect(Collectors.toList());
    }
}
