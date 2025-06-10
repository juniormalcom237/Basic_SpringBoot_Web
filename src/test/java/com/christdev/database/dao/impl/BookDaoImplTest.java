package com.christdev.database.dao.impl;

import com.christdev.database.dao.BookDao;
import com.christdev.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.christdev.database.testDataUtil.createTestBook;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test


    public void testThatCreateGeneratesCorrectSql() {
        Book book = createTestBook();

        underTest.createBook(book);

       verify(jdbcTemplate).update(eq("INSERT INTO books(isbn, title, authorId) values(?, ?, ?)")
               ,eq("987-1-2131-4567-1"),
               eq("The Shadow in the attic"),
               eq(1L));

    }

    @Test

    public void testThatFindOneBookGeneratesCorrectSql() {


        underTest.findOne("987-1-2131-4567-1");
        verify(jdbcTemplate).query(eq("Select isbn, title, author_id, from Books where isbn = ? Limit 1"), ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(), eq("987-1-2131-4567-1"));
    }


    @Test
    public void testThatFindAllBooksGeneratesCorrectSql() {
        underTest.findMany();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }
    @Test
    public void testThatUpdateBookGeneratesCorrectSql() {
        Book book = createTestBook();
        underTest.update("987-1-2131-4567-1", book);
        verify(jdbcTemplate).update("UPDATE books SET title = ?, author_id = ? WHERE isbn = ?",book.getTitle(), book.getAuthorId(), "987-1-2131-4567-1");
    }

    @Test
    public void testThatDeleteBookGeneratesCorrectSql() {
        Book book = createTestBook();
        underTest.delete("987-1-2131-4567-1");
        verify(jdbcTemplate).update("DELETE FROM books WHERE isbn = ?","987-1-2131-4567-1");
    }


}