CREATE TABLE role(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE category(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE state(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE users(
	id serial primary key,
	name varchar(255),
	id_role int references role(id)
);


CREATE TABLE rules(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE item(
	id serial primary key,
	name varchar(255),
	id_category int references category(id),
	id_state int references state(id),
	users_id int references users(id)
);

CREATE TABLE comments(
	id serial primary key,
	text varchar(600),
	id_item int references item(id),
	id_users int references users(id)
);

CREATE TABLE attachs(
	id serial primary key,
	name varchar(255),
	id_item int references item(id),
	id_users int references users(id)
);

CREATE TABLE role_rules(
	id serial primary key,
	id_role int references role(id),
	id_rules int references rules(id)
);



