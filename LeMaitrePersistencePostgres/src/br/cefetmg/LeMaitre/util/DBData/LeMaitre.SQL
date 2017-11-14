/*
Created: 01/06/2017
Modified: 19/10/2017
Model: Le Maitre
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- table table

CREATE TABLE  "table" (
  COD_ID  Integer NOT NULL,
  IDT_status  Character(1) NOT NULL,
  NRO_seat  Integer NOT NULL
)
;
COMMENT ON COLUMN  "table" . IDT_status  IS 'O - occupied
F - free
R - reserved'
;

-- Add keys for table table

ALTER TABLE  "table"  ADD CONSTRAINT  Key1  PRIMARY KEY ( COD_ID )
;

-- table order

CREATE TABLE  "order" (
  COD_ID_Bill  Varchar NOT NULL,
  DAT_order  Date NOT NULL,
  COD_Item  Integer NOT NULL,
  IDT_status  Character(1) NOT NULL,
  VLR_price  Double precision NOT NULL,
  QTD_item  Integer NOT NULL
)
;
COMMENT ON COLUMN  "order" . IDT_status  IS 'D - delivered
P - in progress
N - not started'
;

-- Create indexes for table order

CREATE INDEX  IX_Relationship13  ON  "order"  ( COD_Item )
;

-- Add keys for table order

ALTER TABLE  "order"  ADD CONSTRAINT  Key2  PRIMARY KEY ( COD_ID_Bill , DAT_order )
;

-- table Bill

CREATE TABLE  Bill (
  COD_token  Varchar NOT NULL,
  DAT_use  Date NOT NULL,
  IDT_status  Character(1) NOT NULL
)
;

-- Add keys for table Bill

ALTER TABLE  Bill  ADD CONSTRAINT  Key3  PRIMARY KEY ( COD_token )
;

-- table Item

CREATE TABLE  Item (
  COD_Item  Serial NOT NULL,
  VLR_price  Double precision NOT NULL,
  NOM_item  Varchar NOT NULL,
  DES_item  Varchar NOT NULL,
  IDT_available  Character(1) NOT NULL,
  SEQ_Category  Integer,
  SEQ_Subcategory  Integer
)
;

-- Create indexes for table Item

CREATE INDEX  IX_Relationship15  ON  Item  ( SEQ_Category , SEQ_Subcategory )
;

-- Add keys for table Item

ALTER TABLE  Item  ADD CONSTRAINT  Key4  PRIMARY KEY ( COD_Item )
;

-- table Menu

CREATE TABLE  Menu (
)
;

-- table Employee

CREATE TABLE  Employee (
  COD_ID  Integer NOT NULL,
  NOM_name  Varchar NOT NULL,
  IDT_profile  Character(1) NOT NULL,
  NOM_username  Varchar NOT NULL,
  TXT_password  Varchar NOT NULL
)
;
COMMENT ON COLUMN  Employee . IDT_profile  IS 'M - Manager
W - Waiter'
;

-- Add keys for table Employee

ALTER TABLE  Employee  ADD CONSTRAINT  Key6  PRIMARY KEY ( COD_ID )
;

-- table Reservation

CREATE TABLE  Reservation (
  COD_ID_Table  Integer NOT NULL,
  DAT_reservation  Date NOT NULL,
  DAT_hour_reservation  Time NOT NULL,
  NRO_persons  Integer NOT NULL,
  TXT_contact_name  Varchar NOT NULL,
  TXT_telephone  Varchar,
  TXT_cellphone  Varchar NOT NULL
)
;

-- Add keys for table Reservation

ALTER TABLE  Reservation  ADD CONSTRAINT  Key9  PRIMARY KEY ( COD_ID_Table , DAT_reservation , DAT_hour_reservation )
;

-- table Bill_Table

CREATE TABLE  Bill_Table (
  COD_ID_Bill  Varchar NOT NULL,
  COD_ID_Table  Integer NOT NULL
)
;

-- Add keys for table Bill_Table

ALTER TABLE  Bill_Table  ADD CONSTRAINT  Key11  PRIMARY KEY ( COD_ID_Table , COD_ID_Bill )
;

-- table Category

CREATE TABLE  Category (
  SEQ_Category  Serial NOT NULL,
  NOM_category  Varchar NOT NULL
)
;

-- Add keys for table Category

ALTER TABLE  Category  ADD CONSTRAINT  Key12  PRIMARY KEY ( SEQ_Category )
;

-- table Subcategory

CREATE TABLE  Subcategory (
  SEQ_Category  Integer NOT NULL,
  SEQ_Subcategory  Serial NOT NULL,
  NOM_subcategory  Varchar NOT NULL
)
;

-- Add keys for table Subcategory

ALTER TABLE  Subcategory  ADD CONSTRAINT  Key13  PRIMARY KEY ( SEQ_Category , SEQ_Subcategory )
;

-- table Image

CREATE TABLE  Image (
  COD_image  BigSerial NOT NULL,
  DES_location  Varchar NOT NULL
)
;

-- Add keys for table Image

ALTER TABLE  Image  ADD CONSTRAINT  Key14  PRIMARY KEY ( COD_image )
;

-- table Item_Image

CREATE TABLE  Item_Image (
  COD_Item  Integer NOT NULL,
  COD_image  Bigint NOT NULL
)
;

-- Add keys for table Item_Image

ALTER TABLE  Item_Image  ADD CONSTRAINT  Key15  PRIMARY KEY ( COD_Item , COD_image )
;
-- Create relationships section ------------------------------------------------- 

ALTER TABLE  Bill_Table  ADD CONSTRAINT  Relationship5  FOREIGN KEY ( COD_ID_Table ) REFERENCES  "table"  ( COD_ID ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  Reservation  ADD CONSTRAINT  Relationship6  FOREIGN KEY ( COD_ID_Table ) REFERENCES  "table"  ( COD_ID ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  Bill_Table  ADD CONSTRAINT  Relationship11  FOREIGN KEY ( COD_ID_Bill ) REFERENCES  Bill  ( COD_token ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  "order"  ADD CONSTRAINT  Relationship12  FOREIGN KEY ( COD_ID_Bill ) REFERENCES  Bill  ( COD_token ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  "order"  ADD CONSTRAINT  Relationship13  FOREIGN KEY ( COD_Item ) REFERENCES  Item  ( COD_Item ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  Subcategory  ADD CONSTRAINT  Relationship14  FOREIGN KEY ( SEQ_Category ) REFERENCES  Category  ( SEQ_Category ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  Item  ADD CONSTRAINT  Relationship15  FOREIGN KEY ( SEQ_Category ,  SEQ_Subcategory ) REFERENCES  Subcategory  ( SEQ_Category ,  SEQ_Subcategory ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  Item_Image  ADD CONSTRAINT  Relationship17  FOREIGN KEY ( COD_Item ) REFERENCES  Item  ( COD_Item ) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE  Item_Image  ADD CONSTRAINT  Relationship18  FOREIGN KEY ( COD_image ) REFERENCES  Image  ( COD_image ) ON DELETE NO ACTION ON UPDATE NO ACTION
;