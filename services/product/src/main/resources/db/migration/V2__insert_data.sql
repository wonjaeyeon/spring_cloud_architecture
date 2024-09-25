INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Mouse', 'Computer Mouse');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Keyboard', 'Computer Keyboard');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Monitor', 'Computer Monitor');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer CPU', 'Computer CPU');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer RAM', 'Computer RAM');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Hard Drive', 'Computer Hard Drive');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Graphics Card', 'Computer Graphics Card');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Power Supply', 'Computer Power Supply');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Motherboard', 'Computer Motherboard');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Case', 'Computer Case');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Cooling System', 'Computer Cooling System');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Optical Drive', 'Computer Optical Drive');


INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Razer DeathAdder', 'Razer DeathAdder', 100, 50, 1);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Logitech G502', 'Logitech G502', 100, 50, 1);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Corsair K95 RGB Platinum', 'Corsair K95 RGB Platinum', 100, 150, 2);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Razer BlackWidow Elite', 'Razer BlackWidow Elite', 100, 150, 2);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'ASUS VG248QE', 'ASUS VG248QE', 100, 200, 3);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Acer Predator XB271HU', 'Acer Predator XB271HU', 100, 200, 3);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Intel Core i9-9900K', 'Intel Core i9-9900K', 100, 500, 4);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'AMD Ryzen 9 3900X', 'AMD Ryzen 9 3900X', 100, 500, 4);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Corsair Vengeance LPX 16GB', 'Corsair Vengeance LPX 16GB', 100, 100, 5);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'G.Skill Ripjaws V 16GB', 'G.Skill Ripjaws V 16GB', 100, 100, 5);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Samsung 970 EVO Plus 1TB', 'Samsung 970 EVO Plus 1TB', 100, 150, 6);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Crucial MX500 1TB', 'Crucial MX500 1TB', 100, 150, 6);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'NVIDIA GeForce RTX 2080 Ti', 'NVIDIA GeForce RTX 2080 Ti', 100, 1000, 7);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'AMD Radeon RX 5700 XT', 'AMD Radeon RX 5700 XT', 100, 1000, 7);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'EVGA SuperNOVA 750 G3', 'EVGA SuperNOVA 750 G3', 100, 100, 8);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Corsair RM750x', 'Corsair RM750x', 100, 100, 8);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'ASUS ROG Strix Z390-E', 'ASUS ROG Strix Z390-E', 100, 200, 9);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'MSI MPG Z390 Gaming Edge AC', 'MSI MPG Z390 Gaming Edge AC', 100, 200, 9);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'NZXT H510', 'NZXT H510', 100, 100, 10);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Fractal Design Meshify C', 'Fractal Design Meshify C', 100, 100, 10);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'Corsair H100i RGB Platinum', 'Corsair H100i RGB Platinum', 100, 150, 11);
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES (nextval('product_seq'), 'NZXT Kraken X62', 'NZXT Kraken X62', 100, 150, 11);
