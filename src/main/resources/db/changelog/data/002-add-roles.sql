--liquibase formatted sql
--changeset MazurovY:6

--For the case when the "id" is generated automatically via Sequence = "последовательность"
/* ... */

--For the case when the "id" is generated automatically via "BIGSERIAL"
INSERT INTO PUBLIC.ROLES (role) VALUES ('USER');
INSERT INTO PUBLIC.ROLES (role) VALUES ('ADMIN');
INSERT INTO PUBLIC.ROLES (role) VALUES ('MODERATOR');