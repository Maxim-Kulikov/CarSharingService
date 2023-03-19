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
