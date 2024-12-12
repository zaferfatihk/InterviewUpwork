package org.yourcompany.yourproject.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.yourcompany.yourproject.dao.UserRepository;
import org.yourcompany.yourproject.exception.ApiError;
import org.yourcompany.yourproject.model.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    private final TestRestTemplate template = new TestRestTemplate();

    private String url;

    @BeforeEach
    public void setUp() {
        url = "http://localhost:" + port + "/api/users";

        User user1 = new User();
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setAge(55);
        user1.setHeight("180");
        user1.setVersion(0);
        userRepository.save(user1);
    }
    @Test
    @Order(1)
    public void testFindAll() {
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
   }
    
    @Test
    @Order(2)
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }
    
    @Test
    @Order(3)
    public void testFindByIdUserIsPresent() throws JsonMappingException, JsonProcessingException {
        ResponseEntity<String> response = template.getForEntity(url + "/1", String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        User foundUser = objectMapper.readValue(response.getBody(), User.class);
        assertTrue(foundUser.getId() == 1L);

        log.info("Response body:" + response.getBody());
    }

    @DisplayName("Test create user")
    @Test
    @Order(4)
    public void testCreateUser() throws JsonMappingException, JsonProcessingException {
        User user = new User();
        user.setName("Jane");
        user.setSurname("Doe");
        user.setAge(45);
        user.setHeight("170");
        user.setVersion(0);

        ResponseEntity<String> response = template.postForEntity(url, user, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        User createdUser = objectMapper.readValue(response.getBody(), User.class);
        
        log.info("Response body:" + createdUser.getHeight());
    }

    @DisplayName("Test update user")
    @Test
    @Order(5)
    public void testUpdateUser() {
        User user = new User();
        user.setName("Jane");
        user.setSurname("Doe");
        user.setAge(45);
        user.setHeight("170");
        user.setVersion(0);

        ResponseEntity<User> response = template.exchange(
                url + "/1",
                HttpMethod.PUT,
                new HttpEntity<>(user),
                User.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        User updatedUser = response.getBody();
        assertNotNull(updatedUser);;
        assertThat(updatedUser.getName()).isEqualTo("Jane");
    }

    @Test
    @Order(6)
    public void delete() throws JsonMappingException, JsonProcessingException {
        ResponseEntity<String> response = template.getForEntity(url + "/1", String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        log.info("Response body:" + response.getBody());

        response = template.exchange(url + "/1", HttpMethod.DELETE,null,String.class);

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    @Order(7)
    public void testFindByIdUserIsNotPresent() throws JsonMappingException, JsonProcessingException {
        userRepository.deleteAll();
        ResponseEntity<String> response = template.getForEntity(url + "/1", String.class);
        ApiError apiError = objectMapper.readValue(response.getBody(), ApiError.class);
        assertTrue(apiError.getStatus().equals(HttpStatus.NOT_FOUND));
        assertTrue(apiError.getMessage().equals("User not found"));
    }
}

