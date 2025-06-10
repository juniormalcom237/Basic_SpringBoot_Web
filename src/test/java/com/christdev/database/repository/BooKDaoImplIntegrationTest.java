package com.christdev.database.repository;


import com.christdev.database.dao.AuthorDao;
import com.christdev.database.domain.Author;
import com.christdev.database.domain.Book;
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
public class BooKDaoImplIntegrationTest {


    private AuthorDao authorDao;
    private BookDaoImpl underTest;

    @Autowired
    public BooKDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test

    public void TestThatBookCanBeCreatedAndRetrieved() {
        Author author = testDataUtil.createTestAuthor();
        authorDao.createAuthor(author);
        Book book = testDataUtil.createTestBook();
        book.setAuthorId(author.getId());

        underTest.createBook(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void TestThatMultipleBookCanBeCreatedAndRetrieved() {
        Author author = testDataUtil.createTestAuthor();
        authorDao.createAuthor(author);
        Author author2 = testDataUtil.createTestAuthorB();
        authorDao.createAuthor(author2);
        Author author3 = testDataUtil.createTestAuthorC();
        authorDao.createAuthor(author3);


        Book book = testDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.createBook(book);
        Book book2 = testDataUtil.createTestBookB();
        book2.setAuthorId(author2.getId());
        underTest.createBook(book2);

        Book book3 = testDataUtil.createTestBookC();
        book3.setAuthorId(author3.getId());
        underTest.createBook(book3);

        List<Book> result = underTest.findMany();

        assertThat(result).hasSize(3);
    }

    @Test
    public void TestThatBookCanBeUpdatedAndRetrieved() {
        Author author = testDataUtil.createTestAuthor();

        authorDao.createAuthor(author);
        Book book = testDataUtil.createTestBook();

        book.setAuthorId(author.getId());
        underTest.createBook(book);
        book.setTitle("Clean code");
        underTest.update(book.getIsbn(), book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }
    @Test
    public void TestThatBookCanBeDeleted() {
        Book book = testDataUtil.createTestBook();
        Author author = testDataUtil.createTestAuthor();
        authorDao.createAuthor(author);
        book.setAuthorId(author.getId());
        underTest.createBook(book);

        underTest.delete(book.getIsbn());
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isNotPresent();
    }


}
