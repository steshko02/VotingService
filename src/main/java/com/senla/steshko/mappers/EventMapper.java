package com.senla.steshko.mappers;

import com.senla.steshko.api.CandidateService;
import com.senla.steshko.api.VoteService;
import com.senla.steshko.dto.entities.EventDto;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.Vote;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class EventMapper implements Mapper<Event, EventDto> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoteService voteService;

    public Event toEntity(EventDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Event.class);
    }

    public EventDto toDto(Event entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, EventDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.typeMap(Event.class, EventDto.class)
                .addMappings(mapper -> mapper.using(convertCandidates)
                        .map(Event::getCandidates, EventDto::setCandidateIds))
                .addMappings(mapper -> mapper.using(convertVotes)
                        .map(Event::getVotes, EventDto::setVoteIds));

        mapper.typeMap(EventDto.class,Event.class)
                .addMappings(mapper -> mapper.using(convertDto)
                        .map(EventDto::getCandidateIds, Event::setCandidates))
                .addMappings(mapper -> mapper.using(voteToDto)
                        .map(EventDto::getVoteIds, Event::setVotes));
    }

   private Converter<Set<Candidate>, Set<Long>> convertCandidates = new AbstractConverter<>() {
        @Override
        protected Set<Long> convert(Set<Candidate> candidates) {
            System.out.println("dsfs");
            return candidates.stream().map(e -> e.getId()).collect(Collectors.toSet());
        }
    };

    private Converter<Set<Vote>, Set<Long>> convertVotes = new AbstractConverter<>() {
        @Override
        protected Set<Long> convert(Set<Vote> candidates) {
            System.out.println("dsfs");
            return candidates.stream().map(e -> e.getId()).collect(Collectors.toSet());
        }
    };

    private Converter<Set<Long>, Set<Candidate>> convertDto = new AbstractConverter<>() {
        @Override
        protected Set<Candidate> convert(Set<Long> candidateDtos) {
            return candidateDtos.stream().map(e -> {
                Candidate candidate = candidateService.getById(e);
                return candidate;
            }).collect(Collectors.toSet());
        }
    };

   private Converter<Set<Long>, Set<Vote>> voteToDto = new AbstractConverter<>() {
        @Override
        protected Set<Vote> convert(Set<Long> voteDtos) {
            return voteDtos.stream().map(e -> {
                Vote vote = voteService.getById(e);
                return vote;
            }).collect(Collectors.toSet());
        }
    };

}
