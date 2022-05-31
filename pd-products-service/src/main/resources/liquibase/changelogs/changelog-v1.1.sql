--liquibase formatted sql

--changeset felipe:0
ALTER TABLE IF EXISTS pd_products ALTER COLUMN sku TYPE VARCHAR(30);