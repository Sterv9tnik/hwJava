package repository.impl;

import entity.Booking;

import repository.BookingRepository;

import java.util.Set;

public class BookingRepositoryImpl implements BookingRepository {
    private Set<Booking> bookings;

    public BookingRepositoryImpl(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    @Override
    public Booking save(Booking booking) {
        if (getBy(booking.getId()) != null){
            delete(getBy(booking.getId()));
            bookings.add(booking);
        }
        bookings.add(booking);
        return booking;
    }

    @Override
    public void delete(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public Booking getBy(String id) {
        for (Booking booking: bookings){
            if (booking.getId().equals(id))
            {
                return booking;
            }
        }
        return null;
    }

    @Override
    public Set<Booking> getAll() {
        return bookings;
    }
}
