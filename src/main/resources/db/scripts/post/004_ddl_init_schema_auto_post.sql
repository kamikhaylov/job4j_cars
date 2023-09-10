create table auto_post (
    id              serial primary key,
    text            text         not null,
    created         timestamp    not null,
    auto_user_id    int          not null references auto_user(id),
    car_id          int          not null references car(id),
    photo_id        int          not null references photo(id),
    category_id     int          not null references post_category(id),
    is_sold         boolean     not null
);

comment on table auto_post is 'Объявления';
comment on column auto_post.id is 'Идентификатор объявления';
comment on column auto_post.text is 'Описание';
comment on column auto_post.created is 'Дата создания';
comment on column auto_post.auto_user_id is 'Пользователь';
comment on column auto_post.car_id is 'Автомобиль';
comment on column auto_post.photo_id is 'Фото';
comment on column auto_post.category_id is 'Категория объявления';
comment on column auto_post.is_sold is 'Признак продажи (true - продано, false - нет)';