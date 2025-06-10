package com.christdev.database.dao;

import com.christdev.database.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    void createAuthor(Author author);
    Optional<Author> findOne(Long id);

    List<Author> findMany();

    void update(Author author, Long id);

    void delete(Long id);

}
