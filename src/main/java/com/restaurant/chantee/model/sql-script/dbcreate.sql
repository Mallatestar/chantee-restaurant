CREATE SCHEMA IF NOT EXISTS chanteedb;
USE chanteedb;

/*Tables which are describes all user parameters */
CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    user_password VARCHAR(150) NOT NULL);

CREATE TABLE IF NOT EXISTS managers
(
    user_id INT NOT NULL UNIQUE,
    FOREIGN KEY users_id (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE);

/*Tables which are describes all goods in restaurant menu*/
CREATE TABLE IF NOT EXISTS products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL UNIQUE,
    price INT NOT NULL,
    description VARCHAR(200) NOT NULL,
    img_path VARCHAR(100) NOT NULL UNIQUE,
    category INT NOT NULL);

CREATE TABLE IF NOT EXISTS categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE);

/*Tables which are describes orders*/
CREATE TABLE IF NOT EXISTS orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    date_time VARCHAR(50) NOT NULL,
    comm VARCHAR(200),
    delivery_address VARCHAR(150) NOT NULL ,
    phone VARCHAR(50) NOT NULL,
    stage VARCHAR(50) NOT NULL DEFAULT 'ordered',
    FOREIGN KEY customer (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS order_products(
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    product_quantity INT NOT NULL,
    FOREIGN KEY receipt_num (order_id) REFERENCES orders (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY product_num (product_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE);


/*Creating root user*/
INSERT INTO users (id, username, email, user_password) VALUES (1, 'root', 'bengijha@gmail.com', '0CD3ABB80C5D017FBB3FDA4A2B6069528BE2960107936BF066717432D216E656');
INSERT INTO managers (user_id) VALUES (1);

/*Creating a goods catalog*/
INSERT INTO categories (id, name) VALUES (1, 'cakes'),
                                         (2, 'bread'),
                                         (3, 'croissants'),
                                         (4, 'drinks'),
                                         (5, 'icecream'),
                                         (6, 'candies');
INSERT INTO products (title, price, description, img_path, category) VALUES ('Cake1', 100, 'Decription1', '/chantee-restaurant/view/img/goods/cakes/1e3e170afb348ead7c222200366fae84.jpg', 1),
                                                                            ('Cake2', 150, 'Description2', '/chantee-restaurant/view/img/goods/cakes/2cce38dd5542c967a82b3e0f32c58360.jpg', 1);

