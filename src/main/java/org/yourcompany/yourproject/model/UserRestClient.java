package org.yourcompany.yourproject.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.yourcompany.yourproject.rest.UserExternal;

@Component
public class UserRestClient {
    
    private static final Logger logger = LoggerFactory.getLogger(UserRestClient.class);

    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
        .baseUrl("http://jsonplaceholder.typicode.com")
        .build();
    }

    public List<UserExternal> findAll() {
        return restClient.get()
        .uri("/users")
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
    }

    public UserExternal findById(int id) {
        return restClient.get()
        .uri("/users/{id}", id)
        .retrieve()
        .body(UserExternal.class);
    }

}
