package com.christdev.database;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class DatabaseApplication implements CommandLineRunner {



    private final DataSource dataSource;


    public DatabaseApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public static void main(String[] args) {
        SpringApplication.run(DatabaseApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
//        log.info("Datasource:" + dataSource.toString());
//
//        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.execute("select 1");
    }



}
