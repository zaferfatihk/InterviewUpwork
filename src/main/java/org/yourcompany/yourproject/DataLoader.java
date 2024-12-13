package org.yourcompany.yourproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yourcompany.yourproject.dao.UserRepository;

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

        // try (InputStream inputStream = getClass().getResourceAsStream("/users.json")) {
        //     List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
        //     for (User user : users) {
        //         userRepository.save(user);
        //     }
        //     log.info("Successfully loaded user data.");
        // } catch (IOException e) {
        //     log.error("Error loading data for users...", e);
        // }
    }
}