package com.senla.steshko.repository;

import com.senla.steshko.VotingServiceApplication;
import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Event;
import com.senla.steshko.entities.User;
import com.senla.steshko.entities.Vote;
import com.senla.steshko.repositories.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(locations = "classpath:application-test.properties")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {VotingServiceApplication.class, VoteRepository.class})
public class VoteDaoTest {

    @Autowired
    private VoteRepository voteRepository;

    private Vote vote;
    private Event event;
    private Candidate candidate;
    private User owner;

    private void setDate() {
        event= new Event();
        owner = new User();
        candidate = new Candidate();
        event.setId(1L);
        candidate.setId(1L);
        owner.setId(1L);

    }
    @BeforeEach
    private void setup() {
        vote =  new Vote();

        setDate();
//        vote.setId(1L);
        vote.setEvent(event);
        vote.setOwner(owner);
        vote.setCandidate(candidate);

    }

    @Test
    public void saveTest() {
        Vote result = voteRepository.save(vote);
        assertNotNull(result.getId());
    }

    @Test
    public void update() {

        Candidate newCandidate = new Candidate();
        newCandidate.setId(2L);
        newCandidate.setVotes(Set.of(vote));
        newCandidate.setDescription("dgndkfgkdnfgknkdfg");
        newCandidate.setUser(owner);
        newCandidate.setEvent(event);

        voteRepository.save(vote);
        vote.setCandidate(newCandidate);
        Vote newResult = voteRepository.save(vote);

        assertEquals(newResult.getCandidate(), newCandidate);
    }

    @Test
    public void readTest() {
        voteRepository.save(vote);
        Vote getVote = voteRepository.findVoteById(vote.getId());
        assertEquals(getVote, vote);
    }

    @Test
    public void deleteTest() {
        Vote newVote = voteRepository.save(vote);
        Long id = newVote.getId();
        voteRepository.deleteById(id);
        Vote result = voteRepository.findVoteById(id);
        assertNull(result);
    }

    @Test
    public void countByCandidateAndEventTest() {
        Vote newVote = voteRepository.save(vote);
        Long count = voteRepository.countByCandidateAndEvent(newVote.getCandidate().getId(),newVote.getEvent().getId());

        assertEquals(1L,count);
    }


}
