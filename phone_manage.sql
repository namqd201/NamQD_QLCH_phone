CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    price DECIMAL(12,2) NOT NULL CHECK (price >= 0),
    stock INT NOT NULL CHECK (stock >= 0)
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    address VARCHAR(255)
);


CREATE TABLE invoice (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(12,2) NOT NULL CHECK (total_amount >= 0),

    CONSTRAINT fk_invoice_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
        ON DELETE RESTRICT
);


CREATE TABLE invoice_details (
    id SERIAL PRIMARY KEY,
    invoice_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(12,2) NOT NULL CHECK (unit_price >= 0),

    CONSTRAINT fk_invoice_details_invoice
        FOREIGN KEY (invoice_id)
        REFERENCES invoice(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_invoice_details_product
        FOREIGN KEY (product_id)
        REFERENCES product(id)
        ON DELETE RESTRICT
);

INSERT INTO admin (username, password) VALUES
('admin', '123'),
('manager', '123');


INSERT INTO product (name, brand, price, stock) VALUES
('iPhone 15 Pro Max', 'Apple', 34990000, 10),
('Samsung Galaxy S24 Ultra', 'Samsung', 32990000, 15),
('Xiaomi 14 Pro', 'Xiaomi', 24990000, 20),
('OPPO Find X7', 'OPPO', 21990000, 12),
('Vivo X100 Pro', 'Vivo', 23990000, 18);


INSERT INTO customer (name, phone, email, address) VALUES
('Nguyễn Văn A', '0901234567', 'a@gmail.com', 'Hà Nội'),
('Trần Thị B', '0912345678', 'b@gmail.com', 'TP Hồ Chí Minh'),
('Lê Văn C', '0923456789', 'c@gmail.com', 'Đà Nẵng');


INSERT INTO invoice (customer_id, total_amount) VALUES
(1, 69980000),
(2, 32990000);


INSERT INTO invoice_details (invoice_id, product_id, quantity, unit_price) VALUES
-- Hóa đơn 1
(1, 1, 2, 34990000),

-- Hóa đơn 2
(2, 2, 1, 32990000);