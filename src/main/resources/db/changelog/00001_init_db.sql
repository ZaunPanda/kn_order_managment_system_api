CREATE TABLE IF NOT EXISTS customers
(
    `registration_code` INT PRIMARY KEY AUTO_INCREMENT,
    `fullName`         VARCHAR(255),
    `email`            VARCHAR(255),
    `telephone`        VARCHAR(20)
);
CREATE TABLE IF NOT EXISTS orders
(
    `orderId`        INT PRIMARY KEY AUTO_INCREMENT,
    `customer_id`     INT,
    `submissionDate` DATE,
    FOREIGN KEY (`customer_id`) REFERENCES `customers` (`registration_code`)
);

CREATE TABLE IF NOT EXISTS products
(
    `productId`   INT PRIMARY KEY AUTO_INCREMENT,
    `productName` VARCHAR(255),
    `skuCode` varchar(255),
    `unitPrice` INT
);
CREATE TABLE IF NOT EXISTS order_lines
(
    `orderline_id` INT PRIMARY KEY AUTO_INCREMENT,
    `orderId`     INT,
    `productId`   INT,
    `quantity`     INT,
    FOREIGN KEY (`orderId`) REFERENCES orders (`orderId`),
    FOREIGN KEY (`productId`) REFERENCES products (`productId`)
);

