CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY UNIQUE ,
    role VARCHAR(45) not null
    );

INSERT INTO roles(id, role) VALUES (1, 'admin'), (2, 'user')