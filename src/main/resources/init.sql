CREATE TABLE authority(
    id          BIGSERIAL   PRIMARY KEY NOT NULL,
    role        TEXT        NOT NULL
);

CREATE TABLE customer(
    id          BIGSERIAL   PRIMARY KEY NOT NULL,
    fio         TEXT        NOT NULL,
    email       TEXT        ,
    password    TEXT        NOT NULL,
    authority   BIGINT      NOT NULL,
    created_at  TIMESTAMP   DEFAULT now(),
    FOREIGN KEY (authority) REFERENCES authority (id)
);

CREATE TABLE task(
    id          BIGSERIAL   PRIMARY KEY NOT NULL,
    header      TEXT        NOT NULL,
    description TEXT        ,
    status      TEXT        NOT NULL,
    priority    TEXT        NOT NULL,
    author      BIGINT      NOT NULL,
    executor    BIGINT      ,
    FOREIGN KEY (author) REFERENCES customer (id),
    FOREIGN KEY (executor) REFERENCES customer (id)
);

CREATE TABLE comment(
    id          BIGSERIAL   PRIMARY KEY NOT NULL,
    text        TEXT        NOT NULL,
    author      BIGINT      NOT NULL,
    task        BIGINT      NOT NULL,
    created_at  TIMESTAMP   DEFAULT now(),
    FOREIGN KEY (author) REFERENCES customer (id),
    FOREIGN KEY (task) REFERENCES task (id)
)