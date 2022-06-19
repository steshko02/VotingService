package com.senla.steshko.repositories;

import com.senla.steshko.entities.Candidate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @EntityGraph(attributePaths = {"user","event","votes"})
    Candidate findCandidateById(Long id);

}
