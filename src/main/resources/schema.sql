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

CREATE TABLE T_TRANSACTION (
    id IDENTITY NOT NULL PRIMARY KEY,
    amount INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    account_id INT NOT NULL,
    FOREIGN KEY (account_id) REFERENCES T_ACCOUNT(id)
);

CREATE VIEW V_TRANSACTION_DETAILS AS
SELECT
    tr.id AS transaction_id,
    tr.amount,
    tr.created_at,
    cl.id AS client_id,
    cl.first_name,
    cl.last_name,
    ac.id AS account_id,
    ac.name AS account_name
FROM
    T_TRANSACTION tr
        JOIN
    T_ACCOUNT ac ON tr.account_id = ac.id
        JOIN
    T_CLIENT cl ON ac.client_id = cl.id;

CREATE VIEW V_CLIENT_TRANSACTION_SUMMARY AS
SELECT
    cl.id AS client_id,
    cl.first_name,
    cl.last_name,
    COUNT(tr.id) AS transaction_count,
    SUM(tr.amount) AS total_amount
FROM
    T_CLIENT cl
        LEFT JOIN
    T_ACCOUNT ac ON cl.id = ac.client_id
        LEFT JOIN
    T_TRANSACTION tr ON ac.id = tr.account_id
GROUP BY
    cl.id, cl.first_name, cl.last_name;

CREATE VIEW V_LAST_ACCOUNT_TRANSACTIONS AS
SELECT
    ac.id AS account_id,
    ac.name AS account_name,
    MAX(tr.created_at) AS last_transaction_date,
    tr.amount AS last_transaction_amount
FROM
    T_ACCOUNT ac
        LEFT JOIN
    T_TRANSACTION tr ON ac.id = tr.account_id
GROUP BY
    ac.id, ac.name, tr.amount;

CREATE VIEW V_LAST_2_MONTHS_TRANSACTIONS AS
SELECT
    cl.id AS client_id,
    cl.first_name,
    cl.last_name,
    ac.id AS account_id,
    ac.name AS account_name,
    tr.id AS transaction_id,
    tr.amount,
    tr.created_at
FROM
    T_CLIENT cl
        JOIN
    T_ACCOUNT ac ON cl.id = ac.client_id
        JOIN
    T_TRANSACTION tr ON ac.id = tr.account_id
WHERE
        tr.created_at >= CURRENT_DATE() - INTERVAL '2' MONTH;

CREATE VIEW V_LARGEST_TRANSACTIONS AS
SELECT
    tr.id AS transaction_id,
    tr.amount,
    tr.created_at,
    ac.id AS account_id,
    ac.name AS account_name,
    cl.id AS client_id,
    cl.first_name,
    cl.last_name
FROM
    T_TRANSACTION tr
        JOIN
    T_ACCOUNT ac ON tr.account_id = ac.id
        JOIN
    T_CLIENT cl ON ac.client_id = cl.id
ORDER BY
    tr.amount DESC
LIMIT 10;
