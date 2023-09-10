create table color(
    id      serial      primary key,
    name    varchar     not null
);

comment on table color is 'Цвета автомобилей';
comment on column color.id is 'Идентификатор цвета';
comment on column color.name is 'Наименноваание цвета';