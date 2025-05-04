CREATE TABLE IF NOT EXISTS orders (
    id serial PRIMARY KEY,
    order_number varchar(36) NOT NULL,
    sku_code varchar(36) NOT NULL,
    price DECIMAL NOT NULL,
    quantity int NOT NULL
)