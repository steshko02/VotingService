package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.EventAuthDto;
import com.senla.steshko.dto.entities.EventDto;
import com.senla.steshko.dtoapi.EventDtoService;
import com.senla.steshko.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/events")
@RequiredArgsConstructor
public class EventController {

    private final EventDtoService eventService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public EventDto getById(@PathVariable("id") Long id) {
        return eventService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/singin")
    public boolean eventSingIn(@RequestBody EventAuthDto event) {
        return eventService.eventSingIn(event);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/pages/{page}")
    public List<EventDto> getById(@PathVariable Integer page,
                                  @RequestParam("size") Integer size, @RequestParam("attr") String sortAttribute) {
        return eventService.getPaginationSortedEvents(page,size,sortAttribute);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/actuals")
    public List<EventDto> getActual(  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("date") Date actualTime,
                                      @RequestParam("attr") String attribute){
        return eventService.getActualSortedEvents(actualTime,attribute);
    }

    //new dto with passw

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/")
    public Long save(@RequestBody Event event) {
        return eventService.save(event);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/")
    public EventDto update(@RequestBody EventDto event) {
        return eventService.update(event, event.getId());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return eventService.delete(id);
    }
}
