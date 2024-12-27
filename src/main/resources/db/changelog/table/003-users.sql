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
    password   text,
    role_id_fk integer REFERENCES PUBLIC.ROLES (role_id_pk)
)
/*
 Вспомогательный запрос для просмотра пользователей

 select * from users u inner join public.roles r on u.role_id_fk = r.role_id_pk
 */

