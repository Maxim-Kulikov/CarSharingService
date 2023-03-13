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
