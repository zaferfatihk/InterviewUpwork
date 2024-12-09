package org.yourcompany.yourproject.model;

import java.util.List;

public record Users(List<User> users) {
    public Users {
        if (users == null) {
            throw new IllegalArgumentException("Users must not be null");
        }
    }
}
