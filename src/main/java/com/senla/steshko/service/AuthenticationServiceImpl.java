package com.senla.steshko.service;

import com.senla.steshko.api.AuthenticationService;
import com.senla.steshko.dto.entities.UserAuthDto;
import com.senla.steshko.dto.entities.UserDto;
import com.senla.steshko.dto.entities.UserDtoWithoutPass;
import com.senla.steshko.dtoapi.UserDtoService;
import com.senla.steshko.entities.Role;
import com.senla.steshko.entities.User;
import com.senla.steshko.exception.IncorrectPasswordException;
import com.senla.steshko.mappers.Mapper;
import com.senla.steshko.repositories.RoleRepository;
import com.senla.steshko.repositories.UserRepository;
import com.senla.steshko.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final  AuthenticationManager authenticationManager;
    private final JwtProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDtoService userDtoService;
    private final Mapper<User,UserDto> userDtoMapper;

    @Override
    @Transactional
    public Map<String, String> login(UserAuthDto dto) {
        String password = dto.getPassword();
        String email = dto.getEmail();
        User user = userRepository.findUserWithRoleByEmail(email);

        Role adminRole = user.getRoles()
                .stream()
                .filter(role -> role.getName().equals("ADMIN"))
                .findAny().orElse(null);

        if (adminRole != null && !encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException(dto.getPassword() + "is invalid.");
        }

        return authenticate(email, password, user.getRoles());
    }

    @Override
    public UserDtoWithoutPass register(UserDto dto) throws IncorrectPasswordException {
        dto.setPassword(encoder.encode(dto.getPassword()));
        userDtoService.save(userDtoMapper.toEntity(dto));
            return new UserDtoWithoutPass(dto.getFirstName(),dto.getLastName(),dto.getEmail());
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
