package api;

import entity.Booking;

public interface BookingAPIService {
    Booking createBooking(Booking booking);
    Booking updateBooking(String id, Booking booking);
    Booking getBooking(String id);
    void deleteBooking(String id);
}
