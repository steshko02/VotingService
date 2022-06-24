package com.senla.steshko.dtoapi;

import com.senla.steshko.dto.entities.EventAuthDto;
import com.senla.steshko.dto.entities.EventDto;
import com.senla.steshko.entities.Event;

import java.util.Date;
import java.util.List;

public interface EventDtoService {
    Long save(Event entity);
    Long delete(Long id);
    EventDto getById(Long id);
    EventDto update(EventDto newEntity, Long id);
    boolean eventSingIn(EventAuthDto entity);

    List<EventDto> getActualSortedEvents(Date start, String attribute);

    List<EventDto> getPaginationSortedEvents(int pageNum, int pageSize, String attribute);
}
