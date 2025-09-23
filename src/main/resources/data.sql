INSERT INTO T_CLIENT (id, first_name, last_name, age, phone_number, nationality)
    VALUES (-1, 'John', 'Doe', 25, '+420111222333', 'CZECH_REPUBLIC');

INSERT INTO T_ACCOUNT (id, name, balance, type, client_id) VALUES (-1, 'John', 1000, 'CASH', -1);

INSERT INTO T_LOCALITY (id, name, address, type) VALUES (-1, 'Prague Main Branch', 'Wenceslas Square 1, Prague', 'BRANCH');
INSERT INTO T_LOCALITY (id, name, address, type) VALUES (-2, 'Prague ATM Center', 'Shopping Center Palladium, Prague', 'ATM');
INSERT INTO T_LOCALITY (id, name, address, type) VALUES (-3, 'Brno Branch', 'Svobody Square 10, Brno', 'BRANCH');