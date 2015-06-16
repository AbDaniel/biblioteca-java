package com.biblioteca.visitor;

import com.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDefaulterVisitorTest {

    BookDefaulterVisitor userVisitor;

    @Mock
    private CheckedoutBookVisitor bookVisitor;

    @Mock
    private Map<User, String> defaultedBooks;

    @Mock
    User user;

    @Before
    public void setUp() throws Exception {
        userVisitor = new BookDefaulterVisitor(bookVisitor, defaultedBooks);
    }

    @Test
    public void shouldAddTheVisitedObjectToDefaultedBooks() {
        when(bookVisitor.visitables()).thenReturn("Visitables");
        userVisitor.visit(user);
        String books = bookVisitor.visitables();

        verify(defaultedBooks).put(user, books);
    }

}