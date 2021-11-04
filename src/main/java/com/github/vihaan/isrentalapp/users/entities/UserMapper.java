package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalMapper;
import com.github.vihaan.isrentalapp.users.User;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper implements DomainObjectMapper<UserEntity, User> {

    private final UserRoleMapper userRoleMapper;
    private final ComputerRentalMapper computerRentalMapper;

    @Autowired
    public UserMapper(UserRoleMapper userRoleMapper, ComputerRentalMapper computerRentalMapper) {
        this.userRoleMapper = userRoleMapper;
        this.computerRentalMapper = computerRentalMapper;
    }

    @Override
    public UserEntity convertToEntity(User user) {
        return null;
    }

    @Override
    public User convertToDomainObject(UserEntity userEntity) {
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getRoles().stream().map(userRoleMapper::convertToDomainObject).collect(Collectors.toSet()),
                userEntity.getComputerRentals().stream().map(computerRentalMapper::convertToDomainObject).collect(Collectors.toList()));
    }
}
