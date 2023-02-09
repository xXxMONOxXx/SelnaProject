alter table users
    add role_id int not null default 1,
    add foreign key (role_id) references roles (id);