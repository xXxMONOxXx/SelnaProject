create table users(
	id serial primary key not null,
	is_blocked bool not null default false,
	username char varying(32) not null unique,
	password char varying(256) not null,
	user_role role not null default 'customer'
);