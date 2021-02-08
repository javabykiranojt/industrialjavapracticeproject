CREATE DATABASE  IF NOT EXISTS `indianmy_mystery` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `indianmy_mystery`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: kdtesting123
-- ------------------------------------------------------
-- Server version	5.0.11-beta-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `admin_details`
--

DROP TABLE IF EXISTS `admin_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_details` (
  `userEmpId` int(10) unsigned NOT NULL auto_increment,
  `adminFirstName` varchar(45) default NULL,
  `adminLastName` varchar(45) default NULL,
  `adminAddress` varchar(45) default NULL,
  `adminEmail` varchar(45) default NULL,
  `adminContactNo` varchar(45) default NULL,
  `adminStatus` varchar(45) default NULL,
  `uId` int(10) unsigned default NULL,
  `locId` int(10) unsigned default NULL,
  PRIMARY KEY  (`userEmpId`),
  KEY `FK_admin_details_1` (`uId`),
  KEY `FK_admin_details_2` (`locId`),
  CONSTRAINT `FK_admin_details_1` FOREIGN KEY (`uId`) REFERENCES `users` (`uId`),
  CONSTRAINT `FK_admin_details_2` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client_details`
--

DROP TABLE IF EXISTS `client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_details` (
  `userEmpId` int(10) unsigned NOT NULL auto_increment,
  `cltFirstName` varchar(45) default NULL,
  `cltLastName` varchar(45) default NULL,
  `cltAddress` int(10) unsigned default NULL,
  `cltContactNo` varchar(45) default NULL,
  `cltEmail` varchar(45) default NULL,
  `cltStatus` varchar(45) default NULL,
  `uId` int(10) unsigned default NULL,
  `locId` int(10) unsigned default NULL,
  PRIMARY KEY  (`userEmpId`),
  KEY `FK_client_details_1` (`uId`),
  KEY `FK_client_details_2` (`locId`),
  KEY `FK_client_details_3` (`cltAddress`),
  CONSTRAINT `FK_client_details_1` FOREIGN KEY (`uId`) REFERENCES `users` (`uId`),
  CONSTRAINT `FK_client_details_2` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`),
  CONSTRAINT `FK_client_details_3` FOREIGN KEY (`cltAddress`) REFERENCES `location_area` (`areaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client_documents`
--

DROP TABLE IF EXISTS `client_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_documents` (
  `client_docid` int(10) unsigned NOT NULL auto_increment,
  `client_id` int(10) unsigned default NULL,
  `docId` int(10) unsigned default NULL,
  `documentName` varchar(45) default NULL,
  PRIMARY KEY  (`client_docid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client_location`
--

DROP TABLE IF EXISTS `client_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_location` (
  `clientLocId` int(10) unsigned NOT NULL auto_increment,
  `locId` int(10) unsigned NOT NULL,
  `client_id` int(10) unsigned NOT NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  PRIMARY KEY  (`clientLocId`),
  KEY `locId` (`locId`),
  KEY `clientid` (`client_id`),
  CONSTRAINT `clientid` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `locId` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `client_id` int(10) unsigned NOT NULL auto_increment,
  `client_name` varchar(45) NOT NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `userEmpId` int(10) unsigned default NULL,
  `client_userEmpId` int(10) unsigned default NULL,
  `docId` int(10) unsigned default NULL,
  `docname` varchar(45) default NULL,
  PRIMARY KEY  (`client_id`),
  KEY `FK_clients_1` (`userEmpId`),
  KEY `FK_clients_2` (`client_userEmpId`),
  KEY `FK_clients_3` (`docId`),
  CONSTRAINT `FK_clients_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`),
  CONSTRAINT `FK_clients_2` FOREIGN KEY (`client_userEmpId`) REFERENCES `client_details` (`userEmpId`),
  CONSTRAINT `FK_clients_3` FOREIGN KEY (`docId`) REFERENCES `documet_repository` (`docId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documet_repository`
--

DROP TABLE IF EXISTS `documet_repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documet_repository` (
  `docId` int(10) unsigned NOT NULL auto_increment,
  `Document` longblob NOT NULL,
  PRIMARY KEY  (`docId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback_documents`
--

DROP TABLE IF EXISTS `feedback_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback_documents` (
  `feedbackDocId` int(10) unsigned NOT NULL auto_increment,
  `docId` int(10) unsigned default NULL,
  `documentName` varchar(45) default NULL,
  `feedbackId` int(10) unsigned default NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `documentStatus` varchar(45) default NULL,
  PRIMARY KEY  (`feedbackDocId`),
  KEY `FK_feedback_documents_3` (`docId`),
  KEY `FK_feedback_documents_4` (`feedbackId`),
  CONSTRAINT `FK_feedback_documents_3` FOREIGN KEY (`docId`) REFERENCES `documet_repository` (`docId`),
  CONSTRAINT `FK_feedback_documents_4` FOREIGN KEY (`feedbackId`) REFERENCES `user_feedback` (`feedbackId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `Sr_no` int(10) unsigned NOT NULL auto_increment,
  `DTM` datetime NOT NULL,
  `old_value` varchar(45) default NULL,
  `new_value` varchar(45) default NULL,
  `function` varchar(45) default NULL,
  `column_name` varchar(45) default NULL,
  `uid` int(10) unsigned default NULL,
  PRIMARY KEY  (`Sr_no`),
  KEY `FK_history_1` (`uid`),
  CONSTRAINT `FK_history_1` FOREIGN KEY (`uid`) REFERENCES `user_detail` (`userEmpId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `locId` int(10) unsigned NOT NULL auto_increment,
  `country` varchar(45) default NULL,
  `state` varchar(45) default NULL,
  `city` varchar(45) default NULL,
  `area` varchar(45) default NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `status` varchar(45) default 'Active',
  PRIMARY KEY  (`locId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location_area`
--

DROP TABLE IF EXISTS `location_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_area` (
  `areaId` int(10) unsigned NOT NULL auto_increment,
  `areaName` varchar(45) default NULL,
  `locId` int(10) unsigned default NULL,
  `areaStatus` varchar(45) default 'Active',
  PRIMARY KEY  (`areaId`),
  KEY `FK_location_area_1` (`locId`),
  CONSTRAINT `FK_location_area_1` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task_client_points`
--

DROP TABLE IF EXISTS `task_client_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_client_points` (
  `taskPointId` int(10) unsigned NOT NULL auto_increment,
  `client_id` int(10) unsigned default NULL,
  `taskPoints` int(10) unsigned default NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  PRIMARY KEY  (`taskPointId`),
  KEY `FK_Task_Client_Points_1` (`client_id`),
  CONSTRAINT `FK_task_client_points_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `taskId` int(10) unsigned NOT NULL,
  `scheduleDate` date default NULL,
  `time` varchar(45) default NULL,
  `venue` varchar(400) default NULL,
  `userEmpId` int(10) unsigned default NULL,
  `locId` int(10) unsigned default NULL,
  `status` varchar(45) default 'Initiate',
  `client_id` int(10) unsigned NOT NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `expiryDate` date default NULL,
  `taskUniqueName` varchar(75) NOT NULL,
  PRIMARY KEY  (`taskId`),
  KEY `userId` (`userEmpId`),
  KEY `FK_tasks_2` (`locId`),
  KEY `FK_tasks_3` (`client_id`),
  CONSTRAINT `FK_tasks_2` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`),
  CONSTRAINT `FK_tasks_3` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `userId` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail` (
  `userEmpId` int(10) unsigned NOT NULL auto_increment,
  `firstName` varchar(45) default NULL,
  `lastName` varchar(45) default NULL,
  `address` int(10) unsigned default NULL,
  `contactNo` varchar(45) default NULL,
  `emailId` varchar(45) default NULL,
  `status` varchar(45) default 'Inactive',
  `gender` varchar(45) default NULL,
  `uId` int(10) unsigned default NULL,
  `locId` int(10) unsigned default NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `verificationId` varchar(45) default NULL,
  `expiryDate` date default NULL,
  PRIMARY KEY  (`userEmpId`),
  KEY `FK_user_detail_1` (`locId`),
  KEY `FK_user_detail_2` (`uId`),
  KEY `FK_user_detail_3` (`address`),
  CONSTRAINT `FK_user_detail_1` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`),
  CONSTRAINT `FK_user_detail_2` FOREIGN KEY (`uId`) REFERENCES `users` (`uId`),
  CONSTRAINT `FK_user_detail_3` FOREIGN KEY (`address`) REFERENCES `location_area` (`areaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_documents`
--

DROP TABLE IF EXISTS `user_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_documents` (
  `uidDocId` int(10) unsigned NOT NULL auto_increment,
  `userEmpId` int(10) unsigned NOT NULL,
  `docid` int(10) unsigned NOT NULL,
  `documentName` varchar(45) NOT NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  PRIMARY KEY  (`uidDocId`),
  KEY `FK_user_documents_1` (`userEmpId`),
  KEY `FK_user_documents_2` (`docid`),
  CONSTRAINT `FK_user_documents_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`),
  CONSTRAINT `FK_user_documents_2` FOREIGN KEY (`docid`) REFERENCES `documet_repository` (`docId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_feedback`
--

DROP TABLE IF EXISTS `user_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_feedback` (
  `feedbackId` int(10) unsigned NOT NULL auto_increment,
  `taskAssignProof` varchar(45) default NULL,
  `feedbackDetails` varchar(45) default NULL,
  `userEmpId` int(10) unsigned default NULL,
  `client_id` int(10) unsigned default NULL,
  `Status` varchar(45) default NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `taskId` int(10) unsigned default NULL,
  `sent_status` varchar(45) default NULL,
  `sentDTM` datetime default NULL,
  `remarkByClient` varchar(45) default NULL,
  PRIMARY KEY  (`feedbackId`),
  KEY `FK_user_feedback_1` (`userEmpId`),
  KEY `FK_user_feedback_2` (`client_id`),
  KEY `FK_user_feedback_3` (`taskId`),
  CONSTRAINT `FK_user_feedback_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`),
  CONSTRAINT `FK_user_feedback_2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `FK_user_feedback_3` FOREIGN KEY (`taskId`) REFERENCES `tasks` (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_rewards`
--

DROP TABLE IF EXISTS `user_rewards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rewards` (
  `rewardId` int(10) unsigned NOT NULL auto_increment,
  `point` varchar(45) NOT NULL,
  `userEmpId` int(10) unsigned NOT NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `created_uId` int(10) unsigned default NULL,
  `modified_uId` int(10) unsigned default NULL,
  `redeem_point` varchar(45) default NULL,
  `redeem_point_req` varchar(45) default NULL,
  `remarks` varchar(45) default NULL,
  `mode_of_payment` varchar(45) default NULL,
  PRIMARY KEY  (`rewardId`),
  KEY `FK_user_rewards_1` (`userEmpId`),
  CONSTRAINT `FK_user_rewards_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `uId` int(10) unsigned NOT NULL auto_increment,
  `uName` varchar(45) default NULL,
  `uPass` varchar(45) default NULL,
  `uType` varchar(45) default NULL,
  `createdDTM` datetime default NULL,
  `modifiedDTM` datetime default NULL,
  `lastAccessedTime` varchar(45) default NULL,
  PRIMARY KEY  (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-19 20:36:50
