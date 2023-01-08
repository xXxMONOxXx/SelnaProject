create table authors(
	id serial primary key not null,
	firstname char varying(32) not null,
	surname char varying(32) not null,
	patronymic char varying(32) default null
);