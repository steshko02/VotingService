package com.senla.steshko.api;

import com.senla.steshko.entities.Event;

import java.util.Date;
import java.util.List;

public interface EventService {
    Long save(Event entity);
    Long delete(Long id);
    Event getById(Long id);
    Event update(Event newEntity, Long id);
    boolean eventSingIn(Long eventId, String password);

    List<Event> getActualSortedEvents(Date start, String attribute);

    List<Event> getPaginationSortedEvents(int pageNum, int pageSize, String attribute);
}
