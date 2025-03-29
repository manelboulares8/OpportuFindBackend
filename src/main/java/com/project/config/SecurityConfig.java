package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.services.JwtUserDetailsService;
import com.project.filter.JwtTokenFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtUserDetailsService jwtUserDetailsService;

    public SecurityConfig(JwtTokenFilter jwtTokenFilter, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .requestMatchers("/api/auth/register/**").permitAll()  
            .requestMatchers("/api/offres/**").permitAll()
            .requestMatchers("/api/entrepreneurs/**").permitAll()
            .requestMatchers("/api/auth/login/**").permitAll() // This allows registration without authentication
            .requestMatchers("/api/**").authenticated()  // Only authenticated users can access other APIs
            .anyRequest().permitAll();
        return http.build();
    }
    @Bean
    public org.springframework.web.filter.CorsFilter corsFilter() {
        org.springframework.web.cors.CorsConfiguration corsConfig = new org.springframework.web.cors.CorsConfiguration();
        corsConfig.setAllowedOrigins(java.util.Collections.singletonList("http://localhost:4200"));
        corsConfig.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfig.setAllowedHeaders(java.util.Arrays.asList("Authorization", "Content-Type"));
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return new org.springframework.web.filter.CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
