package task4.listenerBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManager {
    private BookList books;
    private Map<String, Long> bookNumberByAuthor;

    public BookManager(List<Book> books) {
        if (!(books instanceof BookList)) {
            throw new IllegalArgumentException(String.format("Incompatible type %s for BookList",
                    books.getClass().getName()));
        }
        this.books = (BookList) books;
        bookNumberByAuthor = new HashMap<>();
        Listener bookListChangeListener = new BookListChangeListener(bookNumberByAuthor);
        this.books.setListener(bookListChangeListener);
    }

    public Map<String, Long> getMapOfBooks() {
        return bookNumberByAuthor;
    }
}