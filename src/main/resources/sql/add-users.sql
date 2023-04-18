INSERT INTO users (id, username, password) VALUES
(10, 'test_user', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q'),
(11, 'test_admin', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q'),
(12, 'test_athlete', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q')
ON CONFLICT DO NOTHING;

INSERT INTO roles (id, name) VALUES
(1, 'USER'),
(2, 'ADMIN'),
(3, 'ATLETE')
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role_id) VALUES
(10, 1),
(11, 2),
(12, 3)
ON CONFLICT DO NOTHING;