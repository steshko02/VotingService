package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dto.views.UserView;
import com.senla.steshko.dtoapi.UserDtoService;
import com.senla.steshko.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import senla.steshko.aop.AutoTimeLogg;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserDtoService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/byEmail")
    public UserView getById(@RequestParam("email")String  email) {
        return userService.getByEmail(email);
    }

    @AutoTimeLogg
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        User user = new User();
        user.setEmail("sdsdfsdf");
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/byRole")
    public List<UserDto> getByRole(@RequestParam("role") String role) {
        return userService.getByRole(role);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/check")
    public boolean check(@RequestParam("email")String  email) {
        return userService.ifExists(email);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/")
    public UserDto update(@RequestBody UserDto user){
        return userService.update(user, user.getId());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
