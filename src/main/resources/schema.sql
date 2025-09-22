-- Create table T_CLIENT with id, first_ame, last_name and age columns
CREATE TABLE T_CLIENT (
    id IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    age INTEGER NOT NULL,
    phone_number VARCHAR(250) NOT NULL,
    nationality VARCHAR(250) NOT NULL
);

CREATE TABLE T_ACCOUNT (
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    balance INTEGER NOT NULL,
    type VARCHAR(250) NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES T_CLIENT(id)
);

CREATE TABLE T_LOCALITY (
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    address VARCHAR(250),
    city VARCHAR(250)
);

CREATE TABLE T_TRANSACTION (
    id IDENTITY NOT NULL PRIMARY KEY,
    amount INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    account_id INT NOT NULL,
    type VARCHAR(250),
    locality_id INT,
    FOREIGN KEY (account_id) REFERENCES T_ACCOUNT(id),
    FOREIGN KEY (locality_id) REFERENCES T_LOCALITY(id)
);
