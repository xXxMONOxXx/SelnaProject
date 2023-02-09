create table items
(
    id              serial primary key        not null,
    fk_book_id      int references books (id) not null,
    fk_users_id     int references users (id) default null,
    taking_date     date                      default null,
    expiration_date date                      default null
);