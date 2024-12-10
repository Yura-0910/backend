--liquibase formatted sql
--changeset MazurovY:2

--For GenerationType.SEQUENCE
/*CREATE TABLE PUBLIC.PRIORITIES
(
    priority_id_pk   integer PRIMARY KEY,
    priority         text
);
 */

--For GenerationType.IDENTITY
CREATE TABLE PUBLIC.PRIORITIES
(
    priority_id_pk   BIGSERIAL PRIMARY KEY,
    priority         text
);