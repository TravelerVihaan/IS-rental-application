package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.users.UserRole;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper implements DomainObjectMapper<UserRoleEntity, UserRole> {
    @Override
    public UserRoleEntity convertToEntity(UserRole userRole) {
        return new UserRoleEntity(userRole.getUserRole());
    }

    @Override
    public UserRole convertToDomainObject(UserRoleEntity userRoleEntity) {
        return UserRole.createFromString(userRoleEntity.getRole());
    }
}
