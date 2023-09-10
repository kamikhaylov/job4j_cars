create table history(
    id           serial  primary key,
    start_at     timestamp without time zone    default now(),
    end_at       timestamp without time zone    default now(),
    owner_id     int     not null references owners(id)
);

comment on table history is 'История владения автомобиля';
comment on column history.start_at is 'Дата начала';
comment on column history.end_at is 'Дата окончания';
comment on column history.owner_id is 'Владелец';