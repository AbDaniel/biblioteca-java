package com.biblioteca.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class BookTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass (Book.class).usingGetClass().verify();
    }

}