package task4.listenerBook;

import java.util.List;

public class BookRunner {
    public static void main(String[] args) {
        List<Book> books = new BookList();
        BookManager bookManager = new BookManager(books);
        books.add(new Book("Harry Potter 1", "Joahn Rowling", 556648));
        books.add(new Book("Harry Potter 2", "Joahn Rowling", 447751));
        books.add(new Book("Harry Potter 3", "Joahn Rowling", 556897));
        books.add(new Book("Harry Potter 4", "Joahn Rowling", 443351));
        books.add(new Book("Harry Potter 5", "Joahn Rowling", 132256));
        System.out.println(bookManager.getMapOfBooks());
        books.add(new Book("The birds", "Stephen King", 5621121));
        books.add(new Book("Harry Potter 6", "Joahn Rowling", 132256));
        System.out.println(bookManager.getMapOfBooks());
    }
}