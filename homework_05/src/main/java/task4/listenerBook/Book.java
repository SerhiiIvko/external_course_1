package task4.listenerBook;


// You have a class Book with fields (title, author, isbn).
// You have a List of books.
// You have Map<String, Integer> where key - author name and value - number of books that were written by this author
// in a list.
// You have to implement a mechanism when every change in the list immediately reflect the map.
// For example there was a book by "Author1" added to the list. In this case value by key "Author1" have to increase by 1.
public class Book {
    private String title;
    private String author;
    private int isbn;

    public Book(String title, String author, int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }
}