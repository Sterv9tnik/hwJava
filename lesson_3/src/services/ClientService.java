package services;

import entities.Book;
import entities.Client;
import java.util.List;

public interface ClientService {
    void takeBooks(Client client, List<Book> books);
    void returnBooks(Client client, List<Book> books);
}
