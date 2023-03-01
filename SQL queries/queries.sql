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
	saleID integer(20),
	advisorID integer(3) NOT NULL,
	customerEmail varchar(30) NULL,

	dateSold date,
	paymentType varchar(4),
	currecy varchar(5),
	price decimal(15,2),
	saleDiscountAmount decimal(15,2),
	saleCommisionAmount decimal(15,2),
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
	CouponID integer(1),
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
	commisionAmount decimal(3,2),
	discountAmount decimal(3,2),
	flexibleDiscountAmount decimal(3,2),
	flexibleDiscountThreshold decimal(15,2),
	autoBackupFreqDays integer(3) DEFAULT 30,
	timeSinceLastBackup integer(3) DEFAULT 0
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
INSERT INTO Coupon VALUES
	(0, 444, 00000001, 26022023, 1230, 'London','Paris'),
	(1, 444, 00000001, 26022023, 1500, 'Paris','Berlin'),
	(2, 444, 00000001, 26022023, 1915, 'Berlin','Amsterdam');



/* UPDATE STATEMENTS */

/* Assign 3 tickets to an Advisor */
INSERT INTO Employee VALUES
	("bob@gmail.com", "password", "bob");

INSERT INTO Employee VALUES
	(1, "bob@gmail.com");


UPDATE Ticket
SET advisorID = 001
WHERE (ticketType = 444) AND (ticketNumber BETWEEN 00000001 AND 00000004);

/* An error was made on ticket 2, void this ticket*/
UPDATE Ticket
SET isValid = 0
WHERE (ticketType = 444) AND (ticketNumber = 00000002);





/* SELECT STATEMENTS */

/* select tickets without an advisor assigned to them */
SELECT ticketType, ticketNumber
FROM Ticket
WHERE advisorID IS NULL;

/* select overdue late payment sales*/
SELECT customerEmail, advisorID
FROM SALE
WHERE (DATEDIFF(NOW(), dateSold) > 30);






/* DELETE STATEMENTS */

/* a customer would like their account to be deleted */
DELETE FROM RegisteredCustomer
WHERE email = 'steve@gmail.com';

/* delete coupons made in error for ticket 444 00000003 */
DELETE FROM Coupon
WHERE ticketType = 444 AND ticketNumber = 00000003;




/* STATEMENTS FOR GLOBAL SALES REPORT */








/* STATEMENTS FOR TICKET TUROVER REPORT */