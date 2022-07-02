package com.senla.steshko.service;

import com.senla.steshko.api.VoteService;
import com.senla.steshko.entities.*;
import com.senla.steshko.repositories.CandidateRepository;
import com.senla.steshko.repositories.EventRepository;
import com.senla.steshko.repositories.UserRepository;
import com.senla.steshko.repositories.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
//@WithUserDetails("user5@mail.ru")
@TestPropertySource(locations = "classpath:application-test.properties")
public class VoteServiceTest {
    @Autowired
    private VoteService voteService;
    @MockBean
    private VoteRepository voteRepository;
    @MockBean
    private CandidateRepository candidateRepository;
    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private UserRepository userRepository;

    private Vote vote;
    private Event event;
    private Candidate candidate;
    private User owner;


    private void setDate() {
        event= new Event();

        owner = new User();

        candidate = new Candidate();

        event.setId(0L);
        event.setPassword("passwordTest");
        event.setName("testEvent");
        event.setDescription("test test test");
        event.setStart(new Date());
        event.setFinish(new Date());


        candidate.setId(0L);
        candidate.setVotes(Set.of(vote));
        candidate.setDescription("testetstetes");

        owner.setId(0L);
        owner.setEmail("testEmail");
        owner.setRoles(Collections.singleton(new Role(2L,"USER")));
        owner.setFirstName("test");
        owner.setLastName("test");
        owner.setPassword("testPassword");

        candidate.setUser(owner);
        candidate.setEvent(event);
        event.setCandidates(Set.of(candidate));
        owner.setVotes(Set.of(vote));
        event.setVotes(Set.of(vote));

        doReturn(event).when(eventRepository).findEventById(any());
        doReturn(candidate).when(candidateRepository).findCandidateById(any());
        doReturn(owner).when(userRepository).findUserById(any());

    }



    @BeforeEach
    private void setup() {
        vote =  new Vote();

        setDate();
        vote.setId(1L);
        vote.setEvent(event);
        vote.setOwner(owner);
        vote.setCandidate(candidate);

        when(voteRepository.findVoteById(any())).thenReturn(vote);
    }

    @Test
    public void readTest()  {
        voteService.getById(1L);
        verify(voteRepository, times(1)).findVoteById(1L);
    }

    @Test
    public void updateTest() {

        Vote vote = new Vote();
        vote.setId(1L);
        voteService.update(vote,1L);
        verify(voteRepository, times(1)).findVoteById(any());
        verify(voteRepository, times(1)).save(any());
    }

    @Test
    public void deleteTest() {
        doReturn(true).when(voteRepository).existsById(1L);
        voteService.delete(1L);
        verify(voteRepository, times(1)).deleteById(1L);
    }
    @Test
    public void countByCandidateAndEventTest() {
        doReturn(3L).when(voteRepository).countByCandidateAndEvent(1L,1L);

       Long expected = voteService.countByCandidateAndEvent(1L,1L);
       assertEquals(expected,3L);
        verify(voteRepository, times(1)).countByCandidateAndEvent(1L,1L);
    }
}

