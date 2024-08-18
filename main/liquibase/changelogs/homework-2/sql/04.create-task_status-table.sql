CREATE TABLE task_status
(
    id     bigserial PRIMARY KEY,
    status character varying(100) NOT NULL UNIQUE
);