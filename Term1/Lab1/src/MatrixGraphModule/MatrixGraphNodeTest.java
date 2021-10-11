package MatrixGraphModule;


import bookAPI.Book;
import bookAPI.BookSeries;
import bookAPI.Character;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static java.util.Arrays.*;


class MatrixGraphNodeTest {
    @org.junit.jupiter.api.Test
    void tests() {
        workingWithData();
        connectionTests();
        customTypesTests();
    }

    @org.junit.jupiter.api.Test
    void workingWithData() {
        ArrayList arrayList = new ArrayList<>(asList("Tests", "for", "All"));
        MatrixGraphNode<Integer> testingInt = new MatrixGraphNode<>(7, true);
        MatrixGraphNode<Double> testingDouble = new MatrixGraphNode<>(8.0, true);
        MatrixGraphNode<String> testingString = new MatrixGraphNode<>("Data1", true);
        MatrixGraphNode<ArrayList> testingArray = new MatrixGraphNode<>(arrayList, true);

        //Initial
        Assertions.assertEquals(7, testingInt.getData(), "Test int getData");
        Assertions.assertEquals(8.0, testingDouble.getData(), "Test double getData");
        Assertions.assertEquals("Data1", testingString.getData(), "Test String getData");
        Assertions.assertEquals(asList("Tests", "for", "All"), testingArray.getData(), "Test Array getData");

        //Arrays
        testingArray.setData(new ArrayList<>(Arrays.asList("Test", "for", "me")));
        Assertions.assertEquals(asList("Test", "for", "me"), testingArray.getData(), "Test Array getData");
        testingArray.setData(new ArrayList<java.io.Serializable>());
        Assertions.assertEquals(Collections.emptyList(), testingArray.getData(), "Test Array getData");
        testingArray.setData(new ArrayList<>(Arrays.asList(2, 3, 4)));
        Assertions.assertEquals(Arrays.asList(2, 3, 4), testingArray.getData(), "Test Array getData");

        //Integer
        testingInt.setData(-5);
        Assertions.assertEquals(-5, testingInt.getData(), "Test int getData");
        testingInt.setData(0);
        Assertions.assertEquals(0, testingInt.getData(), "Test int getData");
        testingInt.setData(25332);
        Assertions.assertEquals(25332, testingInt.getData(), "Test int getData");

        //Double
        testingDouble.setData(-8.2);
        Assertions.assertEquals(-8.2, testingDouble.getData(), "Test double getData");
        testingDouble.setData(0.0);
        Assertions.assertEquals(0, testingDouble.getData(), "Test double getData");
        testingDouble.setData(2543.6);
        Assertions.assertEquals(2543.6, testingDouble.getData(), "Test double getData");

        //String
        testingString.setData("");
        Assertions.assertEquals("", testingString.getData(), "Test double getData");
        testingString.setData("123");
        Assertions.assertEquals("123", testingString.getData(), "Test double getData");
        testingString.setData("Test this");
        Assertions.assertEquals("Test this", testingString.getData(), "Test double getData");
    }

