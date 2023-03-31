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

    password varchar(64) NOT NULL,
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
	fixedDiscountRate decimal(5, 2) DEFAULT 0,

	PRIMARY KEY (email)
);

CREATE TABLE FlexibleDiscount(
	email varchar(30),

	discountRate decimal(5, 2),
	lowerBoundary integer(10),
	upperBoundary integer(10) NULL,

	PRIMARY KEY (email),
	FOREIGN KEY (email) REFERENCES RegisteredCustomer(email)
);

CREATE TABLE Sale(
	saleID integer(20) AUTO_INCREMENT,
	advisorID integer(3) NOT NULL,
	customerEmail varchar(30) NULL,

	dateSold date,
	paymentType varchar(4) NULL,
	cardNo integer(16),
	paymentProvider varchar(10),
	localCurrency varchar(5),
	exchangeRate decimal(9,4) NULL,
	priceLocal varchar(10),
	priceUSD integer(10) NULL,
	saleDiscountAmount integer(5),
	taxAmount integer(10),
	saleCommissionAmount integer(10),
	isDomestic tinyint(1),
	isPaid tinyint(1) DEFAULT 0,
	refundRequested tinyint(1) DEFAULT 0,
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
	dateReceived date,
	dateAssigned date,

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
	commissionRate decimal(5, 2),
	taxRate decimal(5, 2),
	autoBackupFreqDays integer(3) DEFAULT 7,
	lastBackup date
);

/*Note settings exchange rate is the exchange rate for the day, 
not the same as sale exchange rate - which is for the sale and is saved for refunds*/











/* Other assign statements for reports */

INSERT INTO RegisteredCustomer VALUES
	("dylan@gmail.com", "Dylan Dylandson", False, 0, 0, NULL);

