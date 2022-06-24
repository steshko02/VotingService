package com.senla.steshko.security.service;

import com.senla.steshko.entities.User;
import com.senla.steshko.repositories.UserRepository;
import com.senla.steshko.security.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static final String READ_EXCEPTION = "User not found: email = ";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserWithRoleByEmail(email);
        if(user==null) {
            throw new UsernameNotFoundException((READ_EXCEPTION + email));
        }
        return new CustomUserDetails(user);

    }
}