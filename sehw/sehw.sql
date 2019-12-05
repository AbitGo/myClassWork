/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : sehw

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-12-05 12:36:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movies
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `Mid` int(8) NOT NULL,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`,`Mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of movies
-- ----------------------------

-- ----------------------------
-- Table structure for ratings_train
-- ----------------------------
DROP TABLE IF EXISTS `ratings_train`;
CREATE TABLE `ratings_train` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `mId` int(10) NOT NULL,
  `grade` int(2) DEFAULT NULL,
  `timeS` int(15) NOT NULL,
  PRIMARY KEY (`id`,`mId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ratings_train
-- ----------------------------
