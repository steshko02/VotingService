package com.senla.steshko.mappers;

import com.senla.steshko.api.EventService;
import com.senla.steshko.api.VoteService;
import com.senla.steshko.dto.entities.CandidateDto;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.Vote;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CandidateMapper implements Mapper<Candidate, CandidateDto> {

    private final ModelMapper mapper;

    private final VoteService voteService;

    private final EventService eventService;

    public Candidate toEntity(CandidateDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Candidate.class);
    }

    public CandidateDto toDto(Candidate entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CandidateDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.typeMap(Candidate.class, CandidateDto.class)
                .addMappings(mapper -> mapper.using(convertEvent)
                        .map(Candidate::getEvent, CandidateDto::setEvent))
                .addMappings(mapper -> mapper.using(convertVotes)
                        .map(Candidate::getVotes, CandidateDto::setVoteIds));

        mapper.typeMap(CandidateDto.class,Candidate.class)
                .addMappings(mapper -> mapper.using(convertDtoEvent)
                        .map(CandidateDto::getEvent, Candidate::setEvent))
                .addMappings(mapper -> mapper.using(voteToDto)
                        .map(CandidateDto::getVoteIds, Candidate::setVotes));
    }

    private Converter<Event, Long> convertEvent = new AbstractConverter<Event,Long>() {
        @Override
        protected Long convert(Event event) {
            return event.getId();
        }
    };

    private Converter<Long, Event> convertDtoEvent = new AbstractConverter<Long, Event>() {
        @Override
        protected Event convert(Long eventDto) {
            return eventService.getById(eventDto);
        }
    };

    private Converter<Set<Vote>, Set<Long>> convertVotes = new AbstractConverter<Set<Vote>, Set<Long>>() {
        @Override
        protected Set<Long> convert(Set<Vote> candidates) {
            return candidates.stream().map(e -> e.getId()).collect(Collectors.toSet());
        }
    };

    private Converter<Set<Long>, Set<Vote>> voteToDto = new AbstractConverter<Set<Long>, Set<Vote>>() {
        @Override
        protected Set<Vote> convert(Set<Long> voteDtos) {
            return voteDtos.stream().map(e -> {
                Vote vote = voteService.getById(e);
                return vote;
            }).collect(Collectors.toSet());
        }
    };

}
