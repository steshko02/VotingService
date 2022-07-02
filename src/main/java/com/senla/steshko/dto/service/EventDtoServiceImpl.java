package com.senla.steshko.dto.service;

import com.senla.steshko.api.EventService;
import com.senla.steshko.dto.entities.EventAuthDto;
import com.senla.steshko.dto.entities.EventDto;
import com.senla.steshko.dtoapi.EventDtoService;
import com.senla.steshko.entities.Event;
import com.senla.steshko.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class EventDtoServiceImpl implements EventDtoService {


    private final RestTemplate restTemplate;

    private final EventService eventService;

    private final Mapper<Event, EventDto> modelMapper;

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
    public boolean eventSingIn(EventAuthDto entity) {
        return eventService.eventSingIn(entity.getId(),entity.getPassword());
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

    @Override
    public EventDto getEventByRestTemplate() {
        ResponseEntity<EventDto> response = restTemplate.exchange(
                "http://sender-app:8100/event", HttpMethod.GET, null,
                new ParameterizedTypeReference<EventDto>(){});
        return  response.getBody();
    }
    @Override
    public String  sayHello() {
        ResponseEntity<String> response
                = restTemplate.getForEntity("http://sender-app:8100/sayHello", String.class);
        return  response.getBody();
    }
}
