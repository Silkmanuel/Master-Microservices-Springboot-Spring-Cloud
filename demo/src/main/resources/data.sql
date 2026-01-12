DELETE FROM user_details;
-- USER
insert into user_details(id, birth_date, name) values (1, current_date(), 'Adam');
insert into user_details(id, birth_date, name) values (2, current_date(), 'Silkadas');
insert into user_details(id, birth_date, name) values (3, current_date(), 'Manuel');

-- POST
INSERT INTO post (id, description, user_id) VALUES (2001, 'I want to learn AWS', 1);
INSERT INTO post (id, description, user_id) VALUES (2002, 'I want to learn DEVOPS', 2);
INSERT INTO post (id, description, user_id) VALUES (2003, 'I want to learn AWS 2', 1);
