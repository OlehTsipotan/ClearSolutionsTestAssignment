INSERT INTO addresses (id, street, city, state, country, zip_code)
VALUES ('11111111-1111-1111-1111-111111111111', '123 Main St', 'City1', 'State1', 'Country1', '12345'),
       ('22222222-2222-2222-2222-222222222222', '456 Elm St', 'City2', 'State2', 'Country2', '23456'),
       ('33333333-3333-3333-3333-333333333333', '789 Oak St', 'City3', 'State3', 'Country3', '34567'),
       ('44444444-4444-4444-4444-444444444444', '321 Pine St', 'City4', 'State4', 'Country4', '45678'),
       ('55555555-5555-5555-5555-555555555555', '654 Maple St', 'City5', 'State5', 'Country5', '56789'),
       ('66666666-6666-6666-6666-666666666666', '987 Birch St', 'City6', 'State6', 'Country6', '67890'),
       ('77777777-7777-7777-7777-777777777777', '555 Cedar St', 'City7', 'State7', 'Country7', '78901'),
       ('88888888-8888-8888-8888-888888888888', '777 Walnut St', 'City8', 'State8', 'Country8', '89012'),
       ('99999999-9999-9999-9999-999999999999', '999 Pineapple St', 'City9', 'State9', 'Country9', '90123'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '123 Orange St', 'City10', 'State10', 'Country10', '01234');

INSERT INTO users (id, email, first_name, last_name, date_of_birth, phone_number, address_id)
VALUES ('00000000-0000-0000-0000-000000000001', 'user1@example.com', 'User', 'One', '1990-01-01', '1234567890',
        '11111111-1111-1111-1111-111111111111'),
       ('00000000-0000-0000-0000-000000000002', 'user2@example.com', 'User', 'Two', '1991-02-02', '2345678901',
        '22222222-2222-2222-2222-222222222222'),
       ('00000000-0000-0000-0000-000000000003', 'user3@example.com', 'User', 'Three', '1992-03-03', '3456789012',
        '33333333-3333-3333-3333-333333333333'),
       ('00000000-0000-0000-0000-000000000004', 'user4@example.com', 'User', 'Four', '1993-04-04', '4567890123',
        '44444444-4444-4444-4444-444444444444'),
       ('00000000-0000-0000-0000-000000000005', 'user5@example.com', 'User', 'Five', '1994-05-05', '5678901234',
        '55555555-5555-5555-5555-555555555555'),
       ('00000000-0000-0000-0000-000000000006', 'user6@example.com', 'User', 'Six', '1995-06-06', '6789012345',
        '66666666-6666-6666-6666-666666666666'),
       ('00000000-0000-0000-0000-000000000007', 'user7@example.com', 'User', 'Seven', '1996-07-07', '7890123456',
        '77777777-7777-7777-7777-777777777777'),
       ('00000000-0000-0000-0000-000000000008', 'user8@example.com', 'User', 'Eight', '1997-08-08', '8901234567',
        '88888888-8888-8888-8888-888888888888'),
       ('00000000-0000-0000-0000-000000000009', 'user9@example.com', 'User', 'Nine', '1998-09-09', '9012345678',
        '99999999-9999-9999-9999-999999999999'),
       ('00000000-0000-0000-0000-000000000010', 'user10@example.com', 'User', 'Ten', '1999-10-10', '0123456789',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
       ('00000000-0000-0000-0000-000000000011', 'user11@example.com', 'User', 'Eleven', '2000-11-11', '1234567890',
        NULL),
       ('00000000-0000-0000-0000-000000000012', 'user12@example.com', 'User', 'Twelve', '2001-12-12', '2345678901',
        NULL),
       ('00000000-0000-0000-0000-000000000013', 'user13@example.com', 'User', 'Thirteen', '2002-01-13', '3456789012',
        NULL),
       ('00000000-0000-0000-0000-000000000014', 'user14@example.com', 'User', 'Fourteen', '2003-02-14', '4567890123',
        NULL),
       ('00000000-0000-0000-0000-000000000015', 'user15@example.com', 'User', 'Fifteen', '2004-03-15', '5678901234',
        NULL);


