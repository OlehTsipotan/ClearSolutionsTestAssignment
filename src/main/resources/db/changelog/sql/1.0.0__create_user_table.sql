CREATE TABLE addresses
(
    id       UUID PRIMARY KEY NOT NULL,
    street   VARCHAR(255)     NOT NULL,
    city     VARCHAR(255)     NOT NULL,
    state    VARCHAR(255)     NOT NULL,
    country  VARCHAR(255)     NOT NULL,
    zip_code VARCHAR(5)       NOT NULL
);

CREATE TABLE users
(
    id            UUID PRIMARY KEY NOT NULL,
    email         VARCHAR(255)     NOT NULL,
    first_name    VARCHAR(255)     NOT NULL,
    last_name     VARCHAR(255)     NOT NULL,
    date_of_birth date             NOT NULL,
    phone_number  VARCHAR(10),
    address_id    UUID,

    FOREIGN KEY (address_id) REFERENCES addresses (id)
);
