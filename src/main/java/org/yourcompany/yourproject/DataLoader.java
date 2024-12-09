package org.yourcompany.yourproject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yourcompany.yourproject.dao.UserRepository;
import org.yourcompany.yourproject.model.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("reading data for users...");
        try (InputStream inputStream = getClass().getResourceAsStream("/users.json")) {
            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
            userRepository.saveAll(users);
            log.info("Successfully loaded user data.");
        } catch (IOException e) {
            log.error("Error loading data for users...", e);
        }
    }
}