/* CREATE TABLE STATEMENTS 

Pattern: 
CREATE TABLE Name(
Keys,

Attributes,

Key Definitions
);

*/

CREATE TABLE Employee(
	email varchar(30),

    password varchar(20) NOT NULL,
    name varchar(30) NOT NULL,

    PRIMARY KEY (email)
);

CREATE TABLE Administrator(
	admID integer(3),
	email varchar(30) NOT NULL,

	PRIMARY KEY (admID),
	FOREIGN KEY (email) REFERENCES Employee (email)
);

CREATE TABLE Manager(
	manID integer(3),
	email varchar(30) NOT NULL,

	PRIMARY KEY (manID),
	FOREIGN KEY (email) REFERENCES Employee (email)
);

CREATE TABLE Advisor(
	advID integer(3),
	email varchar(30) NOT NULL,

	PRIMARY KEY (advID),
	FOREIGN KEY (email) REFERENCES Employee (email)
);

CREATE TABLE RegisteredCustomer(
	email varchar(30),

	name varchar(30) NOT NULL,
	isValued tinyint(1) NOT NULL DEFAULT 0,
	spentThisMonth bigint(8) NOT NULL DEFAULT 0,
	discountOrRefundToReturn bigint(8) NOT NULL DEFAULT 0,

	PRIMARY KEY (email)
);

CREATE TABLE Sale(
	saleID integer(20) AUTO_INCREMENT,
	advisorID integer(3) NOT NULL,
	customerEmail varchar(30) NULL,

	dateSold date,
	paymentType varchar(4),
	currecy varchar(5),
	price integer(20),
	saleDiscountAmount integer(4),
	saleTaxAmount integer(4),
	saleCommissionAmount integer(4),
	isDomestic tinyint(1),
	isPaid tinyint(1) DEFAULT 0,
	isRefunded tinyint(1) DEFAULT 0,

	PRIMARY KEY (saleID),
	FOREIGN KEY (advisorID) REFERENCES Advisor (advID),
	FOREIGN KEY (customerEmail) REFERENCES RegisteredCustomer (email)
);

CREATE TABLE Ticket(
	ticketType integer(3),
	ticketNumber integer(8),
	advisorID integer(3),
	saleID integer(20),

	isValid tinyint(1) DEFAULT 0,
	seats integer(3) DEFAULT 1,

	UNIQUE (ticketType, ticketNumber),
	PRIMARY KEY (ticketType, ticketNumber),
	FOREIGN KEY (advisorID) REFERENCES Advisor (advID),
	FOREIGN KEY (saleID) REFERENCES Sale (saleID)
);

CREATE TABLE Coupon(
	CouponID integer(1) AUTO_INCREMENT,
	ticketType integer(3),
	ticketNumber integer(8),

	flightDepartureDate date,
	flightDepartureTime integer(4),
	departFrom varchar(30),
	flightTo varchar(30),

	UNIQUE (CouponID, ticketType, ticketNumber),
	PRIMARY KEY  (CouponID, ticketType, ticketNumber),
	FOREIGN KEY (ticketType, ticketNumber) REFERENCES Ticket (ticketType, ticketNumber)
);

CREATE TABLE SystemSettings(
	commissionAmount integer(4),
	saleTaxAmount integer(4),
	discountAmount integer(4),
	flexibleDiscountAmount integer(4),
	flexibleDiscountThreshold integer(8),
	autoBackupFreqDays integer(3) DEFAULT 7,
	lastBackup date
);





/* INSERT STATEMENTS */

/*new ticket stocks added to system*/
INSERT INTO Ticket VALUES
	(444, 00000001, NULL, NULL, False, 1),
	(444, 00000002, NULL, NULL, False, 1),
	(444, 00000003, NULL, NULL, False, 3),
	(444, 00000004, NULL, NULL, False, 4),
	(451, 00000001, NULL, NULL, False, 2);

/*3 add coupons for international ticket with 3 legs for journey */
INSERT INTO Coupon(ticketType, ticketNumber, flightDepartureDate, flightDepartureTime, departFrom, flightTo) VALUES
	(444, 00000001, 26022023, 1230, 'London','Paris'),
	(444, 00000001, 26022023, 1500, 'Paris','Berlin'),
	(444, 00000001, 26022023, 1915, 'Berlin','Amsterdam');

/* Other assign statements for reports */
INSERT INTO Employee VALUES
	("bob@gmail.com", "password", "bob");

INSERT INTO Advisor VALUES
	(1, "bob@gmail.com");

INSERT INTO Sale(advisorID, customerEmail, dateSold, paymentType, currecy, price, saleDiscountAmount, saleTaxAmount, saleCommissionAmount, isDomestic, isPaid, isRefunded) VALUES
	(1, NULL, "2023-15-02", "card", "USD", 20000, 1000, 2000, 500, False, True, False),
	(1, NULL, "1990-01-01", "cash", "USD", 20000, 1000, 2000, 500, False, True, False);