INSERT INTO Employee VALUES
	("bob@gmail.com", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", "bob"),
	("dave@gmail.com", "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", "bob");

INSERT INTO Advisor VALUES
	(1, "bob@gmail.com"),
	(2, "dave@gmail.com");

INSERT INTO Sale(advisorID, 
	customerEmail, 
	dateSold, 
	paymentType,
	localCurrency, 
	priceLocal, 
	saleDiscountAmount, taxAmount, saleCommissionAmount, 
	isDomestic, isPaid, 
	refundRequested, isRefunded) VALUES
	(1, NULL, "2023-02-22", "card", "USD",
		20000, 
		1000, 2000, 500, 
		False, True, 
		False, False),
	(1, NULL, "2023-02-22", "card", "USD",
		23000, 
		1500, 2000, 500, 
		False, True, 
		False, False),
	(1, NULL, "1990-02-22", "card", "USD",
		16000, 
		1500, 2000, 500, 
		False, True, 
		False, False),
	(2, NULL, "2023-02-22", "card", "USD",
		20000, 
		1000, 2000, 500, 
		False, False, 
		False, False);














/* INSERT STATEMENTS */
/*new ticket stocks added to system*/
INSERT INTO Ticket (ticketType, ticketNumber, advisorId, saleID, isValid, dateReceived, dateAssigned) VALUES
	(444, 1, NULL, NULL, False, "2023-02-15", NULL),
	(444, 2, 1, 1, True, "2023-02-15", "2023-02-20"),
	(444, 3, 2, NULL, False, "2023-02-15", "2023-02-20"),
	(444, 4, NULL, NULL, False, "2023-02-15", NULL),
	(451, 1, 1, 1, True, "2023-02-15", "2023-02-20"),
	(444, 5, 2, 2, True, "2023-02-15", "2023-02-20"),
	(444, 6, 2, 3, True, "2023-02-15", "2023-02-20"),
	(444, 7, 1, 2, True, "2023-02-15", "2023-02-20"),
	(444, 8, NULL, NULL, False, "2023-02-15", NULL),
	(451, 2, 2, 3, True, "2023-02-15", "2023-02-20"),
	(444, 9, 2, NULL, True, "2023-02-15", "2023-02-20"),
	(444, 10, 1, NULL, True, "2023-02-15", "2023-02-20"),
	(444, 11, NULL, NULL, True, "2023-02-15", NULL),
	(444, 12, NULL, NULL, False, "2023-02-15", NULL),
	(451, 3, NULL, NULL, True, "2023-02-15", NULL);



/* add coupons for international ticket with 3 legs for journey */
INSERT INTO Coupon(ticketType, ticketNumber, flightDepartureDate, flightDepartureTime, departFrom, flightTo) VALUES
	(444, 00000007, "2023-05-20", 1230, 'London','Paris'),
	(444, 00000007, "2023-05-20", 1500, 'Paris','Berlin'),
	(444, 00000007, "2023-05-20", 1915, 'Berlin','Amsterdam');

INSERT INTO SystemSettings(
	commissionRate,
	taxRate,
	autoBackupFreqDays,
	lastBackup) VALUES
	(10, 20, 3, "2023-05-20");










/* UPDATE STATEMENTS */

/* Assign 3 tickets to an Advisor */
UPDATE Ticket
SET 
	advisorID = 1,
	dateAssigned = NOW()
WHERE (ticketType = 444) AND (ticketNumber BETWEEN 1 AND 5);


/* Sell 3 tickets*/
UPDATE Ticket t, Sale s
SET t.isValid = True,
	t.saleID = 3,
	s.dateSold = NOW()
WHERE (ticketType = 444) AND (ticketNumber = 1 OR ticketNumber = 2);

UPDATE Ticket t, Sale s
SET t.isValid = True,
	t.saleID = 2,
	s.dateSold = NOW()
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












/* STATEMENTS FOR Induvidual Interline SALES REPORT for advisor ID 1,
period 2023-02-01 to 2023-04-01*/
/*Induvidual sales info */
SELECT 
	s.saleID, s.isPaid, s.paymentType, s.dateSold,
	s.priceLocal / 100
		AS 'Price',
	((s.priceLocal / 100) - ((s.priceLocal / 100) * (s.saleDiscountAmount / 10000))) 
		AS 'Discounted Price',
	((s.priceLocal / 100) * (s.saleDiscountAmount / 10000)) 
		AS 'Discount',
	(((s.priceLocal / 100) - ((s.priceLocal / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.taxAmount
 / 10000)) 
		AS 'Tax',
	(((s.priceLocal / 100) - ((s.priceLocal / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.saleCommissionAmount / 10000)) 
		AS 'Commission',
	(((s.priceLocal / 100) - ((s.priceLocal / 100) 
			* (s.saleDiscountAmount / 10000))) 
		- (((s.priceLocal / 100) - ((s.priceLocal / 100) 
			* (s.saleDiscountAmount / 10000))) 
				* (s.saleCommissionAmount / 10000)))
		AS 'Remaining Agents Amount'
FROM Sale s, Ticket t
WHERE
	t.advisorID = s.advisorID
	AND s.advisorID = 1
	AND t.isValid = True
	AND s.isDomestic = False
	AND s.isRefunded = False
	AND ("2023-02-01" <= s.dateSold)
	AND (s.dateSold <= "2023-04-01")
	GROUP BY s.saleID;

/*Sales sums,
period 2023-02-01 to 2023-04-01*/
SELECT 
	COUNT(*) AS 'Tickets Sold by Advisor'
FROM Sale s
INNER JOIN Ticket t ON s.saleID = t.saleID
WHERE
	t.advisorID = 1
	AND t.saleID IS NOT NULL
	AND t.advisorID = s.advisorID
	AND t.isValid = True
	AND s.isDomestic = False
	AND s.isRefunded = False
	AND ("2023-02-01" <= s.dateSold)
	AND (s.dateSold <= "2023-04-01")
	GROUP BY t.advisorID;

SELECT
	SUM(s.priceLocal / 100)
		AS 'Total Price',
	SUM((s.priceLocal / 100) - ((s.priceLocal / 100) * (s.saleDiscountAmount / 10000))) 
		AS 'Total Discounted Price',
	SUM((s.priceLocal / 100) * (s.saleDiscountAmount / 10000)) 
		AS 'Total Discount',
	SUM(((s.priceLocal / 100) - ((s.priceLocal / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.taxAmount
 / 10000)) 
		AS 'Total Tax',
	SUM(((s.priceLocal / 100) - ((s.priceLocal / 100) 
		* (s.saleDiscountAmount / 10000))) * (s.saleCommissionAmount / 10000)) 
		AS 'Total Commission',
	SUM(((s.priceLocal / 100) - ((s.priceLocal / 100) 
			* (s.saleDiscountAmount / 10000))) 
		- (((s.priceLocal / 100) - ((s.priceLocal / 100) 
			* (s.saleDiscountAmount / 10000))) 
				* (s.saleCommissionAmount / 10000)))
		AS 'Total Agents Remaining Amount'
FROM Sale s
WHERE 
	s.advisorID = 1
	AND s.isDomestic = False
	AND s.isRefunded = False
	AND ("2023-02-01" <= s.dateSold)
	AND (s.dateSold <= "2023-04-01")
;








/* Column 1a STATEMENTS FOR TICKET TUROVER REPORT */
/* All tickets received in
period 2023-02-01 to 2023-04-01*/
SELECT 
	ticketType, ticketNumber, dateReceived
FROM Ticket
WHERE 
	("2023-02-01" <= dateReceived)
	AND (dateReceived <= "2023-04-01")
ORDER BY ticketType;

/*Total ^^ */
SELECT 
	COUNT(*)
FROM Ticket
WHERE 
	("2023-02-01" <= dateReceived)
	AND (dateReceived <= "2023-04-01");



/* Column 1b All tickets received and assigned in
period 2023-02-01 to 2023-04-01*/
SELECT 
	advisorID, ticketType, ticketNumber, dateReceived, dateAssigned
FROM Ticket
WHERE 
	("2023-02-01" <= dateReceived)
	AND (dateReceived <= "2023-04-01")
	AND ("2023-02-01" <= dateAssigned)
	AND (dateAssigned <= "2023-04-01")
ORDER BY ticketType;

/*Total ^^ */
SELECT 
	COUNT(*)
FROM Ticket
WHERE 
	("2023-02-01" <= dateReceived)
	AND (dateReceived <= "2023-04-01")
	AND ("2023-02-01" <= dateAssigned)
	AND (dateAssigned <= "2023-04-01")
ORDER BY advisorID, ticketType;


/* Column 2a All tickets assigned to advisors within 
period 2023-02-01 to 2023-04-01*/
SELECT 
	advisorID, ticketType, ticketNumber, dateAssigned
FROM Ticket
WHERE 
	("2023-02-01" <= dateAssigned)
	AND (dateAssigned <= "2023-04-01")
ORDER BY ticketType;


/*Total ^^ */
SELECT 
	COUNT(*)
FROM Ticket
WHERE 
	("2023-02-01" <= dateAssigned)
	AND (dateAssigned <= "2023-04-01")
ORDER BY advisorID, ticketType;


/* Column 2b All tickets used within 
period 2023-02-01 to 2023-04-01*/
SELECT 
	t.ticketType, t.ticketNumber, s.dateSold
FROM Ticket t, Sale s
WHERE 
	t.advisorID = s.saleID
	AND s.isRefunded = False
	AND t.isValid = True
	AND("2023-02-01" <= s.dateSold)
	AND (s.dateSold <= "2023-04-01")
ORDER BY t.ticketType, t.ticketNumber;

/*Total ^^ */
SELECT 
	COUNT(*)
FROM Ticket t, Sale s
WHERE 
	t.advisorID = s.saleID
	AND s.isRefunded = False
	AND t.isValid = True
	AND("2023-02-01" <= s.dateSold)
	AND (s.dateSold <= "2023-04-01");



/* Column 3a All tickets that are unsold and available 
period 2023-02-01 to 2023-04-01*/
SELECT 
	ticketType, ticketNumber
FROM Ticket t, Sale s
WHERE 
	(t.saleID IS NULL
	OR (s.dateSold <= "2023-04-01"))
GROUP by t.ticketType, t.ticketNumber
ORDER BY t.ticketType;

/*Total ^^ */
SELECT DISTINCT
	COUNT(DISTINCT(ticketNumber), ticketType)
FROM Ticket t, Sale s
WHERE 
	(t.saleID IS NULL
	OR (s.dateSold <= "2023-04-01"));



/* Column 3b All tickets that are unsold and assigned by type 
period 2023-02-01 to 2023-04-01*/
SELECT 
	t.advisorID, ticketType, ticketNumber
FROM Ticket t, Sale s
WHERE 
	(t.saleID IS NULL
	OR (s.dateSold <= "2023-04-01"))
	AND t.advisorID IS NOT NULL
GROUP by t.advisorID, t.ticketType, t.ticketNumber
ORDER BY t.advisorID, t.ticketType;

/* Detailed unsold assigned tickets */
SELECT 
	COUNT(DISTINCT(ticketNumber), ticketType)
FROM Ticket t, Sale s
WHERE 
	(t.saleID IS NULL
	OR (s.dateSold <= "2023-04-01"))
	AND t.advisorID IS NOT NULL;














/* DELETE STATEMENTS */

/* a customer would like their account to be deleted */
DELETE FROM RegisteredCustomer
WHERE email = 'dylan@gmail.com';

/* delete coupons made in error for ticket 444 00000003 */
DELETE FROM Coupon
WHERE ticketType = 444 AND ticketNumber = 00000007;








/* DROP TABLES */
/*
DROP TABLE Coupon;
DROP TABLE Ticket;
DROP TABLE Sale;
DROP TABLE FlexibleDiscount;
DROP TABLE RegisteredCustomer;
DROP TABLE Advisor;
DROP TABLE Manager;
DROP TABLE Administrator;
DROP TABLE Employee;
DROP TABLE SystemSettings;
*/