create table price_history (
    id      serial primary key,
    before  bigint not null,
    after   bigint not null,
    created timestamp without time zone default now()
);

comment on table price_history is 'История стоимости автомобиля';
comment on column price_history.id is 'Идентификатор стоимости';
comment on column price_history.before is 'Старая стоимость';
comment on column price_history.after is 'Новая стоимость';
comment on column price_history.created is 'Дата изменения';