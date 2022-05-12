package connect.jpa.entity.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super("User not Found");}
}
