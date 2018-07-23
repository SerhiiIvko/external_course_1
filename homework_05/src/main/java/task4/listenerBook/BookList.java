package task4.listenerBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class BookList extends ArrayList<Book> {
    private Listener listener;

    public BookList(int initialCapacity) {
        super(initialCapacity);
    }

    public BookList() {
    }

    public BookList(Collection<? extends Book> c) {
        super(c);
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public boolean add(Book book) {
        boolean result = super.add(book);
        listener.actionPerformed(new Event(Arrays.asList(book), null));
        return result;
    }

    @Override
    public Book remove(int index) {
        Book removedBook = super.remove(index);
        listener.actionPerformed(new Event(null, Arrays.asList(removedBook)));
        return removedBook;
    }

    @Override
    public void add(int index, Book book) {
        super.add(index, book);
        listener.actionPerformed(new Event(Arrays.asList(book), null));
    }

    @Override
    public boolean remove(Object book) {
        boolean result = super.remove(book);
        if (result) {
            listener.actionPerformed(new Event(null, Arrays.asList((Book) book)));
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends Book> c) {
        boolean result = super.addAll(c);
        if (result) {
            listener.actionPerformed(new Event(new ArrayList<>(c), null));
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Book> c) {
        boolean result = super.addAll(index, c);
        if (result) {
            listener.actionPerformed(new Event(new ArrayList<>(c), null));
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = super.removeAll(c);
        if (result) {
            listener.actionPerformed(new Event(null, new ArrayList<>((Collection<? extends Book>) c)));
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = super.retainAll(c);
        if (result) {
            listener.actionPerformed(new Event(this, new ArrayList<>((Collection<? extends Book>) c)));
        }
        return result;
    }
}