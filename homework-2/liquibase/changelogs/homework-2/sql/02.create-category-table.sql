CREATE TABLE category
(
    id   bigserial PRIMARY KEY,
    name character varying(100) NOT NULL UNIQUE
);