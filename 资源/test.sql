/*
Navicat MySQL Data Transfer

Source Server         : 阿里云mysql
Source Server Version : 50647
Source Host           : 47.98.183.132:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50647
File Encoding         : 65001

Date: 2020-05-07 13:30:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `TradeID` int(11) NOT NULL AUTO_INCREMENT COMMENT '½»Ò×Ô±ID',
  `REL` int(11) DEFAULT NULL COMMENT 'REL¹ÉÆ±³ÖÓÐÊý',
  `ITC` int(11) DEFAULT NULL COMMENT 'ITC¹ÉÆ±³ÖÓÐÊý',
  `INF` int(11) DEFAULT NULL COMMENT 'INF¹ÉÆ±³ÖÓÐÊý',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '½»Ò×Ô±ÐÕÃû',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TradeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('7', '64', '0', '0', '张三', 'zansan', '123456');
INSERT INTO `position` VALUES ('8', '0', '0', '0', '张三', null, null);
INSERT INTO `position` VALUES ('9', '0', '0', '0', '张三', null, null);
INSERT INTO `position` VALUES ('10', '0', '0', '0', '张三', null, null);
INSERT INTO `position` VALUES ('11', '0', '0', '0', '张三', null, null);
INSERT INTO `position` VALUES ('12', '0', '0', '0', '张三', null, null);
INSERT INTO `position` VALUES ('17', '0', '0', '0', '张三', 'zansan', '123456');
INSERT INTO `position` VALUES ('18', '0', '0', '0', '张三', 'zansan', '123456');
INSERT INTO `position` VALUES ('19', '0', '0', '0', '张三', 'zansan', '123456');
INSERT INTO `position` VALUES ('20', '0', '0', '0', '张三', 'zansan', '123456');

-- ----------------------------
-- Table structure for transrecord
-- ----------------------------
DROP TABLE IF EXISTS `transrecord`;
CREATE TABLE `transrecord` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ö÷¼üid',
  `TradeID` int(11) DEFAULT NULL COMMENT '½»Ò×Ô±ID',
  `Version` int(11) DEFAULT NULL COMMENT '°æ±¾ºÅ£¬¹ØÁªµ½Ã¿¸ö½»Ò×Ô±£¬´Ó1¿ªÊ¼¼ÆÊý',
  `SecurityCode` varchar(50) DEFAULT NULL COMMENT 'Ö¤È¯´úÂë£¬ÀýÈç£ºINF´ú±í Infosys',
  `Quantity` int(11) DEFAULT NULL COMMENT 'ÊýÁ¿£¬¹ÉÆ±ÊýÁ¿£¬ÀýÈç£º10ÊÖInfosys¹ÉÆ±',
  `operation` varchar(50) DEFAULT NULL COMMENT '²Ù×÷Óë½»Ò×£¨Trade£©¹ØÁª£¬ (»á²úÉúÏà¹ØµÄtrade id£¬µ«ÊÇ²»Í¬°æ±¾ºÅµÄ¼ÇÂ¼)',
  `deal` varchar(50) DEFAULT NULL COMMENT 'Buy,SellÂò»òÂô',
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of transrecord
-- ----------------------------
INSERT INTO `transrecord` VALUES ('1', '6', '1', 'REL', '50', 'INSERT', 'Sell');
INSERT INTO `transrecord` VALUES ('2', '6', '2', 'REL', '50', 'UPDATE', 'Buy');
INSERT INTO `transrecord` VALUES ('3', '6', '3', 'REL', '50', 'CANCEL', 'Sell');
INSERT INTO `transrecord` VALUES ('4', '6', '4', 'REL', '50', 'UPDATE', 'Buy');
INSERT INTO `transrecord` VALUES ('5', '7', '1', 'REL', '7', 'INSERT', 'Buy');
INSERT INTO `transrecord` VALUES ('6', '7', '2', 'REL', '7', 'CANCEL', 'Buy');
INSERT INTO `transrecord` VALUES ('7', '7', '3', 'REL', '7', 'CANCEL', 'Buy');
INSERT INTO `transrecord` VALUES ('8', '7', '4', 'REL', '7', 'UPDATE', 'Buy');
INSERT INTO `transrecord` VALUES ('9', '7', '5', 'REL', '50', 'UPDATE', 'Buy');
INSERT INTO `transrecord` VALUES ('10', '7', '6', 'REL', '50', 'CANCEL', 'Sell');
