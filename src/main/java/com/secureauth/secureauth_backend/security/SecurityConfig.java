package com.secureauth.secureauth_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // AuthenticationManager bean to allow manual authentication if needed (not strictly needed for filter)
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authBuilder.build();
    }

    // Factory bean for JwtAuthenticationFilter so we can inject JwtUtil and UserDetailsService
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(com.secureauth.secureauth_backend.util.JwtUtil jwtUtil,
                                                           CustomUserDetailsService uds) {
        return new JwtAuthenticationFilter(jwtUtil, uds);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // allow H2 console

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // disable form login & basic auth UI (we're using tokens)
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(form -> form.disable());

        return http.build();
    }
}
