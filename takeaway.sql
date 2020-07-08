/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : takeaway

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-08 22:14:41
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of addresses
-- ----------------------------
INSERT INTO `addresses` VALUES ('1', '7', 'ZheJiang', 'HangZhou', 'ZUCC', 'xj', '11111111111');
INSERT INTO `addresses` VALUES ('4', '13', 'ZheJiang', 'WenZhou', '111111', '11111', '11111111122');
INSERT INTO `addresses` VALUES ('6', '13', 'ZheJiang', 'HangZhou', '1212121', '111', '11111111111');
INSERT INTO `addresses` VALUES ('7', '13', 'ZheJiang', 'TaiZhou', '1212', '11', '11111111111');
INSERT INTO `addresses` VALUES ('8', '13', 'ShangHai', 'PuXi', 'lalalaa', '12', '11111111111');

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `admin_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', '1', '1');
INSERT INTO `admins` VALUES ('2', 'hk', '1');

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
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for counts
-- ----------------------------
DROP TABLE IF EXISTS `counts`;
CREATE TABLE `counts` (
  `count_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `ac_money` double NOT NULL,
  `count_sale` double NOT NULL,
  PRIMARY KEY (`count_no`),
  KEY `FK_have4` (`shop_no`),
  CONSTRAINT `FK_have4` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counts
-- ----------------------------
INSERT INTO `counts` VALUES ('13', '9', '10', '1');
INSERT INTO `counts` VALUES ('14', '9', '200', '3');
INSERT INTO `counts` VALUES ('16', '9', '31', '2');
INSERT INTO `counts` VALUES ('17', '9', '40', '2.5');

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
-- Records of give
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('24', '15', 'xyrs1', '12', '11');
INSERT INTO `goods` VALUES ('25', '15', '111111', '111', '11');
INSERT INTO `goods` VALUES ('26', '15', '121211', '11', '11');
INSERT INTO `goods` VALUES ('28', '15', 'asasasa', '100', '85');
INSERT INTO `goods` VALUES ('29', '18', '212121', '3', '2');

