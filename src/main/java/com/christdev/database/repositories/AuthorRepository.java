package com.christdev.database.repositories;

import com.christdev.database.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //Create a repository bean // This is generally not tested since its generated for us



public interface AuthorRepository extends CrudRepository<Author, Long> {
}
