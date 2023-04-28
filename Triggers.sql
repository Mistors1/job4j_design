create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);


create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger statement_tax
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
	
create trigger row_tax_trigger
    after insert
    on products
    for each row
    execute procedure tax();
	
	create or replace function insert_in_history()
  returns trigger as
$$
    BEGIN
       insert into history_of_price (name, price, date) 
	   values (new.name, new.price, CURRENT_DATE);
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger history_insert_trigger
 after insert
    on products
    for each row
    execute procedure insert_in_history();