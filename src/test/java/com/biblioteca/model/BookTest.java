package com.biblioteca.model;

import com.biblioteca.enums.MenuItem;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Book.class).usingGetClass().verify();
    }

    @Test
    public void shouldReturnFormatedStringToRespresentBookState() {
        Book book = new Book("Lord of the Rings", "JR Toliken", 1930);

        String actualString = book.toString();
        String expectedString = "name='Lord of the Rings', author='JR Toliken', year=1930";

        assertEquals(expectedString, actualString);
    }

}