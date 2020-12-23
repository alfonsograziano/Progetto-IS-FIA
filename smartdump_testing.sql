CREATE DATABASE  IF NOT EXISTS `smartblog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `smartblog`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: smartblog
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` int NOT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `manager_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (6,'3334444444');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `totalScore` int NOT NULL,
  `performance` int NOT NULL,
  `display` int NOT NULL,
  `battery` int NOT NULL,
  `camera` int NOT NULL,
  `text` varchar(200) NOT NULL,
  `state` varchar(40) NOT NULL,
  `userId` int NOT NULL,
  `specId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `specId` (`specId`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`specId`) REFERENCES `spec` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (2,4,5,4,3,3,'Soddisfatto del mio acquisto','pending',3,2046),(3,5,5,4,4,4,'Ero scettico ma le prestazione di questo Huawei mi hanno fatto ricredere!','pending',3,2044),(4,2,3,2,4,1,'Vintage','pending',3,2159);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer`
--

DROP TABLE IF EXISTS `reviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviewer` (
  `id` int NOT NULL,
  `rank` varchar(100) NOT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer`
--

LOCK TABLES `reviewer` WRITE;
/*!40000 ALTER TABLE `reviewer` DISABLE KEYS */;
INSERT INTO `reviewer` VALUES (5,'senior','3333333333');
/*!40000 ALTER TABLE `reviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spec`
--

DROP TABLE IF EXISTS `spec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spec` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `so` varchar(100) DEFAULT NULL,
  `cpu` varchar(100) DEFAULT NULL,
  `chipset` varchar(100) DEFAULT NULL,
  `gpu` varchar(100) DEFAULT NULL,
  `ram` varchar(100) DEFAULT NULL,
  `memory` varchar(100) DEFAULT NULL,
  `screenSize` varchar(100) DEFAULT NULL,
  `image` varchar(300) DEFAULT NULL,
  `display` double DEFAULT '-1',
  `camera` double DEFAULT '-1',
  `performance` double DEFAULT '-1',
  `battery` int DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `reviewerId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reviewerId` (`reviewerId`),
  CONSTRAINT `spec_ibfk_1` FOREIGN KEY (`reviewerId`) REFERENCES `reviewer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2850 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spec`
--

LOCK TABLES `spec` WRITE;
/*!40000 ALTER TABLE `spec` DISABLE KEYS */;
INSERT INTO `spec` VALUES (2041,'Redmi Note 9 4G','Android 10 MIUI 12','4x 2.0 GHz Kryo 260 Gold + 4x 1.8 GHz Kryo 260 Silver','Snapdragon 662 Qualcomm SM6115','Adreno 610','4 GB','128 GB','6.53','https://hd2.tudocdn.net/947320?w=145&h=304',8.6,6.2,6.8,6000,'2020/4',206,0),(2042,'OnePlus Nord N100','Android 10 OxygenOS 10.5','4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240','Snapdragon 460 Qualcomm SM4250','Adreno 610','4 GB','64 GB','6.52','https://hd2.tudocdn.net/941166?w=139&h=304',5.6,5,6.5,5000,'2020/4',167,0),(2043,'OnePlus Nord N10','Android 10 OxygenOS 10.5','2x 2.0 GHz Kryo 560 Gold + 6x 1.7 GHz Kryo 560 Silver','Snapdragon 690 Qualcomm SM6350','Adreno 619L','6 GB','128 GB','6.49','https://hd2.tudocdn.net/941165?w=139&h=304',8.7,6.3,6.7,4300,'2020/4',325,0),(2044,'Huawei Mate 40 Pro','Android 10 EMUI 11','1x 3.13 GHz Cortex-A77 + 3x 2.54 GHz Cortex-A77 + 4x 2.04 GHz Cortex-A55','Huawei HiSilicon Kirin 9000','Mali-G78 MP24','8 GB','256 GB','6.7','https://hd2.tudocdn.net/940650?w=141&h=304',9.3,7,7,4400,'2020/4',1.238,0),(2045,'vivo Y11s','Android 10 Funtouch OS 11','4x 1.8 GHz Kryo 240 + 4x 1.6 GHz Kryo 240','Snapdragon 460 Qualcomm SM4250','Adreno 610','3 GB','32 GB','6.51','https://hd2.tudocdn.net/947173?w=141&h=304',5.6,5,6.5,5000,'2020/4',149,0)
/*!40000 ALTER TABLE `spec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,1,'antonio@sisonoio.com','antonio','!Antonio99'),(4,1,'mario@rossi.com','mario','!Rossi10'),(5,1,'reviewer@reviewer.com','reviewer','!Reviewer10'),(6,1,'manager@manager.com','manager','!Manager10');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-23 16:25:31
