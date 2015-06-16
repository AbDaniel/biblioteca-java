package com.biblioteca.visitor;

import com.biblioteca.model.CheckedOutBook;
import com.biblioteca.model.User;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.biblioteca.model.Book.REGULAR_BOOK_FORMAT;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDefaulterVisitorTest {

    private BookDefaulterVisitor userVisitor;

    @Mock
    private CheckedoutBookVisitor bookVisitor;

    @Mock
    private Map<User, String> defaultedBooks;

    @Mock
    User user;

    @Mock
    List<CheckedOutBook> visitables;

    @Before
    public void setUp() throws Exception {
        userVisitor = new BookDefaulterVisitor(bookVisitor, defaultedBooks);
    }

    @Test
    public void shouldAddTheVisitedObjectToDefaultedBooks() {
        when(bookVisitor.visitablesAsString()).thenReturn("Visitables");
        userVisitor.visit(user, visitables);
        String books = bookVisitor.visitablesAsString();

        verify(defaultedBooks).put(user, books);
    }

    @Test
    public void shouldReturnVisitedElementsAsString() {
        user = new User("111-1111", "sauron", "onering", new ArrayList<>());
        List<CheckedOutBook> checkedOutBooks = new ArrayList<>();
        checkedOutBooks.add(new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930));
        checkedOutBooks.add(new CheckedOutBook("Winds of Winter", "RR Martin", 1930));
        bookVisitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        userVisitor = new BookDefaulterVisitor(bookVisitor, new HashMap<>());
        userVisitor.visit(user, checkedOutBooks);

        String userBooks = userVisitor.visitablesAsString();

        assertEquals("libraryNo='111-1111', name='sauron'\n" +
                "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Winds of Winter', author='RR Martin', year=1930\n", userBooks);
    }

    @Test
    public void shouldReturnVisitUserOnlyIfUserHasANonEmptyBorrowables() {
        user = new User("111-1111", "sauron", "onering", new ArrayList<>());
        List<CheckedOutBook> checkedOutBooks = new ArrayList<>();
        checkedOutBooks.add(new CheckedOutBook("Lord of the Rings", "JR Toliken", 1930));
        checkedOutBooks.add(new CheckedOutBook("Winds of Winter", "RR Martin", 1930));
        bookVisitor = new CheckedoutBookVisitor(new ArrayList<>(), REGULAR_BOOK_FORMAT);
        userVisitor = new BookDefaulterVisitor(bookVisitor, new HashMap<>());
        userVisitor.visit(user, checkedOutBooks);
        user = new User("111-1112", "sauron", "onering", new ArrayList<>());
        userVisitor.visit(user, new ArrayList<>());
        user = new User("111-1113", "sauron", "onering", new ArrayList<>());
        userVisitor.visit(user, checkedOutBooks);

        String userBooks = userVisitor.visitablesAsString();

        assertEquals("libraryNo='111-1111', name='sauron'\n" +
                "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Winds of Winter', author='RR Martin', year=1930\n" +
                "libraryNo='111-1113', name='sauron'\n" +
                "name='Lord of the Rings', author='JR Toliken', year=1930\n" +
                "name='Winds of Winter', author='RR Martin', year=1930\n", userBooks);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(AvailableBookVisitor.class).usingGetClass().verify();
    }


}