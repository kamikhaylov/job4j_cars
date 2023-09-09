create table auto_post (
    id              serial primary key,
    text            text         not null,
    created         timestamp    not null,
    auto_user_id    int          not null references auto_user(id)
);

comment on table auto_post is 'Объявления';
comment on column auto_post.id is 'Идентификатор объявления';
comment on column auto_post.text is 'Описание';
comment on column auto_post.created is 'Дата создания';
comment on column auto_post.auto_user_id is 'Пользователь';