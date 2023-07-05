INSERT INTO PRODUCT(PRODUCT_NAME, PRODUCT_CODE, DESCRIPTION, CATEGORY, BRAND, UNIT_OF_MEASURE, WEIGHT, IS_FRAGILE, DIMENSIONS, STORAGE_TEMPERATURE, UNIT_COST, LEAD_TIME)
VALUES ('Hammer', 'HAM001', 'A claw hammer for DIY projects', 'Tools', 'Brand 1', 'Kg', 1.0, false, 12.0, 0.0, 20.0, 2.0),
  ('Screwdriver', 'SD001', 'A Phillips head screwdriver for DIY projects', 'Tools', 'Brand 2', 'Kg', 0.5, false, 8.0, 0.0, 15.0, 1.5),
  ('Wrench', 'WR001', 'A combination wrench for DIY projects', 'Tools', 'Brand 3', 'Kg', 1.5, false, 10.0, 0.0, 25.0, 2.5),
  ('Pliers', 'PL001', 'Needle-nose pliers for small DIY projects', 'Tools', 'Brand 4', 'Kg', 0.3, false, 6.0, 0.0, 10.0, 1.0),
  ('Circular Saw', 'CS001', 'A power tool for cutting wood and other materials', 'Tools', 'Brand 5', 'Kg', 5.0, false, 20.0, 0.0, 100.0, 5.0);


INSERT INTO WAREHOUSE (name, address, city, province, ZIP_CODE)
VALUES ('Sample Warehouse', '123 Main Street', 'Anytown', 'CA', '12345');


INSERT INTO VENDOR (id, name, address, email, phone) VALUES
(1, 'Vendor 1', '123 Main St.', 'vendor1@example.com', '555-1234'),
(2, 'Vendor 2', '456 Elm St.', 'vendor2@example.com', '555-5678'),
(3, 'Vendor 3', '789 Oak St.', 'vendor3@example.com', '555-9012');


  INSERT INTO INVENTORY(name, product_id, warehouse_id, vendor_id, quantity, awaited_quantity, minimum_stock_level, maximum_stock_level, reorder_quantity, maximum_order_quantity, storage_location)
  VALUES
  ('Hammer', 1, 1, 1, 100,0, 50, 200, 20, 500, 'A1'),
  ('Screwdriver', 1, 1, 2, 100,0, 50, 300, 30, 750, 'B2'),
  ('Wrench', 3, 1, 3, 100,0, 50, 150, 15, 250, 'C3'),
  ('Pliers', 4, 1, 2, 100 ,0, 50, 400, 40, 1000, 'D4'),
  ('Circular Saw', 5, 1, 1, 100,0, 20, 50, 5, 100, 'E5');


  insert into USER_INFO (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHONE_NUMBER)
  values ( 'Adam', 'Sz', 'adam@test.com','$2a$10$J7eFD6Z4mX1NGPeku1467uWlFzx0JjdBB.oBplzVF1pTck5N75jA2', '09893434'),
    ('support', 'support', 'support@test.com','$2a$10$Vy1P7f5epa3wOZHofeADYe2/fkpd.8HiIyyTy6nL5xTqnsgkM29ly', '05678'),
  ( 'Test', 'T', 'test@mail.com','$2a$10$RLhewL4cFkfyzrlY6M3n.OHd8HdzURC8VjdnAh7hlKmZVV5gQDyo.', '01');

 insert into ROLE(NAME)
 values('EMPLOYEE');
  insert into ROLE(NAME)
  values('CUSTOMER');
 insert into ROLE(NAME)
 values('ADMIN');

 INSERT INTO user_roles (id, rid) VALUES (1, 1);
 INSERT INTO user_roles (id, rid) VALUES (2, 3);
  INSERT INTO user_roles (id, rid) VALUES (3, 2);


