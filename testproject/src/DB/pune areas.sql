delete from location_area;
delete from location;
INSERT INTO `location` (`locId`,`country`,`state`,`city`,`area`,`createdDTM`,`modifiedDTM`,`created_uId`,`modified_uId`) VALUES 
 (1,'India','Maharashtra','Pune','karvenagar',NULL,NULL,NULL,NULL);

INSERT INTO `location_area` (`areaName`,`locId`) VALUES 
 ('Shivaji Nagar',1),		
 ('Pune Railway Station',1),
 ('Swargate',1),
 ('Boat club road',1),
 ('Magarpatta',1),
 ('Daund',1),
 ('Chikhli',1),
 ('Kalewadi',1),
 ('Kasarwadi',1),
 ('Phugewadi ',1),
 ('Pimple Saudagar',1),
 ('Narayan peth',1),
 ('Talegaon',1),
 ('Kasba peth',1),
 ('Shirur',1),
 ('Bhor',1),
 ('Mulshi',1),
 ('Wadgaon',1),
 ('Welhe',1),
 ('Ambegaon',1),
 ('Junnar',1),
 ('Rajgurunagar',1),
 ('Baramati',1),
 ('Indapur',1),
 ('Purandhar',1),
 ('Bhawani Peth',1),
 ('Erandwana',1),
 ('Ghorpuri Lines',1),
 ('Kalyani Nagar',1),
 ('Kondhwa',1),
 ('Narayan Peth',1),
 ('Hadapsar',1),
 ('Akurdi',1);
 
