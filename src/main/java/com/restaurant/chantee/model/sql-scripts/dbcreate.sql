CREATE SCHEMA IF NOT EXISTS chanteedb;
USE chanteedb;

/*Tables which are describes all user parameters */
CREATE TABLE IF NOT EXISTS users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    user_password VARCHAR(150) NOT NULL);

CREATE TABLE IF NOT EXISTS delivery_data(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL UNIQUE,
  phone VARCHAR(13) NOT NULL UNIQUE,
  street VARCHAR(50) NOT NULL,
  house INT NOT NULL,
  section INT,
  apartment INT NOT NULL,
  FOREIGN KEY users_id (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS user_role(
    user_id INT NOT NULL UNIQUE,
    user_admin BOOLEAN NOT NULL,
    FOREIGN KEY users_id (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE);

/*Tables which are describes all goods in restaurant menu*/
CREATE TABLE IF NOT EXISTS products(
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(200) NOT NULL,
    img_path VARCHAR(100) NOT NULL UNIQUE,
    category INT NOT NULL);

CREATE TABLE IF NOT EXISTS categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS products_categories(
    product_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY products_id (product_id) REFERENCES products (id)  ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY categories_id (category_id) REFERENCES categories (id) ON DELETE CASCADE ON UPDATE CASCADE);

/*Tables which are describes orders*/
CREATE TABLE IF NOT EXISTS receipts(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    delivery_id INT NOT NULL,
    date_time DATETIME NOT NULL,
    comm VARCHAR(200),
    FOREIGN KEY customer (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY delivery (delivery_id) REFERENCES delivery_data (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS receipt_product(
    receipt_id INT NOT NULL,
    product_id INT NOT NULL,
    product_quantity INT NOT NULL,
    FOREIGN KEY receipt_num (receipt_id) REFERENCES receipts (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY product_num (product_id) REFERENCES products (id) ON DELETE CASCADE ON UPDATE CASCADE);




/*Creating a goods catalog*/
INSERT INTO categories (id, name) VALUES (1, 'cakes'),
                                         (2, 'bread'),
                                         (3, 'croissants'),
                                         (4, 'drinks'),
                                         (5, 'icecream'),
                                         (6, 'candies');

INSERT INTO products (title, description, img_path, category) VALUES ('Cake1', 'Super-puper cake', 'src/main/webapp/view/img/goods/cakes/1d8e22819d98b897bd5f12a67bfa527f.jpg', 1),
                                                                     ('Cake2', 'Super-puper cake', 'src/main/webapp/view/img/goods/cakes/1e3e170afb348ead7c222200366fae84.jpg', 1)



