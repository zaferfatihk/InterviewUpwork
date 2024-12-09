package org.yourcompany.yourproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record User(@Id Long id, @NotEmpty String name, String surname, int age, @Positive Integer height, @Version Long version) {
    public User {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than or equal to 0");
        }
        if (age > 100) {
            throw new IllegalArgumentException("Age must be less than or equal to 100");
        }
    }
}
