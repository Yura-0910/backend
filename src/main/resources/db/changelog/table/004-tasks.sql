--liquibase formatted sql
--changeset MazurovY:4

--For GenerationType.SEQUENCE
/*
CREATE TABLE PUBLIC.TASKS
(
    task_id_pk  integer PRIMARY KEY,
    header         text,
    description    text,
    status_id_fk   integer REFERENCES PUBLIC.STATUSES (status_id_pk),
    priority_id_fk integer REFERENCES PUBLIC.PRIORITIES (priority_id_pk),
    author         text,
    executor       text
);
 */

--For GenerationType.IDENTITY
CREATE TABLE PUBLIC.TASKS
(
    task_id_pk     BIGSERIAL PRIMARY KEY,
    header         text,
    description    text,
    status_id_fk   integer REFERENCES PUBLIC.STATUSES (status_id_pk),
    priority_id_fk integer REFERENCES PUBLIC.PRIORITIES (priority_id_pk),
    author_id_fk   integer REFERENCES PUBLIC.USERS (user_id_pk),
    executor_id_fk integer REFERENCES PUBLIC.USERS (user_id_pk)
);