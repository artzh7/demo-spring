create table animal
(
    id          serial primary key,
    title       text unique not null,
    description text
);

create table employee
(
    id         serial primary key,
    first_name text not null,
    last_name  text not null,
    age        int4 not null
);