package com.biblioteca.repository;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.Movie;
import com.biblioteca.model.User;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {

    private Library library;

    @Mock
    private List<Borrowable> bookList;

    @Mock
    private User user;

    @Mock
    private Visitor visitor;

    @Mock
    private Listener listener;

    @Before
    public void setUp() {
        List<Borrowable> bookList = loadBorrowables();

        Book book = new Book("1984", "George Orwell", 1950);
        book.checkout(user);
        bookList.add(book);
        book = new Book("Alchemist", "Paulo Coelho", 1988);
        book.checkout(user);
        bookList.add(book);
        bookList.forEach(borrowable -> borrowable.addListener(listener));
        this.library = new Library(bookList);
    }

    private List<Borrowable> loadBorrowables() {
        List<Borrowable> bookList = new ArrayList<>();
        bookList.add(new Book("Lord of the Rings", "JR Toliken", 1930));
        bookList.add(new Book("Harry Potter", "JK Rowling", 1992));
        bookList.add(new Book("Catch-22", "Joesph Heller", 1950));
        bookList.add(new Book("Winds of Winter", "George RR Martin", 2017));
        bookList.add(new Movie("The Matrix", "The Wachowskis", 1999, 10));
        return bookList;
    }

    @Test
    public void shouldReturnFalseIfBookIsNotAvailableDuringCheckout() {
        String bookName = "1234";

        assertFalse(library.checkout(bookName, user));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulCheckOut() {
        String name = "Lord of the Rings";

        assertTrue(library.checkout(name, user));
    }

    @Test
    public void shouldReturnFalseIfBookIsValidButCheckStateIsFalseDuringReturn() {
        String bookName = "Lord of the Rings";

        assertFalse(library.returnItem(bookName, user));
    }

    @Test
    public void shouldReturnFalseIfBookIsInValidDuringReturn() {
        String bookName = "1234";

        assertFalse(library.returnItem(bookName, user));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulReturn() {
        String name = "1984";

        assertTrue(library.returnItem(name, user));
    }

    @Test
    public void shouldRetrieveBorrowablesByVisitor() {
        library.getAvailableBorrowables(visitor);

        verify(visitor, times(4)).visit(any(Book.class));
    }

}