SET REFERENTIAL_INTEGRITY FALSE;

TRUNCATE TABLE account;
SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO account(accountnumber, locationcode, holdername, accountbalance, activestatus, city, state, country, currency, bankname)
VALUES(987654,112233,'Apratim', 52.36,true,'New York','NY', 'US', 'USD', 'Manhattan Bank');
INSERT INTO account(accountnumber, locationcode, holdername, accountbalance, activestatus, city, state, country, currency, bankname)
VALUES(987664,112233,'Marko', 62.36,true,'New York','NY', 'US', 'USD','Manhattan Bank');
INSERT INTO account(accountnumber, locationcode, holdername, accountbalance, activestatus, city, state, country, currency, bankname)
VALUES(987659,112233,'Sams Groceries', 2227.36,true,'New York','NY', 'US', 'USD','Manhattan Bank');
INSERT INTO account(accountnumber, locationcode, holdername, accountbalance, activestatus, city, state, country, currency, bankname)
VALUES(987668,112233,'Kits Pharmacy', 62.36,true,'New York','NY', 'US', 'USD','Manhattan Bank');

INSERT INTO account(accountnumber, locationcode, holdername, accountbalance, activestatus, city, state, country, currency, bankname)
VALUES(787668,142233,'Sam', 1162.06,true,'San Francisco','California', 'US', 'USD','Bank of America');
INSERT INTO account(accountnumber, locationcode, holdername, accountbalance, activestatus, city, state, country, currency, bankname)
VALUES(787618,142233,'Kit', 0.01,false,'San Francisco','California', 'US', 'USD','Bank of America');