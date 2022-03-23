package api.impl;

import api.BookingAPIService;
import entity.Booking;
import service.exceptions.BookingNotFoundException;
import service.exceptions.RequiredFieldMissedException;
import service.exceptions.RoomNotFoundException;
import service.impl.BookingServiceImpl;

public class BookingAPIServiceImpl implements BookingAPIService {
    private final BookingServiceImpl bookingServiceImpl;

    public BookingAPIServiceImpl(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @Override
    public Booking createBooking(Booking booking) {
        try{
            bookingServiceImpl.createBooking(booking);
        }
        catch (RequiredFieldMissedException e){
            System.out.println(e);
        }
        catch (RoomNotFoundException e){
            System.out.println(e);
        }
        return booking;
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
        try{
            bookingServiceImpl.updateBooking(id, booking);
        }
        catch (BookingNotFoundException e){
            System.out.println(e);
        }
        catch (RoomNotFoundException e){
            System.out.println(e);
        }
        catch (RequiredFieldMissedException e){
            System.out.println(e);
        }
        return booking;
    }

    @Override
    public Booking getBooking(String id) {
        try{
            bookingServiceImpl.getBy(id);
        }
        catch (BookingNotFoundException e){
            System.out.println(e);
        }
        return bookingServiceImpl.getBy(id);
    }

    @Override
    public void deleteBooking(String id) {
        bookingServiceImpl.deleteBooking(getBooking(id));
    }
}
