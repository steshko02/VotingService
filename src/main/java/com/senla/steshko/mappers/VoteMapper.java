package com.senla.steshko.mappers;

import com.senla.steshko.api.CandidateService;
import com.senla.steshko.api.EventService;
import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.Vote;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class VoteMapper implements Mapper<Vote,VoteDto>{

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private EventService eventService;

    @Override
    public Vote toEntity(VoteDto dto)    {
        return Objects.isNull(dto) ? null : mapper.map(dto, Vote.class);
    }

    @Override
    public VoteDto toDto(Vote entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, VoteDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.typeMap(Vote.class, VoteDto.class)
                .addMappings(mapper -> mapper.using(convertEvent)
                        .map(Vote::getEvent, VoteDto::setEvent))
                .addMappings(mapper -> mapper.using(convertCandidate)
                        .map(Vote::getCandidate, VoteDto::setCandidate));

        mapper.typeMap(VoteDto.class,Vote.class)
                .addMappings(mapper -> mapper.using(convertDtoEvent)
                        .map(VoteDto::getEvent, Vote::setEvent))
                .addMappings(mapper -> mapper.using(convertDtoCandidate)
                        .map(VoteDto::getCandidate, Vote::setCandidate));
    }

    private Converter<Event, Long> convertEvent = new AbstractConverter<>() {
        @Override
        protected Long convert(Event event) {
            return event.getId();
        }
    };

    private Converter<Long, Event> convertDtoEvent = new AbstractConverter<>() {
        @Override
        protected Event convert(Long eventDto) {
            return eventService.getById(eventDto);
        }
    };

    private Converter<Candidate, Long> convertCandidate = new AbstractConverter<>() {
        @Override
        protected Long convert(Candidate event) {
            return event.getId();
        }
    };

    private Converter<Long, Candidate> convertDtoCandidate = new AbstractConverter<>() {
        @Override
        protected Candidate convert(Long eventDto) {
            return candidateService.getById(eventDto);
        }
    };


//    private Converter<Candidate, CandidateDto> convertCandidate = new AbstractConverter<>() {
//        @Override
//        protected CandidateDto convert(Candidate candidate) {
//            return mapper.map(candidate, CandidateDto.class);
//        }
//    };
//
//    private Converter<CandidateDto, Candidate> convertDtoCandidate = new AbstractConverter<>() {
//        @Override
//        protected Candidate convert(CandidateDto candidateDto) {
//            return mapper.map(candidateDto, Candidate.class);
//        }
//    };
}
