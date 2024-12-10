--liquibase formatted sql
--changeset MazurovY:2

--For the case when the "id" is generated automatically via Sequence = "последовательность"
/*
INSERT INTO PUBLIC.PRIORITIES (priority_id_pk, priority)
VALUES (nextval('PUBLIC.последовательность'),'высокий');

INSERT INTO PUBLIC.PRIORITIES (priority_id_pk, priority)
VALUES (nextval('PUBLIC.последовательность'),'средний');

INSERT INTO PUBLIC.PRIORITIES (priority_id_pk, priority)
VALUES (nextval('PUBLIC.последовательность'),'низкий');
*/

--For the case when the "id" is generated automatically via "BIGSERIAL"
INSERT INTO PUBLIC.PRIORITIES (priority) VALUES ('высокий');
INSERT INTO PUBLIC.PRIORITIES (priority) VALUES ('средний');
INSERT INTO PUBLIC.PRIORITIES (priority) VALUES ('низкий');