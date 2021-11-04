package com.github.vihaan.isrentalapp.users.entities;

import com.github.vihaan.isrentalapp.users.User;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements DomainObjectMapper<UserEntity, User> {
    @Override
    public UserEntity convertToEntity(User user) {
        return null;
    }

    @Override
    public User convertToDomainObject(UserEntity userEntity) {
        return null;
    }
}
