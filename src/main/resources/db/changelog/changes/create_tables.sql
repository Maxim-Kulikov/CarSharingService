CREATE TABLE cars_marks
(
    id   SERIAL PRIMARY KEY,
    mark VARCHAR(45)
    );

CREATE TABLE cars_models
(
    id     SERIAL PRIMARY KEY,
    model  VARCHAR(45) not null,
    id_mark INT         not null,
    FOREIGN KEY (id_mark) REFERENCES cars_marks (id)
    );

CREATE TABLE cars
(
    id          SERIAL PRIMARY KEY,
    car_number   VARCHAR(20) not null,
    price       INT         not null,
    limitations VARCHAR(200),
    id_image     INT,
    id_model     INT         not null,
    FOREIGN KEY (id_model) REFERENCES cars_models (id)
    );

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY UNIQUE ,
    role VARCHAR(45) not null
    );

CREATE TABLE extra_users_data
(
    id BIGSERIAL PRIMARY KEY,
    passport_number     VARCHAR(20),
    name           VARCHAR(45),
    lastname       VARCHAR(45),
    birthdate    DATE,
    driving_license VARCHAR(45),
    phone          VARCHAR(10),
    register_date   DATE
    );

CREATE TABLE users
(
    id               BIGSERIAL PRIMARY KEY,
    login            VARCHAR(45) not null,
    password         VARCHAR(45) not null,
    id_role             INT         not null,
    id_extra_users_data BIGINT,
    FOREIGN KEY (id_role) REFERENCES roles (id),
    FOREIGN KEY (id_extra_users_data) REFERENCES extra_users_data (id)
    );

CREATE TABLE orders
(
    id           BIGSERIAL PRIMARY KEY,
    start_date    DATE    not null,
    finish_date   DATE    not null,
    status       BOOLEAN not null,
    id_car        INT     not null,
    id_user       BIGINT     not null,
    id_admin BIGINT,
    price BIGINT not null,
    refuse_reason VARCHAR(200),
    FOREIGN KEY (id_car) REFERENCES cars (id),
    FOREIGN KEY (id_user) REFERENCES users (id),
    FOREIGN KEY (id_admin) REFERENCES users (id)
    );

INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

INSERT INTO extra_users_data (passport_number, name, lastname, birthdate, driving_license, phone, register_date) VALUES (null, null, null, null, null, null, null);

INSERT INTO users (login, password, id_role, id_extra_users_data) VALUES ('admin', 'pass', 1, 1);