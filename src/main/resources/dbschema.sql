drop schema ecare;

CREATE TABLE address (
  id SERIAL PRIMARY KEY,
  street VARCHAR (255),
  city VARCHAR (255),
  country VARCHAR (255),
  zipcode VARCHAR (255),
  house_number INT
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE customer (
  id SERIAL PRIMARY KEY,
  birth_date DATE,
  passport VARCHAR (255) UNIQUE,
  address_id BIGINT DEFAULT NULL REFERENCES address(id),
  user_id BIGINT REFERENCES user (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  role VARCHAR (255),
  user_id BIGINT REFERENCES user (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE user (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR (255),
  last_name VARCHAR (255),
  email VARCHAR (255),
  password VARCHAR (255)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `option` (
  id SERIAL PRIMARY KEY,
  name VARCHAR (255),
  cost INT UNSIGNED,
  cost_of_connection INT UNSIGNED,
  description VARCHAR (255)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE rate (
  id SERIAL PRIMARY KEY,
  name VARCHAR (255),
  cost INT UNSIGNED,
  description VARCHAR (255)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE contract (
  id SERIAL PRIMARY KEY,
  number INT NOT NULL,
  status ENUM('AVAILABLE', 'BLOCKED_BY_THE_CUSTOMER', 'BLOCKED_BY_AN_EMPLOYEE'),
  customer_id BIGINT REFERENCES customer (id),
  rate_id BIGINT REFERENCES rate (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE contract_option (
  contract_id BIGINT REFERENCES contract (id),
  option_id BIGINT REFERENCES `option` (id),
  PRIMARY KEY (contract_id, option_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE rate_option (
  rate_id BIGINT REFERENCES rate (id),
  option_id BIGINT REFERENCES `option` (id),
  PRIMARY KEY (rate_id, option_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

