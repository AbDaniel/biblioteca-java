package com.biblioteca.constants;

import java.util.Scanner;

public interface Constants {
    int INVALID_INPUT = -1;
    String WELCOME_TEXT = "Hello! Welcome to Biblioteca";
    String INVALID_INPUT_TEXT = "Select a valid option!";
    String ENTER_BOOK_NAME = "Enter a book name : ";
    String BOOK_NOT_PRESENT_TEXT = "That book is not available.";
    String SUCCESSFUL_CHECKOUT_TEXT = "Thank you! Enjoy the book.";
    String BOOK_NOT_VALID_TEXT = "That is not a valid book to return.";
    String SUCCESSFUL_RETURN_TEXT = "Thank you for returning the book.";
    String EXIT_MESSAGE = "Thank you! For Using Biblioteca";
    String ENTER_LIBRARY_NO = "Enter Library no :";
    String ENTER_PASSWORD = "Enter password :";
    String INVALID_CREDENTIALS = "Invalid Credentials";
    Scanner scanner = new Scanner(System.in);
    String ENTER_MOVIE_NAME = "Enter Movie name :";
    String MOVIE_IS_NOT_PRESENT = "That Movie is not present";
    String SUCCESS_MOVIE_CHECKOUT = "Thank You! Enjoy the movie";
    String SUCCESS_MOVIE_RETURN = "Thank you for returning the movie";
    String MOVIE_IS_NOT_VALID = "That is a valid movie to return";
}
