package com.senla.steshko.repositories;

import com.senla.steshko.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {

    @EntityGraph(attributePaths = {"candidates","votes"})
    Event findEventById(Long id);

    @Query(value="select case when(a.password=:password) then true else false end " +
            "from event a where a.id = :e_id", nativeQuery = true)
    Boolean checkByPassword(@Param("e_id")Long eventId, @Param("password")String password);


    @EntityGraph(attributePaths = {"candidates","votes"})
    Page<Event> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"candidates","votes"})
    List<Event> findByStartGreaterThanEqual(Date start, Sort sort);
}
