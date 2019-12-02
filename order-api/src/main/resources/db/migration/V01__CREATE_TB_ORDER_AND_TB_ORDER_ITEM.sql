CREATE TABLE tb_order
  (
     id_order          INT8 NOT NULL,
     city              VARCHAR(255),
     date_confirmation TIMESTAMP,
     id_store          INT8,
     order_status      INT4,
     state             VARCHAR(255),
     street            VARCHAR(255),
     zip_code          VARCHAR(255),
     PRIMARY KEY (id_order)
  );

CREATE SEQUENCE order_seq START 1 INCREMENT 1;

CREATE TABLE tb_order_item
  (
     id_order_item INT8 NOT NULL,
     description   VARCHAR(255),
     qt_order_item INT4,
     vl_unit_price NUMERIC(19, 2),
     order_id      INT8,
     PRIMARY KEY (id_order_item)
  );

CREATE SEQUENCE order_item_seq START 1 INCREMENT 1;

ALTER TABLE tb_order_item ADD CONSTRAINT fkgeobgl2xu916he8vhljktwxnx FOREIGN KEY (order_id) REFERENCES tb_order;