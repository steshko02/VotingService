package com.senla.steshko.controllers;

import com.senla.steshko.api.EventService;
import com.senla.steshko.dto.entities.EventDto;
import com.senla.steshko.entities.Event;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private Mapper<Event, EventDto> modelMapper;

    @GetMapping("/get")
    public EventDto getById(@RequestParam("id") Long id) {
        return modelMapper.toDto(eventService.getById(id));
    }

    @PostMapping("/singin")
    public boolean eventSingIn(@RequestParam("id") Long eventId,@RequestParam("password") String password) {
        return eventService.eventSingIn(eventId,password);
    }

    @GetMapping("/getPage/{page}")
    public List<EventDto> getById(@PathVariable Integer page,
                                  @RequestParam("size") Integer size, @RequestParam("attr") String sortAttribute) {
        return eventService.getPaginationSortedEvents(page,size,sortAttribute).stream().
                map(e->modelMapper.toDto(e)).collect(Collectors.toList());
    }

    @GetMapping("/getActual")
    public List<EventDto> getActual(  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("date") Date actualTime,
                                      @RequestParam("attr") String attribute){
        return eventService.getActualSortedEvents(actualTime,attribute).stream().
                map(e->modelMapper.toDto(e)).collect(Collectors.toList());
    }

    @PostMapping("/save")
    public Long save(@RequestBody Event event) {
        return eventService.save(event);
    }

    @PutMapping("/update")
    public EventDto update(@RequestBody EventDto event, @RequestParam Long id) {
        return modelMapper.toDto(eventService.update(modelMapper.toEntity(event), id));
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return eventService.delete(id);
    }
}
