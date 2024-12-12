package org.yourcompany.yourproject.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.yourcompany.yourproject.model.UserExternal;
import org.yourcompany.yourproject.model.UserExternal.Address.Geo;
import org.yourcompany.yourproject.model.UserExternal.Company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestClientTest(UserRestClient.class)
public class UserControllerRestClientTest {
    
    @Autowired
    MockRestServiceServer server;

    @Autowired
    UserRestClient client;

    @Autowired
    ObjectMapper objectMapper;

    private final List<UserExternal> users = new ArrayList<>();

    @Test
    void shouldFindAllUsers()throws JsonProcessingException{
        Company company = new Company("0", "0", "0");
        Geo geo = new Geo("1", "2");
        UserExternal.Address address = new UserExternal.Address("street a", "suite b", "istanbul", "34158", geo);
        UserExternal userExternal = new UserExternal(1, "Fatih", "Koyuncu", "some@email", address, "05325323232", "website", company);
        users.add(userExternal);

        this.server.expect(requestTo("http://jsonplaceholder.typicode.com/users"))
            .andRespond(withSuccess(objectMapper.writeValueAsString(users), MediaType.APPLICATION_JSON));

        List<UserExternal> allUsers = client.findAll();
        
        assertEquals(users, allUsers);
    }
 
    @Test
    void shouldFindUserById()throws JsonProcessingException{
        Company company = new Company("0", "0", "0");
        Geo geo = new Geo("1", "2");
        UserExternal.Address address = new UserExternal.Address("street a", "suite b", "istanbul", "34158", geo);
        UserExternal userExternal = new UserExternal(1, "Fatih", "Koyuncu", "some@email", address, "05325323232", "website", company);
        users.add(userExternal);

        this.server.expect(requestTo("http://jsonplaceholder.typicode.com/users/1"))
            .andRespond(withSuccess(objectMapper.writeValueAsString(userExternal), MediaType.APPLICATION_JSON));

        UserExternal user = client.findById(1);
        
        assertEquals(userExternal, user);
    }
}
