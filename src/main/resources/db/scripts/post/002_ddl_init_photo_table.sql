create table photo (
    id       serial   primary key,
    name     varchar  not null,
    path     varchar  not null unique
);

comment on table photo is 'Фото автомобилей';
comment on column photo.id is 'Идентификатор фото';
comment on column photo.name is 'Наименование фото';
comment on column photo.path is 'Путь к фото';