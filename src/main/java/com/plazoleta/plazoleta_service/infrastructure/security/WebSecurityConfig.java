package com.plazoleta.plazoleta_service.infrastructure.security;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
                http
                        .csrf(csrf -> csrf.disable()) // Deshabilita CSRF si no lo necesitas
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**", "/v2/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                        )
                        .httpBasic(Customizer.withDefaults()) // Configura autenticación básica si es necesaria
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin estado para JWT
                        )
                        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
