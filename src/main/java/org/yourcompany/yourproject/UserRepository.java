package org.yourcompany.yourproject;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository {

    List<User> users = Lists.newArrayList();
    
    public List<User> findAllUsers() {
        return users;
    }

    public User findUserById(Long id) {
        return users.stream().filter(
            user -> user.id() == id
        ).findFirst().get();
    }

    @PostConstruct
    private void init() {
        users.add(new User(1L, "John", "Doe", 25));
        users.add(new User(2L,"Mark", "Sow", 12));
    }
}
