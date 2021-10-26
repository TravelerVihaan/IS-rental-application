package com.github.vihaan.isrentalapp.service.users;

import com.github.vihaan.isrentalapp.users.entities.RoleEntity;
import com.github.vihaan.isrentalapp.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userService
                .getUserByUsername(s).orElseThrow(UserNotFoundException::new);

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                convertAuthorities(userEntity.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<RoleEntity> userRoleEntities) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(RoleEntity roleEntity : userRoleEntities){
            authorities.add(new SimpleGrantedAuthority(roleEntity.getRole()));
        }
        return authorities;
    }
}
