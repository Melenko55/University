package bookAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookSeries {
    String nameOfSeria;
    ArrayList<Book> seriaBooks;

    BookSeries(String nameOfSeria) {
        this.nameOfSeria = nameOfSeria;
    }

    void sortBooks() {
        seriaBooks.sort(new SortByDate());
    };

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
