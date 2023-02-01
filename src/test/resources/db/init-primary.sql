create table animal
(
    id          serial primary key,
    title       text unique not null,
    description text
);

create table user_request
(
    id         serial primary key,
    username   text         not null,
    request    text         not null,
    created_at timestamp(6) not null
);