-- ----------------------------
-- Table structure for good_more
-- ----------------------------
DROP TABLE IF EXISTS `good_more`;
CREATE TABLE `good_more` (
  `order_no` bigint(20) NOT NULL,
  `good_no` bigint(20) NOT NULL,
  `good_count` bigint(20) NOT NULL,
  `good_price` double NOT NULL,
  `good_sale` double DEFAULT NULL,
  `good_name` varchar(20) NOT NULL,
  PRIMARY KEY (`order_no`,`good_no`),
  KEY `FK_good_more2` (`good_no`),
  CONSTRAINT `FK_good_more` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `FK_good_more2` FOREIGN KEY (`good_no`) REFERENCES `goods` (`good_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good_more
-- ----------------------------
INSERT INTO `good_more` VALUES ('113', '24', '121', '1452', '1331', 'xyrs1');
INSERT INTO `good_more` VALUES ('114', '26', '1', '11', '11', '121211');
INSERT INTO `good_more` VALUES ('115', '28', '1', '100', '85', 'asasasa');
INSERT INTO `good_more` VALUES ('116', '25', '1212', '134532', '13332', '111111');
INSERT INTO `good_more` VALUES ('117', '26', '12', '132', '132', '121211');
INSERT INTO `good_more` VALUES ('118', '25', '1212', '134532', '13332', '111111');
INSERT INTO `good_more` VALUES ('118', '26', '1111', '12221', '12221', '121211');
INSERT INTO `good_more` VALUES ('118', '28', '1212', '121200', '103020', 'asasasa');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kinds
-- ----------------------------
INSERT INTO `kinds` VALUES ('15', '9', 'cai12121', '4');
INSERT INTO `kinds` VALUES ('17', '11', 'cai12121', '0');
INSERT INTO `kinds` VALUES ('18', '9', '111', '1');

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
  `money` double DEFAULT NULL,
  `true_money` double DEFAULT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  `arrive` timestamp NULL DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('111', null, null, null, '9', null, null, null, null, null, '0', '13');
INSERT INTO `orders` VALUES ('112', '4', null, null, '9', null, '0', '0', '2020-07-08 21:54:03', '2020-07-08 22:23:56', '1', '13');
INSERT INTO `orders` VALUES ('113', '4', null, '14', '9', null, '1331', '1328', '2020-07-08 21:54:53', '2020-07-08 22:24:46', '1', '13');
INSERT INTO `orders` VALUES ('114', '8', null, '13', '9', '3', '11', '10', '2020-07-08 21:55:53', '2020-07-08 22:25:31', '1', '13');
INSERT INTO `orders` VALUES ('115', '6', null, null, '9', '3', '85', '84', '2020-07-08 22:00:35', '2020-07-08 22:30:08', '1', '13');
INSERT INTO `orders` VALUES ('116', null, null, null, '9', null, null, null, null, null, '0', '13');
INSERT INTO `orders` VALUES ('117', '8', null, '17', '9', '12', '132', '107.5', '2020-07-08 22:04:01', '2020-07-08 22:33:52', '1', '13');
INSERT INTO `orders` VALUES ('118', '8', null, '14', '9', '12', '128573', '128548', '2020-07-08 22:05:08', '2020-07-08 22:34:55', '1', '13');

-- ----------------------------
-- Table structure for owner_count
-- ----------------------------
DROP TABLE IF EXISTS `owner_count`;
CREATE TABLE `owner_count` (
  `youhui_no` bigint(20) NOT NULL,
  `user_no` bigint(20) NOT NULL,
  `count_money` double NOT NULL,
  `num` bigint(20) NOT NULL,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`youhui_no`,`user_no`),
  KEY `FK_owner_count2` (`user_no`),
  KEY `FK_count3` (`shop_no`),
  CONSTRAINT `FK_count3` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`),
  CONSTRAINT `FK_owner_count` FOREIGN KEY (`youhui_no`) REFERENCES `youhui` (`youhui_no`),
  CONSTRAINT `FK_owner_count2` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of owner_count
-- ----------------------------
INSERT INTO `owner_count` VALUES ('3', '13', '1', '1', '2020-07-31 14:09:58', '9');
INSERT INTO `owner_count` VALUES ('12', '13', '22', '2', '2020-07-17 22:01:56', '9');

-- ----------------------------
-- Table structure for rider
-- ----------------------------
DROP TABLE IF EXISTS `rider`;
CREATE TABLE `rider` (
  `rider_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `rider_name` varchar(20) NOT NULL,
  `rider_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rider_level` bigint(20) NOT NULL,
  `rider_site` tinyint(1) NOT NULL,
  PRIMARY KEY (`rider_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rider
-- ----------------------------
INSERT INTO `rider` VALUES ('1', 'ysg2', '2020-07-07 08:27:03', '1', '0');
INSERT INTO `rider` VALUES ('3', 'xj12138', '2020-07-07 08:58:54', '1', '0');
INSERT INTO `rider` VALUES ('4', 'hk1', '2020-07-07 08:58:59', '1', '0');

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
-- Records of rider_income
-- ----------------------------

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `shop_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(20) NOT NULL,
  `level` bigint(20) NOT NULL,
  `avg_consume` double NOT NULL,
  `sum_sale` double NOT NULL,
  PRIMARY KEY (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES ('9', 'xjhouse', '5', '121', '11');
INSERT INTO `shops` VALUES ('11', 'ysghouse', '5', '22', '22');
INSERT INTO `shops` VALUES ('12', 'hkhouse', '5', '0', '0');
INSERT INTO `shops` VALUES ('14', '1', '4', '0', '0');
INSERT INTO `shops` VALUES ('15', '2', '5', '0', '0');
INSERT INTO `shops` VALUES ('16', '3', '5', '0', '0');
INSERT INTO `shops` VALUES ('17', '4', '5', '0', '0');
INSERT INTO `shops` VALUES ('18', '5', '5', '0', '0');
INSERT INTO `shops` VALUES ('19', '6', '5', '0', '0');
INSERT INTO `shops` VALUES ('20', '11', '5', '0', '0');
INSERT INTO `shops` VALUES ('21', '221', '5', '0', '0');
INSERT INTO `shops` VALUES ('22', '121212121', '5', '0', '0');
INSERT INTO `shops` VALUES ('23', '1212121', '5', '0', '0');
INSERT INTO `shops` VALUES ('24', '2121212', '5', '0', '0');
INSERT INTO `shops` VALUES ('25', '323232', '5', '0', '0');
INSERT INTO `shops` VALUES ('26', '323232232', '5', '0', '0');
INSERT INTO `shops` VALUES ('27', '323', '5', '0', '0');
INSERT INTO `shops` VALUES ('28', '3333333333', '5', '0', '0');
INSERT INTO `shops` VALUES ('29', '55555555', '5', '0', '0');
INSERT INTO `shops` VALUES ('30', '11111111111', '5', '0', '0');
INSERT INTO `shops` VALUES ('31', '22222222', '5', '0', '0');
INSERT INTO `shops` VALUES ('32', '3333333333333', '5', '0', '0');
INSERT INTO `shops` VALUES ('33', '1111111111111111111', '5', '0', '0');
INSERT INTO `shops` VALUES ('34', '2222222222', '5', '0', '0');
INSERT INTO `shops` VALUES ('35', '333333333322', '5', '0', '0');
INSERT INTO `shops` VALUES ('36', '11111111', '5', '0', '0');
INSERT INTO `shops` VALUES ('37', '1111', '5', '0', '0');
INSERT INTO `shops` VALUES ('38', '111', '5', '0', '0');
INSERT INTO `shops` VALUES ('39', '222222222', '5', '0', '0');
INSERT INTO `shops` VALUES ('40', '111111', '5', '0', '0');
INSERT INTO `shops` VALUES ('41', '44444444', '5', '0', '0');
INSERT INTO `shops` VALUES ('42', '6666666', '5', '0', '0');
INSERT INTO `shops` VALUES ('43', '77777777', '5', '0', '0');
INSERT INTO `shops` VALUES ('44', '77', '5', '0', '0');
INSERT INTO `shops` VALUES ('45', '77777777777', '5', '0', '0');
INSERT INTO `shops` VALUES ('46', '777777777777', '5', '0', '0');
INSERT INTO `shops` VALUES ('47', '5656', '5', '0', '0');
INSERT INTO `shops` VALUES ('48', '565', '5', '0', '0');
INSERT INTO `shops` VALUES ('49', '4545', '5', '0', '0');
INSERT INTO `shops` VALUES ('50', '21212', '5', '0', '0');
INSERT INTO `shops` VALUES ('51', '2323232', '5', '0', '0');
INSERT INTO `shops` VALUES ('52', '4343434', '5', '0', '0');
INSERT INTO `shops` VALUES ('53', '3434343', '5', '0', '0');
INSERT INTO `shops` VALUES ('54', '34343434', '5', '0', '0');
INSERT INTO `shops` VALUES ('55', '343434343', '5', '0', '0');
INSERT INTO `shops` VALUES ('56', '3232323232323', '5', '0', '0');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `sex` int(1) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `tele` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `city` varchar(20) NOT NULL,
  `sign_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `vip` tinyint(1) NOT NULL,
  `vip_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('7', 'ysg', '2', '1', '11111111134', '1121212@qq.com', 'HongKong', '2020-07-05 09:03:57', '1', '2023-07-04 21:26:41');
INSERT INTO `users` VALUES ('13', '1', '0', '1', '11111111111', '1@qq.com', 'HongKong', '2020-07-07 11:00:49', '1', '2022-07-07 09:21:11');
INSERT INTO `users` VALUES ('14', '11', '2', '11', '11111111111', '1@qq.com', 'HongKong', '2020-07-07 09:22:01', '1', '2020-08-07 09:22:01');

-- ----------------------------
-- Table structure for youhui
-- ----------------------------
DROP TABLE IF EXISTS `youhui`;
CREATE TABLE `youhui` (
  `youhui_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `youhui_sale` double NOT NULL,
  `request` bigint(20) NOT NULL,
  `startday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `endday` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `together` tinyint(1) NOT NULL,
  PRIMARY KEY (`youhui_no`),
  KEY `FK_to` (`shop_no`),
  CONSTRAINT `FK_to` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of youhui
-- ----------------------------
INSERT INTO `youhui` VALUES ('3', '9', '1', '2', '2000-03-01 00:00:00', '2000-03-01 00:00:00', '0');
INSERT INTO `youhui` VALUES ('6', '11', '1', '1', '2000-01-01 00:00:00', '2000-03-01 00:00:00', '0');
INSERT INTO `youhui` VALUES ('9', '11', '30', '3', '2020-03-01 00:00:00', '2020-03-03 00:00:00', '0');
INSERT INTO `youhui` VALUES ('10', '23', '12', '2', '2020-02-01 00:00:00', '2020-03-01 00:00:00', '0');
INSERT INTO `youhui` VALUES ('12', '9', '22', '3', '2020-02-01 00:00:00', '2020-03-01 00:00:00', '1');
