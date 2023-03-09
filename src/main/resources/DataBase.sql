CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY UNIQUE ,
    role VARCHAR(45) not null
    );
CREATE TABLE IF NOT EXISTS extra_users_data
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

CREATE TABLE IF NOT EXISTS users
(
    id               BIGSERIAL PRIMARY KEY,
    login            VARCHAR(45) not null,
    password         VARCHAR(45) not null,
    id_role             INT         not null,
    id_extra_users_data BIGINT,
    FOREIGN KEY (id_role) REFERENCES roles (id),
    FOREIGN KEY (id_extra_users_data) REFERENCES extra_users_data (id)
    );

CREATE TABLE IF NOT EXISTS cars_marks
(
    id   SERIAL PRIMARY KEY,
    mark VARCHAR(45)
    );
CREATE TABLE IF NOT EXISTS cars_models
(
    id     SERIAL PRIMARY KEY,
    model  VARCHAR(45) not null,
    id_mark INT         not null,
    FOREIGN KEY (id_mark) REFERENCES cars_marks (id)
    );

CREATE TABLE IF NOT EXISTS cars
(
    id          SERIAL PRIMARY KEY,
    car_number   VARCHAR(20) not null,
    price       INT         not null,
    limitations VARCHAR(200),
    id_image     INT,
    id_model     INT         not null,
    FOREIGN KEY (id_model) REFERENCES cars_models (id)
    );
CREATE TABLE IF NOT EXISTS orders
(
    id           BIGSERIAL PRIMARY KEY,
    start_date    DATE    not null,
    finish_date   DATE    not null,
    status       BOOLEAN not null,
    id_car        INT     not null,
    id_user       BIGINT     not null,
    admin_login VARCHAR(200),
    refuse_reason VARCHAR(200),
    FOREIGN KEY (id_car) REFERENCES cars (id),
    FOREIGN KEY (id_user) REFERENCES users (id)
    );

/*SELECT * FROM users;
*/
INSERT INTO roles(id, role) VALUES (1, 'admin'), (2, 'user');

INSERT INTO users(login, password, id_role, id_extra_users_data) VALUES ('max', '1234', 2, 1);

INSERT INTO users(login, password, id_role) VALUES ('liza', '4321', 1);

INSERT INTO extra_users_data(passport_number, name, lastname, birthdate, driving_license, phone, register_date) VALUES ('HB1234', 'Maxim', 'Kulikov', '2004-03-18', '446466789', '7644167', '2007-09-18');

/*UPDATE users SET id_extra_users_data = 1 WHERE users.login = 'maks'*/

INSERT INTO cars_marks(mark) VALUES ('BMW'), ('MERCEDES'), ('VOLVO'), ('VOLKSWAGEN');

INSERT INTO cars_models(model, id_mark) VALUES ('X5', 1), ('X6', 1), ('M5', 1), ('GLE', 2), ('GLE coupe', 2), ('S-class', 2), ('XC-70', 3), ('S80', 3), ('passat', 4), ('touareg', 4);