package com.springboot.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Thendo
 * @date 2024/01/18
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService service, JwtAuthEntryPoint point, JwtAuthenticationFilter filter) {
        this.userDetailsService = service;
        this.jwtAuthEntryPoint = point;
        this.jwtAuthenticationFilter = filter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> {
            csrf.disable();
        }).authorizeHttpRequests((authorize) -> {
//            authorize.anyRequest().authenticated();
            authorize
//                    .requestMatchers(HttpMethod.GET, "/api/**")
//                    .permitAll()
                    .requestMatchers("/api/v1/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated();
        }).exceptionHandling(exception ->
                        exception.authenticationEntryPoint(jwtAuthEntryPoint))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    // In memory authentication
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails thendo = User.builder()
//                .username("thendo")
//                .password(passwordEncoder().encode("thendo"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(thendo, admin);
//    }
}
