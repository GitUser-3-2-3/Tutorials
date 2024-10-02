package com.project.tacocloud.security;

import com.project.tacocloud.data.UserRepository;
import com.project.tacocloud.model.TacoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            TacoUser user = userRepo.findByUsername(username);
            if (user != null) {
                log.info("User '{}' has roles: {}", username, user.getAuthorities());
                return user;
            }

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors(Customizer.withDefaults())
            .authorizeHttpRequests(req ->
                req.requestMatchers("/", "/login/**", "/register/**")
                    .permitAll().anyRequest().hasRole("USER")
            );
        http.sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.formLogin(form ->
            form.loginPage("/login")
                .defaultSuccessUrl("/design", true)
        ).logout(logout ->
            logout.logoutUrl("/logout").logoutSuccessUrl("/"));

        return http.build();
    }
}










