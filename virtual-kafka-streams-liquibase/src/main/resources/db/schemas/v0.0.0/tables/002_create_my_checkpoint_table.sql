--liquibase formatted sql
--changeset allmind:create_my_checkpoints_table
CREATE TABLE ${obj_schema}."CHECKPOINTS"(
     "id" serial,
     "created" TIMESTAMP,
     "updated" TIMESTAMP,
     "index" VARCHAR NOT NULL UNIQUE,
     PRIMARY KEY ("id")
);

--rollback DROP TABLE IF EXISTS ${obj_schema}."CHECKPOINTS"