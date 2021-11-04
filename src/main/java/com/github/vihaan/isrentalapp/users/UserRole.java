package com.github.vihaan.isrentalapp.users;

import com.github.vihaan.isrentalapp.devices.OperatingSystem;

import java.util.Arrays;

public enum UserRole {
    ADMINISTRATOR("Administrator"),
    MODERATOR("Moderator"),
    USER("User");

    private final String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public static UserRole createFromString(String userRoleName) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.userRole.equalsIgnoreCase((userRoleName)))
                .findFirst()
                .orElse(UserRole.USER);
    }
}
