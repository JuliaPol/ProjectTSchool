INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password) VALUES ('jpi287', '123456', 1, 'Julia',
                                           'Polushina', 'jpi287@mail.ru', '1234');
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password) VALUES ('ivan_88', '654321', 2, 'Ivan',
                                           'Ivanov', 'ivan111@mail.ru', '4321');
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password) VALUES ('sun123', '63663', 3, 'John',
                                           'Lenon', 'lenon@gmail.com', '123');

INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('main street', 'Saint-Petersburg', 'Russia', '1111', 3);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('street', 'Saint-Petersburg', 'Russia', '1111', 4);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('street2', 'Saint-Petersburg', 'Russia', '11131', 6);

INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('88005353', 'AVAILABLE', 1, 1);
INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('88005354', 'AVAILABLE', 1, 2);

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

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (3, 1);

