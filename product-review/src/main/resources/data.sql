DROP TABLE IF EXISTS product_review;

CREATE TABLE product_review (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  product_id varchar(20) not null,
  rating int NOT NULL
);

INSERT INTO product_review (product_id, rating) VALUES
  ('BB5476', 5),
  ('BB5476', 4),
  ('BB5476', 3),
  ('BB5476', 2);