package org.yourcompany.yourproject.dao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.yourcompany.yourproject.model.User;



@ActiveProfiles("test")
@DataJpaTest 
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        User user1 = new User();
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setAge(55);
        user1.setHeight("180");
        user1.setVersion(0);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jane");
        user2.setSurname("Doe");
        user2.setAge(45);
        user2.setHeight("170");
        user2.setVersion(0);
        userRepository.save(user2);

        User user3 = new User();
        user3.setName("Jim");
        user3.setSurname("Beam");
        user3.setAge(35);
        user3.setHeight("175");
        user3.setVersion(0);
        userRepository.save(user3);
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();

        assertEquals(3, users.size());
    }
}
