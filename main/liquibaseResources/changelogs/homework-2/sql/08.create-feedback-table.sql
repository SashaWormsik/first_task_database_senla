CREATE TABLE feedback
(
    id           bigserial PRIMARY KEY,
    create_date  date   NOT NULL,
    content      character varying(500),
    addressee_id bigint NOT NULL,
    sender_id    bigint NOT NULL,
    FOREIGN KEY (addressee_id) REFERENCES user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    FOREIGN KEY (sender_id) REFERENCES user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);