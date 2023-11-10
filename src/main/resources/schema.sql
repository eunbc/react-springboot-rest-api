DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS order_item;

CREATE TABLE IF NOT EXISTS member
(
    member_id  BINARY(16) PRIMARY KEY,
    name       VARCHAR(20) NOT NULL,
    email      VARCHAR(50) NOT NULL UNIQUE,
    address    VARCHAR(255),
    created_at datetime    NOT NULL,
    updated_at datetime DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS product
(
    product_id  BINARY(16) PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    category    VARCHAR(50)  NOT NULL,
    price       BIGINT       NOT NULL,
    stock       BIGINT       NOT NULL,
    sales       BIGINT       NOT NULL,
    description TEXT,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS cart_item
(
    cart_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id  BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL,
    quantity   INT        NOT NULL
);

CREATE TABLE IF NOT EXISTS `order`
(
    order_id     BINARY(16) PRIMARY KEY,
    member_id    BINARY(16)  NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    created_at   DATETIME    NOT NULL,
    updated_at   DATETIME DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS order_item
(
    order_id   BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL,
    price      BIGINT     NOT NULL,
    quantity   INT        NOT NULL,
    PRIMARY KEY (order_id, product_id)
);
