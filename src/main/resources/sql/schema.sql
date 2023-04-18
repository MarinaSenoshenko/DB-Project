create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

alter table flyway_schema_history
    owner to postgres;

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists sport
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

alter table sport
    owner to postgres;

create table if not exists athlete_rank
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

alter table athlete_rank
    owner to postgres;

create table if not exists court_surface
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

alter table court_surface
    owner to postgres;

create table if not exists sports_facility_type
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

alter table sports_facility_type
    owner to postgres;

create table if not exists sports_facility
(
    id      serial
        primary key,
    address varchar(100),
    type    integer
        references sports_facility_type
);

alter table sports_facility
    owner to postgres;

create table if not exists court
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    surface            integer
        references court_surface
);

alter table court
    owner to postgres;

create table if not exists gym
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    floor_area         double precision not null
);

alter table gym
    owner to postgres;

create table if not exists stadium
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    capacity           integer not null
);

alter table stadium
    owner to postgres;

create table if not exists arena
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    track_number       integer not null
);

alter table arena
    owner to postgres;

create table if not exists sport_club
(
    id    serial
        primary key,
    title varchar(50) not null
);

alter table sport_club
    owner to postgres;

create table if not exists athlete
(
    id         serial
        primary key,
    first_name varchar(50) not null,
    patronymic varchar(50),
    last_name  varchar(50),
    club       integer
        references sport_club
);

alter table athlete
    owner to postgres;

create table if not exists athlete_ranking
(
    athlete_id integer not null
        references athlete,
    sport      integer not null
        references sport,
    rank       integer
        references athlete_rank,
    primary key (athlete_id, sport)
);

alter table athlete_ranking
    owner to postgres;

create table if not exists trainer
(
    id         serial
        primary key,
    first_name varchar(50) not null,
    patronymic varchar(50),
    last_name  varchar(50)
);

alter table trainer
    owner to postgres;

create table if not exists trainer_license
(
    id         serial
        unique,
    trainer_id integer not null
        references trainer,
    sport      integer not null
        references sport,
    primary key (trainer_id, sport)
);

alter table trainer_license
    owner to postgres;

create table if not exists training
(
    athlete_id         integer not null
        references athlete,
    trainer_license_id integer not null
        references trainer_license (id),
    primary key (athlete_id, trainer_license_id)
);

alter table training
    owner to postgres;

create table if not exists sponsor
(
    id      serial
        primary key,
    name    varchar(150),
    company varchar(50),
    constraint sponsor_check
        check ((name IS NOT NULL) OR (company IS NOT NULL))
);

alter table sponsor
    owner to postgres;

create table if not exists competition
(
    id           serial
        primary key,
    title        varchar(100) not null,
    period       date         not null,
    main_sponsor integer
        references sponsor,
    sport        integer
        references sport,
    location     integer
        references sports_facility
);

alter table competition
    owner to postgres;

create table if not exists competition_player
(
    athlete_id     integer not null
        references athlete,
    competition_id integer not null
        references competition,
    was_awarding   boolean not null,
    result         integer not null,
    primary key (athlete_id, competition_id),
    constraint competition_player_check
        check (((NOT was_awarding) AND (result = 0)) OR (was_awarding AND (result > 0)))
);

alter table competition_player
    owner to postgres;

create table if not exists users
(
    id       integer      not null
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

alter table users
    owner to postgres;

create table if not exists roles
(
    id   integer      not null
        primary key,
    name varchar(100) not null
);

alter table roles
    owner to postgres;

create table if not exists user_roles
(
    user_id integer not null
        references users,
    role_id integer not null
        references roles,
    unique (user_id, role_id)
);

alter table user_roles
    owner to postgres;

