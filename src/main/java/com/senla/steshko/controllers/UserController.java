package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dto.views.UserView;
import com.senla.steshko.dtoapi.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {

    @Autowired
    private UserDtoService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getByEmail")
    public UserView getById(@RequestParam("email")String  email) {
        return userService.getByEmail(email);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/get")
    public UserDto getById(@RequestParam("id") Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getByRole")
    public List<UserDto> getByRole(@RequestParam("role") String role) {
        return userService.getByRole(role);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/check")
    public boolean check(@RequestParam("email")String  email) {
        return userService.ifExists(email);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto user, @RequestParam Long id) {
        return userService.update(user, id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return userService.delete(id);
    }
}
