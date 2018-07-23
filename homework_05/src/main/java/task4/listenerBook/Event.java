package task4.listenerBook;

import java.util.List;

public class Event {
    private List<Book> addedBooks;
    private List<Book> removedBooks;

    public Event(List<Book> addedBooks, List<Book> removedBooks) {
        this.addedBooks = addedBooks;
        this.removedBooks = removedBooks;
    }

    public List<Book> getAddedBooks() {
        return addedBooks;
    }

    public List<Book> getRemovedBooks() {
        return removedBooks;
    }
}