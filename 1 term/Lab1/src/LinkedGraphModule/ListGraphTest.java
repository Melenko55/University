package LinkedGraphModule;

import MatrixGraphModule.MatrixGraph;
import MatrixGraphModule.MatrixGraphNode;
import bookAPI.Book;
import bookAPI.BookSeries;
import bookAPI.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class ListGraphTest {
    @Test
    void tests() {
        testCustomTypes();
        testAlgoritmsAndWorking();
    }
    @Test
    void testAlgoritmsAndWorking() {
        {
            Integer[] arr = new Integer[] {6, 7, 8};
            ListGraph<Integer> linkedMatrix = new ListGraph<Integer>(arr);
            linkedMatrix.addLine(0, 1);
            linkedMatrix.addLine(2, 1);
            linkedMatrix.addLine(2, 0);

            Assertions.assertTrue(linkedMatrix.checkConnectivity(0), "Checking connectivity");
            linkedMatrix.removeLine(2,1);

            Assertions.assertEquals(1, linkedMatrix.getDistance(2, 0), "Testing getDistance");
            Assertions.assertEquals(1, linkedMatrix.getDistance(0, 2), "Testing getDistance");

            Assertions.assertTrue(linkedMatrix.checkConnectivity(0), "Checking connectivity");

            linkedMatrix.removeLine(0,1);

            Assertions.assertFalse(linkedMatrix.checkConnectivity(0), "Checking connectivity");

            linkedMatrix.removeVertex(1);
            Assertions.assertTrue(linkedMatrix.checkConnectivity(0), "Checking connectivity");

            Assertions.assertEquals(1, linkedMatrix.getDistance(0, 1), "Testing getDistance");

            Assertions.assertEquals(6, linkedMatrix.getVertexData(0), "Checking Data");
            Assertions.assertEquals(8, linkedMatrix.getVertexData(1), "Checking Data");

            linkedMatrix.removeVertex(0);

            Assertions.assertEquals(8, linkedMatrix.getVertexData(0), "Checking Data");
        }
        {
            String[] arr = new String[] {"Hello", "World", "!"};
            ListGraph<String> linkedMatrix = new ListGraph<String>(arr);
            linkedMatrix.addLine(0, 1);
            linkedMatrix.addLine(2, 1);
            linkedMatrix.addLine(2, 0);

            Assertions.assertTrue(linkedMatrix.checkConnectivity(0), "Checking connectivity");
            linkedMatrix.removeLine(2,1);

            Assertions.assertEquals(1, linkedMatrix.getDistance(2, 0), "Testing getDistance");
            Assertions.assertEquals(1, linkedMatrix.getDistance(0, 2), "Testing getDistance");

            Assertions.assertTrue(linkedMatrix.checkConnectivity(0), "Checking connectivity");

            linkedMatrix.removeLine(0,1);

            Assertions.assertFalse(linkedMatrix.checkConnectivity(0), "Checking connectivity");

            linkedMatrix.removeVertex(1);
            Assertions.assertTrue(linkedMatrix.checkConnectivity(0), "Checking connectivity");

            Assertions.assertEquals(1, linkedMatrix.getDistance(0, 1), "Testing getDistance");

            Assertions.assertEquals("Hello", linkedMatrix.getVertexData(0), "Checking Data");
            Assertions.assertEquals("!", linkedMatrix.getVertexData(1), "Checking Data");

            linkedMatrix.removeVertex(0);

            Assertions.assertEquals("!", linkedMatrix.getVertexData(0), "Checking Data");
        }
    }
    @Test
    void testCustomTypes() {
        Book book = new Book("Philosopher's Stone", "J.K. Rowling", new Date(1997, 5, 26), 223, "");
        Book book1 = new Book("Chamber of Secrets", "J.K. Rowling", new Date(1998, 6, 2), 251, "");
        Book book2 = new Book("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", new Date(1998, 6, 8), 317, "");
        Book book3 = new Book("Harry Potter and the Goblet of Fire", "J.K. Rowling", new Date(2000, 6, 8), 636, "");
        ArrayList<Book> bookList = new ArrayList<>(asList(book, book1, book2, book3));

        BookSeries harryPoterseria = new BookSeries("Harry Poter", bookList);

        Character remusLupin = new Character();
        remusLupin.addBook(book2, "Minor character");
        remusLupin.addName("Remus Lupin");

        Character harryPoter = new Character();
        harryPoter.addBook(book1, "Main character");
        harryPoter.addBook(book2, "Main character");
        harryPoter.addBook(book3, "Main character");
        harryPoter.addBook(book, "Main character");
        harryPoter.addName("Harry Potter");

        Character dracoMalfoi = new Character();
        dracoMalfoi.addName("Draco Lucius Malfoy");
        dracoMalfoi.addBook(book1, "Main character");
        dracoMalfoi.addBook(book2, "Main character");
        dracoMalfoi.addBook(book3, "Main character");
        dracoMalfoi.addBook(book, "Main character");

        Book hg = new Book("The Hunger Games", "Suzanne Collins", new Date(2008, 8, 14), 223, "");
        Book hg1 = new Book("Catching Fire", "Suzanne Collins", new Date(2009, 8, 1), 251, "");
        ArrayList<Book> hgList = new ArrayList<>(asList(hg, hg1));
        BookSeries hungerGames = new BookSeries("hunger Games", hgList);



        Book sh1 = new Book("A Study in Scarlet", "Arthur Conan Doyle", new Date(2008, 8, 14), 223, "");
        Book sh2 = new Book("The Sign of Four", "Arthur Conan Doyle", new Date(2008, 8, 14), 223, "");
        ArrayList<Book> shList = new ArrayList<>(asList(sh1, sh2));
        BookSeries sherlockHolms = new BookSeries("hunger Games", shList);

        {
            ListGraph<Book> books = new ListGraph<>(bookList.toArray(new Book[0]));
            books.addLine(0, 1);
            books.addLine(2, 1);
            books.addLine(2, 3);

            Assertions.assertEquals(3, books.getDistance(3, 0));
            Assertions.assertEquals(3, books.getDistance(0, 3));
            Assertions.assertTrue(books.checkConnectivity(0));

            books.removeLine(2, 1);

            Assertions.assertFalse(books.checkConnectivity(0));
            books.addLine(3, 1);
            books.addLine(3, 0);
            books.removeVertex(2);
            Assertions.assertTrue(books.checkConnectivity(0));
            Assertions.assertEquals(book, books.getVertexData(0), "Checking book getdata");
            Assertions.assertEquals(book3, books.getVertexData(2), "Checking book getdata");
        }
        {
            Character[] charactersList = new Character[]{remusLupin, harryPoter, dracoMalfoi};
            ListGraph<Character> characters = new ListGraph<>(charactersList);
            characters.addLine(0, 1);
            characters.addLine(2, 1);

            Assertions.assertEquals(2, characters.getDistance(2, 0));
            Assertions.assertEquals(2, characters.getDistance(0, 2));
            Assertions.assertTrue(characters.checkConnectivity(0));

            characters.removeLine(2, 1);
            Assertions.assertFalse(characters.checkConnectivity(0));
            characters.addLine(2, 1);
            characters.addLine(2, 0);
            characters.removeVertex(2);
            Assertions.assertTrue(characters.checkConnectivity(0));
            Assertions.assertEquals(remusLupin, characters.getVertexData(0), "Checking book getdata");
            Assertions.assertEquals(harryPoter, characters.getVertexData(1), "Checking book getdata");
        }
        {
            BookSeries[] bookSeriesList = new BookSeries[]{harryPoterseria, hungerGames, sherlockHolms};
            ListGraph<BookSeries> series = new ListGraph<>(bookSeriesList);
            series.addLine(0, 1);
            series.addLine(2, 1);

            Assertions.assertEquals(2, series.getDistance(2, 0));
            Assertions.assertEquals(2, series.getDistance(0, 2));
            Assertions.assertTrue(series.checkConnectivity(0));

            series.removeLine(2, 1);
            Assertions.assertFalse(series.checkConnectivity(0));
            series.addLine(2, 1);
            series.addLine(2, 0);
            series.removeVertex(2);
            Assertions.assertTrue(series.checkConnectivity(0));
            Assertions.assertEquals(harryPoterseria, series.getVertexData(0), "Checking book getdata");
            Assertions.assertEquals(hungerGames, series.getVertexData(1), "Checking book getdata");
        }

    }
}