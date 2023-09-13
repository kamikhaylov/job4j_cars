create table price_history (
    id              serial primary key,
    before          bigint not null,
    after           bigint not null,
    created         timestamp without time zone default now(),
    auto_post_id    int references auto_post (id)
);

comment on table price_history is 'История стоимости автомобиля';
comment on column price_history.id is 'Идентификатор стоимости';
comment on column price_history.before is 'Старая стоимость';
comment on column price_history.after is 'Новая стоимость';
comment on column price_history.created is 'Дата изменения';
comment on column price_history.auto_post_id is 'Идентификатор объявления';