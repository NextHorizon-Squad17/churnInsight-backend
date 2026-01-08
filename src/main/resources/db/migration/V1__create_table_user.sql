CREATE TABLE users(
    id UUID PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    is_admin BOOLEAN NOT NULL DEFAULT FALSE
);