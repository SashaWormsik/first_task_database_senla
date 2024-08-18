CREATE TABLE response
(
    id                 bigserial PRIMARY KEY,
    suggested_price    numeric(10, 2),
    suggested_date     date,
    create_date        date   NOT NULL,
    task_id            bigint NOT NULL,
    executor_id        bigint NOT NULL,
    response_status_id bigint NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (executor_id) REFERENCES user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (response_status_id) REFERENCES response_status (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);