-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: Lab5
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ActualTripStopInfo`
--

DROP TABLE IF EXISTS `ActualTripStopInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ActualTripStopInfo` (
  `TripNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `StopNumber` varchar(11) NOT NULL,
  `ScheduledArrivalTime` time DEFAULT NULL,
  `ActualStartTime` time DEFAULT NULL,
  `NumberOfPassengerIn` varchar(3) DEFAULT NULL,
  `NumberOfpassengerOut` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`Date`,`ScheduledStartTime`,`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ActualTripStopInfo`
--

LOCK TABLES `ActualTripStopInfo` WRITE;
/*!40000 ALTER TABLE `ActualTripStopInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ActualTripStopInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bus`
--

DROP TABLE IF EXISTS `Bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bus` (
  `BusID` varchar(10) NOT NULL,
  `Year` year(4) DEFAULT NULL,
  `Model` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`BusID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bus`
--

LOCK TABLES `Bus` WRITE;
/*!40000 ALTER TABLE `Bus` DISABLE KEYS */;
INSERT INTO `Bus` VALUES ('1',2020,'Accord');
/*!40000 ALTER TABLE `Bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Driver`
--

DROP TABLE IF EXISTS `Driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Driver` (
  `DriverName` varchar(100) NOT NULL,
  `DriverTelephoneNumber` char(9) DEFAULT NULL,
  PRIMARY KEY (`DriverName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Driver`
--

LOCK TABLES `Driver` WRITE;
/*!40000 ALTER TABLE `Driver` DISABLE KEYS */;
INSERT INTO `Driver` VALUES ('helloworld','123456789'),('Steve2','123456789');
/*!40000 ALTER TABLE `Driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stop`
--

DROP TABLE IF EXISTS `Stop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stop` (
  `StopNumber` varchar(11) NOT NULL,
  `StopAddress` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stop`
--

LOCK TABLES `Stop` WRITE;
/*!40000 ALTER TABLE `Stop` DISABLE KEYS */;
/*!40000 ALTER TABLE `Stop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trip`
--

DROP TABLE IF EXISTS `Trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trip` (
  `TripNumber` int(11) NOT NULL,
  `StartLocationName` varchar(100) DEFAULT NULL,
  `DestinationName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trip`
--

LOCK TABLES `Trip` WRITE;
/*!40000 ALTER TABLE `Trip` DISABLE KEYS */;
/*!40000 ALTER TABLE `Trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TripOffering`
--

DROP TABLE IF EXISTS `TripOffering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TripOffering` (
  `TripNumber` int(11) NOT NULL,
  `Date` date NOT NULL,
  `ScheduledStartTime` time NOT NULL,
  `ScheduleArrivalTime` time DEFAULT NULL,
  `DriverName` varchar(100) DEFAULT NULL,
  `BusID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`Date`,`ScheduledStartTime`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TripOffering`
--

LOCK TABLES `TripOffering` WRITE;
/*!40000 ALTER TABLE `TripOffering` DISABLE KEYS */;
INSERT INTO `TripOffering` VALUES (1,'2019-04-28','08:00:00','09:00:00','hello','3'),(1,'2019-04-29','09:00:00','10:00:00','Steve','2'),(2,'2019-04-30','09:00:00','10:00:00','Steve','1'),(3,'2019-04-30','10:00:00','11:00:00','hello','3'),(4,'2019-04-30','11:00:00','12:00:00','Steve1','3');
/*!40000 ALTER TABLE `TripOffering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TripStopInfo`
--

DROP TABLE IF EXISTS `TripStopInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TripStopInfo` (
  `TripNumber` int(11) NOT NULL,
  `StopNumber` varchar(11) NOT NULL,
  `SequenceNumber` varchar(11) DEFAULT NULL,
  `DrivingTime` time DEFAULT NULL,
  PRIMARY KEY (`TripNumber`,`StopNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TripStopInfo`
--

LOCK TABLES `TripStopInfo` WRITE;
/*!40000 ALTER TABLE `TripStopInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `TripStopInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-27  4:12:04
