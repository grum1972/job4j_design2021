create table authors(
     id serial primary key,
     name varchar(255)
 );
 
 create table books(
     id serial primary key,
     name varchar(255)
 );
 
 create table authors_books(
     id serial primary key,
     author_id int references authors(id),
     book_id int references books(id)
 );
 
insert into authors(name) values ('A. Strugatsky');
insert into authors(name) values ('B. Strugatsky');

insert into books(name) values ('Hard To Be a God');
insert into books(name) values ('The Inhabited Island');

insert into authors_books(author_id, book_id) values (1, 1);
insert into authors_books(author_id, book_id) values (1, 2);
insert into authors_books(author_id, book_id) values (2, 1);
insert into authors_books(author_id, book_id) values (2, 2);
