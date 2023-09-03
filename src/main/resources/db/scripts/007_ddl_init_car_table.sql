create table car(
    id          serial      primary key,
    name        varchar     not null,
    engine_id   int         not null unique references engine(id)
);

comment on table car is 'Автомобили';
comment on column car.id is 'Идентификатор автомобиля';
comment on column car.name is 'Наименнование автомобиля';
comment on column car.engine_id is 'Идентификатор двигателя';