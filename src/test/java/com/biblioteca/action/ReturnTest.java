package com.biblioteca.action;

import com.biblioteca.constants.Constants;
import com.biblioteca.model.User;
import com.biblioteca.repository.Library;
import com.biblioteca.view.View;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static nl.jqno.equalsverifier.Warning.NULL_FIELDS;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReturnTest {

    @Mock
    View view;

    @Mock
    private Library library;

    @Mock
    private User user;

    private Return command;
    private String bookName;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        bookName = "Lord of the Rings";
        command = new Return(library, user, bookName);
    }

    @Test
    public void shouldCallReturnBookWithGivenInput() {
        command.execute();

        verify(library).returnItem(eq(bookName), Matchers.any(User.class));
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Return.class).usingGetClass().suppress(NULL_FIELDS).verify();
    }

}