package com.github.vihaan.isrentalapp.users;

import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private Set<UserRole> roles;
    private List<ComputerRental> computerRentals;

    public User() {}
    public User(String username,
                String password,
                String name,
                String surname,
                Set<UserRole> roles,
                List<ComputerRental> computerRentals) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
        this.computerRentals = computerRentals;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<com.github.vihaan.isrentalapp.users.UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public List<ComputerRental> getComputerRentals() {
        return computerRentals;
    }

    public void setComputerRentals(List<ComputerRental> computerRentals) {
        this.computerRentals = computerRentals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, surname);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roleEntities=" + roles +
                '}';
    }
}
