create table book_genres
(
    id          serial primary key         not null,
    fk_book_id  int references books (id)  not null,
    fk_genre_id int references genres (id) not null
);