package com.senla.steshko.controllers;

import com.senla.steshko.api.UserService;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dtoapi.UserDtoService;
import com.senla.steshko.entities.User;
import com.senla.steshko.mappers.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "users")

public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserDtoService userService;

    @Autowired
    private Mapper<User, UserDto> modelMapper;

    @GetMapping("/get")
    public UserDto getById(@RequestParam("id") Long id) {
        return modelMapper.toDto(userService.getById(id));
    }

    @GetMapping("/getByRole")
    public List<UserDto> getByRole(@RequestParam("role") String role) {
        return userService.getByRole(role).stream().map(e->modelMapper.toDto(e)).collect(Collectors.toList());
    }

    @GetMapping("/check")
    public boolean check(@RequestParam("email")String  email) {
        return userService.ifExists(email);
    }

    @PostMapping("/registration")
    public Long registration(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto user, @RequestParam Long id) {
        return modelMapper.toDto(userService.update(modelMapper.toEntity(user), id));
    }

    @DeleteMapping("/delete")
    public Long delete(@RequestParam Long id) {
        return userService.delete(id);
    }
//@GetMapping("/get")
//public UserDto getById(@RequestParam("id") Long id) {
//    return userService.getById(id);
//}
//
//    @GetMapping("/getByRole")
//    public List<UserDto> getByRole(@RequestParam("role") String role) {
//        return userService.getByRole(role);
//    }
//
//    @GetMapping("/check")
//    public boolean check(@RequestParam("email")String  email) {
//        return userService.ifExists(email);
//    }
//
//    @PostMapping("/registration")
//    public Long registration(@RequestBody User user) {
//        return userService.save(user);
//    }
//
//    @PutMapping("/update")
//    public UserDto update(@RequestBody UserDto user, @RequestParam Long id) {
//        return userService.update(user, id);
//    }
//
//    @DeleteMapping("/delete")
//    public Long delete(@RequestParam Long id) {
//        return userService.delete(id);
//    }
}
