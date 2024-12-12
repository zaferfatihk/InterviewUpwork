package org.yourcompany.yourproject.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.yourcompany.yourproject.dao.UserRepository;
import org.yourcompany.yourproject.exception.UserNotFoundException;
import org.yourcompany.yourproject.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    ResponseEntity<User> create(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable Long id) throws Exception{
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        if(userRepository.findById(id) == null) {
            throw new UserNotFoundException("User not found");
        } else {
            userRepository.deleteById(id);
        }
    }
}
