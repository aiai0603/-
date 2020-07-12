/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : takeaway

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-12 21:43:04
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
  `isdelete` int(20) NOT NULL,
  PRIMARY KEY (`address_no`),
  KEY `FK_have5` (`user_no`),
  CONSTRAINT `FK_have5` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of addresses
-- ----------------------------
INSERT INTO `addresses` VALUES ('18', '20', 'ZheJiang', 'HangZhou', 'zucc', 'xj', '11111111111', '0');
INSERT INTO `addresses` VALUES ('19', '20', 'ZheJiang', 'HangZhou', 'zucc', 'xj2', '11111111111', '1');
INSERT INTO `addresses` VALUES ('20', '21', 'ZheJiang', 'WenZhou', '11', '11', '11111111111', '0');

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `admin_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', '1', '1');
INSERT INTO `admins` VALUES ('4', '2', '1');

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
  `shop_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`good_no`,`user_no`),
  KEY `FK_comment2` (`user_no`),
  KEY `FK_shop_no` (`shop_no`),
  CONSTRAINT `FK_comment` FOREIGN KEY (`good_no`) REFERENCES `goods` (`good_no`),
  CONSTRAINT `FK_comment2` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FK_shop_no` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('46', '20', '1', '2020-07-12 21:42:08', '5', '63');
INSERT INTO `comment` VALUES ('47', '20', '1', '2020-07-12 21:41:39', '4', '65');

-- ----------------------------
-- Table structure for counts
-- ----------------------------
DROP TABLE IF EXISTS `counts`;
CREATE TABLE `counts` (
  `count_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `ac_money` double NOT NULL,
  `count_sale` double NOT NULL,
  `isdelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`count_no`),
  KEY `FK_have4` (`shop_no`),
  CONSTRAINT `FK_have4` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counts
-- ----------------------------
INSERT INTO `counts` VALUES ('22', '63', '20', '19', '0');
INSERT INTO `counts` VALUES ('23', '63', '32', '31', '0');
INSERT INTO `counts` VALUES ('24', '63', '31.9', '20', '0');
INSERT INTO `counts` VALUES ('25', '63', '30', '19.5', '0');
INSERT INTO `counts` VALUES ('26', '63', '19', '18', '0');
INSERT INTO `counts` VALUES ('27', '63', '13', '12', '0');
INSERT INTO `counts` VALUES ('28', '62', '20', '11', '1');
INSERT INTO `counts` VALUES ('29', '62', '19', '1', '0');
INSERT INTO `counts` VALUES ('30', '62', '32', '30', '0');
INSERT INTO `counts` VALUES ('31', '65', '12', '1', '0');
INSERT INTO `counts` VALUES ('32', '62', '20', '12', '0');

-- ----------------------------
-- Table structure for give
-- ----------------------------
DROP TABLE IF EXISTS `give`;
CREATE TABLE `give` (
  `user_no` bigint(20) NOT NULL,
  `shop_no` bigint(20) NOT NULL,
  `need` smallint(6) NOT NULL,
  `already` bigint(20) NOT NULL,
  `youhui_no` bigint(20) NOT NULL,
  PRIMARY KEY (`user_no`,`youhui_no`),
  KEY `FK_give2` (`shop_no`),
  KEY `FK_give3` (`youhui_no`),
  CONSTRAINT `FK_give` FOREIGN KEY (`user_no`) REFERENCES `users` (`user_no`),
  CONSTRAINT `FK_give2` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`),
  CONSTRAINT `FK_give3` FOREIGN KEY (`youhui_no`) REFERENCES `youhui` (`youhui_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of give
-- ----------------------------
INSERT INTO `give` VALUES ('20', '62', '2', '1', '24');
INSERT INTO `give` VALUES ('20', '62', '2', '1', '25');
INSERT INTO `give` VALUES ('20', '65', '2', '1', '27');
INSERT INTO `give` VALUES ('21', '65', '2', '1', '27');

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
  `isdelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`good_no`),
  KEY `FK_have2` (`kind_no`),
  CONSTRAINT `FK_have2` FOREIGN KEY (`kind_no`) REFERENCES `kinds` (`kind_no`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('38', '25', '小炸鸡', '20', '19', '1');
INSERT INTO `goods` VALUES ('39', '25', '大炸鸡', '20', '16', '0');
INSERT INTO `goods` VALUES ('40', '25', '小炸鸡', '12', '11', '0');
INSERT INTO `goods` VALUES ('41', '32', '111', '11', '11', '1');
INSERT INTO `goods` VALUES ('42', '34', '可乐', '13', '11', '0');
INSERT INTO `goods` VALUES ('43', '34', '小清新', '5', '3.5', '0');
INSERT INTO `goods` VALUES ('44', '33', '肌肉汉堡', '21.7', '11.4', '0');
INSERT INTO `goods` VALUES ('45', '26', '小炸鸡', '12', '11', '0');
INSERT INTO `goods` VALUES ('46', '26', 'sm炸鸡', '12', '11', '0');
INSERT INTO `goods` VALUES ('47', '29', '11', '11', '11', '0');

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
INSERT INTO `good_more` VALUES ('416', '39', '1', '20', '16', '大炸鸡');
INSERT INTO `good_more` VALUES ('419', '47', '11', '121', '121', '11');
INSERT INTO `good_more` VALUES ('421', '39', '1', '20', '16', '大炸鸡');
INSERT INTO `good_more` VALUES ('423', '47', '1', '11', '11', '11');
INSERT INTO `good_more` VALUES ('424', '39', '1', '20', '16', '大炸鸡');
INSERT INTO `good_more` VALUES ('425', '47', '1', '11', '11', '11');
INSERT INTO `good_more` VALUES ('426', '40', '2', '24', '22', '小炸鸡');
INSERT INTO `good_more` VALUES ('427', '47', '1', '11', '11', '11');
INSERT INTO `good_more` VALUES ('428', '39', '11', '220', '176', '大炸鸡');
INSERT INTO `good_more` VALUES ('429', '46', '1', '12', '11', 'sm炸鸡');
INSERT INTO `good_more` VALUES ('430', '47', '1', '11', '11', '11');
INSERT INTO `good_more` VALUES ('432', '46', '11', '132', '132', 'sm炸鸡');
INSERT INTO `good_more` VALUES ('433', '40', '111', '1332', '1332', '小炸鸡');
INSERT INTO `good_more` VALUES ('434', '39', '1', '20', '20', '大炸鸡');
INSERT INTO `good_more` VALUES ('435', '47', '1', '11', '11', '11');
INSERT INTO `good_more` VALUES ('436', '40', '1', '12', '12', '小炸鸡');

-- ----------------------------
-- Table structure for kinds
-- ----------------------------
DROP TABLE IF EXISTS `kinds`;
CREATE TABLE `kinds` (
  `kind_no` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_no` bigint(20) DEFAULT NULL,
  `kind_name` varchar(20) NOT NULL,
  `kind_sum` bigint(20) NOT NULL,
  `isdelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`kind_no`),
  KEY `FK_have` (`shop_no`),
  CONSTRAINT `FK_have` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kinds
-- ----------------------------
INSERT INTO `kinds` VALUES ('25', '62', '炸鸡', '2', '0');
INSERT INTO `kinds` VALUES ('26', '63', '炸鸡', '2', '0');
INSERT INTO `kinds` VALUES ('27', '64', '炸鸡', '0', '1');
INSERT INTO `kinds` VALUES ('28', '65', '炸鸡', '0', '0');
INSERT INTO `kinds` VALUES ('29', '65', '汉堡', '1', '0');
INSERT INTO `kinds` VALUES ('30', '62', '汉堡', '0', '1');
INSERT INTO `kinds` VALUES ('31', '62', '汉堡', '0', '1');
INSERT INTO `kinds` VALUES ('32', '62', '汉堡', '0', '1');
INSERT INTO `kinds` VALUES ('33', '62', '汉堡', '1', '0');
INSERT INTO `kinds` VALUES ('34', '62', '饮料', '2', '0');
INSERT INTO `kinds` VALUES ('35', '62', '肌肉汉堡', '0', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=443 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('416', '18', '13', '32', '62', null, '16', '4', '2020-07-12 21:03:27', '2020-07-12 21:52:46', '5', '20');
INSERT INTO `orders` VALUES ('419', '18', null, '31', '65', null, '121', '120', '2020-07-12 21:05:15', '2020-07-12 21:35:08', '0', '20');
INSERT INTO `orders` VALUES ('421', '18', '11', '32', '62', null, '16', '4', '2020-07-12 21:14:00', '2020-07-12 21:52:43', '5', '20');
INSERT INTO `orders` VALUES ('423', '18', '13', null, '65', null, '11', '11', '2020-07-12 21:14:26', '2020-07-12 21:52:29', '5', '20');
INSERT INTO `orders` VALUES ('424', '18', '10', '32', '62', null, '16', '4', '2020-07-12 21:17:33', '2020-07-12 21:52:36', '5', '20');
INSERT INTO `orders` VALUES ('425', '18', '12', null, '65', null, '11', '11', '2020-07-12 21:17:49', '2020-07-12 21:52:23', '5', '20');
INSERT INTO `orders` VALUES ('426', '18', '11', null, '62', '23', '22', '11', '2020-07-12 21:18:17', '2020-07-12 21:52:16', '5', '20');
INSERT INTO `orders` VALUES ('427', '18', '10', null, '65', null, '11', '11', '2020-07-12 21:18:52', '2020-07-12 21:52:09', '5', '20');
INSERT INTO `orders` VALUES ('428', '18', '10', null, '62', '25', '176', '167', '2020-07-12 21:19:17', '2020-07-12 21:52:03', '5', '20');
INSERT INTO `orders` VALUES ('429', '18', '10', null, '63', null, '11', '11', '2020-07-12 21:19:40', '2020-07-12 21:51:56', '5', '20');
INSERT INTO `orders` VALUES ('430', '18', '10', null, '65', '27', '11', '8', '2020-07-12 21:20:26', '2020-07-12 21:51:45', '5', '20');
INSERT INTO `orders` VALUES ('432', '20', '10', '23', '63', null, '132', '101', '2020-07-12 21:27:31', '2020-07-12 22:07:20', '5', '21');
INSERT INTO `orders` VALUES ('433', '20', '10', null, '62', null, '1332', '1302', '2020-07-12 21:28:05', '2020-07-12 22:07:30', '5', '21');
INSERT INTO `orders` VALUES ('434', '20', '10', null, '62', '23', '20', '9', '2020-07-12 21:36:36', '2020-07-12 22:07:37', '5', '21');
INSERT INTO `orders` VALUES ('435', '20', '10', null, '65', null, '11', '11', '2020-07-12 21:36:56', '2020-07-12 22:07:47', '5', '21');
INSERT INTO `orders` VALUES ('436', '20', '10', null, '62', '23', '12', '1', '2020-07-12 21:39:19', '2020-07-12 22:09:33', '5', '21');

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
INSERT INTO `owner_count` VALUES ('23', '20', '11', '2', '2020-09-01 00:00:00', '62');
INSERT INTO `owner_count` VALUES ('23', '21', '11', '1', '2020-09-01 00:00:00', '62');
INSERT INTO `owner_count` VALUES ('24', '20', '11', '1', '2021-02-02 00:00:00', '62');
INSERT INTO `owner_count` VALUES ('24', '21', '11', '1', '2021-02-02 00:00:00', '62');
INSERT INTO `owner_count` VALUES ('25', '21', '9', '1', '2021-02-01 00:00:00', '62');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rider
-- ----------------------------
INSERT INTO `rider` VALUES ('10', 'ysg', '2020-07-12 20:18:24', '3', '0');
INSERT INTO `rider` VALUES ('11', 'ysg的小号', '2020-07-12 20:18:43', '1', '0');
INSERT INTO `rider` VALUES ('12', 'ysg的2号小号', '2020-07-12 20:18:50', '1', '0');
INSERT INTO `rider` VALUES ('13', '啦啦啦', '2020-07-12 20:25:59', '1', '0');

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
INSERT INTO `rider_income` VALUES ('430', '2020-07-12 21:25:21', '2', '-17.5', '10');
INSERT INTO `rider_income` VALUES ('429', '2020-07-12 21:25:12', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('428', '2020-07-12 21:25:09', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('427', '2020-07-12 21:25:17', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('426', '2020-07-12 21:22:18', '0', '2.5', '11');
INSERT INTO `rider_income` VALUES ('425', '2020-07-12 21:22:26', '0', '2.5', '12');
INSERT INTO `rider_income` VALUES ('423', '2020-07-12 21:25:14', '1', '3', '13');
INSERT INTO `rider_income` VALUES ('416', '2020-07-12 21:24:56', '1', '3', '13');
INSERT INTO `rider_income` VALUES ('421', '2020-07-12 21:25:00', '1', '3', '11');
INSERT INTO `rider_income` VALUES ('424', '2020-07-12 21:25:05', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('432', '2020-07-12 21:38:33', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('433', '2020-07-12 21:38:22', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('434', '2020-07-12 21:38:28', '2', '-17.5', '10');
INSERT INTO `rider_income` VALUES ('435', '2020-07-12 21:38:35', '1', '3', '10');
INSERT INTO `rider_income` VALUES ('436', '2020-07-12 21:39:37', '0', '2.5', '10');

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
  `isdelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES ('61', '11', '5', '0', '0', '1');
INSERT INTO `shops` VALUES ('62', 'kjj', '5', '201.25', '1610', '0');
INSERT INTO `shops` VALUES ('63', 'kfc', '5', '71.5', '143', '0');
INSERT INTO `shops` VALUES ('64', 'kjj', '5', '0', '0', '1');
INSERT INTO `shops` VALUES ('65', '必胜客', '4', '11', '55', '0');
INSERT INTO `shops` VALUES ('66', 'k记', '5', '0', '0', '1');
INSERT INTO `shops` VALUES ('67', 'kjj', '5', '0', '0', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('20', '1', '0', '1', '11111111111', '1@qq.com', 'HongKong', '2020-07-12 20:59:54', '1', '2021-08-12 20:59:31');
INSERT INTO `users` VALUES ('21', '2', '0', '1', '11111111111', '1@qq.co', 'JiaXing', '2020-07-12 21:40:06', '1', '2020-08-12 21:40:07');
INSERT INTO `users` VALUES ('22', '3', '1', '1', '11111111111', '1@qq.com', 'HongKong', '2020-07-12 19:45:17', '0', '0000-00-00 00:00:00');
INSERT INTO `users` VALUES ('23', '4', '1', '1', '11111111111', '1@qq.com', 'HongKong', '2020-07-12 19:45:32', '0', '0000-00-00 00:00:00');

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
  `isdelete` tinyint(1) NOT NULL,
  PRIMARY KEY (`youhui_no`),
  KEY `FK_to` (`shop_no`),
  CONSTRAINT `FK_to` FOREIGN KEY (`shop_no`) REFERENCES `shops` (`shop_no`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of youhui
-- ----------------------------
INSERT INTO `youhui` VALUES ('23', '62', '11', '1', '2020-04-01 00:00:00', '2020-09-01 00:00:00', '0', '0');
INSERT INTO `youhui` VALUES ('24', '62', '11', '2', '2020-01-01 00:00:00', '2021-02-02 00:00:00', '1', '0');
INSERT INTO `youhui` VALUES ('25', '62', '9', '2', '2020-01-01 00:00:00', '2021-02-01 00:00:00', '0', '0');
INSERT INTO `youhui` VALUES ('26', '63', '1', '1', '2020-01-01 00:00:00', '2031-01-01 00:00:00', '1', '1');
INSERT INTO `youhui` VALUES ('27', '65', '3', '2', '2020-01-01 00:00:00', '2022-04-02 00:00:00', '1', '0');
INSERT INTO `youhui` VALUES ('28', '63', '1', '1', '2020-01-01 00:00:00', '2020-03-01 00:00:00', '0', '0');
