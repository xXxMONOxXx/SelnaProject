create table roles
(
    id   serial primary key not null,
    role char varying(32)   not null unique
);