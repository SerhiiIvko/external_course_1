package task4.listenerBook;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class BookManagerTest {
    private BookManager bookManager;
    private BookList books;

    @Test
    public void whenListIsEmptyThenMapShouldBeEmpty() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);

        //WHEN:
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(0, mapOfBooks.size());
    }

    @Test
    public void whenOneBookIsAddedThenMapShouldHasOneEntry() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);

        //WHEN:
        String author = "Joahn Rowling";
        books.add(new Book("Harry Potter 1", author, 556648));
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(1, mapOfBooks.size());
        Assert.assertEquals(1L, mapOfBooks.get(author).longValue());
    }

    @Test
    public void whenTwoBooksOfOneAuthorAreAddedThenMapShouldHasOneEntry() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);

        //WHEN:
        String author = "Joahn Rowling";
        books.add(new Book("Harry Potter 1", author, 556648));
        books.add(new Book("Harry Potter 2", author, 556649));
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(1, mapOfBooks.size());
        Assert.assertEquals(2L, mapOfBooks.get(author).longValue());
    }

    @Test
    public void whenTwoBooksOfDifferentAuthorsAreAddedThenMapShouldHasTwoEntries() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);

        //WHEN:
        String author1 = "Joahn Rowling";
        String author2 = "Stephen King";
        books.add(new Book("Harry Potter 1", author1, 556648));
        books.add(new Book("The birds", author2, 556649));
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(2, mapOfBooks.size());
        Assert.assertEquals(1L, mapOfBooks.get(author1).longValue());
        Assert.assertEquals(1L, mapOfBooks.get(author2).longValue());
    }

    @Test
    public void whenOneBookIsRemovedThenMapShouldDecreaseByOneEntry() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);
        String author1 = "Joahn Rowling";
        String author2 = "Stephen King";
        Book bookForRemove = new Book("Harry Potter 1", author1, 556648);
        books.add(bookForRemove);
        books.add(new Book("The birds", author2, 556649));

        //WHEN:
        books.remove(bookForRemove);
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(1, mapOfBooks.size());
        Assert.assertNull(mapOfBooks.get(author1));
        Assert.assertEquals(1L, mapOfBooks.get(author2).longValue());
    }

    @Test
    public void whenOneBookOfAuthorIsRemovedThenMapShouldDecreaseAuthorBookNumber() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);
        String author = "Joahn Rowling";
        Book bookForRemove = new Book("Harry Potter 1", author, 556648);
        books.add(bookForRemove);
        books.add(new Book("Harry Potter 2", author, 556649));

        //WHEN:
        books.remove(bookForRemove);
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(1, mapOfBooks.size());
        Assert.assertEquals(1L, mapOfBooks.get(author).longValue());
    }

    @Test
    public void whenAllBooksAreRemovedThenMapShouldBeEmpty() {
        //GIVEN:
        books = new BookList();
        bookManager = new BookManager(books);
        String author1 = "Joahn Rowling";
        String author2 = "Stephen King";
        books.add(new Book("Harry Potter 1", author1, 556648));
        books.add(new Book("Harry Potter 2", author1, 556649));
        books.add(new Book("The birds", author2, 556650));
        ArrayList<Book> booksForRemove = new ArrayList<>(books);

        //WHEN:
        books.removeAll(booksForRemove);
        Map<String, Long> mapOfBooks = bookManager.getMapOfBooks();

        //THEN:
        Assert.assertEquals(0, mapOfBooks.size());
    }
}