/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 8.0.39 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `test`;

/*Table structure for table `membership_level` */

DROP TABLE IF EXISTS `membership_level`;

CREATE TABLE `membership_level` (
  `id` int NOT NULL AUTO_INCREMENT,
  `level_name` varchar(64) NOT NULL,
  `min_points` int NOT NULL,
  `privileges` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `membership_level` */

insert  into `membership_level`(`id`,`level_name`,`min_points`,`privileges`) values (1,'普通会员',0,'无'),(2,'银卡会员',100,'享受折扣'),(3,'金卡会员',500,'优先服务'),(4,'钻石会员',1000,'专属客服');

/*Table structure for table `points_log` */

DROP TABLE IF EXISTS `points_log`;

CREATE TABLE `points_log` (
  `record_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `action` enum('earn','spend') NOT NULL,
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `point` int NOT NULL,
  `reason` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `username` (`username`),
  CONSTRAINT `points_log_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `points_log` */

insert  into `points_log`(`record_id`,`username`,`action`,`created_time`,`point`,`reason`) values (1,'jack123','earn','2024-12-19 16:01:36',100,'完成任务'),(2,'jack123','spend','2024-12-19 16:02:16',-50,'购买商品'),(3,'jack123','earn','2024-12-19 16:02:16',200,'推荐好友'),(4,'jack123','earn','2024-12-19 16:02:16',150,'参与活动'),(5,'jack123','earn','2024-12-19 16:02:16',50,'填写调查问卷'),(6,'jack123','earn','2024-12-19 16:02:16',300,'完成新用户注册'),(7,'jack123','spend','2024-12-19 16:02:16',-30,'兑换礼品'),(8,'jack123','spend','2024-12-19 16:02:16',-20,'参加抽奖活动'),(9,'jack123','spend','2024-12-19 16:02:16',-10,'购买会员服务');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `redeem_points` int NOT NULL,
  `minimum_level` enum('普通会员','银卡会员','金卡会员','钻石会员') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`redeem_points`,`minimum_level`) values (1,'星辰魔法杖',350,'普通会员'),(2,'隐形魔法袍',300,'普通会员'),(3,'古老的魔法书',550,'银卡会员'),(4,'梦幻魔法药水',250,'普通会员'),(5,'传说中的魔法石',800,'钻石会员'),(6,'魔法师的宝石指环',500,'银卡会员'),(7,'闪耀的魔法护符',400,'银卡会员'),(8,'龙鳞魔法斗篷',600,'金卡会员'),(9,'神秘魔法面具',350,'普通会员'),(10,'时间扭曲魔法水晶',700,'金卡会员'),(11,'魔法奇迹手套',300,'普通会员'),(13,'月光魔法项链',450,'银卡会员'),(14,'元素魔法靴子',500,'银卡会员'),(15,'梦境魔法耳环',400,'普通会员'),(16,'光辉魔法镜子',550,'银卡会员'),(17,'黎明魔法灯笼',300,'普通会员'),(19,'隐秘的魔法箱子',450,'金卡会员'),(24,'梅林的魔法药剂',400,'银卡会员');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `points` int DEFAULT '0',
  `membership` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`email`,`password`,`points`,`membership`) values (1,'jack123','1273979112@126.com','jack1',690,'金卡会员'),(2,'MaryYoung6721','26071429@qq.com','123i!',24,'普通会员'),(11,'mary77891','2212213@126.com','mary',0,NULL),(13,'Tori','23792874@qq.com','tri11',0,NULL),(15,'LilyChen','lily7213@126.com','lll',0,NULL),(17,'maryme','mm38e792@qq.com','12791',0,NULL),(20,'John','13789713@gmail.com','hojo821',0,NULL),(22,'sry','1273913@gmail.com','livend',0,NULL),(23,'mystery1','1236823@wue.com','1234',0,NULL),(25,'amybrown','Amy12837@gmail.com','amyme',0,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
