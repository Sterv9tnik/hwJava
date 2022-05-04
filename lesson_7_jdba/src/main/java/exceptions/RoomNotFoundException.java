package exceptions;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException(String message) {
        super(message);
    }
    public RoomNotFoundException() {
        super("Room not found");
    }
}

