--liquibase formatted sql

--changeset felipe:0
CREATE TABLE IF NOT EXISTS pd_products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    price DECIMAL(18,2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    type VARCHAR(60) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    sku VARCHAR(15) UNIQUE NOT NULL
);