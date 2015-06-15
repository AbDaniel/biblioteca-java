package com.biblioteca.repository;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.AvailableBook;
import com.biblioteca.model.Book;
import com.biblioteca.model.Borrowable;
import com.biblioteca.model.User;
import com.biblioteca.search.AvailableBookSearcher;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.constants.Constants.ITEM_NOT_PRESENT;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class LibraryTest {

    Library library;

    @Mock
    List<Borrowable> bookList;

    @Mock
    User user;

    @Mock
    Listener listener;

    @Mock
    Visitor visitor;

    @Before
    public void setUp() {
        initMocks(this);
        library = new Library(bookList);
        library.addListener(listener);
    }

    void setUpWithData() {
        List<Borrowable> bookList = new ArrayList<>();
        bookList.add(new AvailableBook("Lord of the Rings", "JR Toliken", 1930));
        bookList.add(new AvailableBook("Harry Potter", "JK Rowling", 1992));
        bookList.add(new AvailableBook("Catch-22", "Joesph Heller", 1950));
        bookList.add(new AvailableBook("Winds of Winter", "George RR Martin", 2017));
        Book book = new AvailableBook("1984", "George Orwell", 1950);
        book.addListener(listener);
        book.checkout(user);
        bookList.add(book);
        book = new AvailableBook("Alchemist", "Paulo Coelho", 1988);
        book.addListener(listener);
        book.checkout(user);
        bookList.add(book);
        bookList.forEach(borrowable -> borrowable.addListener(listener));
        this.library = new Library(bookList);
        library.addListener(listener);
    }

    @Test
    public void shouldReturnFalseIfBookIsNotAvailableDuringCheckout() {
        setUpWithData();
        String bookName = "1234";

        assertFalse(library.checkout(user, new AvailableBookSearcher(new ArrayList<>(), bookName)));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulCheckOut() {
        setUpWithData();
        String name = "Lord of the Rings";

        assertTrue(library.checkout(user, new AvailableBookSearcher(new ArrayList<>(), name)));
    }

    @Test
    public void shouldReturnFalseIfBookIsValidButCheckStateIsFalseDuringReturn() {
        setUpWithData();
        String bookName = "Lord of the Rings";

        assertFalse(library.returnItem(bookName, user));
    }

    @Test
    public void shouldReturnFalseIfBookIsInValidDuringReturn() {
        setUpWithData();
        String bookName = "1234";

        assertFalse(library.returnItem(bookName, user));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulReturn() {
        setUpWithData();
        String name = "1984";

        assertTrue(library.returnItem(name, user));
    }

    @Test
    public void shouldUpdateListenerWhenItemIsNotFound() {
        setUpWithData();
        String bookName = "1234";
        library.addListener(listener);
        library.checkout(user, new AvailableBookSearcher(new ArrayList<>(), bookName));

        verify(listener).update(ITEM_NOT_PRESENT);
    }

    @Test
    public void shouldResetVistorBeforeMakingItVisit() {
        setUpWithData();

        library.allAvailableItems(visitor);

        verify(visitor).reset();
    }

}