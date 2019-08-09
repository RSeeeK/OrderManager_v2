CREATE SEQUENCE seq_order_id;

CREATE TABLE orders
(
    id              bigint NOT NULL DEFAULT nextval('seq_order_id'::regclass),
    number          character varying COLLATE pg_catalog."default",
    ordertime       timestamp,
    customeremail   character varying COLLATE pg_catalog."default",
    ordersum        numeric(19, 2) DEFAULT 0.0,
    CONSTRAINT order_object_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE seq_product_id;

create table products (
      id        bigint NOT NULL DEFAULT nextval('seq_product_id'::regclass),
      name      varchar(255),
      price     numeric(19, 2),
      CONSTRAINT product_object_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE seq_ordercontent_id;

CREATE TABLE ordercontent
(
    id              bigint NOT NULL DEFAULT nextval('seq_ordercontent_id'::regclass),
    product_id      int4,
    price           numeric(19, 2) DEFAULT 0.0,
    count           numeric(19, 2) DEFAULT 0.0,
    sum             numeric(19, 2) DEFAULT 0.0,
    order_id        bigint NOT NULL,
    CONSTRAINT ordercontent_object_pkey PRIMARY KEY (id),
    CONSTRAINT fk_product FOREIGN KEY (product_id)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT fk_order FOREIGN KEY (order_id)
        REFERENCES orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);