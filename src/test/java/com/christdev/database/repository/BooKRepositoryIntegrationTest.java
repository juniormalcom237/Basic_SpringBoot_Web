package com.christdev.database.repository;



import com.christdev.database.domain.Author;
import com.christdev.database.domain.Book;
import com.christdev.database.repositories.AuthorRepository;
import com.christdev.database.repositories.BookRepository;
import com.christdev.database.testDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BooKRepositoryIntegrationTest {


    private AuthorRepository authorDao;
    private BookRepository underTest;

    @Autowired
    public BooKRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test

    public void TestThatBookCanBeCreatedAndRetrieved() {
        Author author = testDataUtil.createTestAuthor();
        Book book = testDataUtil.createTestBook(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        book.setAuthor(result.get().getAuthor());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }
//
    @Test
    public void TestThatMultipleBookCanBeCreatedAndRetrieved() {
        Author author = testDataUtil.createTestAuthor();
        Book book = testDataUtil.createTestBook(author);
        Author author2 = testDataUtil.createTestAuthorB();
        Book book2 = testDataUtil.createTestBookB(author2);
        Author author3 = testDataUtil.createTestAuthorC();
        underTest.save(book);

        underTest.save(book2);

        Book book3 = testDataUtil.createTestBookC(author3);

        underTest.save(book3);
        Iterable<Book> result = underTest.findAll();

        assertThat(result).hasSize(3);
    }

    @Test
    public void TestThatBookCanBeUpdatedAndRetrieved() {
        Author author = testDataUtil.createTestAuthor();
        Book book = testDataUtil.createTestBook(author);

        book.setAuthor(author);
        book.setTitle("Clean code");
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        book.setAuthor(result.get().getAuthor());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }
    @Test
    public void TestThatBookCanBeDeleted() {

        Author author = testDataUtil.createTestAuthor();
        Book book = testDataUtil.createTestBook(author);
        underTest.save(book);

        underTest.delete(book);
        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isNotPresent();
    }




}
