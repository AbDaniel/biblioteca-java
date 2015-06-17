package com.biblioteca.search;

import com.biblioteca.model.User;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ValidUserSearcherTest {

    UserSearcher searcher;

    @Mock
    List<User> users;

    @Before
    public void setUp() throws Exception {
        searcher = new ValidUserSearcher(users, "111-1111", "onering");
    }

    @Test
    public void shouldAddTheVisitedObjectToBooks() {
        User user = new User("111-1111", "sauron", "onering", new ArrayList<>());

        searcher.visit(user);

        verify(users).add(user);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(ValidUserSearcher.class).usingGetClass().verify();
    }
}
