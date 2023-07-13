-- liquibase formatted sql

-- changeset surokkha:1689243254-1
CREATE TABLE public."otp"
(
    id                   VARCHAR(255) NOT NULL,
    email                VARCHAR(255) NOT NULL,
    otp                  integer NOT NULL,
    time                 timestamp,
    CONSTRAINT otp_key PRIMARY KEY (id)
);

-- changeset surokkha:1689243254-2

ALTER table otp ALTER COLUMN otp TYPE VARCHAR(255) USING otp::VARCHAR;
