create table auto_user (
    id          serial primary key,
    login       varchar not null unique,
    password    varchar not null
);

alter table auto_user add constraint login_unique unique (login);