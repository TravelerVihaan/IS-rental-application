package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalMapper;
import com.github.vihaan.isrentalapp.users.User;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        UserEntity userEntity = new UserEntity(user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getSurname());
        userEntity.setComputerRentals(computerRentalMapper.convertCollectionToEntities(user.getComputerRentals()));
        userEntity.setRoles(userRoleMapper.convertCollectionToEntities(user.getRoles()));
        return userEntity;
    }

    @Override
    public User convertToDomainObject(UserEntity userEntity) {
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getSurname(),
                userRoleMapper.convertCollectionToDomainObjects(userEntity.getRoles()),
                computerRentalMapper.convertCollectionToDomainObjects(userEntity.getComputerRentals()));
    }
}
