DROP TABLE rule, address, contract, contract_option, `option`, rate_option, rate, user, role, user_role;

CREATE TABLE address (
  id           SERIAL PRIMARY KEY,
  street       VARCHAR(255),
  city         VARCHAR(255),
  country      VARCHAR(255),
  zipcode      VARCHAR(255),
  house_number INT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user (
  id                      SERIAL PRIMARY KEY,
  login                   VARCHAR(255) UNIQUE NOT NULL,
  birth_date              DATE,
  passport_number         VARCHAR(255) UNIQUE,
  passport_issued_when    DATE,
  passport_issued_by_whom VARCHAR(255),
  address_id              BIGINT DEFAULT NULL REFERENCES address (id),
  first_name              VARCHAR(255),
  last_name               VARCHAR(255),
  email                   VARCHAR(255),
  password                VARCHAR(255)        NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `option` (
  id                 SERIAL PRIMARY KEY,
  name               VARCHAR(255),
  cost               INT UNSIGNED,
  cost_of_connection INT UNSIGNED,
  description        VARCHAR(255),
  rate_id            BIGINT REFERENCES rate (id),
  contract_id        BIGINT REFERENCES contract (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE rate (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255),
  cost        INT UNSIGNED,
  calls       ENUM ('SELDOM', 'SOMETIMES', 'ALWAYS'),
  sms         ENUM ('SELDOM', 'SOMETIMES', 'ALWAYS'),
  internet    ENUM ('SELDOM', 'SOMETIMES', 'ALWAYS'),
  description VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE contract (
  id          SERIAL PRIMARY KEY,
  number      INT NOT NULL,
  status      ENUM ('AVAILABLE', 'BLOCKED_BY_THE_CUSTOMER', 'BLOCKED_BY_AN_EMPLOYEE'),
  customer_id BIGINT REFERENCES user (id),
  rate_id     BIGINT REFERENCES rate (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE contract_option (
  contract_id BIGINT REFERENCES contract (id),
  option_id   BIGINT REFERENCES `option` (id),
  PRIMARY KEY (contract_id, option_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE rate_option (
  rate_id   BIGINT REFERENCES rate (id),
  option_id BIGINT REFERENCES `option` (id),
  PRIMARY KEY (rate_id, option_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_role (
  id SERIAL,
  role_name VARCHAR(15),
  user_id BIGINT REFERENCES user (id),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE rule (
  id SERIAL,
  option_1 BIGINT,
  option_2  BIGINT,
  compatibility BOOLEAN NOT NULL,
  PRIMARY KEY (option_1, option_2)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password) VALUES ('jpi287', '123456', 1, 'Julia',
                                           'Polushina', 'jpi287@mail.ru', '1234');
INSERT INTO user (login, passport_number, address_id, first_name, last_name,
                  email, password) VALUES ('ivan_88', '654321', 2, 'Ivan',
                                           'Ivanov', 'ivan111@mail.ru', '4321');

INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('main street', 'Saint-Petersburg', 'Russia', '1111', 3);
INSERT INTO address (street, city, country, zipcode, house_number)
VALUES ('street', 'Saint-Petersburg', 'Russia', '1111', 4);

INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('88005353', 'AVAILABLE', 1, 1);
INSERT INTO contract (number, status, customer_id, rate_id) VALUES ('88005354', 'AVAILABLE', 1, 2);

INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('good tariff', 300, 'ALWAYS', 'ALWAYS', 'ALWAYS', 'very good');
INSERT INTO rate (name, cost, calls, sms, internet, description)
VALUES ('simple tariff', 100, 'SELDOM', 'SELDOM', 'SELDOM', 'good');

INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('good option', 100, 100, 'very good');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('stupid option', 200, 100, 'bad');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('stupid option2', 200, 100, 'bad1');
INSERT INTO `option` (name, cost, cost_of_connection, description) VALUES ('stupid option3', 200, 100, 'bad2');

INSERT INTO rate_option (rate_id, option_id) VALUES (1, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (1, 2);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 1);
INSERT INTO rate_option (rate_id, option_id) VALUES (2, 2);

INSERT INTO contract_option (contract_id, option_id) VALUES (1, 4);
INSERT INTO contract_option (contract_id, option_id) VALUES (2, 3);

INSERT INTO user_role (user_id, role_name) VALUES (1, 'ROLE_CUSTOMER');
INSERT INTO user_role (user_id, role_name) VALUES (2, 'ROLE_MANAGER');

INSERT INTO rule (option_1, option_2, compatibility) VALUES (1, 2, TRUE);
