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

	PRIMARY KEY (manID),
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

	PRIMARY KEY (manID),
	FOREIGN KEY (email) REFERENCES Employee (email)
);

CREATE TABLE RegisteredCustomer(
	email varchar(30),

	name varchar(30) NOT NULL,
	isValued bool() DEFAULT False,
	spentThisMonth decimal(15,2) DEFAULT 0.00,
	discountOrRefundToReturn decimal(15,2) DEFAULT 0.00,

	PRIMARY KEY (email)
);

CREATE TABLE Sale(
	saleID integer(20),
	advisorID integer(3) NOT NULL,
	customerEmail email varchar(30) NULL,

	dateSold integer(8) NOT NULL,
	paymentType varchar(4) NOT NULL,
	currecy varchar(5) DEFAULT "USD",
	price decimal(15,2) NOT NULL,
	saleDiscountAmount decimal(15,2) DEFAULT 0.00,
	saleCommisionAmount decimal(15,2) NOT NULL,
	isDomestic bool() NOT NULL,
	isPaid bool() NOT NULL,
	isRefunded bool() DEFAULT False,

	PRIMARY KEY (saleID),
	FOREIGN KEY (advisorID) REFERENCES Advisor (advID),
	FOREIGN KEY (customerEmail) REFERENCES RegisteredCustomer (email)
);

CREATE TABLE Ticket(
	ticketNumber integer(11),
	advisorID integer(3) NULL,
	saleID integer(20) NULL,

	isValid bool() DEFAULT False,
	isVoid bool() DEFAULT False,

	PRIMARY KEY (ticketNumber),
	FOREIGN KEY (advisorID) REFERENCES Advisor (advID),
	FOREIGN KEY (saleID) REFERENCES Sale (saleID)
);

CREATE TABLE Coupon(
	CouponID integer(1),
	ticketNumber integer(11),

	flightDepartureDate integer(8),
	flightDepartureTime integer(4),
	departFrom varchar(30),
	flightTo varchar(30),

	PRIMARY KEY (CouponID, ticketNumber),
	FOREIGN KEY (ticketNumber) REFERENCES Ticket (ticketNumber)
);

CREATE TABLE SystemSettings(
	commisionAmount decimal(3,2),
	discountAmount decimal(3,2),
	flexibleDiscountAmount decimal(3,2),
	flexibleDiscountThreshold decimal(15,2)
	autoBackupFreqDays integer(3),
	timeSinceLastBackup integer(3) DEFAULT 0,
);