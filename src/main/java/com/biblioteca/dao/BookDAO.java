package com.biblioteca.dao;

public class BookDAO {

    private static BookDAO bookDAO;

    private BookDAO() {
    }

    public static BookDAO getInstance() {
        if (bookDAO == null)
            bookDAO = new BookDAO();
        return bookDAO;
    }

}
