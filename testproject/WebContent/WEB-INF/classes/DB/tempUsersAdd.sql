INSERT INTO `users` (`uId`,`uName`,`uPass`,`uType`,`createdDTM`,`modifiedDTM`) VALUES 
 (1,'admin','admin','AE',NULL,NULL),(2,'admin1','admin1','AE',NULL,NULL);
 
INSERT INTO `user_detail` (`userEmpId`,`firstName`,`lastName`,`address`,`contactNo`,`emailId`,`status`,`gender`,`uId`,`locId`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`,`verificationId`) VALUES
 (1,'adminFirstName','adminLastName',2,'8888810613','revan@kjnext.com','ACTIVE','M',1,1,NULL,NULL,NULL,NULL,NULL),(2,'adminFirstName','adminLastName',2,'8888810613','revan@kjnext.com','ACTIVE','M',2,1,NULL,NULL,NULL,NULL,NULL);