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
	flexDiscID integer(20) AUTO_INCREMENT,
	email varchar(30),

	discountRate decimal(5, 2),
	lowerBoundary integer(10),
	upperBoundary integer(10) NULL,

	PRIMARY KEY (flexDiscID),
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
	("bob@gmail.com", "f709f35d0b4b0b88149ee7fd0483d14ca8d1bf5e6956d3372b6accd6b5a0e6ff", "bob"),
	("dave@gmail.com", "f6169ad34b442eb8cbcd3565c141d3ab593dc88ef5903c80802eaf1b763c0969", "bob"),
	("Arthur Daley", "72d3209d73fe29b863b1a57453e12312e02ad70e229cd32dfbb6dce1ca4eb1cf", "Arthur Daley");
	("minnie.minx@gmail.com", "cf8d94fba9deb7244e9f78338d7dbb1cdb9465efa4d6572361a7885d9bddbf58", "Minnie Minx");

INSERT INTO Advisor VALUES
	(1, "bob@gmail.com"),
	(2, "dave@gmail.com");

INSERT INTO Administrator VALUES
	(320, "Arthur Daley");

INSERT INTO Manager VALUES
	(220, "Minnie Minx");

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