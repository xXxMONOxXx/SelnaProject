create table profiles
(
    id        serial primary key references users (id),
    firstname char varying(32)  not null,
    surname   char varying(32)  not null,
    phone     char varying(25)  not null,
    email     char varying(320) not null,
    birthdate date              not null
);