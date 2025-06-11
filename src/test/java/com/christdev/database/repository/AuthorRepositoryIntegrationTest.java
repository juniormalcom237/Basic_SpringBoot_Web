package com.christdev.database.repository;


import com.christdev.database.domain.Author;
import com.christdev.database.repositories.AuthorRepository;
import com.christdev.database.testDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {
     private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRetrievedFromDatabase() {
        Author author = testDataUtil.createTestAuthor();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeRetrievedFromDatabase() {
        Author author = testDataUtil.createTestAuthor();
        underTest.save(author);
        Author author2 = testDataUtil.createTestAuthorB();
        underTest.save(author2);
        Author author3 = testDataUtil.createTestAuthorC();
        underTest.save(author3);

        Iterable<Author> result = underTest.findAll();

        assertThat(result).hasSize(3).contains(author, author2, author3);
    }

   @Test

    public void testThatAuthorCanBeUpdated() {

        Author author = testDataUtil.createTestAuthor();
        underTest.save(author);
        author.setName("Thierry");
        author.setName("25");
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

   }

   @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = testDataUtil.createTestAuthor();
        underTest.save(author);

        Author author2 = testDataUtil.createTestAuthorB();
        underTest.save(author2);

        underTest.delete(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isNotPresent();
   }



    @Test
    public void testThatGetAuthorsWithAgeLessThan(){
        Author author = testDataUtil.createTestAuthor();
        Author author2 = testDataUtil.createTestAuthorB();
        Author author3 = testDataUtil.createTestAuthor();
        underTest.save(author);
        underTest.save(author2);
        underTest.save(author3);

       Iterable<Author> results = underTest.ageLessThan(50);
       assertThat(results).contains(author, author3);


    }

    @Test

    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author author = testDataUtil.createTestAuthor();
        Author author2 = testDataUtil.createTestAuthorB();
        Author author3 = testDataUtil.createTestAuthor();
        underTest.save(author);
        underTest.save(author2);
        underTest.save(author3);
        Iterable<Author> results = underTest.ageGreaterThan(50);
        assertThat(results).contains(author2);

    }



}
