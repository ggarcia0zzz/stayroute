CREATE TABLE users (
                       id          BIGSERIAL PRIMARY KEY,
                       first_name  VARCHAR(80)  NOT NULL,
                       last_name   VARCHAR(80)  NOT NULL,
                       email       VARCHAR(255) NOT NULL UNIQUE,
                       password    VARCHAR(255) NOT NULL,
                       phone       VARCHAR(30),
                       role        VARCHAR(20)  NOT NULL DEFAULT 'GUEST',
                       active      BOOLEAN      NOT NULL DEFAULT TRUE,
                       created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
                       updated_at  TIMESTAMP
);