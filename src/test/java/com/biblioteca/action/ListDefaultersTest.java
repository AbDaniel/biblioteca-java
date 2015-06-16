package com.biblioteca.action;

import com.biblioteca.repository.Accounts;
import com.biblioteca.visitor.DefaulterVisitor;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListDefaultersTest {

    ListDefaulters listDefaulters;

    @Mock
    Accounts accounts;

    @Mock
    DefaulterVisitor visitor;

    @Before
    public void setUp() throws Exception {
        listDefaulters = new ListDefaulters(accounts, visitor);
    }

    @Test
    public void shouldListDefaultersWithDefaulterVisitor() {
        listDefaulters.execute();

        verify(accounts).listDefaulter(visitor);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(ListDefaulters.class).usingGetClass().verify();
    }

}