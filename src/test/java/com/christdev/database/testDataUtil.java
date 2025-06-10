package com.christdev.database;

import com.christdev.database.domain.Author;
import com.christdev.database.domain.Book;

public class testDataUtil {

    private testDataUtil() {  }

    public static Author createTestAuthor() {
        return Author
                .builder()
                .id(1L)
                .name("Junior Malcom")
                .age(24)
                .build();
    }

    public static Author createTestAuthorB(){
        return Author
                .builder()
                .id(2L)
                .name("Tasse Tchoumba")
                .age(222)
                .build();
    }
    public static Author createTestAuthorC(){
        return Author
                .builder()
                .id(3L)
                .name("Hervana Brenda")
                .age(21)
                .build();
    }
    public static Book createTestBook() {
        return Book.builder()
                .isbn("987-1-2131-4567-1")
                .title("The Shadow in the attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("987-2-2131-4567-2")
                .title("The Bow in the artantic")
                .authorId(3L)
                .build();
    }
    public static Book createTestBookC() {
        return Book.builder()
                .isbn("987-3-2131-4567-3")
                .title("The Coding Practice")
                .authorId(2L)
                .build();
    }
}
