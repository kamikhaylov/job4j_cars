create table car_brand(
    id      serial      primary key,
    name    varchar     not null,
    model   varchar     not null
);

comment on table car_brand is 'Марки автомобиля';
comment on column car_brand.id is 'Идентификатор марки';
comment on column car_brand.name is 'Марка автомобиля';
comment on column car_brand.model is 'Модель автомобиля';