create table books(
	id serial primary key,
	name char(50)
);

create table book_data(
	id serial primary key,
	author char(50),
	year smallint,
	pages smallint,
	book_id int references books(id) unique
);

insert into books(name) values ('The Titan');
insert into book_data(author, year, pages, 1) values ('Dreiser',2008, 576, 1);