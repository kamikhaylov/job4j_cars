create table history_owner(
    id          serial  primary key,
    owner_id    int     not null references owners(id),
    car_id      int     not null references car(id)
);

comment on table history_owner is 'История владельцев';
comment on column history_owner.id is 'Идентификатор истории владельцев';
comment on column history_owner.owner_id is 'Идентификатор владльца';
comment on column history_owner.car_id is 'Идентификатор автомобиля';