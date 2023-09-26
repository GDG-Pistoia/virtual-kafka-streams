--liquibase formatted sql
--changeset allmind:create_obj_schema
CREATE SCHEMA ${obj_schema};
--rollback DROP SCHEMA ${obj_schema};
