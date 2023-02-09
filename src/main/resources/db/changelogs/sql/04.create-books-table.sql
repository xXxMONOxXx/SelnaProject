create table books
(
    id           serial primary key not null,
    title        char varying(256)  not null,
    isbn         char varying(13)   not null,
    release_date date               not null
);
