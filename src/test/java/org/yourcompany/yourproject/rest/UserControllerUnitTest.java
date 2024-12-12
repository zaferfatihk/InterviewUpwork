package org.yourcompany.yourproject.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.yourcompany.yourproject.dao.UserRepository;
import org.yourcompany.yourproject.model.User;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerUnitTest.class);

    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    private final List<User> users = new ArrayList<>();
     
    @BeforeEach
    public void setUp() {
        User user1 = new User();
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setAge(55);
        user1.setHeight("180");
        user1.setVersion(0);

        User user2 = new User();
        user2.setName("Jane");
        user2.setSurname("Doe");
        user2.setAge(45);
        user2.setHeight("170");
        user2.setVersion(0);
        
        users.add(user1);
        users.add(user2);
    }
    
    @Test
    public void testFindAll() throws JsonProcessingException, Exception {
        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(users)));
    }

    @Test
    public void testDelete() throws Exception{
        when(userRepository.findById( 1L)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void testDeleteHappy() throws Exception{
        User user1 = new User();
        user1.setName("John");
        user1.setSurname("Doe");
        user1.setAge(55);
        user1.setHeight("180");
        user1.setVersion(0);
        Optional<User> optionalUser1 = Optional.of(user1);

        when(userRepository.findById( 1L)).thenReturn(optionalUser1);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/1"));
        
        verify(userRepository, times(1)).deleteById(1L);
    }
}

