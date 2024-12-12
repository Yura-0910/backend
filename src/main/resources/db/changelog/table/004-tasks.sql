--liquibase formatted sql
--changeset MazurovY:4

--For GenerationType.SEQUENCE
/*
CREATE TABLE PUBLIC.TASKS
(
    task_id_pk     integer PRIMARY KEY,
    header         text,
    description    text,
    status_id_fk   integer REFERENCES PUBLIC.STATUSES (status_id_pk),
    priority_id_fk integer REFERENCES PUBLIC.PRIORITIES (priority_id_pk),
    author_id_fk   integer REFERENCES PUBLIC.USERS (user_id_pk),
    executor_id_fk integer REFERENCES PUBLIC.USERS (user_id_pk)
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

/*
Вспомогательный запрос для просмотра задач

SELECT t.task_id_pk,
       t."header",
       t.description,
       s.status,
       p.priority,
       u.fio as author,
       e.fio as executor
FROM public.tasks t
         inner join public.statuses s on t.status_id_fk = s.status_id_pk
         inner join public.priorities p on t.priority_id_fk = p.priority_id_pk
         inner join public.users u on t.author_id_fk = u.user_id_pk
         inner join public.users e on t.executor_id_fk = e.user_id_pk;
 */