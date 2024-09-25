create table if not exists category
(
    id integer primary key not null,
    description varchar(255),
    name varchar(255)
);

create table if not exists product
(
    id integer primary key not null,
    description varchar(255),
    name varchar(255),
    available_quantity double precision not null,
    price numeric not null,
    category_id integer constraint asdf references category
);

create sequence if not exists category_seq increment by 1;
create sequence if not exists product_seq increment by 1;