package onet.grupa.isrentalapplication.service.users;

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(){
        super("User not found in database");
    }
}
