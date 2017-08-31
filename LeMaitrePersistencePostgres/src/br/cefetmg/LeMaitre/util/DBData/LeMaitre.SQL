/*
Created: 01/06/2017
Modified: 19/07/2017
Model: Le Maitre
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- Table Table

CREATE TABLE "Table"(
 "COD_id" BigSerial NOT NULL,
 "IDT_status" Character(1) NOT NULL,
 "NRO_seat" Integer NOT NULL
)
;
COMMENT ON COLUMN "Table"."IDT_status" IS 'O - occupied
F - free
R - reserved'
;

-- Add keys for table Table

ALTER TABLE "Table" ADD CONSTRAINT "Key1" PRIMARY KEY ("COD_id")
;

-- Table Order

CREATE TABLE "Order"(
 "COD_id" BigSerial NOT NULL,
 "COD_item" Bigint NOT NULL,
 "IDT_status" Character(1) NOT NULL,
 "VLR_price" Double precision NOT NULL,
 "COD_token" Bigint
)
;
COMMENT ON COLUMN "Order"."IDT_status" IS 'D - delivered
P - in progress
N - not started'
;

-- Add keys for table Order

ALTER TABLE "Order" ADD CONSTRAINT "Key2" PRIMARY KEY ("COD_id","COD_item")
;

-- Table Bill

CREATE TABLE "Bill"(
 "COD_id" BigSerial NOT NULL,
 "DAT_use" Date NOT NULL,
 "IDT_status" Character(1) NOT NULL
)
;

-- Add keys for table Bill

ALTER TABLE "Bill" ADD CONSTRAINT "Key3" PRIMARY KEY ("COD_id")
;

-- Table Item

CREATE TABLE "Item"(
 "COD_item" BigSerial NOT NULL,
 "VLR_price" Double precision NOT NULL,
 "NOM_item" Varchar NOT NULL,
 "DES_item" Varchar NOT NULL,
 "IDT_available" Character(1) NOT NULL,
 "COD_category" Bigint NOT NULL
)
;

-- Create indexes for table Item

CREATE INDEX "IX_Relationship7" ON "Item" ("COD_category")
;

-- Add keys for table Item

ALTER TABLE "Item" ADD CONSTRAINT "Key4" PRIMARY KEY ("COD_item")
;

-- Table Menu

CREATE TABLE "Menu"(
)
;

-- Table Employee

CREATE TABLE "Employee"(
 "COD_id" BigSerial NOT NULL,
 "NOM_name" Varchar NOT NULL,
 "IDT_profile" Character(1) NOT NULL,
 "NOM_username" Varchar NOT NULL,
 "TXT_password" Varchar NOT NULL
)
;
COMMENT ON COLUMN "Employee"."IDT_profile" IS 'M - Manager
W - Waiter'
;

-- Add keys for table Employee

ALTER TABLE "Employee" ADD CONSTRAINT "Key6" PRIMARY KEY ("COD_id")
;

-- Table Reservation

CREATE TABLE "Reservation"(
 "COD_id" BigSerial NOT NULL,
 "DAT_reservation" Date NOT NULL,
 "DAT_hour_reservation" Time NOT NULL,
 "NRO_persons" Integer NOT NULL,
 "TXT_contact_name" Varchar NOT NULL,
 "TXT_telephone" Varchar,
 "TXT_cellphone" Varchar NOT NULL
)
;

-- Add keys for table Reservation

ALTER TABLE "Reservation" ADD CONSTRAINT "Key9" PRIMARY KEY ("COD_id","DAT_reservation","DAT_hour_reservation")
;

-- Table Waiter

CREATE TABLE "Waiter"(
)
;

-- Table Bill_Table

CREATE TABLE "Bill_Table"(
 "COD_id_bill" Bigint NOT NULL,
 "COD_id_table" Bigint NOT NULL
)
;

-- Add keys for table Bill_Table

ALTER TABLE "Bill_Table" ADD CONSTRAINT "Key11" PRIMARY KEY ("COD_id_table","COD_id_bill")
;

-- Table Category

CREATE TABLE "Category"(
 "COD_category" BigSerial NOT NULL,
 "NOM_category" Varchar NOT NULL
)
;

-- Add keys for table Category

ALTER TABLE "Category" ADD CONSTRAINT "Key12" PRIMARY KEY ("COD_category")
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "Bill_Table" ADD CONSTRAINT "Relationship5" FOREIGN KEY ("COD_id_table") REFERENCES "Table" ("COD_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Reservation" ADD CONSTRAINT "Relationship6" FOREIGN KEY ("COD_id") REFERENCES "Table" ("COD_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Item" ADD CONSTRAINT "Relationship7" FOREIGN KEY ("COD_category") REFERENCES "Category" ("COD_category") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Bill_Table" ADD CONSTRAINT "Relationship11" FOREIGN KEY ("COD_id_bill") REFERENCES "Bill" ("COD_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Order" ADD CONSTRAINT "Relationship12" FOREIGN KEY ("COD_id") REFERENCES "Bill" ("COD_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "Order" ADD CONSTRAINT "Relationship13" FOREIGN KEY ("COD_item") REFERENCES "Item" ("COD_item") ON DELETE NO ACTION ON UPDATE NO ACTION
;