create table auto_user (
    id          serial primary key,
    login       varchar not null unique,
    password    varchar not null,
    name        varchar not null
);

alter table auto_user add constraint login_unique unique (login);

comment on table auto_user is 'Пользователи';
comment on column auto_user.id is 'Идентификатор пользователя';
comment on column auto_user.login is 'Логин';
comment on column auto_user.login is 'Пароль';
comment on column auto_user.name is 'Имя';