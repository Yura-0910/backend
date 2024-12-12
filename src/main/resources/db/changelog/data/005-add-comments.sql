--liquibase formatted sql
--changeset MazurovY:5

--For the case when the "id" is generated automatically via Sequence = "последовательность"
/* ... */

--For the case when the "id" is generated automatically via "BIGSERIAL"
INSERT INTO PUBLIC.COMMENTS (content, task_id_fk, user_id_fk)
VALUES ('content1',
        (select t.task_id_pk from PUBLIC.TASKS t where t.header like '%header1%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio1%'));

INSERT INTO PUBLIC.COMMENTS (content, task_id_fk, user_id_fk)
VALUES ('content2',
        (select t.task_id_pk from PUBLIC.TASKS t where t.header like '%header1%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio2%'));

INSERT INTO PUBLIC.COMMENTS (content, task_id_fk, user_id_fk)
VALUES ('content3',
        (select t.task_id_pk from PUBLIC.TASKS t where t.header like '%header2%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio3%'));