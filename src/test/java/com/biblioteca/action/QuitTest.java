package com.biblioteca.action;

import com.biblioteca.listener.ExitLogoutListener;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.EXIT_CODE;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QuitTest {

    Quit quit;

    @Mock
    ExitLogoutListener listener;

    @Before
    public void setUp() throws Exception {
        quit = new Quit();
        quit.addExitLogoutListener(listener);
    }

    @Test
    public void shouldUpdateLisitenerWithExitCode() {
        quit.execute();

        verify(listener).update(EXIT_CODE);
    }

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(Quit.class).usingGetClass().verify();
    }

}