INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id) VALUES ('jpi287', '123456', 1, 'Julia',
                                           'Polushina', 'jpi287@mail.ru', '$2a$04$Bc845c8WU6L2aFCyQ/sHkuCc5BMCL6pQyDnfrNXVOV4V8OUQs7d8O', 1);
-- password:1234
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id) VALUES ('ivan_88', '654321', 2, 'Ivan',
                                           'Ivanov', 'ivan111@mail.ru', '$2a$04$2oCF0iLWQOdhEwufBwVtl.zZ5x7YTA/gIpcCNsDAizhiDWut8Sn5.', 2);
-- password:4321
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password, role_id) VALUES ('sun123', '63663', 3, 'John',
                                           'Lenon', 'lenon@gmail.com', '$2a$04$Dgy04povDN7eLKQ19q10O.r5.qbqo5OlpR7epP5pktsrCck5hOPXK', 1);
-- password:123

INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Torzhkovskaya', 'Saint-Petersburg', 'Russia', '1111', 3);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Lenina', 'Saint-Petersburg', 'Russia', '1111', 4);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('Korablestroiteley', 'Saint-Petersburg', 'Russia', '11131', 6);

INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('1401401405123', 'AVAILABLE', 1, 1);
INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('1401401405124', 'BLOCKED_BY_AN_EMPLOYEE', 1, 2);
INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('1401401405125', 'AVAILABLE', 3, 3);

INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('For ladies', 300, 1000 , 1000, 1000 , 'Simple, wonderful tariff');
INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('All in one', 300, 1000, 1000, 1000, 'All inclusive');
INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('Premium', 1500, 1000, 1000, 1000, 'The most expensive tariff');

INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('SMS', 100, 100, 'Include 200 SMS');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('Super calls', 200, 100, 'Include 200 minutes for outgoing calls:');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('Mobile Internet 3 Gb', 200, 100, 'Include 3 Gb');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('Space', 300, 10, 'Include 5 Gb, 200 SMS');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('Mega mobile Internet', 300, 10, 'Include 15 Gb');

INSERT INTO rate_option (rate_id, option_id) VALUES (1, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (1, 2);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 2);
INSERT INTO rate_option (rate_id, option_id) VALUES (3, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (3, 4);

INSERT INTO contract_option (contract_id, option_id) VALUES (1, 4);
INSERT INTO contract_option (contract_id, option_id) VALUES (2, 3);
INSERT INTO contract_option (contract_id, option_id) VALUES (3, 2);

INSERT INTO role (role_name) VALUES ('ROLE_CUSTOMER');
INSERT INTO role (role_name) VALUES ('ROLE_MANAGER');

INSERT INTO compatible_options (first_id, second_id) VALUES (1 , 2);
INSERT INTO compatible_options (first_id, second_id) VALUES (2 , 3);

INSERT INTO incompatible_options (first_id, second_id) VALUES (3 , 4);
INSERT INTO incompatible_options (first_id, second_id) VALUES (4 , 3);

