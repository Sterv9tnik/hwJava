package service.exceptions;

public class RequiredFieldMissedException extends RuntimeException{
    public RequiredFieldMissedException() {
        super("Поля не заполнены.");
    }

    public RequiredFieldMissedException(String message) {
        super(message);
    }
}
