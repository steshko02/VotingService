package com.senla.steshko.controllers;

import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dto.views.UserView;
import com.senla.steshko.dtoapi.UserDtoService;
import com.senla.steshko.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import senla.steshko.aop.AutoTimeLogg;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
@Api(tags="Users")
public class UserController {

    private final UserDtoService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/byEmail")
    @ApiOperation(value = "This controller return user by email")
    public UserView getById(@RequestParam("email")String  email) {
        return userService.getByEmail(email);
    }

    @AutoTimeLogg
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @ApiOperation(value = "This controller return user by id")
    public UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/byRole")
    @ApiOperation(value = "This controller return users by role")
    public List<UserDto> getByRole(@RequestParam("role") String role) {
        return userService.getByRole(role);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/check")
    @ApiOperation(value = "This controller return true if user-email exist in DB or false if not exist")
    public boolean check(@RequestParam("email")String  email) {
        return userService.ifExists(email);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/")
    @ApiOperation(value = "This controller update user")
    public UserDto update(@RequestBody UserDto user){
        return userService.update(user, user.getId());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "This controller delete user by id")
    public Long delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