    @org.junit.jupiter.api.Test
    void connectionTests() {
        ArrayList<String> arrayList = new ArrayList<>(asList("Tests", "for", "All"));
        MatrixGraphNode<Integer> testingIntConnected = new MatrixGraphNode<>(7, true);
        MatrixGraphNode<Integer> testingIntNotConnected = new MatrixGraphNode<>(7, false);
        MatrixGraphNode<Double> testingDoubleConnected = new MatrixGraphNode<>(8.0, true);
        MatrixGraphNode<Double> testingDoubleNotConnected = new MatrixGraphNode<>(8.0, false);
        MatrixGraphNode<String> testingStringConnected = new MatrixGraphNode<>("Data1", true);
        MatrixGraphNode<String> testingStringNotConnected= new MatrixGraphNode<>("Data1", false);
        MatrixGraphNode<ArrayList<String>> testingArrayConnected = new MatrixGraphNode<>(arrayList, true);

        //Initial
        Assertions.assertTrue(testingIntConnected.isConnected(), "Test testingIntConnected isConnected");
        Assertions.assertFalse(testingIntNotConnected.isConnected(), "Test testingIntNotConnected isConnected");
        Assertions.assertTrue(testingDoubleConnected.isConnected(), "Test testingDoubleConnected isConnected");
        Assertions.assertFalse(testingDoubleNotConnected.isConnected(), "Test testingDoubleNotConnected isConnected");
        Assertions.assertTrue(testingStringConnected.isConnected(), "Test testingStringConnected isConnected");
        Assertions.assertFalse(testingStringNotConnected.isConnected(), "Test testingStringNotConnected isConnected");
        Assertions.assertTrue(testingArrayConnected.isConnected(), "Test testingArrayConnected isConnected");

        //Arrays
        testingArrayConnected.setConnected(true);
        Assertions.assertTrue(testingArrayConnected.isConnected(), " Test testingArrayConnected isConnected");

        //Int
        testingIntConnected.setConnected(true);
        Assertions.assertTrue(testingIntConnected.isConnected(), "Int: Test testingIntConnected isConnected");
        testingIntConnected.setConnected(false);
        Assertions.assertFalse(testingIntConnected.isConnected(), "Int: Test testingIntConnected isConnected");

        testingIntNotConnected.setConnected(true);
        Assertions.assertTrue(testingIntNotConnected.isConnected(), "Int: Test testingIntNotConnected isConnected");
        testingIntNotConnected.setConnected(false);
        Assertions.assertFalse(testingIntNotConnected.isConnected(), "Int: Test testingIntNotConnected isConnected");

        //Double
        testingDoubleConnected.setConnected(true);
        Assertions.assertTrue(testingDoubleConnected.isConnected(), "Int: Test testingDoubleConnected isConnected");
        testingDoubleConnected.setConnected(false);
        Assertions.assertFalse(testingDoubleConnected.isConnected(), "Int: Test testingDoubleConnected isConnected");

        testingDoubleNotConnected.setConnected(true);
        Assertions.assertTrue(testingDoubleNotConnected.isConnected(), "Int: Test testingDoubleNotConnected isConnected");
        testingDoubleNotConnected.setConnected(false);
        Assertions.assertFalse(testingDoubleNotConnected.isConnected(), "Int: Test testingDoubleNotConnected isConnected");

        //String
        testingStringConnected.setConnected(true);
        Assertions.assertTrue(testingStringConnected.isConnected(), "Int: Test testingStringConnected isConnected");
        testingStringConnected.setConnected(false);
        Assertions.assertFalse(testingStringConnected.isConnected(), "Int: Test testingStringConnected isConnected");

        testingStringNotConnected.setConnected(true);
        Assertions.assertTrue(testingStringNotConnected.isConnected(), "Int: Test testingStringNotConnected isConnected");
        testingStringNotConnected.setConnected(false);
        Assertions.assertFalse(testingStringNotConnected.isConnected(), "Int: Test testingStringNotConnected isConnected");
    }

    @org.junit.jupiter.api.Test
    void customTypesTests() {
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

        MatrixGraphNode<Book> testBook = new MatrixGraphNode<>(book, true);
        MatrixGraphNode<BookSeries> testBookSeria = new MatrixGraphNode<>(harryPoterseria, true);
        MatrixGraphNode<Character> testCharacter = new MatrixGraphNode<>(remusLupin, true);

        //Book
        Assertions.assertEquals(book, testBook.getData(), "Testing books getData");
        testBook.setData(book1);
        Assertions.assertEquals(book1, testBook.getData(), "Testing books getData");

        Assertions.assertTrue(testBook.isConnected());
        testBook.setConnected(false);
        Assertions.assertFalse(testBook.isConnected());


        //BookSeria
        Assertions.assertEquals(harryPoterseria, testBookSeria.getData(), "Testing books getData");
        testBookSeria.setData(hungerGames);
        Assertions.assertEquals(hungerGames, testBookSeria.getData(), "Testing books getData");

        Assertions.assertTrue(testBookSeria.isConnected());
        testBookSeria.setConnected(false);
        Assertions.assertFalse(testBookSeria.isConnected());

        //Character
        Assertions.assertEquals(remusLupin, testCharacter.getData(), "Testing books getData");
        testCharacter.setData(harryPoter);
        Assertions.assertEquals(harryPoter, testCharacter.getData(), "Testing books getData");

        Assertions.assertTrue(testCharacter.isConnected());
        testCharacter.setConnected(false);
        Assertions.assertFalse(testCharacter.isConnected());
    }
}