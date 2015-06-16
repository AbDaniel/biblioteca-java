package com.biblioteca.repository;

import com.biblioteca.listener.Listener;
import com.biblioteca.model.User;
import com.biblioteca.visitor.DefaulterVisitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountsTest {

    Accounts accounts;

    @Mock
    List<User> users;

    @Mock
    DefaulterVisitor visitor;

    @Mock
    Listener listener;

    @Before
    public void setUp() throws Exception {
        accounts = new Accounts(users);
        accounts.addListener(listener);
    }

    @Test
    public void shouldUpdateListenerWithVistialbleString() {
        when(visitor.visitablesAsString()).thenReturn("Visitables");
        accounts.listDefaulter(visitor);

        verify(listener).update("Visitables");
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Accounts.class).usingGetClass().verify();
    }
}