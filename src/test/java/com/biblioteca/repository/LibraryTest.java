package com.biblioteca.repository;

import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Owner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class LibraryTest {

    Library library;

    @Mock
    List<Borrowable> bookList;

    @Mock
    Owner owner;

    @Before
    public void setUp() {
        initMocks(this);
        library = new Library(bookList);
    }

    void setUpWithData() {
        List<Borrowable> bookList = new ArrayList<>();
        bookList.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        bookList.add(new Book("Harry Potter", "JK Rowling", 1992));
        bookList.add(new Book("Catch-22", "Joesph Heller", 1950));
        bookList.add(new Book("Winds of Winter", "George RR Martin", 2017));
        Book book = new Book("1984", "George Orwell", 1950);
        book.checkout(owner);
        bookList.add(book);
        book = new Book("Alchemist", "Paulo Coelho", 1988);
        book.checkout(owner);
        bookList.add(book);
        this.library = new Library(bookList);
    }

    @Test
    public void shouldReturnListOfAvailableBooksOfRightSize() {
        setUpWithData();
        int actualSize = library.allAvailableItems().size();

        assertEquals(4, actualSize);
    }

    @Test
    public void shouldReturnZeroSizedListIfNoAvailableBooks() {
        this.library = new Library(new ArrayList<>());

        int actualSize = library.allAvailableItems().size();

        assertEquals(0, actualSize);
    }

    @Test
    public void shouldReturnFalseIfBookIsNotAvailableDuringCheckout() {
        setUpWithData();
        String bookName = "1234";

        assertFalse(library.checkout(bookName, owner));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulCheckOut() {
        setUpWithData();
        String name = "Lord of the Rings";

        assertTrue(library.checkout(name, owner));
    }

    @Test
    public void shouldReturnFalseIfBookIsValidButCheckStateIsFalseDuringReturn() {
        setUpWithData();
        String bookName = "Lord of the Rings";

        assertFalse(library.returnItem(bookName, owner));
    }

    @Test
    public void shouldReturnFalseIfBookIsInValidDuringReturn() {
        setUpWithData();
        String bookName = "1234";

        assertFalse(library.returnItem(bookName, owner));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulReturn() {
        setUpWithData();
        String name = "1984";

        assertTrue(library.returnItem(name, owner));
    }

}