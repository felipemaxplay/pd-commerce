-- liquibase formatted sql

-- changeset felipe:0
ALTER TABLE pd_products MODIFY sku VARCHAR(30) NOT NULL;