package com.senla.steshko.repositories;

import com.senla.steshko.dto.views.UserView;
import com.senla.steshko.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(attributePaths = {"votes","roles"})
    User findUserById(Long id);

    @Query("select distinct u from User u inner join u.roles role where role.name = :role ORDER BY u.firstName ASC")
    @EntityGraph(attributePaths = {"votes","roles"})
    List<User> findUsersByRole(@Param("role") String role);

    boolean existsByEmail(String email);

    UserView findByEmail(String email);

    @EntityGraph(attributePaths = {"roles"})
    User findUserWithRoleByEmail (String email);
}
