package controller;

import entities.Admin;
import entities.Book;
import entities.Client;

import java.util.List;

public interface LibraryAPIService {
    void ban(Admin admin, Client client);
    void unban(Admin admin, Client client);
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);

}
