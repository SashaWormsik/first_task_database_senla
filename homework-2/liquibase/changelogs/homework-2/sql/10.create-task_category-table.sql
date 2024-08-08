CREATE TABLE task_category
(
    category_id bigint,
    task_id     bigint,
    PRIMARY KEY (category_id, task_id),
    FOREIGN KEY (task_id) REFERENCES task (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (category_id) REFERENCES category (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);
