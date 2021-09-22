package bookAPI;

import java.util.Date;

public class Book {
    String bookName;
    String author;
    Date dateOfrelease;
    int pages;
    String annotation;

    Book(String bookName, String author, Date dateOfrelease, int pages, String annotation) {
      this.bookName = bookName;
      this.author = author;
      this.dateOfrelease = dateOfrelease;
      this.pages = pages;
      this.annotation = annotation;
    };

    @Override
    public String toString() {
        return "\nBook:" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", dateOfrelease=" + dateOfrelease +
                ", pages=" + pages +
                ", annotation='" + annotation;
    }


}
