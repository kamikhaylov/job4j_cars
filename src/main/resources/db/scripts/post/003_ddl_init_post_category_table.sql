create table post_category(
    id      serial      primary key,
    name    varchar     not null
);

comment on table post_category is 'Категория объявления';
comment on column post_category.id is 'Идентификатор объявления';
comment on column post_category.name is 'Наименнование категории';