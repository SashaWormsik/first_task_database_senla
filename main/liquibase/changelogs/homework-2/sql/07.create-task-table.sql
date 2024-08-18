CREATE TABLE task
(
    id          bigserial PRIMARY KEY,
    title       character varying(100) NOT NULL,
    description character varying(500),
    price       numeric(10, 2),
    deadline    date,
    create_date date                   NOT NULL,
    customer_id bigint                 NOT NULL,
    status_id   bigint                 NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (status_id) REFERENCES task_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);