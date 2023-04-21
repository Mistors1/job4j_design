create table phone (
id serial primary key,
	number text
);

create table users (
id serial primary key,
	name varchar(255),
	id_phone int references phone(id) unique
)