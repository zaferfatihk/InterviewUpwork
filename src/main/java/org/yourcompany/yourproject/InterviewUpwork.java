/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author zfk
 */
@SpringBootApplication
public class InterviewUpwork {


    private static final Logger log = LoggerFactory.getLogger(InterviewUpwork.class);


    public static void main(String[] args) {
        SpringApplication.run(InterviewUpwork.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            User user = new User("Zafer", "Koyuncu", 41);
            log.info("User: " + user);
        };
    }
}
