package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class UserRoleMapper implements DomainObjectMapper<UserRoleEntity, com.github.vihaan.isrentalapp.users.UserRole> {
    @Override
    public UserRoleEntity convertToEntity(com.github.vihaan.isrentalapp.users.UserRole userRole) {
        return new UserRoleEntity(userRole.getUserRole());
    }

    @Override
    public com.github.vihaan.isrentalapp.users.UserRole convertToDomainObject(UserRoleEntity userRole) {
        return com.github.vihaan.isrentalapp.users.UserRole.createFromString(userRole.getRole());
    }
}
