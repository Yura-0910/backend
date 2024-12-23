--liquibase formatted sql
--changeset MazurovY:6

--For GenerationType.SEQUENCE

--For GenerationType.IDENTITY
CREATE TABLE PUBLIC.ROLES
(
    role_id_pk BIGSERIAL PRIMARY KEY,
    role text UNIQUE
);