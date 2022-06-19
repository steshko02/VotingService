package com.senla.steshko.api;

import com.senla.steshko.entities.User;

import java.util.List;

public interface UserService {
    Long save(User entity);
    Long delete(Long id);
    User getById(Long id);
    User update(User newEntity, Long id);

    List<User> getByRole(String role);

    boolean ifExists(String email);
}
