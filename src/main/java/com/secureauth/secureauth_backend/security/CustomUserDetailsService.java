package com.secureauth.secureauth_backend.security;

import com.secureauth.secureauth_backend.model.User;
import com.secureauth.secureauth_backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }

        // for now, no roles. grant a single USER authority
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), // already hashed in DB
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
