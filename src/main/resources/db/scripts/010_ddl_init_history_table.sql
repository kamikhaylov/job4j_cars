create table history(
    id           serial  primary key,
    start_at     timestamp without time zone    default now(),
    end_at       timestamp without time zone    default now()
);