-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: smcse-stuproj00.city.ac.uk    Database: in2018g11
-- ------------------------------------------------------
-- Server version	5.5.68-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Administrator`
--

DROP TABLE IF EXISTS `Administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Administrator` (
  `admID` int(3) NOT NULL DEFAULT '0',
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`admID`),
  KEY `email` (`email`),
  CONSTRAINT `Administrator_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Employee` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrator`
--

LOCK TABLES `Administrator` WRITE;
/*!40000 ALTER TABLE `Administrator` DISABLE KEYS */;
INSERT INTO `Administrator` VALUES (320,'Arthur Daley');
/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Advisor`
--

DROP TABLE IF EXISTS `Advisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Advisor` (
  `advID` int(3) NOT NULL DEFAULT '0',
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`advID`),
  KEY `email` (`email`),
  CONSTRAINT `Advisor_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Employee` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Advisor`
--

LOCK TABLES `Advisor` WRITE;
/*!40000 ALTER TABLE `Advisor` DISABLE KEYS */;
INSERT INTO `Advisor` VALUES (1,'bob@gmail.com'),(2,'dave@gmail.com');
/*!40000 ALTER TABLE `Advisor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Coupon`
--

DROP TABLE IF EXISTS `Coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Coupon` (
  `CouponID` int(1) NOT NULL AUTO_INCREMENT,
  `ticketType` int(3) NOT NULL DEFAULT '0',
  `ticketNumber` int(8) NOT NULL DEFAULT '0',
  `flightDepartureDate` date DEFAULT NULL,
  `flightDepartureTime` int(4) DEFAULT NULL,
  `departFrom` varchar(30) DEFAULT NULL,
  `flightTo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CouponID`,`ticketType`,`ticketNumber`),
  UNIQUE KEY `CouponID` (`CouponID`,`ticketType`,`ticketNumber`),
  KEY `ticketType` (`ticketType`,`ticketNumber`),
  CONSTRAINT `Coupon_ibfk_1` FOREIGN KEY (`ticketType`, `ticketNumber`) REFERENCES `Ticket` (`ticketType`, `ticketNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--

LOCK TABLES `Coupon` WRITE;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
INSERT INTO `Coupon` VALUES (1,444,7,'2023-05-20',1230,'London','Paris'),(2,444,7,'2023-05-20',1500,'Paris','Berlin'),(3,444,7,'2023-05-20',1915,'Berlin','Amsterdam');
/*!40000 ALTER TABLE `Coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `email` varchar(30) NOT NULL DEFAULT '',
  `password` varchar(64) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES ('Arthur Daley','72d3209d73fe29b863b1a57453e12312e02ad70e229cd32dfbb6dce1ca4eb1cf','Arthur Daley'),('bob@gmail.com','f709f35d0b4b0b88149ee7fd0483d14ca8d1bf5e6956d3372b6accd6b5a0e6ff','bob'),('dave@gmail.com','f6169ad34b442eb8cbcd3565c141d3ab593dc88ef5903c80802eaf1b763c0969','bob');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FlexibleDiscount`
--

DROP TABLE IF EXISTS `FlexibleDiscount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FlexibleDiscount` (
  `flexDiscID` int(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) DEFAULT NULL,
  `discountRate` decimal(5,2) DEFAULT NULL,
  `lowerBoundary` int(10) DEFAULT NULL,
  `upperBoundary` int(10) DEFAULT NULL,
  PRIMARY KEY (`flexDiscID`),
  KEY `email` (`email`),
  CONSTRAINT `FlexibleDiscount_ibfk_1` FOREIGN KEY (`email`) REFERENCES `RegisteredCustomer` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FlexibleDiscount`
--

LOCK TABLES `FlexibleDiscount` WRITE;
/*!40000 ALTER TABLE `FlexibleDiscount` DISABLE KEYS */;
/*!40000 ALTER TABLE `FlexibleDiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manager`
--

DROP TABLE IF EXISTS `Manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Manager` (
  `manID` int(3) NOT NULL DEFAULT '0',
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`manID`),
  KEY `email` (`email`),
  CONSTRAINT `Manager_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Employee` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manager`
--

LOCK TABLES `Manager` WRITE;
/*!40000 ALTER TABLE `Manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `Manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RegisteredCustomer`
--

DROP TABLE IF EXISTS `RegisteredCustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RegisteredCustomer` (
  `email` varchar(30) NOT NULL DEFAULT '',
  `name` varchar(30) NOT NULL,
  `isValued` tinyint(1) NOT NULL DEFAULT '0',
  `spentThisMonth` bigint(8) NOT NULL DEFAULT '0',
  `discountOrRefundToReturn` bigint(8) NOT NULL DEFAULT '0',
  `fixedDiscountRate` decimal(5,2) DEFAULT '0.00',
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RegisteredCustomer`
--

LOCK TABLES `RegisteredCustomer` WRITE;
/*!40000 ALTER TABLE `RegisteredCustomer` DISABLE KEYS */;
INSERT INTO `RegisteredCustomer` VALUES ('dylan@gmail.com','Dylan Dylandson',0,0,0,NULL);
/*!40000 ALTER TABLE `RegisteredCustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sale`
--

DROP TABLE IF EXISTS `Sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Sale` (
  `saleID` int(20) NOT NULL AUTO_INCREMENT,
  `advisorID` int(3) NOT NULL,
  `customerEmail` varchar(30) DEFAULT NULL,
  `dateSold` date DEFAULT NULL,
  `paymentType` varchar(4) DEFAULT NULL,
  `cardNo` int(16) DEFAULT NULL,
  `paymentProvider` varchar(10) DEFAULT NULL,
  `localCurrency` varchar(5) DEFAULT NULL,
  `exchangeRate` decimal(9,4) DEFAULT NULL,
  `priceLocal` varchar(10) DEFAULT NULL,
  `priceUSD` int(10) DEFAULT NULL,
  `saleDiscountAmount` int(5) DEFAULT NULL,
  `taxAmount` int(10) DEFAULT NULL,
  `saleCommissionAmount` int(10) DEFAULT NULL,
  `isDomestic` tinyint(1) DEFAULT NULL,
  `isPaid` tinyint(1) DEFAULT '0',
  `refundRequested` tinyint(1) DEFAULT '0',
  `isRefunded` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`saleID`),
  KEY `advisorID` (`advisorID`),
  KEY `customerEmail` (`customerEmail`),
  CONSTRAINT `Sale_ibfk_1` FOREIGN KEY (`advisorID`) REFERENCES `Advisor` (`advID`),
  CONSTRAINT `Sale_ibfk_2` FOREIGN KEY (`customerEmail`) REFERENCES `RegisteredCustomer` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sale`
--

LOCK TABLES `Sale` WRITE;
/*!40000 ALTER TABLE `Sale` DISABLE KEYS */;
INSERT INTO `Sale` VALUES (1,1,NULL,'2023-02-22','card',NULL,NULL,'USD',NULL,'20000',NULL,1000,2000,500,0,1,0,0),(2,1,NULL,'2023-02-22','card',NULL,NULL,'USD',NULL,'23000',NULL,1500,2000,500,0,1,0,0),(3,1,NULL,'1990-02-22','card',NULL,NULL,'USD',NULL,'16000',NULL,1500,2000,500,0,1,0,0),(4,2,NULL,'2023-02-22','card',NULL,NULL,'USD',NULL,'20000',NULL,1000,2000,500,0,0,0,0);
/*!40000 ALTER TABLE `Sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SystemSettings`
--

DROP TABLE IF EXISTS `SystemSettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SystemSettings` (
  `commissionRate` decimal(5,2) DEFAULT NULL,
  `taxRate` decimal(5,2) DEFAULT NULL,
  `autoBackupFreqDays` int(3) DEFAULT '7',
  `lastBackup` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SystemSettings`
--

LOCK TABLES `SystemSettings` WRITE;
/*!40000 ALTER TABLE `SystemSettings` DISABLE KEYS */;
INSERT INTO `SystemSettings` VALUES (0.00,0.00,2,'2023-04-02');
/*!40000 ALTER TABLE `SystemSettings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ticket`
--

DROP TABLE IF EXISTS `Ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ticket` (
  `ticketType` int(3) NOT NULL DEFAULT '0',
  `ticketNumber` int(8) NOT NULL DEFAULT '0',
  `advisorID` int(3) DEFAULT NULL,
  `saleID` int(20) DEFAULT NULL,
  `isValid` tinyint(1) DEFAULT '0',
  `dateReceived` date DEFAULT NULL,
  `dateAssigned` date DEFAULT NULL,
  PRIMARY KEY (`ticketType`,`ticketNumber`),
  UNIQUE KEY `ticketType` (`ticketType`,`ticketNumber`),
  KEY `advisorID` (`advisorID`),
  KEY `saleID` (`saleID`),
  CONSTRAINT `Ticket_ibfk_1` FOREIGN KEY (`advisorID`) REFERENCES `Advisor` (`advID`),
  CONSTRAINT `Ticket_ibfk_2` FOREIGN KEY (`saleID`) REFERENCES `Sale` (`saleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ticket`
--

LOCK TABLES `Ticket` WRITE;
/*!40000 ALTER TABLE `Ticket` DISABLE KEYS */;
INSERT INTO `Ticket` VALUES (444,1,NULL,NULL,0,'2023-02-15',NULL),(444,2,1,1,1,'2023-02-15','2023-02-20'),(444,3,2,NULL,0,'2023-02-15','2023-02-20'),(444,4,NULL,NULL,0,'2023-02-15',NULL),(444,5,2,2,1,'2023-02-15','2023-02-20'),(444,6,2,3,1,'2023-02-15','2023-02-20'),(444,7,1,2,1,'2023-02-15','2023-02-20'),(444,8,NULL,NULL,0,'2023-02-15',NULL),(444,9,2,NULL,1,'2023-02-15','2023-02-20'),(444,10,1,NULL,1,'2023-02-15','2023-02-20'),(444,11,NULL,NULL,1,'2023-02-15',NULL),(444,12,NULL,NULL,0,'2023-02-15',NULL),(451,1,1,1,1,'2023-02-15','2023-02-20'),(451,2,2,3,1,'2023-02-15','2023-02-20'),(451,3,NULL,NULL,1,'2023-02-15',NULL);
/*!40000 ALTER TABLE `Ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-03  8:44:07
