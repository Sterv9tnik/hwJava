package exceptions;

public class FieldNotFilledException  extends RuntimeException {
    public FieldNotFilledException(String message) {
        super(message);
    }

    public FieldNotFilledException() {
        super("Field is not filled");
    }
}
