package services.impl;

import entities.Book;
import entities.Client;
import services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    public void takeBooks(Client client, List<Book> books) {
        client.setBooks(books);
    }
    public void returnBooks(Client client, List<Book> books) {
        client.getBooks().removeAll(books);
    }
}

