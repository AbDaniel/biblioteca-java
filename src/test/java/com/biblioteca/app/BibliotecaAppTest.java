package com.biblioteca.app;

import com.biblioteca.action.Parser;
import com.biblioteca.model.User;
import com.biblioteca.view.View;
import com.biblioteca.controller.UserController;
import com.biblioteca.controller.LoginController;
import com.biblioteca.repository.Library;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.biblioteca.constants.Constants.*;
import static com.biblioteca.constants.Constants.WELCOME_TEXT;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Mock
    Library library;

    @Mock
    View view;

    @Mock
    Parser parser;

    @Mock
    UserController userController;

    @Mock
    LoginController loginController;

    BibliotecaApp bibliotecaApp;

    @Before
    public void setUp() {
        User user = new User("111-1111", "sauron", "onering", null);
        bibliotecaApp = new BibliotecaApp(WELCOME_TEXT, view, userController, loginController);
        bibliotecaApp.update(EXIT_CODE);
        bibliotecaApp.update(user);
    }

    @Test
    public void shouldPrintWelcomeMessageAtStart() {
        bibliotecaApp.start();

        verify(view).displayMessage(WELCOME_TEXT);
    }

    @Test
    public void shouldStartController() {
        bibliotecaApp.start();

        verify(userController).execute(any(User.class));
    }

    @Test
    public void shouldExitApp() {
        bibliotecaApp.start();

        verify(userController, times(1)).execute(any(User.class));
    }

    @Test
    public void shouldContinueWhenUserDoesNotExit() throws InterruptedException {
        bibliotecaApp.update(RUNNING);
        Thread t = new Thread(bibliotecaApp::start);
        t.start();
        t.join(500);

        assertTrue(t.isAlive());

        t.stop();
    }

    @Test
    public void shouldExecuteLoginController() {
        bibliotecaApp.start();

        verify(loginController).execute();
    }

}