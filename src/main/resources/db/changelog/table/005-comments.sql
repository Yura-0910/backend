--liquibase formatted sql
--changeset MazurovY:4

--For GenerationType.SEQUENCE
/*
 CREATE TABLE PUBLIC.COMMENTS(
    comment_id_pk integer PRIMARY KEY,
    content text,
    task_id_fk integer REFERENCES PUBLIC.TASKS (task_id_pk),
    user_id_fk integer REFERENCES PUBLIC.USERS (user_id_pk)
)
 */

--For GenerationType.IDENTITY
CREATE TABLE PUBLIC.COMMENTS
(
    comment_id_pk BIGSERIAL PRIMARY KEY,
    content       text,
    task_id_fk    integer REFERENCES PUBLIC.TASKS (task_id_pk),
    user_id_fk    integer REFERENCES PUBLIC.USERS (user_id_pk)
)