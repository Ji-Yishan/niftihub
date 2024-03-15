/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.31 : Database - niftiihub
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`niftiihub` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `niftiihub`;

/*Table structure for table `design` */

CREATE TABLE `design` (
  `did` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `price` double DEFAULT '0',
  `info` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `brand` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pic` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  `id` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `describe` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作品介绍',
  `right` int DEFAULT '0' COMMENT '作品权益',
  `amount` int NOT NULL DEFAULT '1',
  `uid` int NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `design` */


/*Table structure for table `order` */

CREATE TABLE `order` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `sellerId` int NOT NULL,
  `buyerId` int NOT NULL,
  `time` timestamp NOT NULL,
  `did` int DEFAULT NULL,
  `id` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `order` */


/*Table structure for table `user_info` */

CREATE TABLE `user_info` (
  `phone` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `profileP` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `uid` int NOT NULL AUTO_INCREMENT,
  `password` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user_info` */


/*Table structure for table `user_setting` */

CREATE TABLE `user_setting` (
  `recognized` tinyint(1) DEFAULT '1',
  `showCollection` tinyint(1) DEFAULT '1',
  `showDesign` tinyint(1) DEFAULT '1',
  `uid` int NOT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `uid_info` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user_setting` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
