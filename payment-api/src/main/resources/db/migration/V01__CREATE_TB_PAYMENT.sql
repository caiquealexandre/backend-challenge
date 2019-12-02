CREATE TABLE tb_payment
  (
     id_payment         INT8 NOT NULL,
     credit_card_number VARCHAR(255),
     order_id           INT8,
     payment_date       TIMESTAMP,
     payment_status     INT4,
     PRIMARY KEY (id_payment)
  );

CREATE SEQUENCE payment_seq START 1 INCREMENT 1;