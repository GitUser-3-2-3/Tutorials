package com.project.sec.config;

import com.project.sec.model.Users;
import com.project.sec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner loadUsers() {
        return args -> {
            Users userOne = new Users();
            userOne.setUsername("Parth");
            userOne.setPassword(passwordEncoder.encode("parth"));
            userRepository.save(userOne);

            Users userTwo = new Users();
            userTwo.setUsername("Juhi");
            userTwo.setPassword(passwordEncoder.encode("juhi"));
            userRepository.save(userTwo);
        };
    }
}
