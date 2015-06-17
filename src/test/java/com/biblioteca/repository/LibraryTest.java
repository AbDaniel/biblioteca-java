package com.biblioteca.repository;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.*;
import com.biblioteca.search.AvailableBookSearcher;
import com.biblioteca.search.CheckedOutBookSearcher;
import com.biblioteca.search.Searcher;
import com.biblioteca.visitor.AvailableBookVisitor;
import com.biblioteca.visitor.Visitor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static org.mockito.Mockito.mock;
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

    @Mock
    Searcher searcher;

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
        bookList.add(new CheckedOutBook("Dance With Dragons", "George RR Martin", 2017));
        bookList.forEach(borrowable -> borrowable.addListener(listener));
        Book book = new AvailableBook("1984", "George Orwell", 1950);
        book.addListener(listener);
        bookList.add(book);
        this.library = new Library(bookList);
        library.addListener(listener);
    }

    @Test
    public void shouldUpdateListenerWithAvailableBorrowablesOnList() {
        setUpWithData();
        listener = mock(Listener.class);
        library.addListener(listener);
        visitor = new AvailableBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);

        library.allAvailableItems(visitor);
        String expected = "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Harry Potter', author='JK Rowling', year=1992\n" +
                "name='Catch-22', author='Joesph Heller', year=1950\n" +
                "name='Winds of Winter', author='George RR Martin', year=2017\n" +
                "name='1984', author='George Orwell', year=1950\n";

        verify(listener).update(expected);
    }

    @Test
    public void shouldRemoveAvailableBorrowableFromListOnSuccessFullCheckout() {
        AvailableBook book = new AvailableBook("Lord of the Rings", "JR Toliken", 1930);
        String bookName = "Lord of the Rings";
        ArrayList<AvailableBook> books = new ArrayList<>();
        books.add(book);
        book.addListener(listener);
        library.checkout(user, new AvailableBookSearcher(books, bookName));

        verify(bookList).remove(book);
    }

    @Test
    public void shouldAddCheckedoutBorrowableFromListOnSuccessFullCheckout() {
        CheckedOutBook book = new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930);
        AvailableBook availableBook = new AvailableBook("Lord of the Rings", "JR Toliken", 1930);
        String bookName = "Lord of the Rings";
        ArrayList<AvailableBook> books = new ArrayList<>();
        books.add(availableBook);
        book.addListener(listener);
        availableBook.addListener(listener);
        library.checkout(user, new AvailableBookSearcher(books, bookName));

        verify(bookList).add(book);
    }

    @Test
    public void shouldRemoveCheckoutBorrowableFromListOnSuccessFullReturn() {
        CheckedOutBook checkedOutBook = new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930);
        String bookName = "Lord of the Rings";
        ArrayList<CheckedOutBook> books = new ArrayList<>();
        books.add(checkedOutBook);
        checkedOutBook.addListener(listener);
        library.returnItem(user, new CheckedOutBookSearcher(books, bookName));

        verify(bookList).remove(checkedOutBook);
    }

    @Test
    public void shouldAddAvailableBorrowableFromListOnSuccessFullReturn() {
        CheckedOutBook checkedoutBook = new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930);
        AvailableBook availableBook = new AvailableBook("Lord of the Rings", "JR Toliken", 1930);
        String bookName = "Lord of the Rings";
        ArrayList<CheckedOutBook> books = new ArrayList<>();
        books.add(checkedoutBook);
        checkedoutBook.addListener(listener);
        availableBook.addListener(listener);
        library.returnItem(user, new CheckedOutBookSearcher(books, bookName));

        verify(bookList).add(availableBook);
    }

    @Test
    public void shouldAddLisitenerToSearcherDuringCheckout() {
        library.checkout(user, searcher);

        verify(searcher).addListener(listener);
    }

    @Test
    public void shouldAddLisitenerToSearcherDuringReturn() {
        library.returnItem(user, searcher);

        verify(searcher).addListener(listener);
    }

}