/*
Create and Populate the DB.

Alexander Kruger
Felipe de Arruda Gebara Moreira
Michael Metz

Created for use by Benevity Calgary
*/

DROP DATABASE IF EXISTS CRYPTO_DONATION_WEBAPP_DB;
CREATE DATABASE CRYPTO_DONATION_WEBAPP_DB; 
USE CRYPTO_DONATION_WEBAPP_DB;

DROP TABLE IF EXISTS TAX_RECEIPT;
CREATE TABLE TAX_RECEIPT (
	tax_receipt_id				integer	not null auto_increment,
	amount						float,
    given_names					varchar(100) not null,
    last_name					varchar(100) not null,
    email						varchar(30) not null,
    address1					varchar(100) not null,
    address2					varchar(100),
    city						varchar(85) not null,
    country						varchar(60) not null,
    state_province_region		varchar(10) not null,
    zip_postal_code				varchar(7) not null,
    
    
	primary key (tax_receipt_id)
);

INSERT INTO TAX_RECEIPT (amount, given_names, last_name, email, address1, address2, city, country, state_province_region, zip_postal_code)
VALUES
(150000.25, 'Reginald', 'Richman', 'RFB@rich.com', '539 Gated Community Rd', null, 'Calgary','Canada', 'AB', 'T6B 5J9'),
(420.69, 'Johnny', 'Cool', 'Coolguy@swag.com', '1121 EpicVille Pl', null, 'Calgary','Canada', 'AB', 'T9A 3B3');

CREATE TABLE TRANSACTION (
	transaction_id				integer	not null auto_increment,
    currency					varchar(4) not null,
    amount 						float not null,
    time						DateTime,
    final_amount				float,
	
    
	primary key (transaction_id)
);

INSERT INTO TRANSACTION (currency, amount, time, final_amount)
VALUES
('ETH', 66.980000, '2022-11-16 06:47:44', 66.971300),
('ETH', 66.971300, '2022-11-16 06:55:32', 66.962503),
('CAD', 149963.85, '2022-11-16 07:01:24', '149932.03'),
('CAD', 149963.85, '2022-11-16 07:01:24', '149932.03'),
('CAD', 149964.85, '2022-11-16 08:01:24', '149932.03'),
('CAD', 149965.85, '2022-11-16 09:01:24', '149932.03'),
('CAD', 149966.85, '2022-11-16 10:01:24', '149932.03');



CREATE TABLE FEE (
	fee_id						integer	not null auto_increment,
    amount 						float not null,
    fee_type					varchar(15) not null,
    transaction_id				integer not null,
	
    
	primary key (fee_id),
    foreign key (transaction_id) references TRANSACTION (transaction_id)
);

INSERT INTO FEE (amount, fee_type, transaction_id)
VALUES
(0.008700, 'Gas', 1),
(0.008797, 'Trade', 1),
(10.000000, 'Wire', 2),
(21.820000, 'Profit', 3),
(21.820000, 'Wire', 5),
(25, 'Gas', 6),
(12.820000, 'Trade', 7),
(43.820000, 'Profit', 4);

CREATE TABLE CRYPTO_TRANSFER (
    transaction_id				integer	not null,
    exchange_reference_id		varchar(30) not null,
    transaction_type			varchar(15) not null,
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (transaction_id)
);

INSERT INTO CRYPTO_TRANSFER (transaction_id, exchange_reference_id, transaction_type)
VALUES
(1, 'Xr7ckw9bhf09', 'Deposit');

CREATE TABLE TRADE (
    transaction_id				integer	not null,
    exchange_reference_id		varchar(30) not null,
    to_currency					varchar(4) not null,
    converted_amount			float,
    
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (transaction_id)
);

INSERT INTO TRADE (transaction_id, exchange_reference_id, to_currency, converted_amount)
VALUES
(3, 'Xr7ckw9bhf09', 'ETH', 149963.85);

CREATE TABLE EXCHANGE_BANK_TRANSFER (
    transaction_id				integer	not null,
    exchange_reference_id		varchar(30) not null,
    
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (transaction_id)
);

INSERT INTO EXCHANGE_BANK_TRANSFER (transaction_id,exchange_reference_id)
VALUES
(5, 'Ys83nfP0a4cb');

DROP TABLE IF EXISTS CURRENCY_DONATION;
CREATE TABLE CURRENCY_DONATION (
	donation_id					integer	not null auto_increment,
	from_crypto_address			varchar(50)	not null,
    to_crypto_address			varchar(50)	not null,
    tax_receipt_id				integer not null,
    non_profit_id				varchar(30)	not null,
    donor_user_id				varchar(30)	not null,
	cryptocurrency_tx_id		varchar(30)	not null,
    crypto_transfer_id			integer not null,
    trade_id					integer not null,
    exchange_bank_transfer_id	integer not null,
    status						varchar(10) not null,

    
	primary key (donation_id),
    foreign key (tax_receipt_id) references TAX_RECEIPT(tax_receipt_id),
    foreign key (crypto_transfer_id) references CRYPTO_TRANSFER(transaction_id),
    foreign key (trade_id) references TRADE(transaction_id),
    foreign key (exchange_bank_transfer_id) references EXCHANGE_BANK_TRANSFER(transaction_id)
);

INSERT INTO CURRENCY_DONATION (from_crypto_address, to_crypto_address, tax_receipt_id, non_profit_id, donor_user_id, cryptocurrency_tx_id, crypto_transfer_id, trade_id, exchange_bank_transfer_id, status)
VALUES
('a5Fhsl3jgwSWghjdhfSdj94nFhls0jlsjk9gdj', 'jhgfsd839nhflk9SDF930hf990SDfhkjh93gAhf', 1, '036-76376807382', 'DR_00101', 'kjds983hkjds', 1, 3, 5, 'Complete');

