package LinkedGraphModule;

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

class ListGraphNodeTest {
    @Test
    void test() {
        testForCustoms();
        testsForPrimativesAndArray();
    }

    @Test
    void testsForPrimativesAndArray() {
        ArrayList arrayList = new ArrayList<>(asList("Tests", "for", "All"));
        ListGraphNode<Integer> testingInt = new ListGraphNode<>(7, 0);
        ListGraphNode<Double> testingDouble = new ListGraphNode<>(8.0, 0);
        ListGraphNode<String> testingString = new ListGraphNode<>("Data1", 0);
        ListGraphNode<ArrayList> testingArray = new ListGraphNode<>(arrayList, 0);

        //Initial
        Assertions.assertEquals(7, testingInt.getData(), "Test int getData");
        Assertions.assertEquals(8.0, testingDouble.getData(), "Test double getData");
        Assertions.assertEquals("Data1", testingString.getData(), "Test String getData");
        Assertions.assertEquals(asList("Tests", "for", "All"), testingArray.getData(), "Test Array getData");

       //Int
        {
            testingInt.addConnection(1);
            Assertions.assertTrue(testingInt.getConnectedVertex().contains(1));
            testingInt.addConnection(2);
            Assertions.assertTrue(testingInt.getConnectedVertex().contains(2));
            testingInt.removeConnection(1, false);
            Assertions.assertFalse(testingInt.getConnectedVertex().contains(1));
            Assertions.assertEquals(7, testingInt.getData(), "Get int data in listMatrix");
        }

        //Double
        {
            testingDouble.addConnection(1);
            Assertions.assertTrue(testingDouble.getConnectedVertex().contains(1));
            testingDouble.addConnection(2);
            Assertions.assertTrue(testingDouble.getConnectedVertex().contains(2));
            testingDouble.removeConnection(1, false);
            Assertions.assertFalse(testingDouble.getConnectedVertex().contains(1));
            Assertions.assertEquals(8.0, testingDouble.getData(), "Get int data in listMatrix");
        }
        //String
        {
            testingString.addConnection(1);
            Assertions.assertTrue(testingString.getConnectedVertex().contains(1));
            testingString.addConnection(2);
            Assertions.assertTrue(testingString.getConnectedVertex().contains(2));
            testingString.removeConnection(1, false);
            Assertions.assertFalse(testingString.getConnectedVertex().contains(1));
            Assertions.assertEquals("Data1", testingString.getData(), "Get int data in listMatrix");
        }
        //ArrayList
        {
            testingArray.addConnection(1);
            Assertions.assertTrue(testingArray.getConnectedVertex().contains(1));
            testingArray.addConnection(2);
            Assertions.assertTrue(testingArray.getConnectedVertex().contains(2));
            testingArray.removeConnection(1, false);
            Assertions.assertFalse(testingArray.getConnectedVertex().contains(1));
            Assertions.assertEquals(arrayList, testingArray.getData(), "Get int data in listMatrix");
        }
    }
    @Test
    void testForCustoms() {
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

        Book hg = new Book("The Hunger Games", "Suzanne Collins", new Date(2008, 8, 14), 223, "");
        Book hg1 = new Book("Catching Fire", "Suzanne Collins", new Date(2009, 8, 1), 251, "");
        ArrayList<Book> hgList = new ArrayList<>(asList(hg, hg1));
        BookSeries hungerGames = new BookSeries("hunger Games", hgList);

        ListGraphNode<Book> testBook = new ListGraphNode<>(book, 0);
        ListGraphNode<BookSeries> testBookSeria = new ListGraphNode<>(harryPoterseria, 0);
        ListGraphNode<Character> testCharacter = new ListGraphNode<>(remusLupin, 0);

        //Book
        Assertions.assertEquals(book, testBook.getData(), "Testing books getData");
        Assertions.assertTrue(testBook.getConnectedVertex().isEmpty());
        testBook.addConnection(3);
        Assertions.assertFalse(testBook.getConnectedVertex().isEmpty());


        //BookSeria
        Assertions.assertEquals(harryPoterseria, testBookSeria.getData(), "Testing books getData");
        Assertions.assertTrue(testBookSeria.getConnectedVertex().isEmpty());
        testBookSeria.addConnection(3);
        Assertions.assertFalse(testBookSeria.getConnectedVertex().isEmpty());

        //Character
        Assertions.assertEquals(remusLupin, testCharacter.getData(), "Testing books getData");
        Assertions.assertTrue(testCharacter.getConnectedVertex().isEmpty());
        testCharacter.addConnection(3);
        Assertions.assertFalse(testCharacter.getConnectedVertex().isEmpty());

    }
}