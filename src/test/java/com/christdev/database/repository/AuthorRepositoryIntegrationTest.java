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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
//
//    @Test
//    public void testThatMultipleAuthorsCanBeRetrievedFromDatabase() {
//        Author author = testDataUtil.createTestAuthor();
//        underTest.createAuthor(author);
//        Author author2 = testDataUtil.createTestAuthorB();
//        underTest.createAuthor(author2);
//        Author author3 = testDataUtil.createTestAuthorC();
//        underTest.createAuthor(author3);
//
//        List<Author> result = underTest.findMany();
//
//        assertThat(result).hasSize(3).contains(author, author2, author3);
//    }
//
//   @Test
//
//    public void testThatAuthorCanBeUpdated() {
//
//        Author author = testDataUtil.createTestAuthor();
//        underTest.createAuthor(author);
//        author.setName("Thierry");
//        author.setName("25");
//        underTest.update(author, author.getId());
//        Optional<Author> result = underTest.findOne(author.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(author);
//
//   }
//
//   @Test
//    public void testThatAuthorCanBeDeleted() {
//        Author author = testDataUtil.createTestAuthor();
//        underTest.createAuthor(author);
//
//        Author author2 = testDataUtil.createTestAuthorB();
//        underTest.createAuthor(author2);
//
//        underTest.delete(author.getId());
//        Optional<Author> result = underTest.findOne(author.getId());
//        assertThat(result).isNotPresent();
//   }




}
