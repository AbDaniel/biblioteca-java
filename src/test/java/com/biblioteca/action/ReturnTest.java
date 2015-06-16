package com.biblioteca.action;

import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.search.CheckedOutBookSearcher;
import com.biblioteca.view.View;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static nl.jqno.equalsverifier.Warning.NULL_FIELDS;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class ReturnTest {

    @Mock
    View view;

    @Mock
    private Library library;

    @Mock
    private User user;

    private Return command;
    private String bookName;
    private CheckedOutBookSearcher searcher;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookName = "Lord of the Rings";
        command = new Return(library, user, searcher);
    }

    @Test
    public void shouldCallReturnBookWithGivenInput() {
        searcher = new CheckedOutBookSearcher(new ArrayList<>(), bookName);
        command = new Return(library, user, searcher);

        command.execute();

        verify(library).returnItem(eq(bookName), Matchers.any(User.class), eq(searcher));
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Return.class).usingGetClass().suppress(NULL_FIELDS).verify();
    }

}