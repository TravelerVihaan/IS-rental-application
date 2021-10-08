package com.github.vihaan.isrentalapp.service.users;

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(){
        super("User not found in database");
    }
}
