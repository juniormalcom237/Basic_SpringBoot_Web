-- DROP TABLE IF EXISTS "widgets";
--
-- drop sequence IF exists widgets_id_seq;
-- create sequence widgets_id_seq increment by 1 maxvalue 922337203654775807 cache 1;
-- create table "widgets" (
--     "id" bigint default nextval('widgets_id_seq') not null,
--     "name" text,
--     "purpose" text,
--
--     constraint "widgets_pkey" PRIMARY KEY ("id")
-- );


DROP TABLE IF EXISTS "books";
DROP TABLE IF EXISTS "authors";


drop sequence IF exists authors_id_seq;
create sequence authors_id_seq increment by 1 maxvalue 922337203654775807 cache 1;


create TABLE "authors" (
    "id" bigint default nextval('authors_id_seq') NOT NULL,
    "name" text,
    "age" integer,
    CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

create table "books" (
  "isbn" text not null,
  "title" text,
  "author_id"  bigint,
  constraint "books_pkey" PRIMARY KEY ("isbn"),
  constraint "fk_author" FOREIGN KEY (author_id)
  REFERENCES authors(id)
);


