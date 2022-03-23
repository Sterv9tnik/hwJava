package com.company;

import api.RoomAPIService;
import api.impl.BookingAPIServiceImpl;
import api.impl.RoomAPIServiceImpl;
import entity.Booking;
import entity.Guest;
import entity.Room;
import repository.BookingRepository;
import repository.RoomRepository;
import repository.impl.BookingRepositoryImpl;
import repository.impl.RoomRepositoryImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import entity.RoomType;
import service.BookingService;
import service.RoomService;
import service.impl.BookingServiceImpl;
import service.impl.RoomServiceImpl;

public class Main {

    public static void main(String[] args) {


        Set<Room> rooms = new HashSet<>();
        Set<Guest> guests = new HashSet<>();
        Set<Booking> bookings = new HashSet<>();

        Guest Sasha = new Guest("893214","alo@ya.ry", "Sasha","Ivanov","Sergeev");
        Guest Masha = new Guest("123145","MRV@lol.ru","Masha","Rogova","Vatalievna");

        Room room1 = new Room("1","1",1, RoomType.BASEMENT, "beep", 100, bookings);
        Room room2 = new Room("2","2",1, RoomType.LUXE, "beep", 100, bookings);
        Room room3 = new Room("2","2",1, RoomType.LUXE, "beep", 100, bookings);
        Booking booking1 = new Booking("1", LocalDate.now(),LocalDate.now().plusDays(1),Sasha, room1);
        Booking booking2 = new Booking("1", LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),Sasha, room3);

        rooms.add(room1);
        //rooms.add(room2);

        //bookings.add(booking1);
        //bookings.add(booking2);

        RoomRepository roomRepository = new RoomRepositoryImpl(rooms);
        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl(bookings);

        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository);
        BookingServiceImpl bookingService = new BookingServiceImpl(bookingRepository, roomService);

        RoomAPIService roomAPIService = new RoomAPIServiceImpl(roomService);
        BookingAPIServiceImpl bookingAPIService = new BookingAPIServiceImpl(bookingService);

        System.out.println(roomAPIService.getRoom("1"));
        //roomAPIService.getRoom("10");
        roomAPIService.createRoom(room2);
        System.out.println(roomAPIService.getRoom("2"));

        Room room4 = new Room("3","1",1, RoomType.LUXE, "boop", 100, bookings);
        roomAPIService.updateRoom("1",room4);
        System.out.println(roomAPIService.getRoom("1"));
        System.out.println(roomAPIService.getRoomsGroupedByType());
        roomAPIService.deleteRoom("3");
        System.out.println(roomAPIService.getRoomsGroupedByType());

        bookingAPIService.createBooking(booking1);
        //System.out.println(roomAPIService.getRoomsGroupedByType());
        System.out.println(bookingAPIService.getBooking("1"));
        System.out.println(roomAPIService.getRoomsGroupedByType());
        System.out.println(bookingAPIService.getBooking("1"));
        bookingAPIService.updateBooking("1", booking2);
        //System.out.println(roomAPIService.getRoomsGroupedByType());
        //bookingAPIService.deleteBooking("1");
        //System.out.println(roomAPIService.getRoomsGroupedByType());
    }
}
