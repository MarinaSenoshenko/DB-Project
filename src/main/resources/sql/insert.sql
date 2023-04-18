INSERT INTO sport(id, value) VALUES
(1, 'Swimming'),
(2, 'Hokey'),
(3, 'Sky'),
(40, 'Football')
ON CONFLICT DO NOTHING;

INSERT INTO athlete_rank(id, value) VALUES
(1, 'No'),
(2, '3 child'),
(3, '2 child'),
(4, '1 child'),
(5, '3'),
(6, '2'),
(7, '1'),
(8, 'KMC'),
(9, 'MC')
ON CONFLICT DO NOTHING;

INSERT INTO sport_club(id, title) VALUES
(1, 'Siberia'),
(2, 'Lokovjtiv'),
(3, 'Vitayaz'),
(40, 'SportClut')
ON CONFLICT DO NOTHING;


INSERT INTO athlete(id, first_name, patronymic, last_name, club) VALUES
(1, 'Ivan', 'Ivanovich', 'Ivanov', 1),
(1, 'Ivan', 'Ivanovich', 'Ivanov', 2),
(2, 'Petr', 'Petrovich', 'Petrov', 1),
(3, 'Aleksandr', 'Aleksandrovich', 'Aleksandrov', 1)
ON CONFLICT DO NOTHING;

INSERT INTO athlete_ranking(athlete_id, sport, rank) VALUES
(1, 2, 9),
(1, 1, 1),
(2, 2, 8),
(3, 2, 7)
ON CONFLICT DO NOTHING;

INSERT INTO sponsor(id, name, company) VALUES
(1, 'Marina Senoshenko', 'OOO Marina'),
(2, null, 'Lukoil'),
(3, null, 'Google'),
(40, null, 'Google')
ON CONFLICT DO NOTHING;

insert into sports_facility_type(id, value)
values (1, 'court')
ON CONFLICT DO NOTHING;

INSERT INTO sports_facility(id, address, type) VALUES
(1, 'Novosibirsk, Demakova street, 14', 1)
ON CONFLICT DO NOTHING;

INSERT INTO competition(id, title, period, main_sponsor, sport, location) VALUES
(1, 'The Russian hockey championship', '2023-01-12', 1, 2, 1),
(2, 'The students hockey championship', '2023-11-01', 2, 2, 1)
ON CONFLICT DO NOTHING;

INSERT INTO competition_player(athlete_id, competition_id, was_awarding, result) VALUES
(1, 1, true, 1),
(2, 2, true, 2),
(3, 1, true, 3)
ON CONFLICT DO NOTHING;

INSERT INTO trainer(id, first_name, patronymic, last_name) VALUES
(1, 'Igor', 'Igorevich', 'Igorov'),
(2, 'Pavel', 'Pavlovich', 'Pavlov'),
(3, 'Pavel', 'Ivanovich', 'Pavlov'),
(40, 'Pavel', 'Ivanovich', 'Pavlov')
ON CONFLICT DO NOTHING;

INSERT INTO trainer_license(id, trainer_id, sport) VALUES
(1, 1, 1),
(2, 2, 2)
ON CONFLICT DO NOTHING;

INSERT INTO training(athlete_id, trainer_license_id) VALUES
(1, 1),
(2, 2),
(3, 1)
ON CONFLICT DO NOTHING;