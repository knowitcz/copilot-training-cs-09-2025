INSERT INTO T_CLIENT (id, first_name, last_name, age, phone_number, nationality)
    VALUES (-1, 'John', 'Doe', 25, '+420111222333', 'CZECH_REPUBLIC');

INSERT INTO T_ACCOUNT (id, name, balance, type, client_id) VALUES (-1, 'John', 1000, 'CASH', -1);