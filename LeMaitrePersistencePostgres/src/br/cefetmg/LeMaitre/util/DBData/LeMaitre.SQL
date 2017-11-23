/*
Created: 01/06/2017
Modified: 19/10/2017
Model: Le Maitre
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- table table

CREATE TABLE  "table" (
  COD_ID  Serial NOT NULL,
  IDT_status  Character(1) NOT NULL,
  NRO_seat  Integer NOT NULL
);

COMMENT ON COLUMN  "table" . IDT_status  IS 'O - occupied
F - free
R - reserved';

-- Add keys for table table

ALTER TABLE  "table"  ADD CONSTRAINT  Key1  PRIMARY KEY ( COD_ID );

-- table order

CREATE or REPLACE TABLE "order" (
  COD_ID_Bill  Varchar NOT NULL,
  DAT_order  Timestamp without time zone NOT NULL,
  COD_Item  Integer NOT NULL,
  IDT_status  Character(1) NOT NULL,
  VLR_price  Double precision NOT NULL,
  QTD_item  Integer NOT NULL
);

COMMENT ON COLUMN  "order" . IDT_status  IS 'D - delivered
P - in progress
N - not started';

-- Create indexes for table order

CREATE INDEX  IX_Relationship13  ON  "order"  ( COD_Item );

-- Add keys for table order

ALTER TABLE  "order"  ADD CONSTRAINT  Key2  PRIMARY KEY ( COD_ID_Bill , DAT_order );

-- table Bill

CREATE TABLE  Bill (
  COD_token  Varchar NOT NULL,
  DAT_use  Date NOT NULL,
  IDT_status  Character(1) NOT NULL
);

-- Add keys for table Bill

ALTER TABLE  Bill  ADD CONSTRAINT  Key3  PRIMARY KEY ( COD_token );

-- table Item

CREATE TABLE  Item (
  COD_Item  Serial NOT NULL,
  VLR_price  Double precision NOT NULL,
  NOM_item  Varchar NOT NULL,
  DES_item  Varchar NOT NULL,
  IDT_available  boolean NOT NULL,
  SEQ_Category  Integer,
  SEQ_Subcategory  Integer
);

-- Create indexes for table Item

CREATE INDEX  IX_Relationship15  ON  Item  ( SEQ_Category , SEQ_Subcategory );

-- Add keys for table Item

ALTER TABLE  Item  ADD CONSTRAINT  Key4  PRIMARY KEY ( COD_Item );


-- table Employee

CREATE TABLE  Employee (
  COD_ID  Serial NOT NULL,
  NOM_name  Varchar NOT NULL,
  IDT_profile  Character(1) NOT NULL,
  NOM_username  Varchar NOT NULL,
  TXT_password  Varchar NOT NULL
);

COMMENT ON COLUMN  Employee . IDT_profile  IS 'M - Manager
W - Waiter';

-- Add keys for table Employee

ALTER TABLE  Employee  ADD CONSTRAINT  Key6  PRIMARY KEY ( COD_ID );

-- table Reservation

CREATE TABLE  Reservation (
  COD_ID_Table  Integer NOT NULL,
  DAT_reservation  Date NOT NULL,
  DAT_hour_reservation  Time NOT NULL,
  NRO_persons  Integer NOT NULL,
  TXT_contact_name  Varchar NOT NULL,
  TXT_telephone  Varchar,
  TXT_cellphone  Varchar NOT NULL
);

-- Add keys for table Reservation

ALTER TABLE  Reservation  ADD CONSTRAINT  Key9  PRIMARY KEY ( COD_ID_Table , DAT_reservation , DAT_hour_reservation );

-- table Bill_Table

CREATE TABLE  Bill_Table (
  COD_ID_Bill  Varchar NOT NULL,
  COD_ID_Table  Integer NOT NULL
);

-- Add keys for table Bill_Table

ALTER TABLE  Bill_Table  ADD CONSTRAINT  Key11  PRIMARY KEY ( COD_ID_Table , COD_ID_Bill );

-- table Category

CREATE TABLE  Category (
  SEQ_Category  Serial NOT NULL,
  NOM_category  Varchar NOT NULL
);

-- Add keys for table Category

ALTER TABLE  Category  ADD CONSTRAINT  Key12  PRIMARY KEY ( SEQ_Category );

-- table Subcategory

CREATE TABLE  Subcategory (
  SEQ_Category  Integer NOT NULL,
  SEQ_Subcategory  Serial NOT NULL,
  NOM_subcategory  Varchar NOT NULL
);

-- Add keys for table Subcategory

ALTER TABLE  Subcategory  ADD CONSTRAINT  Key13  PRIMARY KEY ( SEQ_Category , SEQ_Subcategory );

-- table Image

CREATE TABLE  Image (
  COD_image  Serial NOT NULL,
  DES_location  Varchar NOT NULL
);

-- Add keys for table Image

ALTER TABLE  Image  ADD CONSTRAINT  Key14  PRIMARY KEY ( COD_image );

-- table Item_Image

CREATE TABLE  Item_Image (
  COD_Item  Integer NOT NULL,
  COD_image  Integer NOT NULL
);

-- Add keys for table Item_Image

ALTER TABLE  Item_Image  ADD CONSTRAINT  Key15  PRIMARY KEY ( COD_Item , COD_image );
-- Create relationships section -------------------------------------------------

ALTER TABLE  Bill_Table  ADD CONSTRAINT  Relationship5  FOREIGN KEY ( COD_ID_Table ) REFERENCES  "table"  ( COD_ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  Reservation  ADD CONSTRAINT  Relationship6  FOREIGN KEY ( COD_ID_Table ) REFERENCES  "table"  ( COD_ID ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  Bill_Table  ADD CONSTRAINT  Relationship11  FOREIGN KEY ( COD_ID_Bill ) REFERENCES  Bill  ( COD_token ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  "order"  ADD CONSTRAINT  Relationship12  FOREIGN KEY ( COD_ID_Bill ) REFERENCES  Bill  ( COD_token ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  "order"  ADD CONSTRAINT  Relationship13  FOREIGN KEY ( COD_Item ) REFERENCES  Item  ( COD_Item ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  Subcategory  ADD CONSTRAINT  Relationship14  FOREIGN KEY ( SEQ_Category ) REFERENCES  Category  ( SEQ_Category ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  Item  ADD CONSTRAINT  Relationship15  FOREIGN KEY ( SEQ_Category ,  SEQ_Subcategory ) REFERENCES  Subcategory  ( SEQ_Category ,  SEQ_Subcategory ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  Item_Image  ADD CONSTRAINT  Relationship17  FOREIGN KEY ( COD_Item ) REFERENCES  Item  ( COD_Item ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE  Item_Image  ADD CONSTRAINT  Relationship18  FOREIGN KEY ( COD_image ) REFERENCES  Image  ( COD_image ) ON DELETE NO ACTION ON UPDATE NO ACTION;

--Populates Database

INSERT INTO Category (nom_category) values ('Bebidas');

INSERT INTO Category (nom_category) values ('Sobremesas');

INSERT INTO Category (nom_category) values ('Lanches');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (1, 'Refrigerantes');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (1, 'Sucos');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (1, 'Drinks');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (1, 'Cervejas');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (2, 'Bolos');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (2, 'Sorvetes');

INSERT INTO Subcategory (seq_category, nom_subcategory) values (3, 'Sanduíches');

INSERT INTO item values (1,'4.50', 'Coca-Cola Lata', 'Refrigerante Coca-Cola em lata - 220ml', true, 1, 1);

INSERT INTO item values (3,'8.00', 'Coca 2L', 'Refrigerante Coca-Cola de 2L', true, 1, 1);

INSERT INTO item values (4,'7.00', 'Jarra de Suco de Laranja', 'Jarra de Suco de Laranja de 1L', true, 1, 2);

INSERT INTO item values (5,'3.50', 'Limonada Suíça', 'Copo de 300mL de Limonada Suíça', true, 1, 2);

INSERT INTO item values (6,'6.00', 'Capivodka de Frutas', 'Clássica capirinha + vodka', true, 1, 3);

INSERT INTO item values (7,'7.00', 'Coquetel de frutas', 'Batida de frutas + vodka', false, 1, 3);

INSERT INTO item values (8,'6.50', 'Skol Litrão', 'Garrafa de 1L de Skol', true, 1, 4);

INSERT INTO item values (9,'4.00', 'Skol Lata', 'Lata 350mL de Skol', true, 1, 4);

INSERT INTO item values (10,'4.00', 'Heineken Lata', 'Lata 350mL de Heineken', true, 1, 4);

INSERT INTO item values (11,'3.50', 'Kaiser Lata', 'Lata 350mL de Kaiser', false, 1, 4);

INSERT INTO item values (12,'4.99', 'Bolo de Brigadeiro', 'Fatia de Bolo de Brigadeiro', true, 2, 5);

INSERT INTO item values (13,'4.99', 'Bolo de Cenoura', 'Fatia de Bolo de Cenoura com calda de chocolate', true, 2, 5);

INSERT INTO item values (14,'1.99', 'Picolé Nescau', 'Picolé Nescau da Nestlé', false, 2, 6);

INSERT INTO item values (15,'4.99', 'Picolé Magnum', 'Picolé Magnum de Chocolate Branco da Nestlé', true, 2, 6);

INSERT INTO item values (16,'7.99', 'Sorvete 2 Bolas - Chocolate', 'Sorvete com 2 Bolas sabor chocolate', true, 2, 6);

INSERT INTO item values (17,'4.99', 'Bolo de Brigadeiro', 'Fatia de Bolo de Brigadeiro', true, 2, 5);

INSERT INTO item values (18,'4.99', 'Bolo de Cenoura', 'Fatia de Bolo de Cenoura com calda de chocolate', true, 2, 5);

INSERT INTO item values (19,'1.99', 'Picolé Nescau', 'Picolé Nescau da Nestlé', false, 2, 6);

INSERT INTO item values (20,'4.99', 'Picolé Magnum', 'Picolé Magnum de Chocolate Branco da Nestlé', true, 2, 6);

INSERT INTO item values (21,'6.50', 'X-Burguer', 'Sanduíche com hambúrguer, salada, queijo e batata palha', true, 3, 7);

INSERT INTO item values (22,'7.00', 'X-Tudo', 'Sanduíche com hambúrguer, salada, queijo, bacon, ovo, batata palha e tudo mais', true, 3, 7);

INSERT INTO "table" values (1, 'F', 4);

INSERT INTO "table" values (2, 'R', 6);

INSERT INTO "table" values (3, 'O', 4);

INSERT INTO "table" values (4, 'F', 6);

INSERT INTO "table" values (5, 'F', 6);

INSERT INTO Reservation values (2, '28-11-2017', '19:00:00', 6, 'Geraldo', '(31)3399-9928', '(31)99999-8888');

INSERT INTO bill values ('teste4321', '23-11-2017', 'O');

INSERT INTO bill_table values ('teste4321', 3);

INSERT INTO "order" values ('teste4321', '2017-11-23 19:43:38.048225-03', 1, 'D', '4.50', 2);

INSERT INTO "order" values ('teste4321', '2017-11-23 19:48:38.048225-03', 21, 'N', '7.00', 1);

INSERT INTO Employee (nom_name, idt_profile, nom_username, txt_password) values ('Gilson', 'W', 'gilgil', 'deusetop');

INSERT INTO Employee (nom_name, idt_profile, nom_username, txt_password) values ('Vanderley', 'M', 'admin', 'admin');
