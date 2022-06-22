package com.senla.steshko.service;

import com.senla.steshko.api.AuthenticationService;
import com.senla.steshko.dto.entities.UserAuthDto;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dtoapi.UserDtoService;
import com.senla.steshko.entities.Role;
import com.senla.steshko.entities.User;
import com.senla.steshko.exception.IncorrectPasswordException;
import com.senla.steshko.mappers.Mapper;
import com.senla.steshko.repositories.RoleRepository;
import com.senla.steshko.repositories.UserRepository;
import com.senla.steshko.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  JwtProvider jwtTokenProvider;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  PasswordEncoder encoder;
    @Autowired
    private UserDtoService userDtoService;
    @Autowired
    private Mapper<User,UserDto> userDtoMapper;

    @Override
    @Transactional
    public Map<String, String> login(UserAuthDto dto) throws IncorrectPasswordException {
        String password = dto.getPassword();
        String email = dto.getEmail();
        User user = userRepository.findUserWithRoleByEmail(email);
//
//        if (user == null) {
//            Set<Role> roles = new HashSet<>();
//            roles.add(roleRepository.findByName("USER"));
//            User newUser = new User();
//
//            newUser.setEmail(email);
//            newUser.setPassword(encoder.encode(password));
//            newUser.setRoles(roles);
//
//            userRepository.save( newUser);
//
//            return authenticate(email, password, roles);
//        }
        Role adminRole = user.getRoles()
                .stream()
                .filter(role -> role.getName() == "ADMIN")
                .findAny().orElse(null);

        if (adminRole != null && !encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException(dto.getPassword() + "is invalid.");
        }

        return authenticate(email, password, user.getRoles());
    }

    @Override
    public UserDto register(UserDto dto) throws IncorrectPasswordException {
        dto.setPassword(encoder.encode(dto.getPassword()));
        userDtoService.save(userDtoMapper.toEntity(dto));
            return dto;
    }


    private Map<String, String> authenticate(String login, String password, Set<Role> roles) {
        String token = jwtTokenProvider.createToken(login, roles);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        return new HashMap<String, String>() {{
            put("email", login);
            put("token", token);
        }};
    }
}
