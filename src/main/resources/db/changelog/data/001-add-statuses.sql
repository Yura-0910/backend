--liquibase formatted sql
--changeset MazurovY:1

--For the case when the "id" is generated automatically via Sequence = "последовательность"
/*
INSERT INTO PUBLIC.STATUSES (status_id_pk, status)
VALUES (nextval('PUBLIC.последовательность'),'в ожидании');
INSERT INTO PUBLIC.STATUSES (status_id_pk, status)
VALUES (nextval('PUBLIC.последовательность'),'в процессе');
INSERT INTO PUBLIC.STATUSES (status_id_pk, status)
VALUES (nextval('PUBLIC.последовательность'), 'завершено');
 */

--For the case when the "id" is generated automatically via "BIGSERIAL"
INSERT INTO PUBLIC.STATUSES (status) VALUES ('в ожидании');
INSERT INTO PUBLIC.STATUSES (status) VALUES ('в процессе');
INSERT INTO PUBLIC.STATUSES (status) VALUES ('завершено');