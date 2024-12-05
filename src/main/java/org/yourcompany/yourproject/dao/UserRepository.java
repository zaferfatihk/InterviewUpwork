package org.yourcompany.yourproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.model.User;

import com.google.common.collect.Lists;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository {

    List<User> users = Lists.newArrayList();
    
    public List<User> findAllUsers() {
        return users;
    }

    public Optional<User> findUserById(Long id) {
        return users.stream()
        .filter(user -> user.id() == id)
        .findFirst();
    }

    public void save(User user) {
        users.add(user);
    }

    public void update(User user, long id) {
        Optional<User> existingUser = findUserById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            users.remove(userToUpdate);
            users.add(new User(id, user.name(), user.surname(), user.age()));
        }
    }

    public void delete(Long id) {
        users.removeIf(user -> user.id() == id);
    }

    @PostConstruct
    private void init() {
        users.add(new User(1L, "John", "Doe", 25));
        users.add(new User(2L,"Mark", "Sow", 12));
    }
}
