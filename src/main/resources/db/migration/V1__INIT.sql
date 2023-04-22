create table if not exists sport
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

create table if not exists athlete_rank
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

create table if not exists court_surface
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

create table if not exists sports_facility_type
(
    id    serial
        primary key,
    value varchar(50) not null
        unique
);

create table if not exists sports_facility
(
    id      serial
        primary key,
    address varchar(100),
    type    integer
        references sports_facility_type
);

create table if not exists court
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    surface            integer
        references court_surface
);

create table if not exists gym
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    floor_area  double precision not null
);

create table if not exists stadium
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    capacity           integer not null
);

create table if not exists arena
(
    id                 serial
        primary key,
    sports_facility_id integer
        references sports_facility,
    track_number       integer not null
);

create table if not exists sport_club
(
    id    serial
        primary key,
    title varchar(50) not null
);

create table if not exists athlete
(
    id         serial
        primary key,
    first_name varchar(50) not null,
    patronymic varchar(50),
    last_name  varchar(50),
    club       integer
        references sport_club,
    code       integer
);

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

create table if not exists trainer
(
    id         serial
        primary key,
    first_name varchar(50) not null,
    patronymic varchar(50),
    last_name  varchar(50),
    code       integer
);

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

create table if not exists training
(
    athlete_id         integer not null
        references athlete,
    trainer_license_id integer not null
        references trainer_license (id),
    primary key (athlete_id, trainer_license_id)
);

create table if not exists sponsor
(
    id      serial
        primary key,
    name    varchar(150),
    company varchar(50),
    constraint sponsor_check
        check ((name IS NOT NULL) OR (company IS NOT NULL))
);

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

