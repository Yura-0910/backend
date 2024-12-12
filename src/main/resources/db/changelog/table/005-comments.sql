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
);

/*
Вспомогательный запрос для просмотра комментариев

SELECT c.comment_id_pk,
       c."content",
       t."header" as task,
       u.fio      as author
FROM public."comments" c
         inner join public.tasks t on c.task_id_fk = t.task_id_pk
         inner join public.users u on c.user_id_fk = u.user_id_pk;
 */