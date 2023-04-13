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
	admID integer(3) AUTO_INCREMENT,
	email varchar(30) NOT NULL,

	PRIMARY KEY (admID),
	FOREIGN KEY (email) REFERENCES Employee (email)
);

CREATE TABLE Manager(
	manID integer(3) AUTO_INCREMENT,
	email varchar(30) NOT NULL,

	PRIMARY KEY (manID),
	FOREIGN KEY (email) REFERENCES Employee (email)
);

CREATE TABLE Advisor(
	advID integer(3) AUTO_INCREMENT,
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
	priceLocal decimal(10,2),
	priceUSD decimal(10,2) NULL,
	saleDiscountAmount decimal(10,2),
	taxAmount decimal(10,2),
	saleCommissionAmount decimal(10,2),
	isDomestic tinyint(1),
	isPaid tinyint(1) DEFAULT 0,
	datePaid date NULL,
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
	("chris@gmail.com", "Chris Smart", True, 0, 0, 1),
	("sarah@gmail.com", "Sarah Broklehurst", True, 0, 0, 2),
	('david@gmail.com', "David Dodson", true, 0,0,0);


INSERT INTO FlexibleDiscount(`email`, `discountRate`, `lowerBoundary`, `upperBoundary`) 
VALUES 
	('david@gmail.com', 0,Null,100),
	('david@gmail.com', 1,1000,2000),
	('david@gmail.com', 2,2000,null),
	("sarah@gmail.com", 2, null, null);




INSERT INTO Employee VALUES
	("penelope@gmail.com","e1e3fac4b86b779810bbafad03f8ce22d8f01ade21ffc3db527429213b6b9bb8","Penelope Pitstop"),
	("dennis@gmail.com","953c31a4ff959efa5a72f76330c84c48aac221baaa75ad8860939c10cc3afc84","Dennis Menace"),
	("minnie@gmail.com","aaa1aef4546b3c6745c207628af82d192fc2255020a754cad2959810c89860bf","Minnie Minx"),
	("arthur@gmail.com","b571432fce3f132d02903adbcc437e40fa70adb0b9e08dcbd98cbe2a2496446f","Arthur Daley");

INSERT INTO Advisor VALUES
	(250,"penelope@gmail.com"),
	(211,"dennis@gmail.com");

INSERT INTO Administrator VALUES
	(320, "arthur@gmail.com");

INSERT INTO Manager VALUES
	(220, "minnie@gmail.com");



	INSERT INTO Sale
	(`saleID`, `advisorID`, `customerEmail`, `dateSold`, `paymentType`, `cardNo`, `paymentProvider`, `localCurrency`, `exchangeRate`, `priceLocal`, `priceUSD`, `saleDiscountAmount`, `taxAmount`, `saleCommissionAmount`, `isDomestic`, `isPaid`, `datePaid`, `refundRequested`, `isRefunded`) 
	VALUES 
	(11,250,"sarah@gmail.com",'01-01-2023','Cash',NULL,NULL,'GBP',0.8054, 220, 273.16, 2, 44, 9,False,True,NULL,False,False),

	(12,250,NULL,'01-01-2023','Card', 4901000223453456, 'VISA','GBP',0.8054,273.16, 0, NULL, 44,9,False,False,NULL,True,True),
 
	(13,250,NULL,'01-01-2023','Cash', NULL,NULL,'USD',1.0000,43,43,8.6,NULL,5,True,True,NULL,False,False),

	(28,250,"david@gmail.com",'02-02-2023','Card', 5899455432655121,"VISA",'GBP',0.8054,'[value-10]','[value-11]','[value-12]','[value-13]',9,False,True,"15-03-2023",False,False),

	(29,250,"chris@gmail.com",'02-02-2023',NULL, NULL,"Chris Smart",'GBP',0.8054,'[value-10]','[value-11]','[value-12]','[value-13]',9,False,False,NULL,False,False),

	(30,250,NULL,'02-02-2023',Card,6454986387338876,"VISA",'USD',1.0000,'[value-10]','[value-11]',NULL,'[value-13]',5,False,True,NULL,False,False),

	(45,211,"sarah@gmail.com",'02-02-2023',Card,"MC 5301 0234 5698 1234",'Sarah Broklehurst','GBP',0.8054,'[value-10]','[value-11]',2,'[value-13]',9,False,True,'13-02-2023',False,False),

	(46,211,NULL,'02-02-2023',Card,"VISA 7449 1555 4589 3456",NULL,'GBP',0.8054,'[value-10]','[value-11]',NULL,'[value-13]',9,False,True,NULL,False,False),

	(47,211,NULL,'02-02-2023',Cash,NULL,NULL,'USD',1.0000,'[value-10]','[value-11]',NULL,'[value-13]',5,True,True,NULL,True,True);










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




INSERT INTO SystemSettings(
	commissionRate,
	taxRate,
	autoBackupFreqDays,
	lastBackup) VALUES
	(10, 20, 3, "2023-04-04");






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