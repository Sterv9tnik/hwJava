package service;

import entity.Booking;

public interface BookingService {
    Booking getBy(String id);
    Booking createBooking(Booking booking);
    Booking updateBooking(String id, Booking booking);
    void deleteBooking(Booking booking);
}
