package service.exceptions;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException() {
        super("Бронирование с таким id не найдено.");
    }

    public BookingNotFoundException(String message) {
        super(message);
    }
}
