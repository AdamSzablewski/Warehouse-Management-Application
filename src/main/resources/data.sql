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


  INSERT INTO INVENTORY(name, product_id, warehouse_id, vendor_id, quantity, minimum_stock_level, maximum_stock_level, reorder_quantity, minimum_order_quantity, maximum_order_quantity, storage_location)
  VALUES
  ('Hammer', 1, 1, 1, 100, 10, 200, 20, 50, 500, 'A1'),
  ('Screwdriver', 1, 1, 2, 150, 15, 300, 30, 75, 750, 'B2'),
  ('Wrench', 3, 1, 3, 75, 5, 150, 15, 25, 250, 'C3'),
  ('Pliers', 4, 1, 2, 200, 20, 400, 40, 100, 1000, 'D4'),
  ('Circular Saw', 5, 1, 1, 20, 2, 50, 5, 10, 100, 'E5');

