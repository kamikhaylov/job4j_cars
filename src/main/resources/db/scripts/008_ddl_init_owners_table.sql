create table owners(
    id        serial   primary key,
    name      varchar  not null,
    user_id   int      not null references auto_user(id)
);

comment on table owners is 'Владельцы';
comment on column owners.id is 'Идентификатор владельцы';
comment on column owners.name is 'Имя владльца';
comment on column owners.user_id is 'Идентификатор пользователя';