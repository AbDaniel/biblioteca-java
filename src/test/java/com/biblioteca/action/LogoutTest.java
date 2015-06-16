package com.biblioteca.action;

import com.biblioteca.listener.ExitLogoutListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.LOGOUT_CODE;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogoutTest {
    Logout logout;

    @Mock
    ExitLogoutListener listener;

    @Before
    public void setUp() throws Exception {
        logout = new Logout();
        logout.addExitLogoutListener(listener);
    }

    @Test
    public void shouldUpdateLisitenerWithExitCode() {
        logout.execute();

        verify(listener).update(LOGOUT_CODE);
    }

}