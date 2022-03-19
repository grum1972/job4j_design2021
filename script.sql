create table customers(
    id serial primary key,
    firstName character(30),
    lastName character(30),
    email character(30),
    age integer
);
insert into customers (firstname, lastname, email, age) values ('Kotov','Oleg','mail@mail.com',49);
select * from customers;

update customers set firstname='Ivanov';
select * from customers;

delete from customers;
select * from customers;