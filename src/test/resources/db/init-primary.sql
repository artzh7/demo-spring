create table animal
(
    id          serial primary key,
    title       text unique not null,
    description text
);