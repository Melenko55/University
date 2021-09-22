package bookAPI;

import java.util.ArrayList;

public class Character {
    ArrayList<String> listOfPseudo;
    ArrayList<Book> books;
    ArrayList<String> participation;

    public void addName(String name) {
        this.listOfPseudo.add(name);
    }

    public void addBook(Book book, String participation) {
        this.books.add(book);
        this.participation.add(participation);
    }

    @Override
    public String toString() {
        return "\n---Character---" +
                "\nNames: " + listOfPseudo +
                "\nBooks: " + (books.size() == 0 ? "no info" : this.books) +
                "\nParticipation: " + (participation.size() == 0 ? "no info" : this.participation) +
                "\n";
    }
}
