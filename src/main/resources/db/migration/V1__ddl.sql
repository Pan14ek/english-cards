CREATE TABLE if not exists users
(
    id       UUID         not null primary key,
    email    varchar(255) not null unique,
    nickname varchar(32)  not null unique,
    password varchar(255) not null
);