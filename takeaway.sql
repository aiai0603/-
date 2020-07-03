/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : takeaway

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-03 16:33:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for addresses
-- ----------------------------
DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `address_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) DEFAULT NULL,
  `sheng` varchar(20) NOT NULL,
  `shi` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `call_user` varchar(20) NOT NULL,
  `address_tele` varchar(20) NOT NULL,
  PRIMARY KEY (`address_no`),
  KEY `FK_have5` (`user_no`),
  CONSTRAINT `FK_have5` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `admin_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `good_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` bigint(20) NOT NULL,
  `comment_word` varchar(1000) NOT NULL,
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_level` bigint(20) NOT NULL,
  `comment_pic` longblob NOT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`good_no`,`user_no`),
  KEY `FK_comment2` (`user_no`),
  KEY `FK_shop_no` (`shop_no`),
  CONSTRAINT `FK_comment` FOREIGN KEY (`good_no`) REFERENCES `goods` (`good_no`),
  CONSTRAINT `FK_comment2` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FK_shop_no` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for counts
-- ----------------------------
DROP TABLE IF EXISTS `counts`;
CREATE TABLE `counts` (
  `count_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `ac_money` double NOT NULL,
  `count_sale` double NOT NULL,
  `together` tinyint(1) NOT NULL,
  PRIMARY KEY (`count_no`),
  KEY `FK_have4` (`shop_no`),
  CONSTRAINT `FK_have4` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for give
-- ----------------------------
DROP TABLE IF EXISTS `give`;
CREATE TABLE `give` (
  `user_no` bigint(20) NOT NULL,
  `shop_no` bigint(20) NOT NULL,
  `need` smallint(6) NOT NULL,
  `already` bigint(20) NOT NULL,
  `youhui_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_no`,`shop_no`),
  KEY `FK_give2` (`shop_no`),
  KEY `FK_give3` (`youhui_no`),
  CONSTRAINT `FK_give` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FK_give2` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`),
  CONSTRAINT `FK_give3` FOREIGN KEY (`youhui_no`) REFERENCES `youhui` (`youhui_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `good_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `kind_no` bigint(20) DEFAULT NULL,
  `good_name` varchar(20) NOT NULL,
  `good_price` double NOT NULL,
  `good_sale` double NOT NULL,
  PRIMARY KEY (`good_no`),
  KEY `FK_have2` (`kind_no`),
  CONSTRAINT `FK_have2` FOREIGN KEY (`kind_no`) REFERENCES `kinds` (`kind_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for good_more
-- ----------------------------
DROP TABLE IF EXISTS `good_more`;
CREATE TABLE `good_more` (
  `order_no` bigint(20) NOT NULL,
  `good_no` bigint(20) NOT NULL,
  `good_more` bigint(20) NOT NULL,
  `good_price` double NOT NULL,
  `good_sale` double DEFAULT NULL,
  PRIMARY KEY (`order_no`,`good_no`),
  KEY `FK_good_more2` (`good_no`),
  CONSTRAINT `FK_good_more` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `FK_good_more2` FOREIGN KEY (`good_no`) REFERENCES `goods` (`good_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kinds
-- ----------------------------
DROP TABLE IF EXISTS `kinds`;
CREATE TABLE `kinds` (
  `kind_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `kind_name` varchar(20) NOT NULL,
  `kind_sum` bigint(20) NOT NULL,
  PRIMARY KEY (`kind_no`),
  KEY `FK_have` (`shop_no`),
  CONSTRAINT `FK_have` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_no` bigint(20) DEFAULT NULL,
  `rider_no` bigint(20) DEFAULT NULL,
  `count_no` bigint(20) DEFAULT NULL,
  `shop_no` bigint(20) DEFAULT NULL,
  `youhui_no` bigint(20) DEFAULT NULL,
  `money` double NOT NULL,
  `true_money` double NOT NULL,
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `arrive` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `site` bigint(20) NOT NULL,
  `user_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_no`),
  KEY `FK_have10` (`youhui_no`),
  KEY `FK_have11` (`address_no`),
  KEY `FK_have6` (`shop_no`),
  KEY `FK_have8` (`rider_no`),
  KEY `FK_have9` (`count_no`),
  KEY `FK_have7` (`user_no`),
  CONSTRAINT `FK_have10` FOREIGN KEY (`youhui_no`) REFERENCES `youhui` (`youhui_no`),
  CONSTRAINT `FK_have11` FOREIGN KEY (`address_no`) REFERENCES `addresses` (`address_no`),
  CONSTRAINT `FK_have6` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`),
  CONSTRAINT `FK_have7` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FK_have8` FOREIGN KEY (`rider_no`) REFERENCES `rider` (`rider_no`),
  CONSTRAINT `FK_have9` FOREIGN KEY (`count_no`) REFERENCES `counts` (`count_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for owner_count
-- ----------------------------
DROP TABLE IF EXISTS `owner_count`;
CREATE TABLE `owner_count` (
  `youhui_no` bigint(20) NOT NULL,
  `user_no` bigint(20) NOT NULL,
  `count_money` double NOT NULL,
  `num` bigint(20) NOT NULL,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`youhui_no`,`user_no`),
  KEY `FK_owner_count2` (`user_no`),
  KEY `FK_count3` (`shop_no`),
  CONSTRAINT `FK_count3` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`),
  CONSTRAINT `FK_owner_count` FOREIGN KEY (`youhui_no`) REFERENCES `youhui` (`youhui_no`),
  CONSTRAINT `FK_owner_count2` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rider
-- ----------------------------
DROP TABLE IF EXISTS `rider`;
CREATE TABLE `rider` (
  `rider_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `ride_name` varchar(20) NOT NULL,
  `rider_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rider_level` bigint(20) NOT NULL,
  PRIMARY KEY (`rider_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rider_income
-- ----------------------------
DROP TABLE IF EXISTS `rider_income`;
CREATE TABLE `rider_income` (
  `order_no` bigint(20) DEFAULT NULL,
  `rider_income_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rider_comment` bigint(20) NOT NULL,
  `income` double NOT NULL,
  `rider_no` bigint(20) DEFAULT NULL,
  KEY `FK_have12` (`order_no`),
  KEY `FK_have16` (`rider_no`),
  CONSTRAINT `FK_have12` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `FK_have16` FOREIGN KEY (`rider_no`) REFERENCES `rider` (`rider_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `shop_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(20) NOT NULL,
  `level` bigint(20) NOT NULL,
  `avg_consume` double NOT NULL,
  `sum_sale` bigint(20) NOT NULL,
  PRIMARY KEY (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `tele` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `city` varchar(20) NOT NULL,
  `sign_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vip` tinyint(1) NOT NULL,
  `vip_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for youhui
-- ----------------------------
DROP TABLE IF EXISTS `youhui`;
CREATE TABLE `youhui` (
  `youhui_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `youhui_sale` double NOT NULL,
  `request` bigint(20) NOT NULL,
  `startday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endday` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`youhui_no`),
  KEY `FK_to` (`shop_no`),
  CONSTRAINT `FK_to` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
