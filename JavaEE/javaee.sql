/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : javaee

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-06-07 21:20:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for form_record
-- ----------------------------
DROP TABLE IF EXISTS `form_record`;
CREATE TABLE `form_record` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '表单记录',
  `userID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户登录名也是唯一ID',
  `formCode` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '表单唯一id',
  `formValue1` int(1) NOT NULL COMMENT '0为低于37.5 1为高于等于',
  `formValue2` int(1) NOT NULL COMMENT '0为无症状 1为有症状',
  `submitTime` int(32) NOT NULL COMMENT '提交时间',
  `formRecord` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '表单唯一记录',
  PRIMARY KEY (`id`,`formRecord`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of form_record
-- ----------------------------
INSERT INTO `form_record` VALUES ('1', '172001351231', 'Fom15913368001591339375749EYifc', '1', '1', '1591454239', '123123123123');

-- ----------------------------
-- Table structure for form_style
-- ----------------------------
DROP TABLE IF EXISTS `form_style`;
CREATE TABLE `form_style` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `startTime` int(23) NOT NULL COMMENT '表单提交开始时间',
  `endTime` int(23) NOT NULL COMMENT '表单提交结束时间',
  `formDate` date NOT NULL COMMENT '表单日期',
  `formCode` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '表单唯一id',
  `formNum` int(1) NOT NULL COMMENT '1为上午 2为下午',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of form_style
-- ----------------------------
INSERT INTO `form_style` VALUES ('9', '1591336800', '1591628193', '2020-06-05', 'Fom15913368001591339375749EYifc', '1');

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '成员自增id',
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `loginName` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登陆名',
  `loginPassword` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `userRole` int(1) NOT NULL DEFAULT '0' COMMENT '成员等级 0:学生 1:管理员',
  `userEmail` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', 'admin', '9587', 'root1', '1', '15695203200@163.com');
INSERT INTO `user_login` VALUES ('11', '陈娟', '17200135101', '17200135101', '0', '17200135101@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('12', '王敏', '17200135102', '17200135102', '0', '17200135102@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('13', '刘淼尹', '17200135103', '17200135103', '0', '17200135103@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('14', '徐秋月', '17200135104', '17200135104', '0', '17200135104@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('15', '李双钰', '17200135105', '17200135105', '0', '17200135105@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('16', '郭璐', '17200135106', '17200135106', '0', '17200135106@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('17', '蒋婷玥', '17200135107', '17200135107', '0', '17200135107@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('18', '王丹', '17200135108', '17200135108', '0', '17200135108@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('19', '曹淑婷', '17200135109', '17200135109', '0', '17200135109@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('20', '施瑾', '17200135110', '17200135110', '0', '17200135110@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('21', '刘娇娇', '17200135111', '17200135111', '0', '17200135111@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('22', '王慧', '17200135112', '17200135112', '0', '17200135112@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('23', '张梦露', '17200135113', '17200135113', '0', '17200135113@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('24', '杨敏', '17200135114', '17200135114', '0', '17200135114@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('25', '嵇金莲', '17200135115', '17200135115', '0', '17200135115@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('26', '邹丽萍', '17200135116', '17200135116', '0', '17200135116@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('27', '陈雨翔', '17200135117', '17200135117', '0', '17200135117@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('28', '陈京南', '17200135118', '17200135118', '0', '17200135118@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('29', '余灏', '17200135119', '17200135119', '0', '17200135119@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('30', '黄孝文', '17200135120', '17200135120', '0', '17200135120@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('31', '刘洋', '17200135121', '17200135121', '0', '17200135121@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('32', '胡冠中', '17200135122', '17200135122', '0', '17200135122@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('33', '韦海涛', '17200135123', '17200135123', '0', '17200135123@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('34', '费礼敏', '17200135124', '17200135124', '0', '17200135124@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('35', '陈乐', '17200135125', '17200135125', '0', '17200135125@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('36', '叶航舟', '17200135126', '17200135126', '0', '17200135126@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('37', '王炜', '17200135127', '17200135127', '0', '17200135127@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('38', '朱振军', '17200135128', '17200135128', '0', '17200135128@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('39', '唐啸虎', '17200135129', '17200135129', '0', '17200135129@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('40', '张鹏', '17200135130', '17200135130', '0', '17200135130@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('41', '王胜', '17200135131', '17200135131', '0', '17200135131@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('42', '刘智文', '17200135132', '17200135132', '0', '17200135132@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('43', '刘咏三', '17200135133', '17200135133', '0', '17200135133@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('44', '田亚南', '17200135134', '17200135134', '0', '17200135134@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('45', '胡忠尉', '17200135135', '17200135135', '0', '17200135135@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('46', '陈爽', '17200135201', '17200135201', '0', '17200135201@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('47', '张婧怡', '17200135202', '17200135202', '0', '17200135202@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('48', '刘宛松', '17200135203', '17200135203', '0', '17200135203@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('49', '卢诗敏', '17200135204', '17200135204', '0', '17200135204@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('50', '张译文', '17200135205', '17200135205', '0', '17200135205@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('51', '刘新', '17200135206', '17200135206', '0', '17200135206@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('52', '张爱惠', '17200135207', '17200135207', '0', '17200135207@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('53', '梁霜琴', '17200135208', '17200135208', '0', '17200135208@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('54', '杨茜茜', '17200135209', '17200135209', '0', '17200135209@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('55', '蒋雨璐', '17200135210', '17200135210', '0', '17200135210@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('56', '鲁青青', '17200135211', '17200135211', '0', '17200135211@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('57', '孙荣歌', '17200135212', '17200135212', '0', '17200135212@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('58', '秦怡娇', '17200135213', '17200135213', '0', '17200135213@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('59', '宋晴', '17200135214', '17200135214', '0', '17200135214@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('60', '周颖', '17200135215', '17200135215', '0', '17200135215@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('61', '李苗', '17200135216', '17200135216', '0', '17200135216@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('62', '朱鑫', '17200135217', '17200135217', '0', '17200135217@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('63', '曹嘉翼', '17200135218', '17200135218', '0', '17200135218@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('64', '王昆', '17200135219', '17200135219', '0', '17200135219@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('65', '苏文轩', '17200135220', '17200135220', '0', '17200135220@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('66', '魏超', '17200135221', '17200135221', '0', '17200135221@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('67', '潘强', '17200135222', '17200135222', '0', '17200135222@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('68', '王磊', '17200135223', '17200135223', '0', '17200135223@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('69', '李家旺', '17200135224', '17200135224', '0', '17200135224@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('70', '石建国', '17200135225', '17200135225', '0', '17200135225@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('71', '冯杰', '17200135226', '17200135226', '0', '17200135226@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('72', '张雨星', '17200135227', '17200135227', '0', '17200135227@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('73', '陈皓', '17200135228', '17200135228', '0', '17200135228@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('74', '沈明佳', '17200135229', '17200135229', '0', '17200135229@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('75', '马辰熙', '17200135230', '17200135230', '0', '17200135230@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('76', '喜耀先', '17200135231', '17200135231', '0', '17200135231@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('77', '丁翔桐', '17200135232', '17200135232', '0', '17200135232@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('78', '周文豪', '17200135233', '17200135233', '0', '17200135233@post.usts.edu.cn');
INSERT INTO `user_login` VALUES ('79', '王文轩', '17200135234', '17200135234', '0', '17200135234@post.usts.edu.cn');
