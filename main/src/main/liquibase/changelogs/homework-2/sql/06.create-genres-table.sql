create table genres(
	id serial primary key not null,
	genre char varying(32) unique not null 
);