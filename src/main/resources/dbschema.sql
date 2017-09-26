CREATE TABLE user (
  id SERIAL NOT NULL PRIMARY KEY,
  first_name VARCHAR (255),
  last_name VARCHAR (255),
  date DATE ,
  passport VARCHAR (255) UNIQUE,


  email VARCHAR (255),
  password VARCHAR (255)


) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE address (
  id SERIAL NOT NULL PRIMARY KEY,
  street VARCHAR (255),
  city VARCHAR (255),
  country VARCHAR (255),
  zipcode VARCHAR (255),
  house_number INT,
  passport VARCHAR (255) UNIQUE,


) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE option (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR (255),
  cost INT UNSIGNED,
  cost_of_connection INT UNSIGNED,
  description VARCHAR (255)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;