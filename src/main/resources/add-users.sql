INSERT INTO users (id, username, password) VALUES
(1, 'test_user', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q'),
(2, 'test_admin', '$2a$12$GE0fwtXSe4A/LTCWx8UB0OTrWOWDArbbEGoDuXeOvDoDnwnEO357q')
ON CONFLICT DO NOTHING;

INSERT INTO roles (id, name) VALUES
(1, 'USER'),
(2, 'ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(2, 2)
ON CONFLICT DO NOTHING;