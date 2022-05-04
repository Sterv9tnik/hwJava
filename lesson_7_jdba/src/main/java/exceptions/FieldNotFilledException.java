package exceptions;

public class FieldNotFilledException  extends Exception {
    public FieldNotFilledException(String message) {
        super(message);
    }

    public FieldNotFilledException() {
        super("Field is not filled");
    }
}
