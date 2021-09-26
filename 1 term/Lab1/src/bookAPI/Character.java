 package bookAPI;

import java.util.ArrayList;

public class Character {
    public class CharacterPartecipation {
        Book book;
        String participation;
        public CharacterPartecipation(Book book, String participation) {
            this.book = book;
            this.participation = participation;
        }

        @Override
        public String toString() {
            return "Book: " + this.book + "\nParticipation: "  + this.participation;
        }
    }
    ArrayList<String> listOfPseudo = new ArrayList<>();
    ArrayList<CharacterPartecipation> books = new ArrayList<>();


    public void addName(String name) {
        this.listOfPseudo.add(name);
    }

    public void addBook(Book book, String participation) {
        this.books.add(new CharacterPartecipation(book, participation));
    }

    @Override
    public String toString() {
        return "\n---Character---" +
                "\nNames: " + listOfPseudo +
                "\nBooks: " + (books.size() == 0 ? "no info" : this.books) +
                "\n";
    }
}
