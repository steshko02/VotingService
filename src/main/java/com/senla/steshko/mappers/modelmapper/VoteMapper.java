package com.senla.steshko.mappers.modelmapper;

import com.senla.steshko.api.CandidateService;
import com.senla.steshko.api.EventService;
import com.senla.steshko.api.UserService;
import com.senla.steshko.dto.entities.VoteDto;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.User;
import com.senla.steshko.entities.Vote;
import com.senla.steshko.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class VoteMapper implements Mapper<Vote,VoteDto> {

    private final ModelMapper mapper;

    private final CandidateService candidateService;

    private final EventService eventService;

    private final UserService userService;

    public  Vote toEntity(VoteDto dto)    {
        return Objects.isNull(dto) ? null : mapper.map(dto, Vote.class);
    }

    public VoteDto toDto(Vote entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, VoteDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.typeMap(Vote.class, VoteDto.class)
                .addMappings(mapper -> mapper.using(convertEvent)
                        .map(Vote::getEvent, VoteDto::setEvent))
                .addMappings(mapper -> mapper.using(convertCandidate)
                        .map(Vote::getCandidate, VoteDto::setCandidate))
                 .addMappings(mapper -> mapper.using(convertUser)
                        .map(Vote::getOwner, VoteDto::setOwner));

        mapper.typeMap(VoteDto.class,Vote.class)
                .addMappings(mapper -> mapper.using(convertDtoEvent)
                        .map(VoteDto::getEvent, Vote::setEvent))
                .addMappings(mapper -> mapper.using(convertDtoCandidate)
                        .map(VoteDto::getCandidate, Vote::setCandidate))
                .addMappings(mapper -> mapper.using(convertDtoUser)
                        .map(VoteDto::getOwner, Vote::setOwner));
    }

    private Converter<Event, Long> convertEvent = new AbstractConverter<Event, Long>() {
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

    private Converter<User, Long> convertUser = new AbstractConverter<User, Long>() {
        @Override
        protected Long convert(User user) {
            return user.getId();
        }
    };

    private Converter<Long, User> convertDtoUser = new AbstractConverter<Long, User>() {
        @Override
        protected User convert(Long eventDto) {
            return userService.getById(eventDto);
        }
    };

    private Converter<Candidate, Long> convertCandidate = new AbstractConverter<Candidate, Long>() {
        @Override
        protected Long convert(Candidate event) {
            return event.getId();
        }
    };

    private Converter<Long, Candidate> convertDtoCandidate = new AbstractConverter<Long, Candidate>() {
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
