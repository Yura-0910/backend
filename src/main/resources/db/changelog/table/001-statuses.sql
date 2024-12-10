--liquibase formatted sql
--changeset MazurovY:1

--For GenerationType.SEQUENCE
/*
CREATE TABLE PUBLIC.STATUSES
(
    status_id_pk  integer PRIMARY KEY,
    status        text
);
 */

--For GenerationType.IDENTITY
CREATE TABLE PUBLIC.STATUSES
(
    status_id_pk  BIGSERIAL PRIMARY KEY,
    status        text
);