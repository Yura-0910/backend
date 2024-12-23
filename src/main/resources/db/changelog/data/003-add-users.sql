--liquibase formatted sql
--changeset MazurovY:3

--For the case when the "id" is generated automatically via Sequence = "последовательность"
/*
INSERT INTO PUBLIC.USERS (user_id_pk, fio, email, password)
VALUES (nextval('PUBLIC.последовательность'),'fio1', 'email1@ya.ru', '{noop}pwd1');

INSERT INTO PUBLIC.USERS (user_id_pk, fio, email, password)
VALUES (nextval('PUBLIC.последовательность'),'fio2', 'email2@ya.ru', '{noop}pwd2');

INSERT INTO PUBLIC.USERS (user_id_pk, fio, email, password)
VALUES (nextval('PUBLIC.последовательность'),'fio3', 'email3@ya.ru', '{noop}pwd3');
*/

--For the case when the "id" is generated automatically via "BIGSERIAL"
INSERT INTO PUBLIC.USERS (fio, email, password, role_id_fk)
VALUES ('fio1', 'email1@ya.ru', '{noop}pwd1',
        (select r.role_id_pk from PUBLIC.ROLES r where r.role like '%USER%'));

INSERT INTO PUBLIC.USERS (fio, email, password, role_id_fk)
VALUES ('fio2', 'email2@ya.ru', '{noop}pwd2',
        (select r.role_id_pk from PUBLIC.ROLES r where r.role like '%ADMIN%'));

INSERT INTO PUBLIC.USERS (fio, email, password, role_id_fk)
VALUES ('fio3', 'email3@ya.ru', '{noop}pwd3',
        (select r.role_id_pk from PUBLIC.ROLES r where r.role like '%MODERATOR%'));