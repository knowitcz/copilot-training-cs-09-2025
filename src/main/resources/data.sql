INSERT INTO T_CLIENT (id, first_name, last_name, age, phone_number, nationality)
    VALUES (-1, 'John', 'Doe', 25, '+420111222333', 'CZECH_REPUBLIC');

INSERT INTO T_ACCOUNT (id, name, balance, type, client_id) VALUES (-1, 'John', 1000, 'CASH', -1);

INSERT INTO T_LOCALITY (id, name, address, city) VALUES (-1, 'Main Branch', 'Main Street 123', 'Prague');
INSERT INTO T_LOCALITY (id, name, address, city) VALUES (-2, 'ATM Central Plaza', 'Central Plaza 1', 'Prague');
INSERT INTO T_LOCALITY (id, name, address, city) VALUES (-3, 'Branch Downtown', 'Downtown Ave 456', 'Brno');