create table if not exists users
(
    id       integer      not null
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table if not exists roles
(
    id   integer      not null
        primary key,
    name varchar(100) not null
);

create table if not exists user_roles
(
    user_id integer not null
        references users,
    role_id integer not null
        references roles,
    unique (user_id, role_id)
);

insert into sport(id, value)
values (1, 'Футбол'),
      (2, 'Хоккей'),
      (3, 'Теннис'),
      (4, 'Бег'),
      (5, 'Баскетбол'),
      (6, 'Волейбол'),
      (7, 'Танцы'),
      (8, 'Биатлон'),
      (9, 'Лыжи');

insert into athlete_rank(id, value)
values (1, 'Нет'),
      (2, '3 юношеский'),
      (3, '2 юношеский'),
      (4, '1 юношеский'),
      (5, '3'),
      (6, '2'),
      (7, '1'),
      (8, 'КМС'),
      (9, 'МС');

insert into court_surface(id, value)
values (1, 'Трава'),
      (2, 'Грунт'),
      (3, 'Хард'),
      (4, 'Ковер'),
      (5, 'Другое');

insert into sports_facility_type(id, value)
values (1, 'court'),
      (2, 'gym'),
      (3, 'stadium'),
      (4, 'arena');

insert into sports_facility(id, address, type)
values (1, 'г. Новосибирск, проспект Строителей 1', 1),
      (2, 'г. Новосибирск, ул Пирогова 2', 1),
      (4, 'г. Новосибирск ул. Коммунистическая, 10а', 1),
      (5, 'г. Новосибирск ул. Ленина, 107', 1),
      (6, 'г. Новосибирск ул. Степная, 26', 2),
      (7, 'г. Новосибирск ул. 50 лет Октября, 4а', 2),
      (8, 'г. Новосибирск ул. Воровского, д. 77а', 2),
      (9, 'г. Новосибирск ул. Обская, 1а', 2),
      (10, 'г. Новосибирск ул. Октябрьская, 15', 2),
      (11, 'г. Новосибирск ул. Советская', 3),
      (12, 'г. Новосибирск ул. Обская 1б', 3),
      (13, 'г. Новосибирск ул. Советская, 14', 3),
      (14, 'г. Новосибирск пр. Коммунистический, 18а', 3),
      (15, 'г. Новосибирск ул. Щорса, 17б', 3),
      (16, 'г. Новосибирск ул. Коммунистическая, 11', 4),
      (17, 'г. Новосибирск ул. Тургенева, 11а', 4),
      (18, 'г. Новосибирск ул. Центральная, 11', 4),
      (19, 'г. Новосибирск ул. Щорса, 17а', 4),
      (20, 'г. Новосибирск, улица Демакова,1', 4);

insert into court(id, sports_facility_id, surface)
values (1, 1, 1),
      (2, 2, 2),
      (4, 4, 4),
      (5, 5, 5);

insert into gym(id, sports_facility_id, floor_area)
values (1, 6, 150.4),
      (2, 7, 300.5),
      (3, 8, 150),
      (4, 9, 250.8),
      (5, 10, 34.6);

insert into stadium(id, sports_facility_id, capacity)
values (1, 11, 1000),
      (2, 12, 3000),
      (3, 13, 2000),
      (4, 14, 5000),
      (5, 15, 20000);

insert into arena(id, sports_facility_id, track_number)
values (1, 16, 5),
      (2, 17, 3),
      (3, 18, 7),
      (4, 19, 6),
      (5, 20, 5);

insert into sport_club(id, title)
values (1, 'Сибирь'),
      (2, 'Локомотив'),
      (3, 'Спартак'),
      (4, 'Динамо'),
      (5, 'Витязь');

insert into athlete(id, first_name, patronymic, last_name, club, code)
values (1, 'Иван', 'Иванович', 'Иванов', 1, 12234434),
      (2, 'Елена', 'Ивановна', 'Петрова', 1, 94324435),
      (3, 'Евгений', 'Анатольевич', 'Сидоров', 1, 21325455),
      (4, 'Петр', 'Сергеевич', 'Матвеев', 2, 43423423),
      (5, 'Валентина', 'Петровна', 'Владимирова', 2, 95343554),
      (6, 'Дмитрий', 'Кириллович', 'Сеношенко', 2, 95443233),
      (7, 'Михаил', 'Дмитриевич', 'Петров', 2, 37326273),
      (8, 'Дмитрий', 'Евгеньевич', 'Иванов', 3, 93254553),
      (9, 'Евгения', 'Антоновна', 'Матвеенко', 3, 43253232),
      (10, 'Александра', 'Александровна', 'Александрова', 4, 43243423),
      (11, 'Андрей', 'Андреевич', 'Полевой', 4, 32432432),
      (12, 'Максим', 'Максимович', 'Максимов', 4, 97868796),
      (13, 'Васисий', 'Васильевич', 'Васильев', 4, 93854643),
      (14, 'Павел', 'Павлович', 'Павлов', 4, 91234355),
      (15, 'Павел', 'Игоревич', 'Павленко', 5, 14534854),
      (16, 'Нина', 'Никитична', 'Валеева', 5, 92357345),
      (17, 'Юрий', 'Юрьевич', 'Петренко', 5, 53455435),
      (18, 'Александр', 'Даниилович', 'Петров', 5, 34534445),
      (19, 'Антонина', 'Павловна', 'Петрова', 1, 14143432),
      (20, 'Диана', 'Петровна', 'Николаева', 1, 35554545);


insert into athlete_ranking(athlete_id, sport, rank)
values (1, 2, 9),
      (1, 4, 1),
      (2, 2, 8),
      (3, 2, 7),
      (4, 3, 9),
      (4, 4, 5),
      (5, 1, 4),
      (5, 5, 5),
      (5, 4, 1),
      (6, 1, 3),
      (7, 6, 6),
      (8, 6, 2),
      (9, 5, 6),
      (9, 4, 2),
      (10, 5, 6),
      (11, 5, 8),
      (12, 3, 5),
      (13, 3, 7),
      (14, 4, 4),
      (15, 4, 6),
      (16, 1, 8),
      (17, 1, 7),
      (18, 1, 3),
      (19, 6, 9),
      (20, 6, 9);

insert into trainer(id, first_name, patronymic, last_name, code)
values (1, 'Иван', 'Иванович', 'Иванов', 13455353),
      (2, 'Борис', 'Викторович', 'Борисов', 35345253),
      (3, 'Виктория', 'Никитична', 'Ильина', 96545545),
      (4, 'Евгений', 'Игоревич', 'Петров', 54353454),
      (5, 'Сергей', 'Сергеевич', 'Сидоров', 53455345),
      (6, 'Елена', 'Ивановна', 'Белкина', 23543534),
      (7, 'Иван', 'Игоревич', 'Петров', 65234534),
      (8, 'Софья', 'Сергеевна', 'Павлова', 54535445),
      (9, 'Елена', 'Ивановна', 'Белкина', 84324354),
      (10, 'Максим', 'Петрович', 'Иванов', 73543543);

insert into trainer_license(id, trainer_id, sport)
values (1, 1, 1),
      (2, 2, 2),
      (3, 3, 6),
      (4, 4, 3),
      (5, 5, 4),
      (6, 5, 5),
      (7, 6, 2),
      (8, 6, 4),
      (9, 7, 5),
      (10, 7, 1),
      (11, 9, 3),
      (12, 10, 4),
      (13, 10, 5);

insert into training(athlete_id, trainer_license_id)
values (1, 2),
      (1, 5),
      (2, 2),
      (3, 7),
      (4, 4),
      (5, 1),
      (5, 6),
      (5, 5),
      (6, 1),
      (7, 3),
      (8, 10),
      (9, 9),
      (10, 9),
      (11, 6),
      (12, 4),
      (13, 4),
      (14, 8),
      (15, 8),
      (16, 1),
      (17, 1),
      (18, 1),
      (19, 3),
      (20, 10);

insert into sponsor(id, name, company)
values (1, 'Сеношенко', 'Google'),
      (2, 'Иванов', 'Microsoft'),
      (3, 'Петров', 'Sumsung'),
      (4, 'Ильин', 'ООО Сидоров'),
      (5, 'Александров', 'ООО Александров');

insert into competition(id, title, period, main_sponsor, sport, location)
values (1, 'Соревнования по хоккею среди студентов', '2023-02-10', 1, 2, 15),
      (2, 'Соревнования среди подростков Новосибирской области по хоккею', '2023-12-01', 4, 2, 12),
      (3, 'Кубок Новосибирска по футболу', '2023-06-20', 2, 1, 11),
      (4, 'Студенческие сореавнования по футболу', '2021-07-17', 3, 1, 14),
      (5, 'Соревнования России по теннису', '2010-04-27', 2, 3, 1),
      (6, 'Первенство Новосибирской области по теннису', '2021-10-08', 5, 3, 4),
      (7, 'Студенческие соревнования по легкой атлетике', '2005-05-09', 1, 4, 19),
      (8, 'Студенческий марафон', '2023-09-01', 4, 4, 20),
      (9, 'Студенческие соревнования по баскетболу', '2022-06-01', 1, 5, 8),
      (10, 'Кубок Новосибирской области среди студентов', '2015-07-10', 5, 6, 9);

insert into competition_player(athlete_id, competition_id, was_awarding, result)
values (1, 1, true, 1),
      (2, 1, true, 2),
      (3, 1, true, 3),
      (1, 2, true, 2),
      (2, 2, true, 1),
      (16, 3, true, 1),
      (17, 3, true, 2),
      (5, 4, true, 1),
      (6, 4, true, 3),
      (18, 4, true, 4),
      (17, 4, true, 2),
      (4, 5, true, 1),
      (13, 5, true, 2),
      (12, 6, true, 2),
      (13, 6, true, 1),
      (4, 7, true, 1),
      (14, 7, true, 3),
      (15, 7, true, 2),
      (9, 8, true, 2),
      (14, 8, true, 1),
      (5, 9, false, 0),
      (9, 9, false, 0),
      (10, 9, false, 0),
      (11, 9, false, 0),
      (7, 10, true, 3),
      (8, 10, true, 4),
      (19, 10, true, 2),
      (20, 10, true, 1);

insert into users (id, username, password)
values (1, 'user', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q'),
       (2, 'admin', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q'),
       (3, 'trainer', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q'),
       (4, 'athlete', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q');

insert into roles (id, name)
values (1, 'USER'),
       (2, 'ADMIN'),
       (3, 'TRAINER'),
       (4, 'ATHLETE');

insert into user_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);