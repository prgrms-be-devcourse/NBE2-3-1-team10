CREATE TABLE product
(
    product_id   int          PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price        int          NOT NULL,
    quantity     int          NOT NULL,
    imagename    varchar(255) NOT NULL,
    category_id  int          NOT NULL,
    deleted      tinyint(1)   NOT NULL DEFAULT 0
);

CREATE TABLE orders
(
    order_id     int          PRIMARY KEY AUTO_INCREMENT,
    email        VARCHAR(100) NOT NULL,
    address      VARCHAR(100) NOT NULL,
    zipcode      VARCHAR(10)  NOT NULL,
    order_time   VARCHAR(50)  NOT NULL,
    order_status VARCHAR(20)  NOT NULL,
    total_price  int          NOT NULL
);

CREATE TABLE order_item
(
    seq          int    PRIMARY KEY AUTO_INCREMENT,
    order_id     int    NOT NULL,
    product_id   int    NOT NULL,
    count        int    NOT NULL,
    CONSTRAINT fk_order_item_to_order FOREIGN KEY (order_id) REFERENCES orders (order_id),
    CONSTRAINT fk_order_item_to_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE category
(
    category_id     int           primary key,
    name            varchar(20)   NOT NULL
);


-- insert into product values (0, '커피1', 12000, 10,
--                             '607927_1_thumb1.jpg',1);
--
-- insert into product values (0, '커피2', 13000, 20,
--                             '607927_1_thumb1.jpg',1);