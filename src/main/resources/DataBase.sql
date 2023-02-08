CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY UNIQUE ,
    role VARCHAR(45) not null
    );
CREATE TABLE IF NOT EXISTS extraUsersData
(
    id BIGSERIAL PRIMARY KEY,
    passport_number     VARCHAR(20),
    name           VARCHAR(45),
    lastname       VARCHAR(45),
    birthdate    DATE,
    drivingLicense VARCHAR(45),
    phone          VARCHAR(10),
    registerDate   DATE
    );

CREATE TABLE IF NOT EXISTS users
(
    id               BIGSERIAL PRIMARY KEY,
    login            VARCHAR(45) not null,
    password         VARCHAR(45) not null,
    idRole             INT         not null,
    idExtraUsersData BIGINT,
    FOREIGN KEY (idRole) REFERENCES roles (id),
    FOREIGN KEY (idExtraUsersData) REFERENCES extraUsersData (id)
    );

CREATE TABLE IF NOT EXISTS carsMarks
(
    id   SERIAL PRIMARY KEY,
    mark VARCHAR(45)
    );
CREATE TABLE IF NOT EXISTS carsModels
(
    id     SERIAL PRIMARY KEY,
    model  VARCHAR(45) not null,
    idMark INT         not null,
    FOREIGN KEY (idMark) REFERENCES carsMarks (id)
    );

CREATE TABLE IF NOT EXISTS cars
(
    id          SERIAL PRIMARY KEY,
    carNumber   VARCHAR(20) not null,
    price       INT         not null,
    limitations VARCHAR(200),
    idImage     INT,
    idModel     INT         not null,
    FOREIGN KEY (idModel) REFERENCES carsModels (id)
    );
CREATE TABLE IF NOT EXISTS orders
(
    id           BIGSERIAL PRIMARY KEY,
    startDate    DATE    not null,
    finishDate   DATE    not null,
    status       BOOLEAN not null,
    idCar        INT     not null,
    idUser       BIGINT     not null,
    adminLogin VARCHAR(200),
    refuseReason VARCHAR(200),
    FOREIGN KEY (idCar) REFERENCES cars (id),
    FOREIGN KEY (idUser) REFERENCES users (id)
    );

INSERT INTO roles (id, role) VALUES (1, 'admin'), (2, 'user');
INSERT INTO users (login, password, idRole) VALUES ('maks', 1234, 1), ('liza', 4321, 2);
SELECT * FROM users;
