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
    createdAt   TIMESTAMP   DEFAULT now(),
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
    FOREIGN KEY (author, executor) REFERENCES customer (id)
)