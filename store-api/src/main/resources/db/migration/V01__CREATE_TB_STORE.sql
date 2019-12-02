CREATE TABLE tb_store (id_store integer NOT NULL,
                       city varchar(80),
                       name varchar(150),
                       state varchar(50),
                       street varchar(100),
                       zip_code varchar(8),
                       PRIMARY KEY (id_store));

CREATE SEQUENCE store_seq START 1 INCREMENT 1;