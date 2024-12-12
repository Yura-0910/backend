--liquibase formatted sql
--changeset MazurovY:4

--For the case when the "id" is generated automatically via Sequence = "последовательность"
/* ... */

--For the case when the "id" is generated automatically via "BIGSERIAL"
INSERT INTO PUBLIC.TASKS
(header, description, status_id_fk, priority_id_fk, author_id_fk, executor_id_fk)
VALUES ('header1', 'description1',
        (select s.status_id_pk from PUBLIC.STATUSES s where s.status like '%в ожидании%'),
        (select p.priority_id_pk from PUBLIC.PRIORITIES p where p.priority like '%высокий%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio1%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio2%'));

INSERT INTO PUBLIC.TASKS
(header, description, status_id_fk, priority_id_fk, author_id_fk, executor_id_fk)
VALUES ('header2', 'description2',
        (select s.status_id_pk from PUBLIC.STATUSES s where s.status like '%в процессе%'),
        (select p.priority_id_pk from PUBLIC.PRIORITIES p where p.priority like '%средний%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio1%'),
        (select u.user_id_pk from PUBLIC.USERS u where u.fio like '%fio3%'));