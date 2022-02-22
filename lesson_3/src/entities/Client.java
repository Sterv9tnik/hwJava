package entities;

import java.util.ArrayList;
import java.util.List;

public class Client extends AbstractUser{
    private Boolean banned;
    List<Book> books = new ArrayList<>();
    public Client(String firstName, String lastName, String middleName, String phone){
        super(firstName, lastName, middleName, phone);
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
