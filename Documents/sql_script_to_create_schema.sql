create database hibernate;
use hibernate;
CREATE TABLE `notebook` (
  `id` int NOT NULL AUTO_INCREMENT,
  `noteBookName` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`user_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `endDate` date DEFAULT NULL,
  `noteDescription` varchar(1000) DEFAULT NULL,
  `noteName` varchar(255) DEFAULT NULL,
  `remainderDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `noteBook_id` int DEFAULT NULL,
  `statusName` varchar(1000) DEFAULT NULL,
  `tagName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`));
  create table `user`( `id` int not null auto_increment,
  `userName` varchar(255),
  `mobileNumber` varchar(255),
  `email` varchar(255),
  `password` varchar(255),
  primary key(`id`));