create table statuses(
    id serial primary key,
    status varchar(255)
);

create table suppliers(
    id serial primary key,
    name varchar(255),
    status_id int references statuses(id)
);

insert into statuses(status) values ('LTD');
insert into statuses(status) values ('INC');
insert into suppliers(name, status_id) VALUES ('Avrora', 1);

select * from suppliers;

select * from statuses where id in (select id from suppliers);
