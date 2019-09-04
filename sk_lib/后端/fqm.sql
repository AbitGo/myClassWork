-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: exam_online
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `eo_department`
--

DROP TABLE IF EXISTS `eo_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系部ID',
  `name` varchar(255) DEFAULT NULL COMMENT '系部名',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `is_adult` int(11) DEFAULT NULL,
  `approve_user_id` int(11) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `fk_school_dp` (`school_id`) USING BTREE,
  CONSTRAINT `fk_eo_department_eo_school_1` FOREIGN KEY (`school_id`) REFERENCES `eo_school` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系部表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_department`
--

LOCK TABLES `eo_department` WRITE;
/*!40000 ALTER TABLE `eo_department` DISABLE KEYS */;
INSERT INTO `eo_department` VALUES (14,'Training',8,1,NULL,0);
/*!40000 ALTER TABLE `eo_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exam`
--

DROP TABLE IF EXISTS `eo_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '考试名',
  `paper_id` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL COMMENT '持续时间',
  `class_id` int(11) DEFAULT NULL COMMENT '考试班级id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '归属老师id',
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `pass_point` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `is_handle` int(11) unsigned zerofill DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `fk_eo_exam_sys_user_1` (`teacher_id`),
  KEY `fk_eo_exam_eo_exam_paper_1` (`paper_id`),
  KEY `fk_eo_exam_eo_school_class_1` (`class_id`),
  CONSTRAINT `fk_eo_exam_eo_exam_paper_1` FOREIGN KEY (`paper_id`) REFERENCES `eo_exam_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_exam_eo_school_class_1` FOREIGN KEY (`class_id`) REFERENCES `eo_school_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_exam_sys_user_1` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='考试表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exam`
--

LOCK TABLES `eo_exam` WRITE;
/*!40000 ALTER TABLE `eo_exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exam_class`
--

DROP TABLE IF EXISTS `eo_exam_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exam_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试id',
  `created_time` datetime DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unqiue_exam_class` (`class_id`,`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='考试班级对照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exam_class`
--

LOCK TABLES `eo_exam_class` WRITE;
/*!40000 ALTER TABLE `eo_exam_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_exam_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exam_get_info`
--

DROP TABLE IF EXISTS `eo_exam_get_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exam_get_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) DEFAULT NULL COMMENT '试卷id',
  `get_exam_time` datetime DEFAULT NULL COMMENT '获得考试时间',
  `student_id` int(11) DEFAULT NULL COMMENT '学生ID',
  `is_pause` int(11) unsigned zerofill DEFAULT NULL COMMENT '是否暂停',
  `pause_time` int(11) DEFAULT NULL COMMENT '暂停剩余时间（以秒记）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exam_get_info`
--

LOCK TABLES `eo_exam_get_info` WRITE;
/*!40000 ALTER TABLE `eo_exam_get_info` DISABLE KEYS */;
INSERT INTO `eo_exam_get_info` VALUES (9,2,'2018-03-21 20:36:53',32,NULL,NULL),(36,4,'2018-03-24 20:20:43',32,NULL,NULL),(37,5,'2018-03-24 20:40:34',32,NULL,NULL),(41,7,'2018-04-03 15:10:35',32,NULL,NULL),(42,8,'2018-04-03 15:48:01',32,NULL,NULL),(44,9,'2018-04-05 09:51:58',32,NULL,NULL),(45,8,'2018-04-05 09:53:24',35,NULL,NULL),(46,8,'2018-04-05 09:56:28',36,NULL,NULL),(47,8,'2018-04-05 09:57:47',37,NULL,NULL),(48,8,'2018-04-05 09:58:34',38,NULL,NULL),(52,10,'2018-04-05 19:21:47',32,NULL,NULL),(53,10,'2018-04-05 19:33:55',35,NULL,NULL),(54,10,'2018-04-05 19:35:16',36,NULL,NULL),(55,10,'2018-04-05 19:36:06',37,NULL,NULL),(57,11,'2018-04-05 19:43:04',35,NULL,NULL),(58,11,'2018-04-05 19:44:02',36,NULL,NULL),(60,11,'2018-04-05 22:44:10',38,NULL,NULL),(69,11,'2018-04-22 13:32:55',32,NULL,NULL),(104,11,'2018-04-23 20:05:47',34,NULL,NULL),(105,11,'2018-04-23 20:15:32',50,NULL,NULL),(106,11,'2018-04-23 20:17:20',50,NULL,NULL),(107,11,'2018-04-23 20:20:29',50,NULL,NULL),(108,11,'2018-04-23 20:23:08',50,NULL,NULL),(109,11,'2018-04-23 20:24:07',50,NULL,NULL),(110,11,'2018-04-23 20:25:28',50,NULL,NULL),(111,11,'2018-04-23 20:33:39',50,NULL,NULL),(112,11,'2018-04-23 20:35:26',50,NULL,NULL),(113,11,'2018-04-23 20:40:59',50,NULL,NULL),(114,11,'2018-04-23 20:42:38',50,NULL,NULL),(115,11,'2018-04-23 20:48:14',50,NULL,NULL),(116,11,'2018-04-23 20:52:01',50,NULL,NULL),(117,11,'2018-04-23 20:54:02',50,NULL,NULL),(118,11,'2018-04-23 20:54:39',50,NULL,NULL),(119,11,'2018-04-23 20:57:03',50,NULL,NULL),(120,11,'2018-04-23 20:58:29',50,NULL,NULL),(121,11,'2018-04-24 20:17:41',51,NULL,NULL),(122,11,'2018-04-29 09:11:55',52,NULL,NULL),(123,11,'2018-05-01 13:21:24',53,NULL,NULL),(124,16,'2018-05-18 09:07:44',41,NULL,NULL),(125,11,'2018-05-18 10:29:38',42,NULL,NULL),(126,11,'2018-05-18 16:00:36',37,NULL,NULL),(127,22,'2018-05-21 14:55:53',36,NULL,NULL),(128,23,'2018-05-21 14:56:52',36,NULL,NULL),(129,25,'2018-05-21 14:58:28',36,NULL,NULL),(130,24,'2018-05-21 15:05:33',43,NULL,NULL),(131,24,'2018-05-21 15:07:39',36,NULL,NULL),(132,16,'2018-05-21 15:13:28',36,NULL,NULL),(133,30,'2018-05-21 15:24:33',36,NULL,NULL),(134,31,'2018-05-21 15:35:38',36,NULL,NULL),(135,32,'2018-05-21 15:40:45',36,NULL,NULL),(136,24,'2018-05-21 22:25:32',32,NULL,NULL),(137,25,'2018-05-22 01:01:33',32,NULL,NULL),(138,33,'2018-05-22 09:54:32',32,NULL,NULL),(139,37,'2018-06-06 20:13:23',32,NULL,NULL),(140,38,'2018-06-06 20:16:38',32,NULL,NULL),(141,37,'2018-06-06 20:27:04',29,NULL,NULL),(142,36,'2018-06-07 02:11:20',32,NULL,NULL),(143,38,'2018-06-07 02:15:07',35,NULL,NULL),(144,38,'2018-06-07 02:37:51',34,NULL,NULL),(145,38,'2018-06-07 02:38:21',29,NULL,NULL),(146,36,'2018-06-07 02:38:46',29,NULL,NULL),(147,37,'2018-06-07 02:39:36',43,NULL,NULL),(148,41,'2018-06-07 03:02:14',32,NULL,NULL),(149,42,'2018-06-07 17:09:02',32,NULL,NULL),(150,43,'2018-06-08 00:54:47',29,NULL,NULL),(151,43,'2018-06-08 10:20:07',32,NULL,NULL),(152,42,'2018-06-08 15:01:33',29,NULL,NULL),(153,43,'2018-06-08 15:20:24',34,NULL,NULL),(154,42,'2018-06-08 16:23:47',35,NULL,NULL),(155,44,'2018-06-12 16:42:29',505,NULL,NULL),(156,46,'2018-06-12 16:54:57',505,NULL,NULL),(157,47,'2018-06-12 17:01:41',505,NULL,NULL),(158,48,'2018-06-12 17:25:46',505,NULL,NULL),(159,48,'2018-06-12 17:28:04',506,NULL,NULL),(160,48,'2018-06-12 17:35:06',507,NULL,NULL),(161,48,'2018-06-12 17:40:16',508,NULL,NULL),(162,48,'2018-06-12 17:47:02',509,NULL,NULL),(163,48,'2018-06-12 17:47:54',510,NULL,NULL),(164,48,'2018-06-12 17:49:22',511,NULL,NULL),(165,49,'2018-06-12 18:40:19',648,NULL,NULL),(166,50,'2018-06-14 11:52:19',721,NULL,NULL);
/*!40000 ALTER TABLE `eo_exam_get_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exam_paper`
--

DROP TABLE IF EXISTS `eo_exam_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exam_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '试卷名字',
  `content` mediumtext COMMENT '试卷内容',
  `question_info` varchar(255) DEFAULT NULL COMMENT '试卷考题相关信息(每题分值)',
  `total_point` int(255) DEFAULT NULL COMMENT '总分',
  `duration` int(255) DEFAULT NULL COMMENT '考试时间',
  `pass_point` int(11) DEFAULT NULL COMMENT '及格成绩',
  `teacher_id` int(11) DEFAULT NULL COMMENT '归属教师id',
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `fk_eo_exam_paper_sys_user_1` (`teacher_id`),
  CONSTRAINT `fk_eo_exam_paper_sys_user_1` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='试卷表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exam_paper`
--

LOCK TABLES `eo_exam_paper` WRITE;
/*!40000 ALTER TABLE `eo_exam_paper` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_exam_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exam_question_2_student`
--

DROP TABLE IF EXISTS `eo_exam_question_2_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exam_question_2_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) NOT NULL COMMENT '考试id',
  `question_id` int(11) NOT NULL COMMENT '问题id',
  `is_correct` int(11) DEFAULT NULL COMMENT '是否正确',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_id` (`question_id`),
  KEY `fk_student_id` (`student_id`),
  KEY `fk_exam_id` (`exam_id`),
  CONSTRAINT `fk_exam_id` FOREIGN KEY (`exam_id`) REFERENCES `eo_exam` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_question_id` FOREIGN KEY (`question_id`) REFERENCES `eo_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1089 DEFAULT CHARSET=utf8 COMMENT='一次考试学生题目答案正确表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exam_question_2_student`
--

LOCK TABLES `eo_exam_question_2_student` WRITE;
/*!40000 ALTER TABLE `eo_exam_question_2_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_exam_question_2_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exercise`
--

DROP TABLE IF EXISTS `eo_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exercise` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '练习表id',
  `name` varchar(255) DEFAULT NULL COMMENT '练习名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exercise`
--

LOCK TABLES `eo_exercise` WRITE;
/*!40000 ALTER TABLE `eo_exercise` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_exercise_2_question`
--

DROP TABLE IF EXISTS `eo_exercise_2_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_exercise_2_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exercise_id` int(11) DEFAULT NULL COMMENT '练习id',
  `question_id` int(11) DEFAULT NULL COMMENT '问题id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `exercise_id` (`exercise_id`,`question_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `eo_exercise_2_question_ibfk_1` FOREIGN KEY (`exercise_id`) REFERENCES `eo_exercise` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eo_exercise_2_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `eo_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_exercise_2_question`
--

LOCK TABLES `eo_exercise_2_question` WRITE;
/*!40000 ALTER TABLE `eo_exercise_2_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_exercise_2_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_file`
--

DROP TABLE IF EXISTS `eo_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件url',
  `created_time` datetime DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL COMMENT '题目id',
  PRIMARY KEY (`id`),
  KEY `fk_eo_file_eo_question_1` (`question_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_file`
--

LOCK TABLES `eo_file` WRITE;
/*!40000 ALTER TABLE `eo_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_knowleage_point`
--

DROP TABLE IF EXISTS `eo_knowleage_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_knowleage_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识点id',
  `name` varchar(255) DEFAULT NULL COMMENT '知识点名',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `subect_id` int(11) DEFAULT NULL COMMENT '学科id',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `fk_eo_knowleage_point_sys_user_1` (`teacher_id`),
  KEY `fk_eo_knowleage_point_eo_subject_1` (`subect_id`),
  CONSTRAINT `fk_eo_knowleage_point_eo_subject_1` FOREIGN KEY (`subect_id`) REFERENCES `eo_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_knowleage_point_sys_user_1` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识点表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_knowleage_point`
--

LOCK TABLES `eo_knowleage_point` WRITE;
/*!40000 ALTER TABLE `eo_knowleage_point` DISABLE KEYS */;
INSERT INTO `eo_knowleage_point` VALUES (66,'1绪论',1027,32,1),(67,'2硬件',1027,32,0),(68,'3软件',1027,32,0);
/*!40000 ALTER TABLE `eo_knowleage_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_major`
--

DROP TABLE IF EXISTS `eo_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `is_adult` int(11) DEFAULT NULL COMMENT '是否被审核',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `department_id` int(11) DEFAULT NULL COMMENT '院系id',
  `approve_user_id` int(11) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `fk_major_school` (`school_id`),
  KEY `fk_major_department` (`department_id`),
  CONSTRAINT `fk_major_department` FOREIGN KEY (`department_id`) REFERENCES `eo_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_major_school` FOREIGN KEY (`school_id`) REFERENCES `eo_school` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='学校专业表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_major`
--

LOCK TABLES `eo_major` WRITE;
/*!40000 ALTER TABLE `eo_major` DISABLE KEYS */;
INSERT INTO `eo_major` VALUES (24,'ZZB',1,8,14,NULL,0);
/*!40000 ALTER TABLE `eo_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_major_2_subject`
--

DROP TABLE IF EXISTS `eo_major_2_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_major_2_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `major_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `major_id` (`major_id`,`subject_id`),
  KEY `subject_id` (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_major_2_subject`
--

LOCK TABLES `eo_major_2_subject` WRITE;
/*!40000 ALTER TABLE `eo_major_2_subject` DISABLE KEYS */;
INSERT INTO `eo_major_2_subject` VALUES (62,13,1),(63,13,2),(66,1,1),(65,13,8),(67,1,2),(68,1,6),(69,19,28),(70,20,29),(71,20,30),(72,23,31),(73,24,32),(74,24,33);
/*!40000 ALTER TABLE `eo_major_2_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_message`
--

DROP TABLE IF EXISTS `eo_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `from_user_id` int(11) DEFAULT NULL COMMENT '发送者',
  `to_user_id` int(11) DEFAULT NULL COMMENT '接收者',
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL COMMENT '是否已读',
  PRIMARY KEY (`id`),
  KEY `fk_eo_message_sys_user_1` (`from_user_id`),
  KEY `fk_eo_message_sys_user_2` (`to_user_id`),
  CONSTRAINT `fk_eo_message_sys_user_1` FOREIGN KEY (`from_user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_message_sys_user_2` FOREIGN KEY (`to_user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='消息推送表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_message`
--

LOCK TABLES `eo_message` WRITE;
/*!40000 ALTER TABLE `eo_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_paper_2_question`
--

DROP TABLE IF EXISTS `eo_paper_2_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_paper_2_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `paper_id` int(11) DEFAULT NULL COMMENT '试卷id',
  `question_id` int(11) DEFAULT NULL COMMENT '题目id',
  `question_type` varchar(255) DEFAULT NULL COMMENT '题目类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `paper_id` (`paper_id`,`question_id`),
  KEY `fk_paper_question_id` (`paper_id`),
  KEY `fk_question_paper_id` (`question_id`),
  CONSTRAINT `eo_paper_2_question_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `eo_exam_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `eo_paper_2_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `eo_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_paper_2_question`
--

LOCK TABLES `eo_paper_2_question` WRITE;
/*!40000 ALTER TABLE `eo_paper_2_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_paper_2_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_question`
--

DROP TABLE IF EXISTS `eo_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '习题id',
  `name` varchar(255) DEFAULT NULL,
  `title` longtext COMMENT '题目',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `answoer` mediumtext COMMENT '答案',
  `select_option` mediumtext COMMENT '选项',
  `is_audit` int(11) DEFAULT '1' COMMENT '审核状态,0 未审核，1审核',
  `is_public` int(11) DEFAULT '0' COMMENT '是否公开，0不公开，1公开',
  `knowleage_point_id` int(11) DEFAULT NULL COMMENT '知识点',
  `degree` int(11) DEFAULT NULL COMMENT '难度',
  `school_id` int(11) DEFAULT NULL COMMENT '学校Id',
  `type_id` int(11) DEFAULT NULL COMMENT '题目类型id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `subject_id` int(11) DEFAULT NULL COMMENT '科目id0。',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除标志',
  `approve_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_eo_question_eo_question_type_1` (`type_id`),
  CONSTRAINT `fk_eo_question_eo_question_type_1` FOREIGN KEY (`type_id`) REFERENCES `eo_question_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=793 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='习题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_question`
--

LOCK TABLES `eo_question` WRITE;
/*!40000 ALTER TABLE `eo_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_question_type`
--

DROP TABLE IF EXISTS `eo_question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_question_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '题型id',
  `name` varchar(255) DEFAULT NULL COMMENT '题型名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='题目类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_question_type`
--

LOCK TABLES `eo_question_type` WRITE;
/*!40000 ALTER TABLE `eo_question_type` DISABLE KEYS */;
INSERT INTO `eo_question_type` VALUES (1,'选择题'),(2,'多选题'),(3,'判断题'),(4,'填空题'),(5,'特殊题');
/*!40000 ALTER TABLE `eo_question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_schedule_job`
--

DROP TABLE IF EXISTS `eo_schedule_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_schedule_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `job_class` varchar(255) DEFAULT NULL COMMENT '任务类',
  `job_method` varchar(255) DEFAULT NULL COMMENT '任务方法',
  `status` varchar(255) DEFAULT NULL COMMENT '任务状态 0禁用 1启用 2删除',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT '任务运行时间表达式',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `plan_start_date` datetime DEFAULT NULL COMMENT '计划开始时间',
  `plan_end_date` datetime DEFAULT NULL COMMENT '计划结束时间',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='计划任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_schedule_job`
--

LOCK TABLES `eo_schedule_job` WRITE;
/*!40000 ALTER TABLE `eo_schedule_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_schedule_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_school`
--

DROP TABLE IF EXISTS `eo_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_school` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校表ID',
  `name` varchar(255) DEFAULT NULL COMMENT '学校名',
  `teacher_num` int(11) DEFAULT NULL COMMENT '教师人数',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='学校表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_school`
--

LOCK TABLES `eo_school` WRITE;
/*!40000 ALTER TABLE `eo_school` DISABLE KEYS */;
INSERT INTO `eo_school` VALUES (8,'DWZZB',50,0);
/*!40000 ALTER TABLE `eo_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_school_class`
--

DROP TABLE IF EXISTS `eo_school_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_school_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '班级名',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `department_id` int(11) DEFAULT NULL COMMENT '系部id',
  `major_id` int(11) DEFAULT NULL COMMENT '专业id',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `fk_eo_school_class_eo_school_1` (`school_id`),
  KEY `fk_eo_school_class_eo_department_1` (`department_id`),
  KEY `fk_eo_school_class_eo_major_1` (`major_id`),
  CONSTRAINT `fk_eo_school_class_eo_department_1` FOREIGN KEY (`department_id`) REFERENCES `eo_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_school_class_eo_major_1` FOREIGN KEY (`major_id`) REFERENCES `eo_major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_school_class_eo_school_1` FOREIGN KEY (`school_id`) REFERENCES `eo_school` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_school_class`
--

LOCK TABLES `eo_school_class` WRITE;
/*!40000 ALTER TABLE `eo_school_class` DISABLE KEYS */;
INSERT INTO `eo_school_class` VALUES (17,'16class',8,14,24,0);
/*!40000 ALTER TABLE `eo_school_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_score`
--

DROP TABLE IF EXISTS `eo_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩id',
  `student_id` int(11) DEFAULT NULL COMMENT '学术id',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试id',
  `score` varchar(255) DEFAULT NULL COMMENT '成绩',
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  KEY `fk_eo_score_sys_user_1` (`student_id`),
  KEY `fk_eo_score_eo_exam_1` (`exam_id`),
  CONSTRAINT `fk_eo_score_eo_exam_1` FOREIGN KEY (`exam_id`) REFERENCES `eo_exam` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_score_sys_user_1` FOREIGN KEY (`student_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='成绩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_score`
--

LOCK TABLES `eo_score` WRITE;
/*!40000 ALTER TABLE `eo_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_subject`
--

DROP TABLE IF EXISTS `eo_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科目id',
  `name` varchar(255) DEFAULT NULL COMMENT '科目名',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `delete_flag` int(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_id_schoiol_depart` (`name`,`school_id`,`department_id`),
  KEY `foregin_school_id` (`school_id`),
  KEY `foregin_department_id` (`department_id`),
  CONSTRAINT `foregin_department_id` FOREIGN KEY (`department_id`) REFERENCES `eo_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foregin_school_id` FOREIGN KEY (`school_id`) REFERENCES `eo_school` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='科目表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_subject`
--

LOCK TABLES `eo_subject` WRITE;
/*!40000 ALTER TABLE `eo_subject` DISABLE KEYS */;
INSERT INTO `eo_subject` VALUES (32,'计算机',8,14,0),(33,'英语',8,14,0);
/*!40000 ALTER TABLE `eo_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_teacher_2_major`
--

DROP TABLE IF EXISTS `eo_teacher_2_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_teacher_2_major` (
  `id` int(11) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_id` (`teacher_id`,`major_id`),
  KEY `fk_eo_teacher_2_major_sys_user_1` (`teacher_id`),
  KEY `fk_eo_teacher_2_major_eo_major_1` (`major_id`),
  CONSTRAINT `fk_eo_teacher_2_major_eo_major_1` FOREIGN KEY (`major_id`) REFERENCES `eo_major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_teacher_2_major_sys_user_1` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='教师专业对照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_teacher_2_major`
--

LOCK TABLES `eo_teacher_2_major` WRITE;
/*!40000 ALTER TABLE `eo_teacher_2_major` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_teacher_2_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_teacher_2_subject`
--

DROP TABLE IF EXISTS `eo_teacher_2_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_teacher_2_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师科目对应表id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `subject_id` int(11) DEFAULT NULL COMMENT '科目id',
  `class_id` int(11) DEFAULT NULL COMMENT '学校班级id',
  PRIMARY KEY (`id`),
  KEY `fk_eo_teacher_2_subject_sys_user_1` (`teacher_id`),
  KEY `fk_eo_teacher_2_subject_eo_subject_1` (`subject_id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `eo_teacher_2_subject_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `eo_school_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_teacher_2_subject_eo_subject_1` FOREIGN KEY (`subject_id`) REFERENCES `eo_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_teacher_2_subject_sys_user_1` FOREIGN KEY (`teacher_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='教师_科目_对应表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_teacher_2_subject`
--

LOCK TABLES `eo_teacher_2_subject` WRITE;
/*!40000 ALTER TABLE `eo_teacher_2_subject` DISABLE KEYS */;
INSERT INTO `eo_teacher_2_subject` VALUES (34,1027,32,NULL),(35,1099,33,NULL);
/*!40000 ALTER TABLE `eo_teacher_2_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_user_exam_history`
--

DROP TABLE IF EXISTS `eo_user_exam_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_user_exam_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '考试内容',
  `exam_paper_id` int(11) DEFAULT NULL COMMENT '试卷id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `submit_time` datetime DEFAULT NULL COMMENT '提交时间',
  `get_point` int(11) DEFAULT NULL COMMENT '获得分数',
  `answer_sheet` mediumtext COMMENT '回答内容',
  `duration` varchar(255) DEFAULT NULL COMMENT '考试时间',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试id',
  `score_ratio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`exam_id`,`exam_paper_id`),
  KEY `fk_eo_user_exam_history_eo_exam_paper_1` (`exam_paper_id`),
  KEY `fk_eo_user_exam_history_sys_user_1` (`user_id`),
  KEY `fk_eo_user_exam_history_eo_exam_1` (`exam_id`),
  CONSTRAINT `fk_eo_user_exam_history_eo_exam_1` FOREIGN KEY (`exam_id`) REFERENCES `eo_exam` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_user_exam_history_eo_exam_paper_1` FOREIGN KEY (`exam_paper_id`) REFERENCES `eo_exam_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_user_exam_history_sys_user_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='学生考试信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_user_exam_history`
--

LOCK TABLES `eo_user_exam_history` WRITE;
/*!40000 ALTER TABLE `eo_user_exam_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_user_exam_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eo_wrong_question_2_student`
--

DROP TABLE IF EXISTS `eo_wrong_question_2_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eo_wrong_question_2_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生id',
  `question_id` int(11) DEFAULT NULL COMMENT '题目id',
  `created_time` datetime DEFAULT NULL,
  `error_num` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`,`question_id`),
  KEY `fk_eo_wrong_question_2_student_sys_user_1` (`student_id`),
  KEY `fk_eo_wrong_question_2_student_eo_question_1` (`question_id`),
  CONSTRAINT `fk_eo_wrong_question_2_student_eo_question_1` FOREIGN KEY (`question_id`) REFERENCES `eo_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_eo_wrong_question_2_student_sys_user_1` FOREIGN KEY (`student_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=409 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='错题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eo_wrong_question_2_student`
--

LOCK TABLES `eo_wrong_question_2_student` WRITE;
/*!40000 ALTER TABLE `eo_wrong_question_2_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `eo_wrong_question_2_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `type` varchar(255) DEFAULT NULL COMMENT '角色种类',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'root','超级管理员'),(2,'admin','学校管理员'),(3,'teacher','教师'),(4,'student','学生');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `role_id` int(11) DEFAULT NULL COMMENT '所属角色',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `department_id` int(11) DEFAULT NULL COMMENT '系部id',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `wrong_question_num` int(11) DEFAULT NULL COMMENT '学生错题容量',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新事件',
  `delete_flag` int(11) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  KEY `fk_sys_user_sys_user_1` (`role_id`),
  KEY `fk_sys_user_eo_department_1` (`department_id`),
  KEY `fk_sys_user_eo_school_1` (`school_id`),
  KEY `fk_sys_user_eo_school_class_1` (`class_id`),
  CONSTRAINT `fk_sys_user_eo_department_1` FOREIGN KEY (`department_id`) REFERENCES `eo_department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_user_eo_school_1` FOREIGN KEY (`school_id`) REFERENCES `eo_school` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_user_eo_school_class_1` FOREIGN KEY (`class_id`) REFERENCES `eo_school_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sys_user_sys_user_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1100 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','$2a$10$KS4XusHySCOKk2OvbBGopuGF3Ixn/qWSPXd/mHkHN/VqCTYKLiDRK',1,NULL,NULL,NULL,'超管','男',NULL,'2017-12-06 22:45:37','2017-12-29 22:45:42',0),(742,'HJ','$2a$10$VsLnuQpH4pxgfkU17qZJwOF2etPvVwGhkZEXZnBDvYsvhZILasi0u',2,8,NULL,NULL,'HJ','男',NULL,'2018-06-14 13:19:01','2018-06-14 13:19:01',0),(1027,'fqm','$2a$10$kSduFGVzPgg3qqE0oF0RhOhf9yCI3AXua18nz2lJxoDWzgsCRcWg2',3,8,14,NULL,'fqm',NULL,NULL,'2018-06-14 15:01:14','2018-06-14 15:01:14',0),(1028,'DWZZB18S000001','$2a$10$sLaibqX19Z8O6HUQp.45SeFQvB1.x48FOhJ8jEkvSxCzN7JhsJNd2',4,8,14,17,'龚钰','女',NULL,'2018-06-14 15:55:40','2018-06-14 15:55:40',0),(1029,'DWZZB18S000002','$2a$10$T66TGcUi0/tlNyQXnddq2.P3O09iVf.azd0Flx4FTPhKK/Di62xpW',4,8,14,17,'李陆元','女',NULL,'2018-06-14 15:55:40','2018-06-14 15:55:40',0),(1030,'DWZZB18S000003','$2a$10$tmqywC3H//2jSmK1GkDxMeP3ND3QWn8lao8V1.FrsuTCVeK8nTkma',4,8,14,17,'龚丽','女',NULL,'2018-06-14 15:55:40','2018-06-14 15:55:40',0),(1031,'DWZZB18S000004','$2a$10$h0bvXZTgaXbDvG/6ngZaS.dXT5ZIa5B5Z6nogo/Fp2mH4bPiAJBe6',4,8,14,17,'李一笑','女',NULL,'2018-06-14 15:55:40','2018-06-14 15:55:40',0),(1032,'DWZZB18S000005','$2a$10$wPbH5zEg0/rV8EmKUK5GsuFDknrMgK5wkJDh8ypNBdwqE8/OKUqei',4,8,14,17,'王诗雨','女',NULL,'2018-06-14 15:55:40','2018-06-14 15:55:40',0),(1033,'DWZZB18S000006','$2a$10$bKNU5EvlxtitgLSWRQD7ReqYIi8SMHEcJOO/IxeG40tnXxr5zk7de',4,8,14,17,'郑雅芬','女',NULL,'2018-06-14 15:55:40','2018-06-14 15:55:40',0),(1034,'DWZZB18S000007','$2a$10$UCTYuIOHAprHQ014ZAJq0OsQ75rIc5gLBjaaPjJoCYflmpsMjOO6S',4,8,14,17,'顾天鸿','男',NULL,'2018-06-14 15:55:41','2018-06-14 15:55:41',0),(1035,'DWZZB18S000008','$2a$10$auDpzG3Rsw8kKnt/rYN19.uZ4891OFQ6MzNA1ZEHJGWwfSRMYmB6e',4,8,14,17,'时宏康','男',NULL,'2018-06-14 15:55:41','2018-06-14 15:55:41',0),(1036,'DWZZB18S000009','$2a$10$E5mhM1I8yHY.iTdojQCKJOH0UfycRxz8gFNjFip0o7bw7Ahlw2wse',4,8,14,17,'彭浩东','男',NULL,'2018-06-14 15:55:41','2018-06-14 15:55:41',0),(1037,'DWZZB18S000010','$2a$10$FbTc5EEWJIYh04IFm11HYuCsNCa2f.b1/80QUp1WWpTsJJwoVTcNa',4,8,14,17,'李聪','男',NULL,'2018-06-14 15:55:41','2018-06-14 15:55:41',0),(1038,'DWZZB18S000011','$2a$10$euhe3DMmCc7SYxlATbKb1OgoVoqFKPLGUMiWyEgqQYWeHaB92MVve',4,8,14,17,'何猛','男',NULL,'2018-06-14 15:55:41','2018-06-14 15:55:41',0),(1039,'DWZZB18S000012','$2a$10$0Y6qzG09sfYQ1hacPsR.5.4Z1vrp1Bh3IzmKQ1G.EiAE3tJGFkBam',4,8,14,17,'陈海禹','男',NULL,'2018-06-14 15:55:41','2018-06-14 15:55:41',0),(1040,'DWZZB18S000013','$2a$10$tVMSM75FWzZg/Q9PvGWLM.ikfsbe2lXNZ2k73wDdCHwSbgYMWlz.q',4,8,14,17,'张恺成','男',NULL,'2018-06-14 15:55:42','2018-06-14 15:55:42',0),(1041,'DWZZB18S000014','$2a$10$/9QDfuak7QTeQFlZShIq3OcJywA0uJhMlQg6431PjxIbTceOagJQ2',4,8,14,17,'殷艳祺','女',NULL,'2018-06-14 15:55:42','2018-06-14 15:55:42',0),(1042,'DWZZB18S000015','$2a$10$b71J5X4/llueKHzVCVt.F.kT27hUY/opKpVKpVyDBe7/iOWfR6GvC',4,8,14,17,'贾冰雁','女',NULL,'2018-06-14 15:55:42','2018-06-14 15:55:42',0),(1043,'DWZZB18S000016','$2a$10$CJgWN32C4XKXaEuQrT.8cuo6V9ZCDfzj3veJtQrCUC/da1u32JFS.',4,8,14,17,'杨心雨','女',NULL,'2018-06-14 15:55:42','2018-06-14 15:55:42',0),(1044,'DWZZB18S000017','$2a$10$K6mDJsgn/j70piKjZITuq.dqKdpnjGWiO2AxWWjAmjgzkH7ZtxDIS',4,8,14,17,'陈树','女',NULL,'2018-06-14 15:55:42','2018-06-14 15:55:42',0),(1045,'DWZZB18S000018','$2a$10$0JZk4oqNXMDrU8TUWeWlOumQlclWD4vWC2pIwtEl7mksAOcWokc/u',4,8,14,17,'胡瀚文','女',NULL,'2018-06-14 15:55:42','2018-06-14 15:55:42',0),(1046,'DWZZB18S000019','$2a$10$0umMkzZ.q3pMc9wd51NuHOgbW/1dQmMQ8o1jUxquqd5.xwfA960iC',4,8,14,17,'韦浩','男',NULL,'2018-06-14 15:55:43','2018-06-14 15:55:43',0),(1047,'DWZZB18S000020','$2a$10$H65Sm0Mx6n7uGOS562whnezx2Gc0YidCcShrFJfyqVEVxbMJyyUDe',4,8,14,17,'倪云霞','女',NULL,'2018-06-14 15:55:43','2018-06-14 15:55:43',0),(1048,'DWZZB18S000021','$2a$10$ok2RGkTwmXYSTfk9.ZXcVuCyVTuF98G/bs5vbv6fAoJ/IKRmsbbqm',4,8,14,17,'王韵','女',NULL,'2018-06-14 15:55:43','2018-06-14 15:55:43',0),(1049,'DWZZB18S000022','$2a$10$f4CT5Z0z5QPHQnH30dIMgOZ/xXyTD4lB9cXPuMkCbee3.BaYhXELS',4,8,14,17,'印潇宇','女',NULL,'2018-06-14 15:55:43','2018-06-14 15:55:43',0),(1050,'DWZZB18S000023','$2a$10$.Hq67om0hyHqxF6WggBoIeS8X2Iyud/a4F7iYkbCUr4SbGrdBT.WK',4,8,14,17,'俞嘉琪','女',NULL,'2018-06-14 15:55:43','2018-06-14 15:55:43',0),(1051,'DWZZB18S000024','$2a$10$eCAzybCYd6T00o443zgGwuIzTBxjiWQUaN0WymMPVbPREXgMDkg2C',4,8,14,17,'林映岕','男',NULL,'2018-06-14 15:55:43','2018-06-14 15:55:43',0),(1052,'DWZZB18S000025','$2a$10$263e4uga51IVGRLQiQwO8eBkMt6fkRgChAM9.tIxg.MTKepzHJ93e',4,8,14,17,'朱颖','女',NULL,'2018-06-14 15:55:44','2018-06-14 15:55:44',0),(1053,'DWZZB18S000026','$2a$10$kXRugkopa4HCgCjogFp9kOviE2BBglEq8xEXP41jTSlTnSI7r4/Xe',4,8,14,17,'宗致远','男',NULL,'2018-06-14 15:55:44','2018-06-14 15:55:44',0),(1054,'DWZZB18S000027','$2a$10$fYh6HnX82ikPld/1Inw5au5uX3VB4BDNfGDMsjjXEJuM2xK82MxNa',4,8,14,17,'徐雯','女',NULL,'2018-06-14 15:55:44','2018-06-14 15:55:44',0),(1055,'DWZZB18S000028','$2a$10$oyL.e6VU692b8iUBKUAxZ.s3n7JWqoxNExENbme0oYGioWqh62Mbq',4,8,14,17,'张芷怡','女',NULL,'2018-06-14 15:55:44','2018-06-14 15:55:44',0),(1056,'DWZZB18S000029','$2a$10$0AHI6uiCk35cYtLRQS7Yz.A7ucuaIjYPUJGxECj27kiOvzv6PAuua',4,8,14,17,'贾嵘','女',NULL,'2018-06-14 15:55:44','2018-06-14 15:55:44',0),(1057,'DWZZB18S000030','$2a$10$AYMp28kdz54FK8VRjtijTeFbcNOd3WYJ.vddSmC47/e.N4JifSR/6',4,8,14,17,'俞言丽','女',NULL,'2018-06-14 15:55:44','2018-06-14 15:55:44',0),(1058,'DWZZB18S000031','$2a$10$mfaRp44UT.rrGSbM59S6jepgOOe9q0qksN20UaFq4FzEprccRg2Lq',4,8,14,17,'孙月红','女',NULL,'2018-06-14 15:55:45','2018-06-14 15:55:45',0),(1059,'DWZZB18S000032','$2a$10$Hgq0w5DAbKEwNkE4xUot8.464WcyiXf7JOVf4vduU.8KR9BBB6j7G',4,8,14,17,'朱振军','男',NULL,'2018-06-14 15:55:45','2018-06-14 15:55:45',0),(1060,'DWZZB18S000033','$2a$10$tfR49JnP3ULf7T14Z3n4K.mRtbfkq0t0WwoBUMLT6gAcMQl4mzYuG',4,8,14,17,'张轩','女',NULL,'2018-06-14 15:55:45','2018-06-14 15:55:45',0),(1061,'DWZZB18S000034','$2a$10$4VQ2L1mq0O1TKGUsJtIdX.YDR659dhEHpuJdw.YYSWOupWP3OS4V2',4,8,14,17,'唐奕','男',NULL,'2018-06-14 15:55:45','2018-06-14 15:55:45',0),(1062,'DWZZB18S000035','$2a$10$uBvt9Yflll7nDCKnxxEpE.e0A1K.EkciHFYYTKEvwsDRUUgrwiljC',4,8,14,17,'严玲','女',NULL,'2018-06-14 15:55:45','2018-06-14 15:55:45',0),(1063,'DWZZB18S000036','$2a$10$/HPihaUbsclV8zIvpZsiqOtng5.DxsDjQDqxPjF4At9sm3gqPKTg2',4,8,14,17,'商樊淇','男',NULL,'2018-06-14 15:55:45','2018-06-14 15:55:45',0),(1064,'DWZZB18S000037','$2a$10$qsRTtkDcQjwDgsq4gRLXYe3sDwysdMzv6uOvwW4fhewYEt7odJlki',4,8,14,17,'朱博闻','男',NULL,'2018-06-14 15:55:46','2018-06-14 15:55:46',0),(1065,'DWZZB18S000038','$2a$10$D/1h5PV/NoBrppdijb6HmuEUiU2mHzLZc8Rh8TGm4/HneECwjEu6O',4,8,14,17,'包元凤','女',NULL,'2018-06-14 15:55:46','2018-06-14 15:55:46',0),(1066,'DWZZB18S000039','$2a$10$xelm7gfhcIVkRWlj8RBHTOkJCPjLIqQkG7wyHXZocoV7xfZhluHJ.',4,8,14,17,'廖鸿森','男',NULL,'2018-06-14 15:55:46','2018-06-14 15:55:46',0),(1067,'DWZZB18S000040','$2a$10$mcyKas2qHjySxTFClsIjSOqRG/69pPwQuwM3aYmmEF/4NQqWWjAMa',4,8,14,17,'昌皓杰','男',NULL,'2018-06-14 15:55:46','2018-06-14 15:55:46',0),(1068,'DWZZB18S000041','$2a$10$tkMch/0oe9DW72RKCjFdq.UigNyiViwXkKjsvWRbE57f45YcKbKuW',4,8,14,17,'朱易','女',NULL,'2018-06-14 15:55:46','2018-06-14 15:55:46',0),(1069,'DWZZB18S000042','$2a$10$Sp6FMyDzR4SqogRMCAtHc.KNfNvTJqwELiRE9cAfH4PyCcovr.qKC',4,8,14,17,'朱沛宇','男',NULL,'2018-06-14 15:55:46','2018-06-14 15:55:46',0),(1070,'DWZZB18S000043','$2a$10$aGXHhDtZPoVtzqGT1VDQDuhsprP./qKQLUAitDIJ673rSn.Q81mGu',4,8,14,17,'朱凡','女',NULL,'2018-06-14 15:55:47','2018-06-14 15:55:47',0),(1071,'DWZZB18S000044','$2a$10$M0vaW407x3VNrTx2pP8i4OYt8WPhlLfrq/T4Cgky2D5FJ2JcO5ME6',4,8,14,17,'张文楷','男',NULL,'2018-06-14 15:55:47','2018-06-14 15:55:47',0),(1072,'DWZZB18S000045','$2a$10$IWqRt3uBGLjAbeQAsrbb6.LaqYAGz30yH4MLc19HCOHbc3/.GO79m',4,8,14,17,'徐凯','男',NULL,'2018-06-14 15:55:47','2018-06-14 15:55:47',0),(1073,'DWZZB18S000046','$2a$10$At33xvdSLecfdvNyT8SHWuhtXhMLt1wTWWapICLLFnV4xlVA8dT9G',4,8,14,17,'肖婉婷','女',NULL,'2018-06-14 15:55:47','2018-06-14 15:55:47',0),(1074,'DWZZB18S000047','$2a$10$.pChlOJao3VxlwAdz8tVjeCkUPAecAJdRJl./SfzXbuSJXcQ.twPu',4,8,14,17,'肖若瑶','男',NULL,'2018-06-14 15:55:47','2018-06-14 15:55:47',0),(1075,'DWZZB18S000048','$2a$10$QxA6Qg.dewoP8enHLx.Ec.uAovHMQgfgQ8t4mBNKLBDqEL1BMbf52',4,8,14,17,'王学浩','男',NULL,'2018-06-14 15:55:47','2018-06-14 15:55:47',0),(1076,'DWZZB18S000049','$2a$10$52o/bvOPQU3i9Rze7TGUj.PmhZikuqMgJStSVaDnFGHtZMipv7zJC',4,8,14,17,'王柏清','男',NULL,'2018-06-14 15:55:48','2018-06-14 15:55:48',0),(1077,'DWZZB18S000050','$2a$10$HqNEJQ23./OdlRa1Gffc3.aXQrCIORQNfI5MoxeJD6gXFu.edNX82',4,8,14,17,'沈谷悦','女',NULL,'2018-06-14 15:55:48','2018-06-14 15:55:48',0),(1078,'DWZZB18S000051','$2a$10$v9P93i7bicflN7KYnqbokeR19dRE7oQGsrPThxQy3qPp.nEJBPBMa',4,8,14,17,'惠昉','女',NULL,'2018-06-14 15:55:48','2018-06-14 15:55:48',0),(1079,'DWZZB18S000052','$2a$10$7y2i5ZfGr8OpX54NeVMWd.N9ZKBwz01eIBW7PcHRJpl/yeTVx/Cr.',4,8,14,17,'胡路康','男',NULL,'2018-06-14 15:55:48','2018-06-14 15:55:48',0),(1080,'DWZZB18S000053','$2a$10$7i4St./9GbrNWNpCYje.OOG5h.V.oWGnli/AHN/3PGPjmZSKthIqK',4,8,14,17,'侯宇博','男',NULL,'2018-06-14 15:55:48','2018-06-14 15:55:48',0),(1081,'DWZZB18S000054','$2a$10$Vbpqa5j.yUqPNTdPDY2baOyA/EYrEOo8MxpofMSbMHhjcjp8Y3s/i',4,8,14,17,'范紫倩','女',NULL,'2018-06-14 15:55:48','2018-06-14 15:55:48',0),(1082,'DWZZB18S000055','$2a$10$tBNolB9Cq.J72DL8HGzEe.LUWaxPRiL3vtAXpvv7ntxlmdNM2m3Fy',4,8,14,17,'褚嘉晨','男',NULL,'2018-06-14 15:55:49','2018-06-14 15:55:49',0),(1083,'DWZZB18S000056','$2a$10$mCuhDGasGfjvOgpU8MGhW.L2Qx5dUOoIsKMA3Ufy5A/xyQP7EoCsi',4,8,14,17,'陈名豪','男',NULL,'2018-06-14 15:55:49','2018-06-14 15:55:49',0),(1084,'DWZZB18S000057','$2a$10$ZRyGwR1gjRrxQDmnWOZU.uEa70Cf3UPuzoNO4ZDYqOC3fs4Y0zva.',4,8,14,17,'张晟','男',NULL,'2018-06-14 15:55:49','2018-06-14 15:55:49',0),(1085,'DWZZB18S000058','$2a$10$tS3Sidf8Da4mCTv8I8scKeyghaLhpc3vkRXjqSqsPpI5v6qJmUbha',4,8,14,17,'翟杰','女',NULL,'2018-06-14 15:55:49','2018-06-14 15:55:49',0),(1086,'DWZZB18S000059','$2a$10$uIrO8vvYYmEjwPViq7VtUuUdFJVZXXo/JNW8tneG6qhNA5GaO77P.',4,8,14,17,'殷思远','男',NULL,'2018-06-14 15:55:49','2018-06-14 15:55:49',0),(1087,'DWZZB18S000060','$2a$10$4uzFa6QVnOXzQMK6AtW6h.J.O99QyScA2sDXSbq7kd8zYsIMzE26i',4,8,14,17,'许晓雯','女',NULL,'2018-06-14 15:55:49','2018-06-14 15:55:49',0),(1088,'DWZZB18S000061','$2a$10$uR.0GYCwcU2P17gRhkBiHOw65ZmFH2VqSh0tufxUYAK1xOA3gDxvK',4,8,14,17,'谢文举','男',NULL,'2018-06-14 15:55:50','2018-06-14 15:55:50',0),(1089,'DWZZB18S000062','$2a$10$P0Nw6KRs/UX9Vq46thOeyO6sHE7e5cPc.Cq.oUcWq0Hv1.vjIhKQa',4,8,14,17,'吴明瀚','男',NULL,'2018-06-14 15:55:50','2018-06-14 15:55:50',0),(1090,'DWZZB18S000063','$2a$10$e5.vMsssKE/qvZJUSYgHU.7i8OAoDFAREJlRmHkVIPDP8TkuUFyqG',4,8,14,17,'王丹','女',NULL,'2018-06-14 15:55:50','2018-06-14 15:55:50',0),(1091,'DWZZB18S000064','$2a$10$B.Wl8qmfxhB/nI0Ur/SZmOlGwQZ6Qn4Bs.b55Np78mdeqhcNKZDGO',4,8,14,17,'潘锦博','男',NULL,'2018-06-14 15:55:50','2018-06-14 15:55:50',0),(1092,'DWZZB18S000065','$2a$10$I64MXopoc9rI8.fqa.Az7e9Ab.VEWOTErSncSok2zwjyzh/IClA.O',4,8,14,17,'孟纯飞','男',NULL,'2018-06-14 15:55:50','2018-06-14 15:55:50',0),(1093,'DWZZB18S000066','$2a$10$Vp8wX9liy/yKvPAGentpQurU9ZQUWEUbaAwQkcWX5R1heGZW3YLxm',4,8,14,17,'马辰熙','男',NULL,'2018-06-14 15:55:50','2018-06-14 15:55:50',0),(1094,'DWZZB18S000067','$2a$10$dWKuUHAomgty/XLHrtF5LebnEdmo92zWI1bNnbHSKV3DcllH2kpDi',4,8,14,17,'李萌','女',NULL,'2018-06-14 15:55:51','2018-06-14 15:55:51',0),(1095,'DWZZB18S000068','$2a$10$ByQi4k1FXDgURsyzqgZC/exC0MSPYzZQJtCDMDGdSIfXOM00qOFta',4,8,14,17,'郜鸿程','男',NULL,'2018-06-14 15:55:51','2018-06-14 15:55:51',0),(1096,'DWZZB18S000069','$2a$10$UYVvv/fB1AFKgWKe6Pb3rOhGvrd/Q7HUK7z8tHRgxHZ9EJOCsgrke',4,8,14,17,'符诗杰','男',NULL,'2018-06-14 15:55:51','2018-06-14 15:55:51',0),(1097,'DWZZB18S000070','$2a$10$4u71kgwoaOh/Sn0iK3yMwejpKmodaFujrf0wc3oo.2DQFFESNo1wq',4,8,14,17,'崔炜','女',NULL,'2018-06-14 15:55:51','2018-06-14 15:55:51',0),(1098,'DWZZB18S000071','$2a$10$GisJBYXb0WJexO22300UfeyBZzp1lIXrkmn91dx46grn3hv0DxbIy',4,8,14,17,'陈嘉俊','男',NULL,'2018-06-14 15:55:51','2018-06-14 15:55:51',0),(1099,'wxj','$2a$10$ku7yGhYpjmMhoLXNMbmJl.J5IYp78kw9P1ey06Nyz6CXMEQ.ehJ7K',3,8,14,NULL,'wxj',NULL,NULL,'2018-06-14 16:19:50','2018-06-14 16:19:50',0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-14 16:21:05
