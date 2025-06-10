package com.christdev.database.dao.impl;

import com.christdev.database.dao.BookDao;
import com.christdev.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO books(isbn, title, author_id) values(?, ?, ?)", book.getIsbn(), book.getTitle(), book.getAuthorId());
    }

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> books = jdbcTemplate.query("Select isbn, title, author_id from Books where isbn = ? Limit 1", new BookRowMapper(), isbn);
        return books.stream().findFirst();
    }

    @Override
    public List<Book> findMany() {
        List<Book> result = jdbcTemplate.query("SELECT isbn, title, author_id from books", new BookRowMapper());
        return result;
    }

    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update("UPDATE books SET title = ?, author_id = ? WHERE isbn = ?",book.getTitle(), book.getAuthorId(), isbn);
    }

    @Override
    public void delete(String isbn) {
        jdbcTemplate.update("DELETE FROM books WHERE isbn = ?", isbn);
    }


    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }


}
