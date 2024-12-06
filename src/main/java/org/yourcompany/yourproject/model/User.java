package org.yourcompany.yourproject.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record User(long id, @NotEmpty String name, String surname, int age, @Positive Integer height) {
    public User {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than or equal to 0");
        }
        if (age > 100) {
            throw new IllegalArgumentException("Age must be less than or equal to 100");
        }
    }
}
