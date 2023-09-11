create table car(
    id          serial      primary key,
    brand_id    int         not null unique references car_brand(id),
    engine_id   int         not null unique references engine(id),
    year        int         not null,
    mileage     int         not null,
    vin         varchar     not null,
    color_id    int         not null unique references color(id)
);

comment on table car is 'Автомобили';
comment on column car.id is 'Идентификатор автомобиля';
comment on column car.brand_id is 'Марка';
comment on column car.engine_id is 'Идентификатор двигателя';
comment on column car.year is 'Год выпуска';
comment on column car.mileage is 'Пробег';
comment on column car.vin is 'VIN-номер';
comment on column car.color_id is 'Цвет';