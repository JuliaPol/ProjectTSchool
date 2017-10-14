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
VALUES ('main street', 'Saint-Petersburg', 'Russia', '1111', 3);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('street', 'Saint-Petersburg', 'Russia', '1111', 4);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('street2', 'Saint-Petersburg', 'Russia', '11131', 6);

INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('88005353', 'AVAILABLE', 1, 1);
INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('88005354', 'BLOCKED_BY_AN_EMPLOYEE', 1, 2);

INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('good tariff', 300, 1000 , 1000, 1000 , 'very good');
INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('simple tariff', 100, 100, 100, 100, 'good');

INSERT INTO `option` (name, cost, cost_of_connection, description, compatible_option, incompatible_option) VALUES ('good option', 100, 100, 'very good', 2, 3);
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('stupid option', 200, 100, 'bad');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('stupid option2', 200, 100, 'bad1');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('stupid option3', 200, 100, 'bad2');

INSERT INTO rate_option (rate_id, option_id) VALUES (1, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (1, 2);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 2);

INSERT INTO contract_option (contract_id, option_id) VALUES (1, 4);
INSERT INTO contract_option (contract_id, option_id) VALUES (2, 3);

INSERT INTO role (role_name) VALUES ('ROLE_CUSTOMER');
INSERT INTO role (role_name) VALUES ('ROLE_MANAGER');


