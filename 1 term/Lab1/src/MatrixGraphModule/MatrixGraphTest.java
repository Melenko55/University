package MatrixGraphModule;

import bookAPI.Book;
import bookAPI.BookSeries;
import bookAPI.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class MatrixGraphTest {

    @Test
    void test(){
        testAlgoritmsAndWorking();
        testCustomTypes();
    }

    @Test
    void testAlgoritmsAndWorking() {
        {
            Integer[] arr = new Integer[] {6, 7, 8};
            MatrixGraph<Integer> matrixGraph = new MatrixGraph<Integer>(arr);
            matrixGraph.addGraphLine(0, 1);
            matrixGraph.addGraphLine(2, 1);
            matrixGraph.addGraphLine(2, 0);

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");
            matrixGraph.removeGraphLine(2,1);

            matrixGraph.showMatrixGraph();
            Assertions.assertEquals(1, matrixGraph.getDistance(2, 0), "Testing getDistance");
            Assertions.assertEquals(1, matrixGraph.getDistance(0, 2), "Testing getDistance");

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeGraphLine(0,1);

            Assertions.assertFalse(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeVertex(1);
            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            Assertions.assertEquals(1, matrixGraph.getDistance(0, 1), "Testing getDistance");

            Assertions.assertEquals(6, matrixGraph.getVertexData(0), "Checking Data");
            Assertions.assertEquals(8, matrixGraph.getVertexData(1), "Checking Data");

            matrixGraph.removeVertex(0);

            Assertions.assertEquals(8, matrixGraph.getVertexData(0), "Checking Data");
        }
        {
            String[] arr = new String[] {"Hello", "World", "!"};
            MatrixGraph<String> matrixGraph = new MatrixGraph<String>(arr);
            matrixGraph.addGraphLine(0, 1);
            matrixGraph.addGraphLine(2, 1);
            matrixGraph.addGraphLine(2, 0);

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");
            matrixGraph.removeGraphLine(2,1);

            Assertions.assertEquals(1, matrixGraph.getDistance(2, 0), "Testing getDistance");
            Assertions.assertEquals(1, matrixGraph.getDistance(0, 2), "Testing getDistance");

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeGraphLine(0,1);

            Assertions.assertFalse(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeVertex(1);
            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            Assertions.assertEquals(1, matrixGraph.getDistance(0, 1), "Testing getDistance");

            Assertions.assertEquals("Hello", matrixGraph.getVertexData(0), "Checking Data");
            Assertions.assertEquals("!", matrixGraph.getVertexData(1), "Checking Data");

            matrixGraph.removeVertex(0);

            Assertions.assertEquals("!", matrixGraph.getVertexData(0), "Checking Data");
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
            MatrixGraph<Book> books = new MatrixGraph<>(bookList.toArray(new Book[0]));
            books.addGraphLine(0, 1);
            books.addGraphLine(2, 1);
            books.addGraphLine(2, 3);
            books.showMatrixGraph();

            Assertions.assertEquals(3, books.getDistance(3, 0));
            Assertions.assertEquals(3, books.getDistance(0, 3));
            Assertions.assertTrue(books.checkConnectivity());

            books.removeGraphLine(2, 1);
            books.showMatrixGraph();
            Assertions.assertFalse(books.checkConnectivity());
            books.addGraphLine(3, 1);
            books.addGraphLine(3, 0);
            books.removeVertex(2);
            Assertions.assertTrue(books.checkConnectivity());
            Assertions.assertEquals(book, books.getVertexData(0), "Checking book getdata");
            Assertions.assertEquals(book3, books.getVertexData(2), "Checking book getdata");
        }
        {
            Character[] charactersList = new Character[]{remusLupin, harryPoter, dracoMalfoi};
            MatrixGraph<Character> characters = new MatrixGraph<>(charactersList);
            characters.addGraphLine(0, 1);
            characters.addGraphLine(2, 1);

            Assertions.assertEquals(2, characters.getDistance(2, 0));
            Assertions.assertEquals(2, characters.getDistance(0, 2));
            Assertions.assertTrue(characters.checkConnectivity());

            characters.removeGraphLine(2, 1);
            characters.showMatrixGraph();
            Assertions.assertFalse(characters.checkConnectivity());
            characters.addGraphLine(2, 1);
            characters.addGraphLine(2, 0);
            characters.removeVertex(2);
            Assertions.assertTrue(characters.checkConnectivity());
            Assertions.assertEquals(remusLupin, characters.getVertexData(0), "Checking book getdata");
            Assertions.assertEquals(harryPoter, characters.getVertexData(1), "Checking book getdata");
        }
        {
            BookSeries[] bookSeriesList = new BookSeries[]{harryPoterseria, hungerGames, sherlockHolms};
            MatrixGraph<BookSeries> series = new MatrixGraph<>(bookSeriesList);
            series.addGraphLine(0, 1);
            series.addGraphLine(2, 1);

            Assertions.assertEquals(2, series.getDistance(2, 0));
            Assertions.assertEquals(2, series.getDistance(0, 2));
            Assertions.assertTrue(series.checkConnectivity());

            series.removeGraphLine(2, 1);
            series.showMatrixGraph();
            Assertions.assertFalse(series.checkConnectivity());
            series.addGraphLine(2, 1);
            series.addGraphLine(2, 0);
            series.removeVertex(2);
            Assertions.assertTrue(series.checkConnectivity());
            Assertions.assertEquals(harryPoterseria, series.getVertexData(0), "Checking book getdata");
            Assertions.assertEquals(hungerGames, series.getVertexData(1), "Checking book getdata");
        }

    }
}