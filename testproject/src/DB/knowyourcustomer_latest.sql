-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.11-beta-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema indianmy_mystery
--

CREATE DATABASE IF NOT EXISTS indianmy_mystery;
USE indianmy_mystery;

--
-- Definition of table `admin_details`
--

DROP TABLE IF EXISTS `admin_details`;
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

--
-- Dumping data for table `admin_details`
--

/*!40000 ALTER TABLE `admin_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_details` ENABLE KEYS */;


--
-- Definition of table `client_details`
--

DROP TABLE IF EXISTS `client_details`;
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

--
-- Dumping data for table `client_details`
--

/*!40000 ALTER TABLE `client_details` DISABLE KEYS */;
INSERT INTO `client_details` (`userEmpId`,`cltFirstName`,`cltLastName`,`cltAddress`,`cltContactNo`,`cltEmail`,`cltStatus`,`uId`,`locId`) VALUES 
 (1,'sd','fdf',33,'2423423','qq243@fff.com','ACTIVE',NULL,1),
 (2,'qq','qqq',5,'3334','qq@qq.com','ACTIVE',NULL,1);
/*!40000 ALTER TABLE `client_details` ENABLE KEYS */;


--
-- Definition of table `client_documents`
--

DROP TABLE IF EXISTS `client_documents`;
CREATE TABLE `client_documents` (
  `client_docid` int(10) unsigned NOT NULL auto_increment,
  `client_id` int(10) unsigned default NULL,
  `docId` int(10) unsigned default NULL,
  `documentName` varchar(45) default NULL,
  PRIMARY KEY  (`client_docid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_documents`
--

/*!40000 ALTER TABLE `client_documents` DISABLE KEYS */;
INSERT INTO `client_documents` (`client_docid`,`client_id`,`docId`,`documentName`) VALUES 
 (1,5,1,'aaa.jpeg');
/*!40000 ALTER TABLE `client_documents` ENABLE KEYS */;


--
-- Definition of table `client_location`
--

DROP TABLE IF EXISTS `client_location`;
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

--
-- Dumping data for table `client_location`
--

/*!40000 ALTER TABLE `client_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_location` ENABLE KEYS */;


--
-- Definition of table `clients`
--

DROP TABLE IF EXISTS `clients`;
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

--
-- Dumping data for table `clients`
--

/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`client_id`,`client_name`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`userEmpId`,`client_userEmpId`,`docId`,`docname`) VALUES 
 (1,'aaa - Akurdi','2020-11-28 12:29:19',NULL,1,NULL,5,1,1,'aaa.jpeg'),
 (2,'qq - Magarpatta','2020-11-28 12:29:40',NULL,1,NULL,5,2,NULL,NULL);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;


--
-- Definition of table `documet_repository`
--

DROP TABLE IF EXISTS `documet_repository`;
CREATE TABLE `documet_repository` (
  `docId` int(10) unsigned NOT NULL auto_increment,
  `Document` longblob,
  PRIMARY KEY  (`docId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documet_repository`
--

/*!40000 ALTER TABLE `documet_repository` DISABLE KEYS */;
INSERT INTO `documet_repository` (`docId`,`Document`) VALUES 
 (1,NULL),
 (2,NULL),
 (3,NULL);
/*!40000 ALTER TABLE `documet_repository` ENABLE KEYS */;


--
-- Definition of table `feedback_documents`
--

DROP TABLE IF EXISTS `feedback_documents`;
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

--
-- Dumping data for table `feedback_documents`
--

/*!40000 ALTER TABLE `feedback_documents` DISABLE KEYS */;
INSERT INTO `feedback_documents` (`feedbackDocId`,`docId`,`documentName`,`feedbackId`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`documentStatus`) VALUES 
 (1,2,'aaa.jpeg',1,'2020-11-28 13:02:29',NULL,3,NULL,'OFFLINE_DOC_UPLOAD');
/*!40000 ALTER TABLE `feedback_documents` ENABLE KEYS */;


--
-- Definition of table `history`
--

DROP TABLE IF EXISTS `history`;
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

--
-- Dumping data for table `history`
--

/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;


--
-- Definition of table `location`
--

DROP TABLE IF EXISTS `location`;
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

--
-- Dumping data for table `location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`locId`,`country`,`state`,`city`,`area`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`status`) VALUES 
 (1,'India','Maharashtra','Pune','karvenagar',NULL,NULL,NULL,NULL,'Active');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;


--
-- Definition of table `location_area`
--

DROP TABLE IF EXISTS `location_area`;
CREATE TABLE `location_area` (
  `areaId` int(10) unsigned NOT NULL auto_increment,
  `areaName` varchar(45) default NULL,
  `locId` int(10) unsigned default NULL,
  `areaStatus` varchar(45) default 'Active',
  PRIMARY KEY  (`areaId`),
  KEY `FK_location_area_1` (`locId`),
  CONSTRAINT `FK_location_area_1` FOREIGN KEY (`locId`) REFERENCES `location` (`locId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location_area`
--

/*!40000 ALTER TABLE `location_area` DISABLE KEYS */;
INSERT INTO `location_area` (`areaId`,`areaName`,`locId`,`areaStatus`) VALUES 
 (1,'Shivaji Nagar',1,'Active'),
 (2,'Pune Railway Station',1,'Active'),
 (3,'Swargate',1,'Active'),
 (4,'Boat club road',1,'Active'),
 (5,'Magarpatta',1,'Active'),
 (6,'Daund',1,'Active'),
 (7,'Chikhli',1,'Active'),
 (8,'Kalewadi',1,'Active'),
 (9,'Kasarwadi',1,'Active'),
 (10,'Phugewadi ',1,'Active'),
 (11,'Pimple Saudagar',1,'Active'),
 (12,'Narayan peth',1,'Active'),
 (13,'Talegaon',1,'Active'),
 (14,'Kasba peth',1,'Active'),
 (15,'Shirur',1,'Active'),
 (16,'Bhor',1,'Active'),
 (17,'Mulshi',1,'Active'),
 (18,'Wadgaon',1,'Active'),
 (19,'Welhe',1,'Active'),
 (20,'Ambegaon',1,'Active'),
 (21,'Junnar',1,'Active'),
 (22,'Rajgurunagar',1,'Active'),
 (23,'Baramati',1,'Active'),
 (24,'Indapur',1,'Active'),
 (25,'Purandhar',1,'Active'),
 (26,'Bhawani Peth',1,'Active'),
 (27,'Erandwana',1,'Active'),
 (28,'Ghorpuri Lines',1,'Active'),
 (29,'Kalyani Nagar',1,'Active'),
 (30,'Kondhwa',1,'Active'),
 (31,'Narayan Peth',1,'Active'),
 (32,'Hadapsar',1,'Active'),
 (33,'Akurdi',1,'Active');
/*!40000 ALTER TABLE `location_area` ENABLE KEYS */;


--
-- Definition of table `task_client_points`
--

DROP TABLE IF EXISTS `task_client_points`;
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

--
-- Dumping data for table `task_client_points`
--

/*!40000 ALTER TABLE `task_client_points` DISABLE KEYS */;
INSERT INTO `task_client_points` (`taskPointId`,`client_id`,`taskPoints`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`) VALUES 
 (1,1,222,'2020-11-28 13:03:18',NULL,1,NULL),
 (2,2,33,'2020-11-28 13:03:26',NULL,1,NULL);
/*!40000 ALTER TABLE `task_client_points` ENABLE KEYS */;


--
-- Definition of table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
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

--
-- Dumping data for table `tasks`
--

/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`taskId`,`scheduleDate`,`time`,`venue`,`userEmpId`,`locId`,`status`,`client_id`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`expiryDate`,`taskUniqueName`) VALUES 
 (1,'2020-11-30','1:30pm','hfhfh',3,1,'FINISHED_USER',1,'2020-11-28 12:34:45','2020-11-28 13:02:29',1,3,'2020-12-15','lllkjhg-aaa-Pune-Aku-28-11-2020');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


--
-- Definition of table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
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

--
-- Dumping data for table `user_detail`
--

/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` (`userEmpId`,`firstName`,`lastName`,`address`,`contactNo`,`emailId`,`status`,`gender`,`uId`,`locId`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`verificationId`,`expiryDate`) VALUES 
 (1,'dipak','JBK',1,'8888809416','jayshree@xevatechnologies.com','ACTIVE','M',1,1,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'urban','JBK',2,'8888810613','jayshree@xevatechnologies.com','ACTIVE','M',2,1,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'kalpesh JBK - Shivaji Nagar','JBK',1,'8888810613','jayshree@xevatechnologies.com','ACTIVE','M',3,1,NULL,'2020-11-28 13:01:41',NULL,1,NULL,NULL),
 (4,'gajanan JBK - Pune Railway Station','JBK',2,'8888810613','jayshree@xevatechnologies.com','ACTIVE','M',4,1,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'rrr1','rrer',33,'24343','fdr@dfd.com','ACTIVE','M/S',5,1,'2020-11-28 12:29:19','2020-11-28 16:56:47',1,5,NULL,NULL),
 (6,'jbkagent','jbkagent',31,'8888809416','jbkagent@gmail.com','APPROVED_USER','M',NULL,1,'2020-11-28 17:56:43','2020-11-28 17:57:58',NULL,1,'H9M1X',NULL);
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;


--
-- Definition of table `user_documents`
--

DROP TABLE IF EXISTS `user_documents`;
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

--
-- Dumping data for table `user_documents`
--

/*!40000 ALTER TABLE `user_documents` DISABLE KEYS */;
INSERT INTO `user_documents` (`uidDocId`,`userEmpId`,`docid`,`documentName`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`) VALUES 
 (1,5,1,'aaa.jpeg','2020-11-28 12:29:19',NULL,NULL,NULL),
 (2,6,3,'aaa.jpeg','2020-11-28 17:56:43',NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_documents` ENABLE KEYS */;


--
-- Definition of table `user_feedback`
--

DROP TABLE IF EXISTS `user_feedback`;
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

--
-- Dumping data for table `user_feedback`
--

/*!40000 ALTER TABLE `user_feedback` DISABLE KEYS */;
INSERT INTO `user_feedback` (`feedbackId`,`taskAssignProof`,`feedbackDetails`,`userEmpId`,`client_id`,`Status`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`taskId`,`sent_status`,`sentDTM`,`remarkByClient`) VALUES 
 (1,'fgdsgd','fdgdfg',3,1,'REASSIGNED_BY_CLIENT','2020-11-28 13:02:29','2020-11-28 13:03:36',1,1,1,'Y','2020-11-28 17:01:43','sdsdsd');
/*!40000 ALTER TABLE `user_feedback` ENABLE KEYS */;


--
-- Definition of table `user_rewards`
--

DROP TABLE IF EXISTS `user_rewards`;
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

--
-- Dumping data for table `user_rewards`
--

/*!40000 ALTER TABLE `user_rewards` DISABLE KEYS */;
INSERT INTO `user_rewards` (`rewardId`,`point`,`userEmpId`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`redeem_point`,`redeem_point_req`,`remarks`,`mode_of_payment`) VALUES 
 (1,'222',3,'2020-11-28 13:03:36',NULL,1,NULL,NULL,'100',NULL,NULL);
/*!40000 ALTER TABLE `user_rewards` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
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

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`uId`,`uName`,`uPass`,`uType`,`createdDTM`,`modifiedDTM`,`lastAccessedTime`) VALUES 
 (1,'javabykiran','javabykiran','AE',NULL,NULL,'Thu Dec 03 18:49:31 IST 2020'),
 (2,'info','info','AE',NULL,NULL,NULL),
 (3,'JBK','kiran','UA',NULL,'2020-11-28 13:01:41','Sat Nov 28 16:59:57 IST 2020'),
 (4,'JBK1','JBK1','UU',NULL,NULL,NULL),
 (5,'24AWAC','4T5E','CA','2020-11-28 13:04:03',NULL,'Sat Nov 28 16:56:30 IST 2020');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
