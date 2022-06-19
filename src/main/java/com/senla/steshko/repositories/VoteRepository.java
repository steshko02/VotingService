package com.senla.steshko.repositories;

import com.senla.steshko.entities.Candidate;
import com.senla.steshko.entities.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @EntityGraph(attributePaths = {"owner","candidate","event"})
    Vote findVoteById(Long id);

    @Query(value="select count(*) from vote a where a.candidate_id = :c_id and a.event_id = :e_id", nativeQuery = true)
    Long countByCandidateAndEvent(@Param("c_id")Long candidateId, @Param("e_id")Long eventId);
}
