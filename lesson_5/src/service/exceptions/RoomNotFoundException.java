package service.exceptions;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException() {
        super("Комната с таким id не найдена.");
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
