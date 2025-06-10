package com.christdev.database.dao;


import com.christdev.database.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    public void createBook(Book book);
    public Optional<Book> findOne(String isbn);

    public List<Book> findMany();
    public void update(String isbn, Book book);
    public void delete(String isbn);
}
