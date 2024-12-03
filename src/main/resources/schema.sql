CREATE TABLE product
(
    product_id   int          PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    price        int          NOT NULL,
    quantity     int          NOT NULL,
    imagename    varchar(255) NOT NULL,
    category_id  int          NOT NULL
);

CREATE TABLE order
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
    CONSTRAINT fk_order_items_to_order FOREIGN KEY (order_id) REFERENCES order (order_id),
    CONSTRAINT fk_order_items_to_product FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE category
(
    category_id     int           primary key AUTO_INCREMENT,
    name            varchar(20)   NOT NULL
)