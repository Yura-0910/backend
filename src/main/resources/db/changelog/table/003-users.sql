--liquibase formatted sql
--changeset MazurovY:3

--For GenerationType.SEQUENCE
/*
 CREATE TABLE PUBLIC.USERS
(
    user_id_pk integer PRIMARY KEY,
    fio        text,
    email      text,
    password   text
)
 */

--For GenerationType.IDENTITY
CREATE TABLE PUBLIC.USERS
(
    user_id_pk BIGSERIAL PRIMARY KEY,
    fio        text,
    email      text,
    password   text
)