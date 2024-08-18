CREATE TABLE credential
(
    id          bigint PRIMARY KEY,
    email       character varying(100) NOT NULL UNIQUE,
    password    character varying(250) NOT NULL,
    create_date date                   NOT NULL,
    active      boolean                NOT NULL,
    token       character varying(250) UNIQUE,
    role_id     bigint                 NOT NULL,
    FOREIGN KEY (id) REFERENCES user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (role_id) REFERENCES role (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);