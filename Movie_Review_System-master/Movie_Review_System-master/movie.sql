CREATE DATABASE  IF NOT EXISTS `movie` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `movie`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: movie
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `director` (
  `idDirector` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDirector`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (1,'Frank Darabont'),(2,'Francis Ford Coppola'),(3,'Christopher Nolan');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `idMovie` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Release_date` date DEFAULT NULL,
  `Duration` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `Gross_Income` varchar(20) DEFAULT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`idMovie`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Shawshank Redemption','1994-10-14',142,9,'28.34','Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.'),(2,'The Godfather','1972-03-24',175,9,'134.97','The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.'),(3,'The Godfather: Part II','1974-12-20',202,9,'57.30','The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.'),(4,'The Dark Knight','2008-07-18',152,9,'534.86','When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.'),(5,'ok','1998-03-03',150,9,'56','good'),(6,'ok','2018-08-15',180,8,'50','Olympic gold medal'),(7,'sh','1998-02-02',1,9,'5','s');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_director`
--

DROP TABLE IF EXISTS `movie_has_director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_director` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMovie` int(11) DEFAULT NULL,
  `idDirector` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_director`
--

LOCK TABLES `movie_has_director` WRITE;
/*!40000 ALTER TABLE `movie_has_director` DISABLE KEYS */;
INSERT INTO `movie_has_director` VALUES (1,1,1),(2,2,2),(3,3,1),(4,4,3),(5,5,2),(6,5,1);
/*!40000 ALTER TABLE `movie_has_director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_producer`
--

DROP TABLE IF EXISTS `movie_has_producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_producer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMovie` int(11) DEFAULT NULL,
  `idProducer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_producer`
--

LOCK TABLES `movie_has_producer` WRITE;
/*!40000 ALTER TABLE `movie_has_producer` DISABLE KEYS */;
INSERT INTO `movie_has_producer` VALUES (1,1,1),(2,2,2),(3,3,1),(4,4,3),(5,5,1),(6,5,1);
/*!40000 ALTER TABLE `movie_has_producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_reviews`
--

DROP TABLE IF EXISTS `movie_has_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMovie` int(11) DEFAULT NULL,
  `idReview` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_reviews`
--

LOCK TABLES `movie_has_reviews` WRITE;
/*!40000 ALTER TABLE `movie_has_reviews` DISABLE KEYS */;
INSERT INTO `movie_has_reviews` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,4),(5,4,5),(6,5,6);
/*!40000 ALTER TABLE `movie_has_reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_stars`
--

DROP TABLE IF EXISTS `movie_has_stars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_stars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMovie` int(11) NOT NULL,
  `idStar` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_stars`
--

LOCK TABLES `movie_has_stars` WRITE;
/*!40000 ALTER TABLE `movie_has_stars` DISABLE KEYS */;
INSERT INTO `movie_has_stars` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,4),(5,2,5),(6,2,6),(7,3,5),(8,3,7),(9,3,8),(10,4,9),(11,4,10),(12,5,1),(13,5,11);
/*!40000 ALTER TABLE `movie_has_stars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_writer`
--

DROP TABLE IF EXISTS `movie_has_writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_writer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMovie` int(11) DEFAULT NULL,
  `idWriter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_writer`
--

LOCK TABLES `movie_has_writer` WRITE;
/*!40000 ALTER TABLE `movie_has_writer` DISABLE KEYS */;
INSERT INTO `movie_has_writer` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,3),(6,5,1);
/*!40000 ALTER TABLE `movie_has_writer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producer` (
  `idProducer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProducer`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (1,'Frank Darabont'),(2,'Francis Ford Coppola'),(3,'Christopher Nolan');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `idReview` int(11) NOT NULL AUTO_INCREMENT,
  `rating` float DEFAULT NULL,
  `Description` varchar(1000) DEFAULT NULL,
  `Submitted_By` int(11) DEFAULT NULL,
  PRIMARY KEY (`idReview`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,9,'A movie with an excellent theme',161080057),(2,8,'Good one',161080057),(3,1,'not nice',161080054),(4,8,'haha',161080054),(5,8,'A very good movie',161080054),(6,9,'2',161080054);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stars`
--

DROP TABLE IF EXISTS `stars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stars` (
  `idStars` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStars`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stars`
--

LOCK TABLES `stars` WRITE;
/*!40000 ALTER TABLE `stars` DISABLE KEYS */;
INSERT INTO `stars` VALUES (1,'Tim Robbins'),(2,'Morgan Freeman'),(3,'Bob Gunton'),(4,'Marlon Brando'),(5,'Al Pacino'),(6,'James Caan'),(7,'Robert De Niro'),(8,'Robert Duvall'),(9,'Christian Bale'),(10,'Heath Ledger'),(11,'Akshay Kumar');
/*!40000 ALTER TABLE `stars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdata`
--

DROP TABLE IF EXISTS `userdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdata` (
  `userid` int(11) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdata`
--

LOCK TABLES `userdata` WRITE;
/*!40000 ALTER TABLE `userdata` DISABLE KEYS */;
INSERT INTO `userdata` VALUES (88,'sam'),(1011,'samkit99'),(1234,'1234'),(161080049,'1234'),(161080053,'5678'),(161080054,'1234'),(161080057,'6969'),(161080058,'1234');
/*!40000 ALTER TABLE `userdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `writer`
--

DROP TABLE IF EXISTS `writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writer` (
  `idWriter` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idWriter`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writer`
--

LOCK TABLES `writer` WRITE;
/*!40000 ALTER TABLE `writer` DISABLE KEYS */;
INSERT INTO `writer` VALUES (1,'Stephen King'),(2,'Mario Puzo'),(3,'Francis Ford Coppola'),(4,'Jonathan Nolan');
/*!40000 ALTER TABLE `writer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-23 15:57:29
