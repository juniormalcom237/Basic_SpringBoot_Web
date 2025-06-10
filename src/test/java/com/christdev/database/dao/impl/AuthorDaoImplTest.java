package com.christdev.database.dao.impl;


import com.christdev.database.domain.Author;
import com.christdev.database.testDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import static com.christdev.database.testDataUtil.createTestAuthor;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGenerateCorrectSql() {
        Author author = createTestAuthor();
        underTest.createAuthor(author);

        verify(jdbcTemplate).update(eq("INSERT into authors (id,name, age) values (?, ?,?)"),
                eq(1L),
                eq("Junior Malcom"),
                eq(24)
        );
    }


    @Test
    public void testThatFindOneAuthorGenerateTheCorrectSql() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(eq("Select id, name, age from authors where id = ? Limit 1"), ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),eq(1L));
    }

    @Test
    public void TestThatFindManyGeneratesCorrectSql(){
        underTest.findMany();
        verify(jdbcTemplate).query(eq("SELECT id, name, age from authors"), ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
    }

    @Test
    public void testThatUpdateGeneratesCorrectSQl() {
        Author author = testDataUtil.createTestAuthor();
        underTest.update(author, 1L);
        verify(jdbcTemplate).update("Update authors set name = ?, age = ? where id = ?", author.getName(), author.getAge(), 1L);
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete(1L);
        verify(jdbcTemplate).update("DELETE from authors where id = ?", 1L);
    }



}
