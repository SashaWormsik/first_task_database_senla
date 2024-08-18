CREATE TABLE user_info
(
    id              bigserial PRIMARY KEY,
    name            character varying(100),
    surname        character varying(100),
    profession      character varying(100),
    work_experience integer,
    description     character varying(500)
);