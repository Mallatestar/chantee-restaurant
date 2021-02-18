DROP SCHEMA IF EXISTS testdb;
CREATE SCHEMA IF NOT EXISTS testdb;
USE testdb;

/*Tables which are describes all user parameters */
CREATE TABLE IF NOT EXISTS users(
                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                    username VARCHAR(25) NOT NULL,
                                    email VARCHAR(70) NOT NULL UNIQUE,
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
                                         (2, 'drinks'),
                                         (3, 'tarts'),
                                         (4, 'eklers');

INSERT INTO products (title, price, description, img_path, category) VALUES
('Малина-шоколад', 70, 'Насыщений шоколадный вкус бисквита и шоколадного крема в сочетании с нежным муссом и прослойкой из малины.',
 '/chantee-restaurant/view/img/goods/cakes/40eb7507a192b0a1808a3fd9480f2b6c.jpg', 1),
('«Сникерс»', 65, 'Бархатный шоколадный бисквит,нежнейший чизкейк, солёная карамель, кусочки арахиса и воздушный шоколадный крем.',
 '/chantee-restaurant/view/img/goods/cakes/042ac703bde1378206f721d068081dfa.jpg', 1),
('Пина-колада', 60, 'Нежный сливочный бисквит в соединении с кокосовым муссом и ананасовой прослойкой.',
 '/chantee-restaurant/view/img/goods/cakes/95aa775bcfb6ef6aa998f79950b8dc0e.jpg', 1),
('Черничный йогурт', 65, 'Воздушный бисквит с йогуртовой прослойкой и черничным джемом.',
 '/chantee-restaurant/view/img/goods/cakes/06377f76e8f13f89c8ae21d8541a4fb0.jpg', 1),
('Торт пряное яблоко', 75, 'Ароматный фундучный бисквит с прослойкой из карамели и кусочков яблок,а также мусс из белого вина.',
 '/chantee-restaurant/view/img/goods/cakes/a0e476df467a491ab6e4997b4ed46d9b.jpg', 1),
('Красный бархат', 55, 'Плотный красный бисквит с ароматом какао,воздушный крем и кусочки ореха пекан.',
 '/chantee-restaurant/view/img/goods/cakes/b14e2aa6f4abd522f3a2e5018647c1e9.jpg', 1),
('Кофе-бейлис', 70, 'Основа из миндального бисквита, мусс кофе-бейлис покрытый блестящей зеркальной глазурью.',
 '/chantee-restaurant/view/img/goods/cakes/кофе-бейлис.jpg', 1),
('Манго-маракуя', 80, 'Нежный бисквит с кусочками манго, манговый мусс и прослойка из маракуйи  покрытый блестящей зеркальной глазурью.',
 '/chantee-restaurant/view/img/goods/cakes/8ad0e61d25dd60cfcb47bb96179765e9.jpg', 1),

('Цейлонский байховый чёрный чай', 15, '','/chantee-restaurant/view/img/goods/drinks/черный.jpg', 2),
('Чай зелёный с жасмином', 15, '','/chantee-restaurant/view/img/goods/drinks/зеленый с жасмином.jpg', 2),
('Витаминный коктейль', 25, 'Соединение апельсина,лимона,красной смородины и мяты.','/chantee-restaurant/view/img/goods/drinks/фруктовый.jpg', 4),
('Чёрный чай с лимоном и мятой', 20, '','/chantee-restaurant/view/img/goods/drinks/лимон-мята.jpg', 2),
('Анчан - тайский синий', 30, 'Чай из лепестков синей тайской орхидеи','/chantee-restaurant/view/img/goods/drinks/анчан-тайский синий.jpg', 4),
('Капучино', 25, '','/chantee-restaurant/view/img/goods/drinks/капучино.jpg', 2),
('Американо', 10, '','/chantee-restaurant/view/img/goods/drinks/американо.jpg', 2),
('Раф ванильный', 35, '','/chantee-restaurant/view/img/goods/drinks/раф.jpg', 2),
('Какао', 27, '','/chantee-restaurant/view/img/goods/drinks/какао.jpg', 2),
('Лимонад цитрусовый с мятой', 32, '','/chantee-restaurant/view/img/goods/drinks/цитрусовый.jpg', 2),

('Инжир-ваниль', 50, 'Хрустящая песочная основа,нежнейший ванильный крем и свежие кусочки инжира.',
 '/chantee-restaurant/view/img/goods/tarts/photo_2021-02-16_21-23-34.jpg', 3),
('Персиковый тарт', 80, 'Миндальная песочная основа,хрустящий кокосовый слой,воздушный запечённый крем, карамелизированные кусочки персика и крем на основе итальянского белого шоколада.',
 '/chantee-restaurant/view/img/goods/tarts/2.jpg', 3),
('Тарт кофейный', 65, 'Ароматная шоколадная песочная основа,воздушный запечённый крем с добавлением кофе,кофейный мусс и ванильный крем.',
 '/chantee-restaurant/view/img/goods/tarts/3.jpg', 3),
('Ваниль-малина', 70, 'Миндальная шоколадная основа,ванильный мусс и свежая малина.',
 '/chantee-restaurant/view/img/goods/tarts/4.jpg', 3),
('Пряное яблоко', 75, 'Хрустящая миндальная основа,воздушный запечённый крем с бадьяном,мусс на белом шоколаде с серединкой из кусочков яблок с корицей.',
 '/chantee-restaurant/view/img/goods/tarts/1.jpg', 3),
('Малина-Роза', 78, 'Хрустящая песочная основа,хрустящий слой из кокоса,нежный запечённый крем,мусс на основе розы с серединкой из малины.',
 '/chantee-restaurant/view/img/goods/tarts/photo_2021-02-16_21-23-38.jpg', 3),

('Эклер ванильный', 30, 'Нежное заварное тесто и ванильный крем,покрытый блестящей зеркальной глазурью.',
 '/chantee-restaurant/view/img/goods/eklers/photo_2021-02-16_21-24-12.jpg', 4),
('Эклер шоколадный', 33, 'Нежная заварная основа,крем на основе молочного шоколада и шоколадная глазурь.',
 '/chantee-restaurant/view/img/goods/eklers/photo_2021-02-16_21-24-18.jpg', 4),
('Эклер яблочный', 35, 'Заварная основа,ванильный заварной крем и прослойка из яблочного джема.',
 '/chantee-restaurant/view/img/goods/eklers/photo_2021-02-16_21-24-09.jpg', 4),
('Эклер фисташковый', 40, 'Нежная заварная основа,фисташковый мусс внутри и нежный фисташковый крем сверху.',
 '/chantee-restaurant/view/img/goods/eklers/photo_2021-02-16_21-23-53.jpg', 4),
('Эклер черничный', 37, 'Нежная заварная основа,черничный заварной крем и свежая черника.',
 '/chantee-restaurant/view/img/goods/eklers/photo_2021-02-16_21-24-21.jpg', 4),
('Эклер клубничный', 38, 'Заварная основа,ванильный заварной крем прослойка из клубничного джема и блестящая зеркальная глазурь',
 '/chantee-restaurant/view/img/goods/eklers/photo_2021-02-16_21-24-06.jpg', 4);

INSERT INTO orders (user_id, date_time, comm, delivery_address, phone) VALUES (1, 'testDate',
                                                                               'testComm', 'testAddress',
                                                                               'testPhone');
INSERT INTO orders (user_id, date_time, comm, delivery_address, phone, stage) VALUES (1, 'testDate',
                                                                               'testComm', 'testAddress',
                                                                               'testPhone', 'kitchen');
INSERT INTO orders (user_id, date_time, comm, delivery_address, phone, stage) VALUES (1, 'testDate',
                                                                                      'testComm', 'testAddress',
                                                                                      'testPhone', 'delivery');