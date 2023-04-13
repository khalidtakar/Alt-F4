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
  `admID` int(3) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`admID`),
  KEY `email` (`email`),
  CONSTRAINT `Administrator_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Employee` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrator`
--

LOCK TABLES `Administrator` WRITE;
/*!40000 ALTER TABLE `Administrator` DISABLE KEYS */;
INSERT INTO `Administrator` VALUES (320,'arthur@gmail.com');
/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Advisor`
--

DROP TABLE IF EXISTS `Advisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Advisor` (
  `advID` int(3) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`advID`),
  KEY `email` (`email`),
  CONSTRAINT `Advisor_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Employee` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Advisor`
--

LOCK TABLES `Advisor` WRITE;
/*!40000 ALTER TABLE `Advisor` DISABLE KEYS */;
INSERT INTO `Advisor` VALUES (251,'bob@gmail.com'),(211,'dennis@gmail.com'),(250,'penelope@gmail.com');
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--

LOCK TABLES `Coupon` WRITE;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
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
INSERT INTO `Employee` VALUES ('arthur@gmail.com','b571432fce3f132d02903adbcc437e40fa70adb0b9e08dcbd98cbe2a2496446f','Arthur Daley'),('bob@gmail.com','4dddac6c9fa5ff0d9bfb52f440c9b8de48daaf1782ccb8289487be37d0841f21','bob'),('dennis@gmail.com','953c31a4ff959efa5a72f76330c84c48aac221baaa75ad8860939c10cc3afc84','Dennis Menace'),('minnie@gmail.com','aaa1aef4546b3c6745c207628af82d192fc2255020a754cad2959810c89860bf','Minnie Minx'),('penelope@gmail.com','e1e3fac4b86b779810bbafad03f8ce22d8f01ade21ffc3db527429213b6b9bb8','Penelope Pitstop');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FlexibleDiscount`
--

LOCK TABLES `FlexibleDiscount` WRITE;
/*!40000 ALTER TABLE `FlexibleDiscount` DISABLE KEYS */;
INSERT INTO `FlexibleDiscount` VALUES (1,'david@gmail.com',0.00,NULL,1000),(2,'david@gmail.com',1.00,1000,2000),(3,'david@gmail.com',2.00,2000,NULL),(4,'sarah@gmail.com',2.00,NULL,NULL);
/*!40000 ALTER TABLE `FlexibleDiscount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manager`
--

DROP TABLE IF EXISTS `Manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Manager` (
  `manID` int(3) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`manID`),
  KEY `email` (`email`),
  CONSTRAINT `Manager_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Employee` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manager`
--

LOCK TABLES `Manager` WRITE;
/*!40000 ALTER TABLE `Manager` DISABLE KEYS */;
INSERT INTO `Manager` VALUES (220,'minnie@gmail.com');
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
INSERT INTO `RegisteredCustomer` VALUES ('chris@gmail.com','Chris Smart',1,0,0,1.00),('david@gmail.com','David Dodson',1,3259,0,0.00),('sarah@gmail.com','Sarah Broklehurst',1,0,0,2.00);
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
  `cardNo` bigint(16) DEFAULT NULL,
  `paymentProvider` varchar(10) DEFAULT NULL,
  `localCurrency` varchar(5) DEFAULT NULL,
  `exchangeRate` decimal(9,4) DEFAULT NULL,
  `priceLocal` decimal(10,2) DEFAULT NULL,
  `priceUSD` decimal(10,2) DEFAULT NULL,
  `saleDiscountAmount` decimal(10,2) DEFAULT NULL,
  `taxAmount` decimal(10,2) DEFAULT NULL,
  `saleCommissionAmount` decimal(10,2) DEFAULT NULL,
  `isDomestic` tinyint(1) DEFAULT NULL,
  `isPaid` tinyint(1) DEFAULT '0',
  `datePaid` date DEFAULT NULL,
  `refundRequested` tinyint(1) DEFAULT '0',
  `isRefunded` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`saleID`),
  KEY `advisorID` (`advisorID`),
  KEY `customerEmail` (`customerEmail`),
  CONSTRAINT `Sale_ibfk_1` FOREIGN KEY (`advisorID`) REFERENCES `Advisor` (`advID`),
  CONSTRAINT `Sale_ibfk_2` FOREIGN KEY (`customerEmail`) REFERENCES `RegisteredCustomer` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sale`
--

LOCK TABLES `Sale` WRITE;
/*!40000 ALTER TABLE `Sale` DISABLE KEYS */;
INSERT INTO `Sale` VALUES (1,211,'david@gmail.com','2023-04-13','Cash',0,'','GBP',0.7981,1200.00,1503.57,0.00,240.00,150.36,0,1,'2023-04-13',0,0),(2,211,'david@gmail.com','2023-04-13','Cash',0,'','GBP',0.7981,1200.00,1503.57,15.04,240.00,150.36,0,1,'2023-04-13',0,0),(3,211,'david@gmail.com','2023-04-13','Cash',0,'','GBP',0.7981,200.00,250.59,5.01,40.00,25.06,0,0,NULL,0,0);
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
INSERT INTO `SystemSettings` VALUES (10.00,20.00,3,'2023-04-13');
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
INSERT INTO `Ticket` VALUES (444,10000001,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000002,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000003,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000004,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000005,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000006,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000007,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000008,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000009,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000010,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000011,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000012,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000013,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000014,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000015,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000016,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000017,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000018,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000019,211,1,0,'2023-04-13','2023-04-13'),(444,10000020,211,2,0,'2023-04-13','2023-04-13'),(444,10000021,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000022,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000023,211,3,0,'2023-04-13','2023-04-13'),(444,10000024,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000025,211,NULL,0,'2023-04-13','2023-04-13'),(444,10000026,NULL,NULL,0,'2023-04-13',NULL),(444,10000027,NULL,NULL,0,'2023-04-13',NULL),(444,10000028,NULL,NULL,0,'2023-04-13',NULL),(444,10000029,NULL,NULL,0,'2023-04-13',NULL),(444,10000030,NULL,NULL,0,'2023-04-13',NULL),(444,10000031,NULL,NULL,0,'2023-04-13',NULL),(444,10000032,NULL,NULL,0,'2023-04-13',NULL),(444,10000033,NULL,NULL,0,'2023-04-13',NULL),(444,10000034,NULL,NULL,0,'2023-04-13',NULL),(444,10000035,NULL,NULL,0,'2023-04-13',NULL),(444,10000036,NULL,NULL,0,'2023-04-13',NULL),(444,10000037,NULL,NULL,0,'2023-04-13',NULL),(444,10000038,NULL,NULL,0,'2023-04-13',NULL),(444,10000039,NULL,NULL,0,'2023-04-13',NULL),(444,10000040,NULL,NULL,0,'2023-04-13',NULL),(444,10000041,NULL,NULL,0,'2023-04-13',NULL),(444,10000042,NULL,NULL,0,'2023-04-13',NULL),(444,10000043,NULL,NULL,0,'2023-04-13',NULL),(444,10000044,NULL,NULL,0,'2023-04-13',NULL),(444,10000045,NULL,NULL,0,'2023-04-13',NULL),(444,10000046,NULL,NULL,0,'2023-04-13',NULL),(444,10000047,NULL,NULL,0,'2023-04-13',NULL),(444,10000048,NULL,NULL,0,'2023-04-13',NULL),(444,10000049,NULL,NULL,0,'2023-04-13',NULL),(444,10000050,NULL,NULL,0,'2023-04-13',NULL);
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

-- Dump completed on 2023-04-13 18:15:16
