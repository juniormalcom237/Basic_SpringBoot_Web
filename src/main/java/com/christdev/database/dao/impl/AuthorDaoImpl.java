package com.christdev.database.dao.impl;

import com.christdev.database.dao.AuthorDao;
import com.christdev.database.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl( final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createAuthor(Author author) {
        jdbcTemplate.update("INSERT into authors (id,name, age) values (?, ?,?)",author.getId(), author.getName(), author.getAge());
    }

    @Override
    public Optional<Author> findOne(Long authorId) {
        List<Author> results = jdbcTemplate.query("Select id, name, age from authors where id = ? Limit 1",new AuthorRowMapper(), authorId);
        return results.stream().findFirst();
    }

    @Override
    public List<Author> findMany() {
        List<Author> results = jdbcTemplate.query("SELECT id, name, age from authors",new AuthorRowMapper());
        return results;
    }

    @Override
    public void update(Author author, Long id) {
        jdbcTemplate.update("Update authors set name = ?, age = ? where id = ?", author.getName(), author.getAge(), id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE from authors where id = ?", id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }




}
