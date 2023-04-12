INSERT INTO sport(id, value) VALUES
(1, 'Swimming'),
(2, 'Hokey')
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
(2, 'Lokovjtiv')
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
