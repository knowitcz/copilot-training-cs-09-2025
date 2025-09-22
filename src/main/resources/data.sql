INSERT INTO T_CLIENT (id, first_name, last_name, age, phone_number, nationality)
    VALUES (CLIENT_SEQ.NEXTVAL, 'John', 'Doe', 25, '+420111222333', 'CZECH_REPUBLIC');

INSERT INTO T_ACCOUNT (id, name, balance, type, client_id) VALUES (ACCOUNT_SEQ.NEXTVAL, 'John', 1000, 'CASH', 1);