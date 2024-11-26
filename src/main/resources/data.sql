INSERT INTO T_CLIENT (id, first_name, last_name, age, phone_number, nationality)
    VALUES (-1, 'John', 'Doe', 25, '+420111222333', 'CZECH_REPUBLIC');

INSERT INTO T_ACCOUNT (id, name, balance, type, client_id)
    VALUES (-1, 'John', 1000, 'CASH', -1);

INSERT INTO T_TRANSACTION (id, amount, created_at, account_id)
    VALUES (-1, 100, '2020-01-01 00:00:00', -1);
INSERT INTO T_TRANSACTION (id, amount, created_at, account_id)
    VALUES (-2, 200, '2020-02-01 00:00:00', -2);
INSERT INTO T_TRANSACTION (id, amount, created_at, account_id)
    VALUES (-3, 300, '2020-03-01 00:00:00', -1);
INSERT INTO T_TRANSACTION (id, amount, created_at, account_id)
    VALUES (-4, 400, '2020-04-01 00:00:00', -1);
INSERT INTO T_TRANSACTION (id, amount, created_at, account_id)
    VALUES (-5, 500, '2020-05-01 00:00:00', -1);
INSERT INTO T_TRANSACTION (id, amount, created_at, account_id)
    VALUES (-6, 600, '2020-06-01 00:00:00', -1);