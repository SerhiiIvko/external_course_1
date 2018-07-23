package task4.listenerBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookListChangeListener implements Listener {
    private Map<String, Long> bookMap;

    public BookListChangeListener(Map<String, Long> bookMap) {
        this.bookMap = bookMap;
    }

    @Override
    public void actionPerformed(Event event) {
        List<Book> addedBooks = event.getAddedBooks();
        List<Book> removedBooks = event.getRemovedBooks();
        Map<String, Long> addedMap = getBookNumberByAuthor(addedBooks);
        Map<String, Long> removedMap = getBookNumberByAuthor(removedBooks);

        BiConsumer<String, Long> incrementConsumer = (k,v) ->
        {

            Optional<Map.Entry<String, Long>> currentAuthorEntry = bookMap.entrySet()
                    .stream()
                    .filter(e -> e.getKey().equals(k))
                    .findFirst();
            if (currentAuthorEntry.isPresent()) {
                Map.Entry<String, Long> entry = currentAuthorEntry.get();
                entry.setValue(entry.getValue() + v);
            } else {
                bookMap.put(k, v);
            }
        };
        BiConsumer<String, Long> decrementConsumer = (k,v) ->
        {

            Optional<Map.Entry<String, Long>> currentAuthorEntry = bookMap.entrySet()
                    .stream()
                    .filter(e -> e.getKey().equals(k))
                    .findFirst();
            if (currentAuthorEntry.isPresent()) {
                Map.Entry<String, Long> entry = currentAuthorEntry.get();
                Long value = entry.getValue();
                if (value <= v) {
                    bookMap.remove(k);
                } else {
                    entry.setValue(value - v);
                }
            }
        };
        addedMap.forEach(incrementConsumer);
        removedMap.forEach(decrementConsumer);
    }

    private Map<String, Long> getBookNumberByAuthor(List<Book> books) {
        Map<String, Long> resultMap = new HashMap<>();
        if (books != null) {
            resultMap = books
                    .stream()
                    .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));
            return resultMap;
        }
        return resultMap;
    }
}