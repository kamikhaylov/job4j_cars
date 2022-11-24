CREATE TABLE auto_user (
    id SERIAL PRIMARY KEY,
    login VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL
);

ALTER TABLE auto_user ADD CONSTRAINT login_unique UNIQUE (login);