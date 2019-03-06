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
  registration_date       DATE,
  passport_number         VARCHAR(255) UNIQUE,
  passport_issued_when    DATE,
  passport_issued_by_whom VARCHAR(255),
  address_id              BIGINT DEFAULT NULL REFERENCES address (id),
  first_name              VARCHAR(255),
  last_name               VARCHAR(255),
  email                   VARCHAR(255),
  comment                 VARCHAR(255),
  password                VARCHAR(255)        NOT NULL,
  role_id                 BIGINT REFERENCES role (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE `option` (
  id                  SERIAL PRIMARY KEY,
  name                VARCHAR(255),
  cost                INT UNSIGNED,
  cost_of_connection  INT UNSIGNED,
  description         VARCHAR(255),
  image         VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE rate (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255),
  cost        INT UNSIGNED,
  calls       INT UNSIGNED,
  sms         INT UNSIGNED,
  internet    INT UNSIGNED,
  description VARCHAR(255),
  image         VARCHAR(255)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE contract (
  id          SERIAL PRIMARY KEY,
  number      VARCHAR(20) NOT NULL,
  creation_date DATE,
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

CREATE TABLE role (
  id        SERIAL,
  role_name VARCHAR(15),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE incompatible_options (
  first_id BIGINT REFERENCES `option` (id),
  second_id   BIGINT REFERENCES `option` (id),
  PRIMARY KEY (first_id, second_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE compatible_options (
first_id BIGINT REFERENCES `option` (id),
second_id   BIGINT REFERENCES `option` (id),
PRIMARY KEY (first_id, second_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


