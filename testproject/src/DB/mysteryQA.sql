CREATE DATABASE  IF NOT EXISTS `indianmy_mystery` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `indianmy_mystery`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: kjnextdemo.com    Database: kjnextde_mystery
-- ------------------------------------------------------
-- Server version	5.1.70-cll

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
-- Table structure for table `admin_details`
--

DROP TABLE IF EXISTS `admin_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_details` (
  `userEmpId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `adminFirstName` varchar(45) DEFAULT NULL,
  `adminLastName` varchar(45) DEFAULT NULL,
  `adminAddress` varchar(45) DEFAULT NULL,
  `adminEmail` varchar(45) DEFAULT NULL,
  `adminContactNo` varchar(45) DEFAULT NULL,
  `adminStatus` varchar(45) DEFAULT NULL,
  `uId` int(10) unsigned DEFAULT NULL,
  `locId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`userEmpId`),
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
  `userEmpId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cltFirstName` varchar(45) DEFAULT NULL,
  `cltLastName` varchar(45) DEFAULT NULL,
  `cltAddress` int(10) unsigned DEFAULT NULL,
  `cltContactNo` varchar(45) DEFAULT NULL,
  `cltEmail` varchar(45) DEFAULT NULL,
  `cltStatus` varchar(45) DEFAULT NULL,
  `uId` int(10) unsigned DEFAULT NULL,
  `locId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`userEmpId`),
  KEY `FK_client_details_1` (`uId`),
  KEY `FK_client_details_2` (`locId`),
  KEY `FK_client_details_3` (`cltAddress`),
  CONSTRAINT `FK_client_details_1` FOREIGN KEY (`uId`) REFERENCES `users` (`uId`),
  CONSTRAINT `FK_client_details_2` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`),
  CONSTRAINT `FK_client_details_3` FOREIGN KEY (`cltAddress`) REFERENCES `location_area` (`areaId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client_location`
--

DROP TABLE IF EXISTS `client_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_location` (
  `clientLocId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `locId` int(10) unsigned NOT NULL,
  `client_id` int(10) unsigned NOT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`clientLocId`),
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
  `client_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `client_name` varchar(45) NOT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `userEmpId` int(10) unsigned DEFAULT NULL,
  `client_userEmpId` int(10) unsigned DEFAULT NULL,
  `docId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `FK_clients_1` (`userEmpId`),
  KEY `FK_clients_2` (`client_userEmpId`),
  KEY `FK_clients_3` (`docId`),
  CONSTRAINT `FK_clients_3` FOREIGN KEY (`docId`) REFERENCES `documet_repository` (`docId`),
  CONSTRAINT `FK_clients_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`),
  CONSTRAINT `FK_clients_2` FOREIGN KEY (`client_userEmpId`) REFERENCES `client_details` (`userEmpId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documet_repository`
--

DROP TABLE IF EXISTS `documet_repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documet_repository` (
  `docId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Document` longblob NOT NULL,
  PRIMARY KEY (`docId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback_documents`
--

DROP TABLE IF EXISTS `feedback_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback_documents` (
  `feedbackDocId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `docId` int(10) unsigned DEFAULT NULL,
  `documentName` varchar(45) DEFAULT NULL,
  `feedbackId` int(10) unsigned DEFAULT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `documentStatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`feedbackDocId`),
  KEY `FK_feedback_documents_3` (`docId`),
  KEY `FK_feedback_documents_4` (`feedbackId`),
  CONSTRAINT `FK_feedback_documents_3` FOREIGN KEY (`docId`) REFERENCES `documet_repository` (`docId`),
  CONSTRAINT `FK_feedback_documents_4` FOREIGN KEY (`feedbackId`) REFERENCES `user_feedback` (`feedbackId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `Sr_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `DTM` datetime NOT NULL,
  `old_value` varchar(45) DEFAULT NULL,
  `new_value` varchar(45) DEFAULT NULL,
  `function` varchar(45) DEFAULT NULL,
  `column_name` varchar(45) DEFAULT NULL,
  `uid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Sr_no`),
  KEY `FK_history_1` (`uid`),
  CONSTRAINT `FK_history_1` FOREIGN KEY (`uid`) REFERENCES `user_detail` (`userEmpId`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `locId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `area` varchar(45) DEFAULT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Active',
  PRIMARY KEY (`locId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location_area`
--

DROP TABLE IF EXISTS `location_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_area` (
  `areaId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `areaName` varchar(45) DEFAULT NULL,
  `locId` int(10) unsigned DEFAULT NULL,
  `areaStatus` varchar(45) DEFAULT 'Active',
  PRIMARY KEY (`areaId`),
  KEY `FK_location_area_1` (`locId`),
  CONSTRAINT `FK_location_area_1` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `task_client_points`
--

DROP TABLE IF EXISTS `task_client_points`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_client_points` (
  `taskPointId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` int(10) unsigned DEFAULT NULL,
  `taskPoints` int(10) unsigned DEFAULT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`taskPointId`),
  KEY `FK_Task_Client_Points_1` (`client_id`),
  CONSTRAINT `FK_task_client_points_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `taskId` int(10) unsigned NOT NULL,
  `scheduleDate` date DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `venue` varchar(400) DEFAULT NULL,
  `userEmpId` int(10) unsigned DEFAULT NULL,
  `locId` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Initiate',
  `client_id` int(10) unsigned NOT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `expiryDate` date DEFAULT NULL,
  `taskUniqueName` varchar(75) NOT NULL,
  PRIMARY KEY (`taskId`),
  KEY `userId` (`userEmpId`),
  KEY `FK_tasks_2` (`locId`),
  KEY `FK_tasks_3` (`client_id`),
  CONSTRAINT `FK_tasks_2` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`),
  CONSTRAINT `FK_tasks_3` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `userId` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kjnextde_mystery`@`%`*/ /*!50003 trigger tasks_trigger_insert
after insert
on tasks
for each row

begin
   
    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.scheduleDate,'TASK','scheduleDate',new.created_uId);
    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.time,'TASK','time',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.venue,'TASK','venue',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.userEmpId,'TASK','userEmpId',new.created_uId);
    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.status,'TASK','status',new.created_uId);
  
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_detail` (
  `userEmpId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `address` int(10) unsigned DEFAULT NULL,
  `contactNo` varchar(45) DEFAULT NULL,
  `emailId` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Inactive',
  `gender` varchar(45) DEFAULT NULL,
  `uId` int(10) unsigned DEFAULT NULL,
  `locId` int(10) unsigned DEFAULT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `verificationId` varchar(45) DEFAULT NULL,
  `expiryDate` date DEFAULT NULL,
  PRIMARY KEY (`userEmpId`),
  KEY `FK_user_detail_1` (`locId`),
  KEY `FK_user_detail_2` (`uId`),
  KEY `FK_user_detail_3` (`address`),
  CONSTRAINT `FK_user_detail_1` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`),
  CONSTRAINT `FK_user_detail_2` FOREIGN KEY (`uId`) REFERENCES `users` (`uId`),
  CONSTRAINT `FK_user_detail_3` FOREIGN KEY (`address`) REFERENCES `location_area` (`areaId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kjnextde_mystery`@`%`*/ /*!50003 trigger user_detail_trigger_insert 
after insert
on user_detail

for each row

begin
   
	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.firstName,'USER','firstName',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.lastName,'USER','lastName',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.address,'USER','address',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.contactNo,'USER','contactNo',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.emailId,'USER','emailId',new.created_uId);

	insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.status,'USER','status',new.created_uId);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kjnextde_mystery`@`%`*/ /*!50003 trigger user_detail_trigger_update 

after update

on user_detail

for each row

begin
   
   if(old.firstName!=new.firstName) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.firstName,new.firstName,'USER','firstName',new.modified_uid);

   end if;

   if(old.lastName!=new.lastName) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.lastName,new.lastName,'USER','lastName',new.modified_uid);

   end if;

   if(old.address!=new.address) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.address,new.address,'USER','address',new.modified_uid);

   end if;

   if(old.contactNo!=new.contactNo) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.contactNo,new.contactNo,'USER','contactNo',new.modified_uid);

   end if;

   if(old.emailId!=new.emailId) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.emailId,new.emailId,'USER','emailId',new.modified_uid);

   end if;

   if(old.status!=new.status) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.status,new.status,'USER','status',new.modified_uid);

   end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_documents`
--

DROP TABLE IF EXISTS `user_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_documents` (
  `uidDocId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userEmpId` int(10) unsigned NOT NULL,
  `docid` int(10) unsigned NOT NULL,
  `documentName` varchar(45) NOT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`uidDocId`),
  KEY `FK_user_documents_1` (`userEmpId`),
  KEY `FK_user_documents_2` (`docid`),
  CONSTRAINT `FK_user_documents_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`),
  CONSTRAINT `FK_user_documents_2` FOREIGN KEY (`docid`) REFERENCES `documet_repository` (`docId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_feedback`
--

DROP TABLE IF EXISTS `user_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_feedback` (
  `feedbackId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taskAssignProof` varchar(45) DEFAULT NULL,
  `feedbackDetails` varchar(45) DEFAULT NULL,
  `userEmpId` int(10) unsigned DEFAULT NULL,
  `client_id` int(10) unsigned DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `taskId` int(10) unsigned DEFAULT NULL,
  `sent_status` varchar(45) DEFAULT NULL,
  `sentDTM` datetime DEFAULT NULL,
  `remarkByClient` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`feedbackId`),
  KEY `FK_user_feedback_1` (`userEmpId`),
  KEY `FK_user_feedback_2` (`client_id`),
  KEY `FK_user_feedback_3` (`taskId`),
  CONSTRAINT `FK_user_feedback_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`),
  CONSTRAINT `FK_user_feedback_2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `FK_user_feedback_3` FOREIGN KEY (`taskId`) REFERENCES `tasks` (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kjnextde_mystery`@`%`*/ /*!50003 trigger user_feedback_trigger_insert
after insert
on user_feedback
for each row

begin

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.taskAssignProof,'FEEDBACK','taskAssignProof',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.feedbackDetails,'FEEDBACK','feedbackDetails',new.created_uId);

    insert into history(dtm,new_value,function,column_name,uid) values(NOW(),new.status,'FEEDBACK','status',new.created_uId);

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kjnextde_mystery`@`%`*/ /*!50003 trigger user_feedback_trigger_update


after update


on user_feedback

for each row


begin

   
if(old.taskAssignProof!=new.taskAssignProof) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.taskAssignProof,new.taskAssignProof,'FEEDBACK','taskAssignProof',new.modified_uid);

   end if;

   
if(old.feedbackDetails!=new.feedbackDetails) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.feedbackDetails,new.feedbackDetails,'FEEDBACK','feedbackDetails',new.modified_uid);

   end if;

   
if(old.status!=new.status) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.status,new.status,'FEEDBACK','status',new.modified_uid);

   end if;


end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_rewards`
--

DROP TABLE IF EXISTS `user_rewards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rewards` (
  `rewardId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `point` varchar(45) NOT NULL,
  `userEmpId` int(10) unsigned NOT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `created_uId` int(10) unsigned DEFAULT NULL,
  `modified_uId` int(10) unsigned DEFAULT NULL,
  `redeem_point` varchar(45) DEFAULT NULL,
  `redeem_point_req` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `mode_of_payment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rewardId`),
  KEY `FK_user_rewards_1` (`userEmpId`),
  CONSTRAINT `FK_user_rewards_1` FOREIGN KEY (`userEmpId`) REFERENCES `user_detail` (`userEmpId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`kjnextde_mystery`@`%`*/ /*!50003 trigger user_rewards_trigger_update
after update
on user_rewards
for each row
begin

if(old.point!=new.point) then
  insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.point,new.point,'REWARDS','point',new.modified_uid);
   end if;
if(old.redeem_point_req!=new.redeem_point_req) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.redeem_point_req,new.redeem_point_req,'REWARDS','redeem_point_req',new.modified_uid);

   end if;
   if(old.mode_of_payment!=new.mode_of_payment) then

    insert into history(dtm,old_value,new_value,function,column_name,uid) values(NOW(),old.mode_of_payment,new.mode_of_payment,'REWARDS','mode_of_payment',new.modified_uid);

   end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `uId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uName` varchar(45) DEFAULT NULL,
  `uPass` varchar(45) DEFAULT NULL,
  `uType` varchar(45) DEFAULT NULL,
  `createdDTM` datetime DEFAULT NULL,
  `modifiedDTM` datetime DEFAULT NULL,
  `lastAccessedTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-18 20:38:31