/* UPDATE STATEMENTS */

/* Assign 3 tickets to an Advisor */
UPDATE Ticket
SET 
	advisorID = 001
WHERE (ticketType = 444) AND (ticketNumber BETWEEN 1 AND 4);


/* Sell 3 tickets*/
UPDATE Ticket
SET isValid = True,
	saleID = 1
WHERE (ticketType = 444) AND (ticketNumber = 1 OR ticketNumber = 2);

UPDATE Ticket
SET isValid = True,
	saleID = 2
WHERE (ticketType = 444) AND ticketNumber = 3;





/* SELECT STATEMENTS */

/* select tickets without an advisor assigned to them */
SELECT ticketType, ticketNumber
FROM Ticket
WHERE advisorID IS NULL;

/* select overdue late payment sales*/
SELECT saleId, customerEmail, advisorID, dateSold
FROM Sale
WHERE (DATEDIFF(NOW(), dateSold) > 30);






/* DELETE STATEMENTS */

/* a customer would like their account to be deleted */
DELETE FROM RegisteredCustomer
WHERE email = 'steve@gmail.com';

/* delete coupons made in error for ticket 444 00000003 */
DELETE FROM Coupon
WHERE ticketType = 444 AND ticketNumber = 00000003;




/* STATEMENTS FOR Induvidual Interline SALES REPORT for advisor ID 1 */
/*Induvidual sales info */
SELECT 
	s.saleID, s.isPaid, s.paymentType,
	s.price / 100
		AS 'Price',
	((s.price / 100) - ((s.price / 100) * (s.saleDiscountAmount / 10000))) 
		AS 'Discounted Price',
	((s.price / 100) * (s.saleDiscountAmount / 10000)) 
		AS 'Discount',
	(((s.price / 100) - ((s.price / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.saleCommissionAmount / 10000)) 
		AS 'Commission Amount',
	(((s.price / 100) - ((s.price / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.saleTaxAmount / 10000)) 
		AS 'TaxAmount',
	(s.price / 100 
		- (((s.price / 100) - ((s.price / 100) 
			* (s.saleDiscountAmount / 10000))) * (s.saleCommissionAmount / 10000)) 
		- (((s.price / 100) - ((s.price / 100) 
			* (s.saleDiscountAmount / 10000))) * (s.saleTaxAmount / 10000))) 
		AS 'Remaining NonAssess Amount'
FROM Sale s, Ticket t
WHERE
	t.advisorID = s.advisorID
	AND s.advisorID = 1
	AND t.isValid = 1
	AND s.isDomestic = False
	AND s.isRefunded = False
	GROUP BY s.saleID;

/*Sales sums */
SELECT 
	COUNT(*) AS 'Tickets Sold by Advisor'
FROM Ticket t, Sale s
WHERE
	
	t.advisorID = 1
	AND t.saleID IS NOT NULL
	AND t.advisorID = s.advisorID
	AND t.isValid = 1
	AND s.isDomestic = False
	AND s.isRefunded = False
	GROUP BY t.advisorID;

SELECT
	SUM(s.price / 100)
		AS 'Total Price',
	SUM((s.price / 100) - ((s.price / 100) * (s.saleDiscountAmount / 10000))) 
		AS 'Total Discounted Price',
	SUM((s.price / 100) * (s.saleDiscountAmount / 10000)) 
		AS 'Total Discount',
	SUM(((s.price / 100) - ((s.price / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.saleCommissionAmount / 10000)) 
		AS 'Total Commission Amount',
	SUM(((s.price / 100) - ((s.price / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.saleTaxAmount / 10000)) 
		AS 'Total TaxAmount',
	SUM(s.price / 100 
		- (((s.price / 100) - ((s.price / 100) 
			* (s.saleDiscountAmount / 10000))) * (s.saleCommissionAmount / 10000)) 
		- (((s.price / 100) - ((s.price / 100) 
			* (s.saleDiscountAmount / 10000))) * (s.saleTaxAmount / 10000))) 
		AS 'Total Remaining NonAssess Amount'
FROM Sale s
JOIN Ticket t ON s.saleID = t.saleID
WHERE 
	s.advisorID = 1
	AND s.saleID = t.saleID
	AND t.advisorID = s.saleID
	AND t.isValid = 1
	AND s.isDomestic = False
	AND s.isRefunded = False
GROUP BY s.advisorID;




/* STATEMENTS FOR TICKET TUROVER REPORT */




/* DROP TABLES */
/*
DROP TABLE Coupon;
DROP TABLE Ticket;
DROP TABLE Sale;
DROP TABLE RegisteredCustomer;
DROP TABLE Advisor;
DROP TABLE Manager;
DROP TABLE Administrator;
DROP TABLE Employee;
DROP TABLE SystemSettings;
*/