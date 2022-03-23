package service.impl;

import entity.Booking;
import repository.BookingRepository;
import service.BookingService;
import service.RoomService;
import service.exceptions.BookingNotFoundException;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomService roomService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
    }

    @Override
    public Booking getBy(String id) {
        if (bookingRepository.getBy(id) == null){
            throw new BookingNotFoundException();
        }
        return bookingRepository.getBy(id);
    }

    @Override
    public Booking createBooking(Booking booking) {
        if (booking.getCheckIn() == null && booking.getCheckOut() == null && booking.getGuest() == null && booking.getRoom() == null){
            throw new RequiredFieldMissedException();
        }
        else if (roomService.getBy(booking.getRoom().getId()) == null){
            throw new RoomNotFoundException();
        }
        roomService.getBy(booking.getRoom().getId()).getBookings().add(booking);
        bookingRepository.save(booking);
        System.out.println(booking.getRoom().getId());
        return booking;
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
        if (id == null){
            throw new RequiredFieldMissedException();
        }
        else if (bookingRepository.getBy(id) == null){
            throw new BookingNotFoundException();
        }
        else if (roomService.getBy(booking.getRoom().getId()) != null ){
            throw new RoomNotFoundException();
        }
        roomService.getBy(booking.getRoom().getId()).getBookings().remove(getBy(id));
        roomService.getBy(booking.getRoom().getId()).getBookings().add(booking);
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public void deleteBooking(Booking booking) {
        roomService.getBy(booking.getRoom().getId()).getBookings().remove(booking);
        bookingRepository.delete(booking);
    }
}
