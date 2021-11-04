package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.users.UserRole;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleMapper implements DomainObjectMapper<UserRoleEntity, com.github.vihaan.isrentalapp.users.UserRole> {
    @Override
    public UserRoleEntity convertToEntity(com.github.vihaan.isrentalapp.users.UserRole userRole) {
        return new UserRoleEntity(userRole.getUserRole());
    }

    public Set<UserRoleEntity> convertCollectionToEntities(Collection<UserRole> userRoles) {
        return userRoles.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet());
    }

    public Set<UserRole> convertCollectionToDomainObjects(Collection<UserRoleEntity> userRolesEntities) {
        return userRolesEntities.stream()
                .map(this::convertToDomainObject)
                .collect(Collectors.toSet());
    }

    @Override
    public com.github.vihaan.isrentalapp.users.UserRole convertToDomainObject(UserRoleEntity userRole) {
        return com.github.vihaan.isrentalapp.users.UserRole.createFromString(userRole.getRole());
    }
}
