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
    state_province_region		varchar(100) not null,
    zip_postal_code				varchar(7) not null,
    
    
	primary key (tax_receipt_id)
);

CREATE TABLE TRANSACTION (
	id							varchar(30)	not null,
    currency					varchar(4) not null,
    amount 						float not null,
    time						DateTime,
    final_amount				float,
	
    
	primary key (id)
);

CREATE TABLE FEE (
	fee_id						varchar(30)	not null,
    amount 						float not null,
    fee_type					varchar(15) not null,
    transaction_id				varchar(30) not null,
	
    
	primary key (fee_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

CREATE TABLE CRYPTO_TRANSFER (
    transaction_id				varchar(30) not null,
    exchange_reference_id		varchar(30) not null,
    transaction_type			varchar(15) not null,
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

CREATE TABLE TRADE (
    transaction_id				varchar(30) not null,
    exchange_reference_id		varchar(30) not null,
    to_currency					varchar(4) not null,
    converted_amount			float,
    
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

CREATE TABLE EXCHANGE_BANK_TRANSFER (
    transaction_id				varchar(30) not null,
    exchange_reference_id		varchar(30) not null,
    
	
	primary key (transaction_id),
    foreign key (transaction_id) references TRANSACTION (id)
);

DROP TABLE IF EXISTS CURRENCY_DONATION;
CREATE TABLE CURRENCY_DONATION (
	donation_id					varchar(30)	not null,
	from_crypto_address			varchar(30)	not null,
    to_crypto_address			varchar(30)	not null,
    tax_receipt_id				varchar(30) not null,
    non_profit_id				varchar(30)	not null,
    donor_user_id				varchar(30)	not null,
	cryptocurrency_tx_id		varchar(30)	not null,
    crypto_transfer_id			varchar(30)	not null,
    trade_id					varchar(30)	not null,
    exchange_bank_transfer_id	varchar(30)	not null,
    status						varchar(10) not null,

    
	primary key (tax_receipt_id)
);

