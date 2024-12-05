package org.yourcompany.yourproject;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }   

    @GetMapping("")
    List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }   
 
    @GetMapping("/{id}")
    User findUserById(@PathVariable Long id) {
        return userRepository.findUserById(id);
    }

}
