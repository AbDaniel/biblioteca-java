package com.biblioteca.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(User.class).usingGetClass();
    }

}