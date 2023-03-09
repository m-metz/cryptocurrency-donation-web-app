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
	tax_receipt_id				varchar(30)	not null,
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

INSERT INTO TAX_RECEIPT (tax_receipt_id, amount, given_names, last_name, email, address1, address2, city, country, state_province_region, zip_postal_code)
VALUES
('TR_100001',150000.25, 'Reginald', 'Fancy Bottom', 'RFB@rich.com', '539 Gated Community Rd', null, 'Calgary','Canada', 'AB', 'T6B 5J9'),
('TR_100002',420.69, 'Johnny', 'Cool', 'Coolguy@swag.com', '1121 EpicVille Pl', null, 'Calgary','Canada', 'AB', 'T9A 3B3');

CREATE TABLE TRANSACTION (
	id							varchar(30)	not null,
    currency					varchar(4) not null,
    amount 						float not null,
    time						DateTime,
    final_amount				float,
	
    
	primary key (id)
);

INSERT INTO TRANSACTION (id, currency, amount, time, final_amount)
VALUES
('T_0000001_1', 'ETH', 66.980000, '2022-11-16 06:47:44', 66.971300),
('T_0000001_2', 'ETH', 66.971300, '2022-11-16 06:55:32', 66.962503),
('T_0000001_3', 'CAD', '149963.85', '2022-11-16 07:01:24', '149932.03');



CREATE TABLE FEE (
	fee_id						varchar(30)	not null,
    amount 						float not null,
    fee_type					varchar(15) not null,
    transaction_id				varchar(30) not null,
	
    
	primary key (fee_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

INSERT INTO FEE (fee_id, amount, fee_type, transaction_id)
VALUES
('F_0000001_1', 0.008700, 'Gas', 'T_0000001_1'),
('F_0000001_2', 0.008797, 'Trade', 'T_0000001_2'),
('F_0000001_3', 10.000000, 'Wire', 'T_0000001_3'),
('F_0000001_4', 21.820000, 'Profit', 'T_0000001_3');

CREATE TABLE CRYPTO_TRANSFER (
    transaction_id				varchar(30) not null,
    exchange_reference_id		varchar(30) not null,
    transaction_type			varchar(15) not null,
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

INSERT INTO CRYPTO_TRANSFER (transaction_id, exchange_reference_id, transaction_type)
VALUES
('T_0000001_1', 'Xr7ckw9bhf09', 'Deposit');

CREATE TABLE TRADE (
    transaction_id				varchar(30) not null,
    exchange_reference_id		varchar(30) not null,
    to_currency					varchar(4) not null,
    converted_amount			float,
    
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

INSERT INTO TRADE (transaction_id, exchange_reference_id, to_currency, converted_amount)
VALUES
('T_0000001_2', 'Xr7ckw9bhf09', 'ETH', 149963.85);

CREATE TABLE EXCHANGE_BANK_TRANSFER (
    transaction_id				varchar(30) not null,
    exchange_reference_id		varchar(30) not null,
    
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

INSERT INTO EXCHANGE_BANK_TRANSFER (transaction_id, exchange_reference_id)
VALUES
('T_0000001_3', 'Ys83nfP0a4cb');

DROP TABLE IF EXISTS CURRENCY_DONATION;
CREATE TABLE CURRENCY_DONATION (
	donation_id					varchar(30)	not null,
	from_crypto_address			varchar(50)	not null,
    to_crypto_address			varchar(50)	not null,
    tax_receipt_id				varchar(30) not null,
    non_profit_id				varchar(30)	not null,
    donor_user_id				varchar(30)	not null,
	cryptocurrency_tx_id		varchar(30)	not null,
    crypto_transfer_id			varchar(30)	not null,
    trade_id					varchar(30)	not null,
    exchange_bank_transfer_id	varchar(30)	not null,
    status						varchar(10) not null,

    
	primary key (donation_id),
    foreign key (tax_receipt_id) references TAX_RECEIPT(tax_receipt_id),
    foreign key (crypto_transfer_id) references CRYPTO_TRANSFER(transaction_id),
    foreign key (trade_id) references TRADE(transaction_id),
    foreign key (exchange_bank_transfer_id) references EXCHANGE_BANK_TRANSFER(transaction_id)
);

INSERT INTO CURRENCY_DONATION (donation_id, from_crypto_address, to_crypto_address, tax_receipt_id, non_profit_id, donor_user_id, cryptocurrency_tx_id, crypto_transfer_id, trade_id, exchange_bank_transfer_id, status)
VALUES
('D_00000001', 'a5Fhsl3jgwSWghjdhfSdj94nFhls0jlsjk9gdj', 'jhgfsd839nhflk9SDF930hf990SDfhkjh93gAhf', 'TR_100001', 'NP_000123', 'DR_00101', 'kjds983hkjds', 'T_0000001_1', 'T_0000001_2', 'T_0000001_3', 'Complete');

