INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('jpi287', '123456', 1, 'Julia',
                                           'Polushina', 'jpi287@mail.ru', '$2a$04$Bc845c8WU6L2aFCyQ/sHkuCc5BMCL6pQyDnfrNXVOV4V8OUQs7d8O', 1, '2017-10-10');
-- password:1234
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('ivan_88', '654321', 2, 'Ivan',
                                           'Ivanov', 'ivan111@mail.ru', '$2a$04$2oCF0iLWQOdhEwufBwVtl.zZ5x7YTA/gIpcCNsDAizhiDWut8Sn5.', 2, '2017-10-11');
-- password:4321
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('sun123', '63663', 3, 'John',
                                           'Lenon', 'lenon@gmail.com', '$2a$04$Dgy04povDN7eLKQ19q10O.r5.qbqo5OlpR7epP5pktsrCck5hOPXK', 1, '1970-10-15');
-- password:123

INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('king14', '93826475', 4, 'Louis XIV',
                                           'Great', 'king@gmail.com', '$2a$04$wHBcib.ZvChc2pBmoRWjMeTwnvvEXeAOjYhuhPUVO.MKXh2u23wVq', 1, '1700-08-08');
-- password:1234

INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('darya', '7643433', 5, 'Darya',
                                           'Princess', 'darya@gmail.com', '$2a$04$wHBcib.ZvChc2pBmoRWjMeTwnvvEXeAOjYhuhPUVO.MKXh2u23wVq', 1, '2017-11-11');
-- password:1234

INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('snow', '64862662', 6, 'John',
                                           'Snow', 'snow@gmail.com', '$2a$04$wHBcib.ZvChc2pBmoRWjMeTwnvvEXeAOjYhuhPUVO.MKXh2u23wVq', 1, '2017-11-15');
-- password:1234

INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id, registration_date) VALUES ('finn', '6486562', 7, 'Finn',
                                           'Human', 'finn@gmail.com', '$2a$04$wHBcib.ZvChc2pBmoRWjMeTwnvvEXeAOjYhuhPUVO.MKXh2u23wVq', 1, '2017-11-19');
-- password:1234

INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Torzhkovskaya', 'Saint-Petersburg', 'Russia', '1111', 3);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Lenina', 'Saint-Petersburg', 'Russia', '1111', 4);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Korablestroiteley', 'Saint-Petersburg', 'Russia', '11131', 6);
INSERT INTO address (city, country)
VALUES ('Paris', 'France');
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Lenina', 'Saint-Petersburg', 'Russia', '1111', 42);
INSERT INTO address (city, country, zipcode)
VALUES ('Winterfell', 'Westeros', '11131');
INSERT INTO address (country)
VALUES ('Earth');

INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405123', 'AVAILABLE', 1, 1, '2017-10-11');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405124', 'BLOCKED_BY_AN_EMPLOYEE', 1, 2, '2017-10-11');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405125', 'AVAILABLE', 3, 3, '2017-10-16');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405126', 'AVAILABLE', 4, 1, '2017-11-16');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405127', 'BLOCKED_BY_AN_EMPLOYEE', 5, 3, '2017-11-11');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405128', 'AVAILABLE', 6, 6, '2017-11-14');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405129', 'BLOCKED_BY_AN_EMPLOYEE', 6, 2, '2017-11-17');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405140', 'AVAILABLE', 7, 5, '2017-11-19');
INSERT INTO contract (number, status, customer_id, rate_id, creation_date) VALUES ('1401401405141', 'BLOCKED_BY_AN_EMPLOYEE', 7, 4, '2017-11-21');

INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('For ladies', 300, 1000 , 1000, 2000 , 'Simple, wonderful tariff', 'http://localhost:8080/img/star (1).png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('All in one', 300, 300, 300, 5000, 'All inclusive', 'http://localhost:8080/img/users.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('Premium', 1500, 5000, 5000, 15000, 'The most expensive tariff', 'http://localhost:8080/img/smartphone.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('For students', 200, 100 , 50, 10000 , 'You will be online always', 'http://localhost:8080/img/like.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('Cheap tariff', 150, 200, 99, 1500, 'Cheap', 'http://localhost:8080/img/house.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('Talker', 200, 1000, 99, 2000, 'A lot of minutes for outgoing calls', 'http://localhost:8080/img/microphone.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('Gossip', 200, 200, 1000, 2000, 'A lot of sms', 'http://localhost:8080/img/information.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('Without sms', 200, 1500, 50, 6000, 'A lot of minutes for outgoing calls and internet', 'http://localhost:8080/img/internet.png');
INSERT INTO rate (name, cost, calls, sms, internet, description, image)
VALUES ('Introvert', 200, 200, 1000, 6000, 'Do not you like talking with people?', 'http://localhost:8080/img/paper-plane (1).png');

INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('SMS', 100, 100, 'Include 200 SMS', 'http://localhost:8080/img/smartphone (1).png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Super calls', 200, 100, 'Include 200 minutes for outgoing calls:', 'http://localhost:8080/img/users.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Mobile Internet 3 Gb', 200, 100, 'Include 3 Gb', 'http://localhost:8080/img/internet.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Space', 300, 10, 'Include 5 Gb, 200 SMS', 'http://localhost:8080/img/paper-plane (1).png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Mega mobile Internet', 300, 10, 'Include 15 Gb', 'http://localhost:8080/img/internet.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Mobile Internet 45 Gb', 400, 100, 'Include 45 Gb', 'http://localhost:8080/img/internet.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Family', 100, 10, 'Include 5 favorite numbers', 'http://localhost:8080/img/users.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Black list', 300, 10, 'You can block the user', 'http://localhost:8080/img/avatar.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('More SMS', 200, 100, 'Include 2000 SMS', 'http://localhost:8080/img/smartphone (1).png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Top calls', 300, 100, 'Include 1000 minutes for outgoing calls:', 'http://localhost:8080/img/users.png');
INSERT INTO `option` (name, cost, cost_of_connection, description, image) VALUES ('Roaming', 100, 100, 'For traveling', 'http://localhost:8080/img/route.png');

INSERT INTO rate_option (rate_id, option_id) VALUES (1, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (1, 2);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 2);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 3);
INSERT INTO rate_option (rate_id, option_id) VALUES (3, 10);
INSERT INTO rate_option (rate_id, option_id) VALUES (3, 11);
INSERT INTO rate_option (rate_id, option_id) VALUES (3, 6);
INSERT INTO rate_option (rate_id, option_id) VALUES (3, 4);
INSERT INTO rate_option (rate_id, option_id) VALUES (4, 7);
INSERT INTO rate_option (rate_id, option_id) VALUES (5, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (6, 10);
INSERT INTO rate_option (rate_id, option_id) VALUES (7, 9);
INSERT INTO rate_option (rate_id, option_id) VALUES (8, 10);
INSERT INTO rate_option (rate_id, option_id) VALUES (9, 8);

INSERT INTO contract_option (contract_id, option_id) VALUES (1, 4);
INSERT INTO contract_option (contract_id, option_id) VALUES (2, 3);
INSERT INTO contract_option (contract_id, option_id) VALUES (3, 2);

INSERT INTO role (role_name) VALUES ('ROLE_CUSTOMER');
INSERT INTO role (role_name) VALUES ('ROLE_MANAGER');

INSERT INTO compatible_options (first_id, second_id) VALUES (1 , 2);
INSERT INTO compatible_options (first_id, second_id) VALUES (2 , 3);
INSERT INTO compatible_options (first_id, second_id) VALUES (9 , 1);
INSERT INTO compatible_options (first_id, second_id) VALUES (10 , 2);

INSERT INTO incompatible_options (first_id, second_id) VALUES (3 , 4);
INSERT INTO incompatible_options (first_id, second_id) VALUES (4 , 3);

