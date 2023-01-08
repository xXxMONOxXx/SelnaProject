create table books_author(
	id serial primary key not null,
	fk_book_id int references books(id) not null,
	fk_author_id int references authors(id) not null
);