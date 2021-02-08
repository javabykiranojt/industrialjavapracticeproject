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
 (2,'qq','qqq',5,'3334','qq@qq.com','ACTIVE',NULL,1),
 (3,'bigbazar fname','bigbazar lname',34,'1234567890','bigbazar@gmail.com','ACTIVE',NULL,2),
 (4,'bigbazarfname','bigbazarlname',34,'1234567890','1234567890','ACTIVE',NULL,2),
 (5,'XXXBB','BB',24,'423424234','suspendUserCheck@gmail.com','ACTIVE',NULL,1);
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
 (1,5,1,'aaa.jpeg'),
 (2,9,8,'Spring Boot 12 API New.docx');
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
 (2,'qq - Magarpatta','2020-11-28 12:29:40',NULL,1,NULL,5,2,NULL,NULL),
 (3,'bigbazar-sec16','2020-12-03 20:06:10',NULL,1,NULL,9,3,8,'Spring Boot 12 API New.docx'),
 (4,'sec-62-labour chowk','2020-12-03 20:07:15',NULL,1,NULL,9,4,NULL,NULL),
 (5,'XXXBB','2021-02-06 21:26:16',NULL,1,NULL,9,5,NULL,NULL);
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
 (1,2,'aaa.jpeg',1,'2020-11-28 13:02:29',NULL,3,NULL,'OFFLINE_DOC_UPLOAD'),
 (2,11,'Learn Angular at JBK.png',2,'2020-12-03 20:32:21',NULL,7,NULL,'BILL_UPLOAD'),
 (3,12,'Learn Angular at JBK.png',2,'2020-12-03 20:32:21',NULL,7,NULL,'OTHER_DOC_UPLOAD'),
 (4,10,'business-requirements-document.docx',2,'2020-12-03 20:32:21',NULL,7,NULL,'OFFLINE_DOC_UPLOAD'),
 (5,13,'Jbktest.docx',3,'2020-12-12 18:57:15',NULL,8,NULL,'OFFLINE_DOC_UPLOAD'),
 (6,16,'Syllabus Full Stack Java With Microservice an',4,'2021-02-06 21:37:16',NULL,8,NULL,'OFFLINE_DOC_UPLOAD');
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
 (1,'India','Maharashtra','Pune','karvenagar',NULL,NULL,NULL,NULL,'Active'),
 (2,'India','UttarPradesh','Noida',NULL,'2020-12-03 20:01:52',NULL,1,NULL,'ACTIVE');
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
 (33,'Akurdi',1,'Active'),
 (34,'atta market - sec 16',2,'ACTIVE');
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
 (2,2,33,'2020-11-28 13:03:26',NULL,1,NULL),
 (3,3,200,'2020-12-03 20:40:13',NULL,1,NULL),
 (4,5,4444,'2021-02-06 21:38:52',NULL,1,NULL);
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
 (1,'2020-11-30','1:30pm','hfhfh',3,1,'FINISHED_USER',1,'2020-11-28 12:34:45','2020-11-28 13:02:29',1,3,'2020-12-15','lllkjhg-aaa-Pune-Aku-28-11-2020'),
 (2,'2020-12-05','9:00pm','pls bring all details properly',10,2,'INITIATED_ADMIN_MULTIUSER',3,'2020-12-03 20:12:58',NULL,1,0,'2020-12-07','ankitlanuorchowkbigbazar-big-Noida-att-03-12-2020'),
 (3,'2020-12-07','8:30pm','do all properly',7,1,'FINISHED_USER',1,'2020-12-03 20:13:39','2020-12-03 20:32:21',1,7,'2020-12-10','puneketask-aaa-Pune-Aku-03-12-2020'),
 (4,'2020-12-07','8:30pm','do all properly',8,1,'REJECTED_TASK',1,'2020-12-03 20:13:39',NULL,1,0,'2020-12-10','puneketask-aaa-Pune-Aku-03-12-2020'),
 (5,'2020-12-16','9:00pm','asdasfsdf',7,1,'REJECTED_TASK',2,'2020-12-03 20:14:35','2020-12-03 20:17:20',1,6,'2020-12-23','nayawalatask-qq -Pune-Mag-03-12-2020'),
 (6,'2020-12-16','9:00pm','asdasfsdf',8,1,'FINISHED_USER',2,'2020-12-03 20:14:35','2020-12-12 18:57:16',1,8,'2020-12-23','nayawalatask-qq -Pune-Mag-03-12-2020'),
 (7,'2020-12-15','8:00pm','qewqwqwqw',4,1,'INITIATED_ADMIN',1,'2020-12-12 18:53:34','2020-12-12 18:55:37',1,1,'2020-12-16','qewqwqwqw-aaa-Pune-Aku-12-12-2020'),
 (8,'2021-02-11','10:30pm','XXXXXXX',7,1,'REJECTED_TASK',5,'2021-02-06 21:30:42',NULL,1,0,'2021-02-24','XXXXXXX-XXX-Pune-Ind-06-02-2021'),
 (9,'2021-02-11','10:30pm','XXXXXXX',8,1,'FINISHED_USER',5,'2021-02-06 21:30:42','2021-02-06 21:37:16',1,8,'2021-02-24','XXXXXXX-XXX-Pune-Ind-06-02-2021'),
 (10,'2021-02-11','10:30pm','XXXXXXX',11,1,'REJECTED_TASK',5,'2021-02-06 21:30:42',NULL,1,0,'2021-02-24','XXXXXXX-XXX-Pune-Ind-06-02-2021');
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
 (1,'javabykiran','javabykiran',1,'8888809416','jayshree@xevatechnologies.com','ACTIVE','M',1,1,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'urban','JBK',2,'8888810613','jayshree@xevatechnologies.com','ACTIVE','M',2,1,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'kalpesh JBK - Shivaji Nagar','JBK',1,'8888810613','jayshree@xevatechnologies.com','ACTIVE','M',3,1,NULL,'2020-11-28 13:01:41',NULL,1,NULL,NULL),
 (4,'gajanan JBK - Pune Railway Station','JBK',2,'8888810613','jayshree@xevatechnologies.com','ACTIVE','M',4,1,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'rrr1','rrer',33,'24343','fdr@dfd.com','ACTIVE','M/S',5,1,'2020-11-28 12:29:19','2020-11-28 16:56:47',1,5,NULL,NULL),
 (6,'jbkagent','jbkagent',31,'8888809416','jbkagent@gmail.com','APPROVED_USER','M',NULL,1,'2020-11-28 17:56:43','2020-11-28 17:57:58',NULL,1,'H9M1X',NULL),
 (7,'shwetaAgent','shwetaAgent',6,'1231231231','shwetaAgent@gmail.com','ACTIVE','F',6,1,'2020-12-03 19:37:23','2020-12-03 19:42:20',NULL,1,'DONE',NULL),
 (8,'vidyaAgent','vidyaAgent',29,'1231231231','vidyaAgent@gmail.com','ACTIVE','F',7,1,'2020-12-03 19:38:23','2020-12-03 19:42:51',NULL,1,'DONE',NULL),
 (9,'bigbazar','bigbazar lname',34,'1234567890','bigbazar-sec16@gmail.com','ACTIVE','M/S',9,2,'2020-12-03 20:06:10','2020-12-03 20:36:35',1,NULL,NULL,NULL),
 (10,'ankitAgent','ankitAgent',34,'1231231231','ankitAgent@gmail.com','ACTIVE','M',8,2,'2020-12-03 20:09:19',NULL,NULL,1,NULL,NULL),
 (11,'kirantrainiing','kirantrainiing',14,'99999999999','kirantrainiing@gmail.com','ACTIVE','M',10,1,'2021-02-06 20:51:14','2021-02-06 21:12:15',NULL,1,'DONE',NULL),
 (12,'XXAgent','XXAgent',34,'344535354','XXAgent@dfdsf.com','ACTIVE','M',11,2,'2021-02-06 21:27:56','2021-02-06 21:28:41',NULL,1,NULL,NULL);
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
 (2,6,3,'aaa.jpeg','2020-11-28 17:56:43',NULL,NULL,NULL),
 (3,7,4,'aaa.jpeg','2020-12-03 19:37:23',NULL,NULL,NULL),
 (4,7,5,'WhatsApp Image 2019-10-05 at 8.38.18 PM.jpeg','2020-12-03 19:37:23',NULL,NULL,NULL),
 (5,8,6,'WhatsApp Image 2019-10-05 at 8.38.58 PM.jpeg','2020-12-03 19:38:23',NULL,NULL,NULL),
 (6,8,7,'WhatsApp Image 2019-10-05 at 8.38.35 PM.jpeg','2020-12-03 19:38:23',NULL,NULL,NULL),
 (7,9,8,'Spring Boot 12 API New.docx','2020-12-03 20:06:10',NULL,NULL,NULL),
 (8,10,9,'Learn Angular at JBK.png','2020-12-03 20:09:19',NULL,NULL,NULL),
 (9,11,14,'WhatsApp Image 2019-10-05 at 8.38.58 PM.jpeg','2021-02-06 20:51:14',NULL,NULL,NULL),
 (10,12,15,'WhatsApp Image 2019-10-05 at 8.38.58 PM.jpeg','2021-02-06 21:27:56',NULL,NULL,NULL);
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
 (1,'fgdsgd','fdgdfg',3,1,'REASSIGNED_BY_CLIENT','2020-11-28 13:02:29','2020-11-28 13:03:36',1,1,1,'Y','2020-11-28 17:01:43','sdsdsd'),
 (2,'bad service','Not so good',7,1,'REASSIGNED_BY_CLIENT','2020-12-03 20:32:21','2020-12-03 20:33:13',1,1,3,'Y','2020-12-03 20:35:08','i am not satisfied with your feedback'),
 (3,'GOOD','BADDD',8,2,'REASSIGNED_BY_CLIENT','2020-12-12 18:57:15','2020-12-12 18:58:24',1,1,6,'Y','2020-12-12 18:58:53','pls check again'),
 (4,'Good ddd','Badddd',8,5,'REASSIGNED_BY_CLIENT','2021-02-06 21:37:16','2021-02-06 21:39:06',1,1,9,'Y','2021-02-06 21:39:57','I o not like this is got');
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
 (1,'222',3,'2020-11-28 13:03:36',NULL,1,NULL,NULL,'100',NULL,NULL),
 (2,'222',7,'2020-12-03 20:33:13',NULL,1,NULL,NULL,'123',NULL,NULL),
 (3,'4463',8,'2020-12-12 18:58:24','2021-02-06 21:37:16',1,1,'14','333','dfsdf','Cash');
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
 (1,'javabykiran','javabykiran','AE',NULL,NULL,'Sat Feb 06 21:41:04 IST 2021'),
 (2,'info','info','AE',NULL,NULL,'Thu Dec 03 20:24:27 IST 2020'),
 (3,'JBK','kiran','UA',NULL,'2020-11-28 13:01:41','Sat Nov 28 16:59:57 IST 2020'),
 (4,'JBK1','JBK1','UU',NULL,NULL,NULL),
 (5,'24AWAC','4T5E','CA','2020-11-28 13:04:03',NULL,'Sat Feb 06 20:46:39 IST 2021'),
 (6,'shwetaAgent@gmail.com','B4PEFWCE','UA','2020-12-03 19:51:11',NULL,'Thu Dec 03 20:39:42 IST 2020'),
 (7,'vidyaAgent@gmail.com','C2QKYIX1','UA','2020-12-03 20:00:09',NULL,'Sat Feb 06 21:39:13 IST 2021'),
 (8,'ankitAgent@gmail.com','NZUI*514','UA','2020-12-03 20:09:30',NULL,'Sat Feb 06 21:32:54 IST 2021'),
 (9,'H*QMTA','WKQP','CA','2020-12-03 20:36:35',NULL,'Sat Feb 06 21:40:20 IST 2021'),
 (10,'kirantrainiing@gmail.com','65CH127A','UA','2021-02-06 20:58:13',NULL,NULL),
 (11,'XXAgent@dfdsf.com','kiran','UA','2021-02-06 21:28:08','2021-02-06 21:28:41','Sat Feb 06 21:31:40 IST 2021');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
