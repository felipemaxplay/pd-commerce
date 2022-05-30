-- liquibase formatted sql

-- changeset felipe:0
CREATE TABLE IF NOT EXISTS pd_products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    price DECIMAL(18,2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    type VARCHAR(60) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    sku VARCHAR(15) UNIQUE NOT NULL
);

-- changeset felipe:1
CREATE TABLE IF NOT EXISTS pd_orders (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     address VARCHAR(130) NOT NULL,
     email VARCHAR(30) NOT NULL,
     date DATETIME(6) NOT NULL,
     price DECIMAL(18,2) NOT NULL
);

-- changeset felipe:2
CREATE TABLE IF NOT EXISTS pd_order_product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_order BIGINT NOT NULL,
    id_product BIGINT NOT NULL,
    qtd INT NOT NULL,
    price DECIMAL(18, 2) NOT NULL,
    total_price DECIMAL(18,2) NOT NULL,
    FOREIGN KEY (id_order) REFERENCES pd_orders(id),
    FOREIGN KEY (id_product) REFERENCES pd_products(id)
);