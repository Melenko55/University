package bookAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookSeries {
    String nameOfSeria;
    ArrayList<Book> seriaBooks;

    ArrayList<Book> getSeriaBooks() {
        return this.seriaBooks;
    }

    public BookSeries(String nameOfSeria, ArrayList<Book> books) {
        this.nameOfSeria = nameOfSeria;
        this.seriaBooks = books;
    }

    void sortBooks() {
        seriaBooks.sort(new SortByDate());
    };

    void addBook(Book book) {
        this.seriaBooks.add(book);
    }

    @Override
    public String toString() {
        return "\nBookSeria " +
                 nameOfSeria + '\n' +
                "Books: " + seriaBooks +
                '\n';
    }
}

class SortByDate implements Comparator<Book> {
    public int compare(Book a, Book b) {
        return a.dateOfrelease.compareTo(b.dateOfrelease);
    }
}
