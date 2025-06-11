package com.christdev.database;

import com.christdev.database.domain.Author;
import com.christdev.database.domain.Book;

public class testDataUtil {

    private testDataUtil() {  }

    public static Author createTestAuthor() {
        return Author
                .builder()
                .name("Junior Malcom")
                .age(24)
                .build();
    }

    public static Author createTestAuthorB(){
        return Author
                .builder()
                .name("Tasse Tchoumba")
                .age(222)
                .build();
    }
    public static Author createTestAuthorC(){
        return Author
                .builder()
                .name("Hervana Brenda")
                .age(21)
                .build();
    }
    public static Book createTestBook(final Author author) {
        return Book.builder()
                .isbn("987-1-2131-4567-1")
                .title("The Shadow in the attic")

                .author(author)
                .build();
    }

    public static Book createTestBookB(Author author) {
        return Book.builder()
                .isbn("987-2-2131-4567-2")
                .title("Coding in practice")

                .author(author)
                .build();
    }

//
//    public static Book createTestBookB() {
//        return Book.builder()
//                .isbn("987-2-2131-4567-2")
//                .title("The Bow in the artantic")
////                .authorId(3L)
//                .build();
//    }
    public static Book createTestBookC( Author author) {
        return Book.builder()
                .isbn("987-3-2131-4567-3")
                .title("The Coding Practice")
//                .authorId(2L)
                .author(author)
                .build();
    }
}
