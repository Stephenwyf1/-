-- MySQL dump 10.13  Distrib 8.0.25, for macos11.3 (x86_64)
--
-- Host: 127.0.0.1    Database: company_project
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assay`
--

DROP TABLE IF EXISTS `assay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assay` (
  `Assay_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Assay_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Assay_test` varchar(32) DEFAULT NULL COMMENT '化验检查',
  `Assay_idea` varchar(128) DEFAULT NULL COMMENT '医师意见',
  `Assay_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Assay_error` varchar(1) DEFAULT NULL COMMENT '是否处于驳回状态',
  PRIMARY KEY (`Stu_id`),
  KEY `assay_doctor_Doctor_id_fk` (`Assay_doctor_id`),
  CONSTRAINT `assay_doctor_Doctor_id_fk` FOREIGN KEY (`Assay_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assay_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='化验';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assay`
--

LOCK TABLES `assay` WRITE;
/*!40000 ALTER TABLE `assay` DISABLE KEYS */;
INSERT INTO `assay` VALUES (100019,'2021-06-17 18:34:16','化验检查无问题','无异样','1',1,'0');
/*!40000 ALTER TABLE `assay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blood`
--

DROP TABLE IF EXISTS `blood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blood` (
  `Blood_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Blood_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Blood_pressure` varchar(32) DEFAULT NULL COMMENT '血压',
  `Blood_pulse` varchar(32) DEFAULT NULL COMMENT '脉搏',
  `Blood_idea` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医生意见',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Blood_error` varchar(1) DEFAULT NULL COMMENT '是否处于驳回状态',
  `Blood_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='血压脉搏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blood`
--

LOCK TABLES `blood` WRITE;
/*!40000 ALTER TABLE `blood` DISABLE KEYS */;
INSERT INTO `blood` VALUES (100012,'2021-06-17 19:15:34','110','80','正常',1,'0','1');
/*!40000 ALTER TABLE `blood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boss`
--

DROP TABLE IF EXISTS `boss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boss` (
  `Boss_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Boss_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Boss_conclusion` varchar(128) DEFAULT NULL COMMENT '检查医院意见',
  `Boss_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Boss_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  CONSTRAINT `boss_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='医院领导信息录入';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boss`
--

LOCK TABLES `boss` WRITE;
/*!40000 ALTER TABLE `boss` DISABLE KEYS */;
INSERT INTO `boss` VALUES (100029,'2021-06-17 19:40:41','正常','1',1,'0');
/*!40000 ALTER TABLE `boss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chest`
--

DROP TABLE IF EXISTS `chest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chest` (
  `Chest_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Chest_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Chest_test` varchar(32) DEFAULT NULL COMMENT '胸部放射检查',
  `Chest_idea` varchar(128) DEFAULT NULL COMMENT '医师意见',
  `Chest_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Chest_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  CONSTRAINT `chest_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='胸部';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chest`
--

LOCK TABLES `chest` WRITE;
/*!40000 ALTER TABLE `chest` DISABLE KEYS */;
INSERT INTO `chest` VALUES (100022,'2021-06-17 18:39:35','胸部放射检查没有问题','无异样','1',1,'0'),(100022,'2021-06-17 19:06:16','无异样','很好','1',2,'0');
/*!40000 ALTER TABLE `chest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `Doctor_id` int NOT NULL COMMENT '编号',
  `Doctor_name` varchar(32) DEFAULT NULL COMMENT '医生姓名',
  `Doctor_card` varchar(32) DEFAULT NULL COMMENT '医生身份证号',
  `Doctor_department` varchar(32) DEFAULT NULL COMMENT '医生科室',
  PRIMARY KEY (`Doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='医生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (100000,'王洪侠','210124198508162281','眼科'),(100001,'付文文','150429800509501','眼科'),(100002,'史莹','210411198504282942','眼科'),(100003,'白瑞峰','622723198602013412','耳鼻喉科'),(100004,'兴明明','210304198504260488 ','耳鼻喉科'),(100005,'刘颖','210421198403162020','耳鼻喉科'),(100006,'孙雪','210303198412082729 ','口腔科'),(100007,'孙源龙','210302198607160938 ','口腔科'),(100008,'朱振华','211003198407230111','外科'),(100009,'佟琳','210303198508131214','外科'),(100010,'吴春雨','210111198503063012 ','外科'),(100011,'宋林良','210304198503040045','外科'),(100012,'张帆','152801198703025310 ','血压脉搏科'),(100013,'张纯华','411422198412055424 ','血压脉搏科'),(100014,'张泽利','370205197405213513 ','血压脉搏科'),(100015,'张家超','37010219680709292X','血压脉搏科'),(100016,'苏士超','411422198412055424','血压脉搏科'),(100017,'徐宁','21031119850417003x','内科'),(100018,'张志军','511428196305026357','内科'),(100019,'张志新','37142819800508053x','化验科'),(100020,'张忠荣','370283790911703 ','化验科'),(100021,'张子贵','210905197807210546','化验科'),(100022,'张宗敏','370205197405213513 ','胸部放射科'),(100023,'张作芹','370284800121002','胸部放射科'),(100024,'章莉','370727791118517 ','胸部放射科'),(100025,'赵标','370284197901130819','其他科'),(100026,'赵宾','370284801127364 ','体检负责医师'),(100027,'赵伯佳','370203800901162','体检负责医师'),(100028,'赵春风','372922198012224773','体检负责医师'),(100029,'赵丹','370722197812222517 ','医院领导');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ebh`
--

DROP TABLE IF EXISTS `ebh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ebh` (
  `EBH_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `EBH_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `EBH_throat` varchar(32) DEFAULT NULL COMMENT '咽喉',
  `EBH_stammer` varchar(32) DEFAULT NULL COMMENT '口吃',
  `EBH_idea` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医师意见',
  `EBH_all` varchar(1) DEFAULT NULL COMMENT '是否全部录入完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `EBH_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  `EBH_hearing_left` decimal(32,1) DEFAULT NULL,
  `EBH_hearing_right` decimal(32,1) DEFAULT NULL,
  `EBH_ear_disease` varchar(32) DEFAULT NULL,
  `EBH_smell_sense` varchar(32) DEFAULT NULL,
  `EBH_nose_disease` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`Stu_id`),
  KEY `ebh_doctor_Doctor_id_fk` (`EBH_doctor_id`),
  CONSTRAINT `ebh_doctor_Doctor_id_fk` FOREIGN KEY (`EBH_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='耳鼻喉科体检信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ebh`
--

LOCK TABLES `ebh` WRITE;
/*!40000 ALTER TABLE `ebh` DISABLE KEYS */;
INSERT INTO `ebh` VALUES (100003,'2021-06-17 19:07:49','无异样','无异样','正常','1',1,'0',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ebh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eye`
--

DROP TABLE IF EXISTS `eye`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eye` (
  `Eye_insight_left` decimal(32,1) DEFAULT NULL COMMENT '左眼视力',
  `Eye_insight_right` decimal(32,1) DEFAULT NULL COMMENT '右眼视力',
  `Eye_insight_left_correct` decimal(32,1) DEFAULT NULL COMMENT '左眼矫正视力',
  `Eye_insight_right_correct` decimal(32,1) DEFAULT NULL COMMENT '右眼矫正视力',
  `Eye_other` varchar(32) DEFAULT NULL COMMENT '其他眼病',
  `Eye_red` varchar(1) DEFAULT NULL COMMENT '红色检查',
  `Eye_green` varchar(1) DEFAULT NULL COMMENT '绿色检查',
  `Eye_purple` varchar(1) DEFAULT NULL COMMENT '紫色检查',
  `Eye_blue` varchar(1) DEFAULT NULL COMMENT '兰色检查',
  `Eye_yellow` varchar(1) DEFAULT NULL COMMENT '黄色检查',
  `Eye_idea` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医师意见',
  `Eye_all` varchar(1) DEFAULT NULL COMMENT '内容是否填写完毕',
  `Eye_error` varchar(1) DEFAULT NULL COMMENT '是否出现错误驳回',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Eye_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Eye_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  PRIMARY KEY (`Stu_id`),
  KEY `eye_doctor_Doctor_id_fk` (`Eye_doctor_id`),
  CONSTRAINT `eye_doctor_Doctor_id_fk` FOREIGN KEY (`Eye_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='眼科信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eye`
--

LOCK TABLES `eye` WRITE;
/*!40000 ALTER TABLE `eye` DISABLE KEYS */;
INSERT INTO `eye` VALUES (4.2,4.6,4.1,4.6,'无','1',NULL,'1','1','1','正常','1','0',1,100001,'2021-06-17 20:26:55'),(4.6,4.4,4.8,4.6,'无','1',NULL,NULL,'1',NULL,'正常','1','0',2,100001,'2021-06-17 19:05:19');
/*!40000 ALTER TABLE `eye` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internal`
--

DROP TABLE IF EXISTS `internal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internal` (
  `Internal_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Internal_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Internal_nutrition` varchar(32) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '发育营养',
  `Internal_nerve_spirit` varchar(32) DEFAULT NULL COMMENT '神经及精神',
  `Internal_lung_respiratory` varchar(32) DEFAULT NULL COMMENT '肺及呼吸道',
  `Internal_heart_blood` varchar(32) DEFAULT NULL COMMENT '心脏及血管',
  `Internal_liver` varchar(32) DEFAULT NULL COMMENT '腹部器官-肝',
  `Internal_spleen` varchar(32) DEFAULT NULL COMMENT '腹部器官-脾',
  `Internal_other` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他',
  `Internal_idea` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医师意见',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Internal_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Internal_error` varchar(1) DEFAULT NULL COMMENT '是否处于驳回状态',
  PRIMARY KEY (`Stu_id`),
  KEY `internal_doctor_Doctor_id_fk` (`Internal_doctor_id`),
  CONSTRAINT `internal_doctor_Doctor_id_fk` FOREIGN KEY (`Internal_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='内科医生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internal`
--

LOCK TABLES `internal` WRITE;
/*!40000 ALTER TABLE `internal` DISABLE KEYS */;
INSERT INTO `internal` VALUES (100017,'2021-06-17 19:08:32','正常','正常','正常','正常','正常','正常','无','正常',1,'1','0');
/*!40000 ALTER TABLE `internal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manage`
--

DROP TABLE IF EXISTS `manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manage` (
  `Manage_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Manage_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Manage_conclusion` varchar(128) DEFAULT NULL COMMENT '检查结论',
  `Manage_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Manage_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  KEY `manage_doctor_Doctor_id_fk` (`Manage_doctor_id`),
  CONSTRAINT `manage_doctor_Doctor_id_fk` FOREIGN KEY (`Manage_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `manage_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体检负责医生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage`
--

LOCK TABLES `manage` WRITE;
/*!40000 ALTER TABLE `manage` DISABLE KEYS */;
INSERT INTO `manage` VALUES (100027,'2021-06-17 19:30:41','正常','1',1,'0');
/*!40000 ALTER TABLE `manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `other`
--

DROP TABLE IF EXISTS `other`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `other` (
  `Other_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Other_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Other_test` varchar(32) DEFAULT NULL COMMENT '其他检查',
  `Other_idea` varchar(128) DEFAULT NULL COMMENT '医师意见',
  `Other_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Other_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  KEY `other_doctor_Doctor_id_fk` (`Other_doctor_id`),
  CONSTRAINT `other_doctor_Doctor_id_fk` FOREIGN KEY (`Other_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `other_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='其他检查';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `other`
--

LOCK TABLES `other` WRITE;
/*!40000 ALTER TABLE `other` DISABLE KEYS */;
INSERT INTO `other` VALUES (100025,'2021-06-17 18:41:49','其他科检查无异样','很正常','1',1,'0');
/*!40000 ALTER TABLE `other` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu_test`
--

DROP TABLE IF EXISTS `stu_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stu_test` (
  `Stu_id` int NOT NULL,
  `Eye_idea` varchar(255) DEFAULT NULL,
  `Eye_doctor_name` varchar(255) DEFAULT NULL,
  `Eye_doctor_id` int DEFAULT NULL,
  `Eye_operation_time` datetime DEFAULT NULL,
  `EBH_idea` varchar(255) DEFAULT NULL,
  `EBH_doctor_name` varchar(255) DEFAULT NULL,
  `EBH_doctor_id` int DEFAULT NULL,
  `EBH_operation_time` datetime DEFAULT NULL,
  `Tooth_idea` varchar(255) DEFAULT NULL,
  `Tooth_doctor_id` int DEFAULT NULL,
  `Tooth_doctor_name` varchar(255) DEFAULT NULL,
  `Tooth_operation_time` datetime DEFAULT NULL,
  `Surgery_idea` varchar(255) DEFAULT NULL,
  `Surgery_doctor_id` int DEFAULT NULL,
  `Surgery_doctor_name` varchar(255) DEFAULT NULL,
  `Surgery_operation_time` datetime DEFAULT NULL,
  `Blood_idea` varchar(255) DEFAULT NULL,
  `Blood_doctor_id` int DEFAULT NULL,
  `Blood_doctor_name` varchar(255) DEFAULT NULL,
  `Blood_operation_time` datetime DEFAULT NULL,
  `Internal_idea` varchar(255) DEFAULT NULL,
  `Internal_doctor_id` int DEFAULT NULL,
  `Internal_doctor_name` varchar(255) DEFAULT NULL,
  `Internal_operation_time` datetime DEFAULT NULL,
  `Assay_idea` varchar(255) DEFAULT NULL,
  `Assay_doctor_id` int DEFAULT NULL,
  `Assay_doctor_name` varchar(255) DEFAULT NULL,
  `Assay_operation_time` datetime DEFAULT NULL,
  `Chest_idea` varchar(255) DEFAULT NULL,
  `Chest_doctor_id` int DEFAULT NULL,
  `Chest_doctor_name` varchar(255) DEFAULT NULL,
  `Chest_operation_time` datetime DEFAULT NULL,
  `Other_idea` varchar(255) DEFAULT NULL,
  `Other_doctor_id` int DEFAULT NULL,
  `Other_doctor_name` varchar(255) DEFAULT NULL,
  `Other_operation_time` datetime DEFAULT NULL,
  `Manage_conclusion` varchar(255) DEFAULT NULL,
  `Manage_doctor_id` int DEFAULT NULL,
  `Manage_doctor_name` varchar(255) DEFAULT NULL,
  `Manage_operation_time` datetime DEFAULT NULL,
  `Stu_test_count` int DEFAULT NULL,
  PRIMARY KEY (`Stu_id`),
  CONSTRAINT `stu_test_student_Stu_id_fk` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生体检信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu_test`
--

LOCK TABLES `stu_test` WRITE;
/*!40000 ALTER TABLE `stu_test` DISABLE KEYS */;
INSERT INTO `stu_test` VALUES (1,'正常','付文文',100001,'2021-06-17 20:26:55','正常','白瑞峰',100003,'2021-06-17 19:07:49','正常',100006,'孙雪','2021-06-17 19:17:45','正常',100008,'朱振华','2021-06-17 19:13:00','正常',100012,'张帆','2021-06-17 19:15:34','正常',100017,'徐宁','2021-06-17 19:08:32','正常',100001,'付文文','2021-06-17 18:58:18','无异样',100022,'张宗敏','2021-06-17 18:39:35','很正常',100025,'赵标','2021-06-17 18:41:49','正常',100027,'赵伯佳','2021-06-17 19:30:41',10),(2,'正常','付文文',100001,'2021-06-17 19:05:19',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'很好',100022,'张宗敏','2021-06-17 19:06:16',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `stu_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `Stu_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `Stu_id` int NOT NULL COMMENT '编号',
  `Stu_card` varchar(32) DEFAULT NULL COMMENT '身份证',
  `Stu_college` varchar(32) DEFAULT NULL COMMENT '学院',
  `Stu_major` varchar(32) DEFAULT NULL COMMENT '专业',
  `Stu_class` varchar(32) DEFAULT NULL COMMENT '班级',
  `Stu_num` varchar(32) DEFAULT NULL COMMENT '学号',
  `Stu_sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `Stu_birth` date DEFAULT NULL COMMENT '出生年月日',
  `Stu_age` int DEFAULT NULL COMMENT '年龄',
  `Stu_education` varchar(32) DEFAULT NULL COMMENT '文化程度',
  `Stu_nation` varchar(32) DEFAULT NULL COMMENT '民族',
  `Stu_profession` varchar(32) DEFAULT NULL COMMENT '职业',
  `Stu_native` varchar(32) DEFAULT NULL COMMENT '籍贯',
  `Stu_location` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '家庭住址',
  `Stu_graduate_job` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '毕业学校或工作单位',
  `Stu_anamnesis` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '既往病史',
  `Stu_test_all` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('孔霆麟',1,'230882198906222898','软件学院','软件工程','1班','2018171931269','男','2020-06-08',20,'本科','汉','学生','广西','广西省廉江市车板镇大坝村委员会文头岭村96号','四川大学','无','1'),('劳家博',2,'371402197508174273','软件学院','软件工程','1班','2018967931269','男','2020-06-01',20,'本科','汉','学生','四川','四川省广安市广安区协兴镇果山村7组16-1号\n','四川大学','无','0'),('张宏斌',3,'340822198002250772','软件学院','软件工程','1班','2018171856979','女','2020-06-02',20,'本科','汉','学生','湖南','湖南省沅江市阳罗洲镇兴隆村十一村民组240\n','四川大学','无','0'),('林晓洁',4,'230127199105261557','软件学院','软件工程','1班','2018171931269','女','2020-04-15',20,'本科','汉','学生','广东','广东省茂名市七逢镇柏坡村1号\n','四川大学','无','0'),('聂诗军',5,'130133198712084731','软件学院','软件工程','1班','2018171657821','男','2020-12-09',20,'本科','汉','学生','广东','广东省佛山市沧江路110号3座305\n','四川大学','无','0'),('郜学来',6,'140424199005237635','软件学院','软件工程','1班','2018171931299','男','2020-06-05',20,'本科','汉','学生','河南','河南省光山县城关镇文化街破堰\n','四川大学','无','0'),('叶志雄',7,'330226197203032890','软件学院','软件工程','1班','2018171953169','男','2014-06-11',20,'本科','满','学生','河南','河南省息县白土店乡王大围孜村孙庄\n','四川大学','无','0'),('谭平升',8,'37010319840920387X','软件学院','软件工程','1班','2018171931269','男','2013-06-11',21,'本科','汉','学生','湖南','湖南省邵东县杨桥镇前丰村兴德组9号\n','四川大学','无','0'),('高麟傑',9,'230716198402299075','软件学院','软件工程','1班','2018171931111','女','2009-06-20',19,'本科','汉','学生','广东','广东省化州市下郭街道办坡尾旺跟岭村41号\n','四川大学','无','0'),('华子纲',10,'340822199301200235','软件学院','软件工程','1班','2018746981269','女','2007-03-16',20,'本科','汉','学生','广东','广东省惠来县隆江镇见龙管区见中井头东三直巷17号之一\n','四川大学','无','0'),('柴小宏',11,'230716197509268477','软件学院','软件工程','1班','2018171931269','男','2010-06-16',20,'本科','汉','学生','广东','广东省陆丰市河东镇欧厝村委会欧厝二村\n','四川大学','无','0'),('毛诗源',12,'411402198001142333','软件学院','软件工程','2班','2018171931269','男','2011-06-22',20,'本科','汉','学生','湖南','湖南省常宁市宜阳镇裕民电站\n','四川大学','无','0'),('高子桐',13,'13022419850331136X','软件学院','软件工程','2班','2018732931269','女','2009-06-02',21,'本科','汉','学生','湖南','湖南省东安县南桥镇上塘村6组-37\n','四川大学','无','0'),('吴琼珠',14,'130684198011058087','软件学院','软件工程','2班','2018171974269','男','2021-06-01',21,'本科','汉','学生','广东','广东省普宁市高埔镇山下村80号\n','四川大学','无','0'),('温小雨',15,'130526198507215964','软件学院','软件工程','2班','2018171931269','男','2012-06-25',21,'本科','汉','学生','广西','广西桂林市秀峰区丽中路22号2单元7-1\n','四川大学','无','0'),('胡雪婷',16,'13013119860128026X','软件学院','软件工程','2班','2018171931269','男','2004-06-01',21,'本科','汉','学生','湖南','湖南省洞口县又兰镇凤凰村8组12号\n','四川大学','无','0'),('方燕华',17,'231085199010090184','软件学院','软件工程','2班','2018171931269','男','2008-06-21',22,'本科','汉','学生','广东','广东广州市越秀区\r\n中山六路238号越秀新都会大厦','四川大学','无','0'),('赵汀婷',18,'330902197508012927','软件学院','软件工程','2班','2018000251269','男','2008-06-27',19,'本科','汉','学生','广东','广东广州市荔湾区\r\n芳村大道东200号1850创意园72栋A','四川大学','无','0'),('董文洁',19,'370983199104088348','软件学院','软件工程','3班','2018171931269','男','2008-06-20',22,'本科','汉','学生','四川','四川省合江县实录乡慈竹林村六社103号\n','四川大学','无','0'),('翟蕾蕾',20,'330283198509065769','软件学院','软件工程','3班','2018171931269','男','2009-06-26',21,'本科','汉','学生','四川','四川省广安市广安区协兴镇果山村7组16-1号\n','四川大学','无','0');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surgery`
--

DROP TABLE IF EXISTS `surgery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surgery` (
  `Surgery_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Surgery_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Surgery_height` decimal(32,1) DEFAULT NULL COMMENT '身长',
  `Surgery_waistline` decimal(32,1) DEFAULT NULL COMMENT '腰围',
  `Surgery_weight` decimal(32,1) DEFAULT NULL COMMENT '体重',
  `Surgery_skin` varchar(32) DEFAULT NULL COMMENT '皮肤',
  `Surgery_lymph` varchar(32) DEFAULT NULL COMMENT '淋巴',
  `Surgery_thyroid` varchar(32) DEFAULT NULL COMMENT '甲状腺',
  `Surgery_spine` varchar(32) DEFAULT NULL COMMENT '脊柱',
  `Surgery_fours` varchar(32) DEFAULT NULL COMMENT '四肢',
  `Surgery_joint` varchar(32) DEFAULT NULL COMMENT '关节',
  `Surgery_flat_extension_foot` varchar(32) DEFAULT NULL COMMENT '平拓足',
  `Surgery_other` varchar(32) DEFAULT NULL COMMENT '其他',
  `Surgery_idea` varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医师意见',
  `Surgery_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Surgery_error` varchar(1) DEFAULT NULL COMMENT '是否处于驳回状态',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  PRIMARY KEY (`Stu_id`),
  KEY `surgery_doctor_Doctor_id_fk` (`Surgery_doctor_id`),
  CONSTRAINT `surgery_doctor_Doctor_id_fk` FOREIGN KEY (`Surgery_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='外科';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surgery`
--

LOCK TABLES `surgery` WRITE;
/*!40000 ALTER TABLE `surgery` DISABLE KEYS */;
INSERT INTO `surgery` VALUES (100008,'2021-06-17 19:13:00',183.0,751.0,75.0,'正常','未触及','正常','正常','正常','正常',NULL,'无','正常','1','0',1);
/*!40000 ALTER TABLE `surgery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_permission` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(300) DEFAULT NULL COMMENT '菜单权限名称',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `url` varchar(100) DEFAULT NULL COMMENT '访问地址URL',
  `target` varchar(50) DEFAULT NULL COMMENT 'a target属性:_self _blank',
  `pid` varchar(64) DEFAULT NULL COMMENT '父级菜单权限名称',
  `order_num` int DEFAULT NULL COMMENT '排序',
  `type` tinyint DEFAULT NULL COMMENT '菜单权限类型(1:目录;2:菜单;3:按钮)',
  `status` tinyint DEFAULT NULL COMMENT '状态1:正常 0：禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES ('1','删除','sysGenerator:delete',NULL,'sysGenerator/delete',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('10','赋予角色','sys:user:role:update',NULL,'/sys/user/roles/*',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('1000','体检表单','sys:form',NULL,NULL,NULL,'0',100,1,1,'2021-06-11 20:49:36','2021-06-11 20:49:38',1),('10001','外科体检','sys:form:surgery','layui-icon-read','/index/surgery','_self','1000',2,2,1,'2021-06-07 10:03:37','2021-06-17 11:45:37',1),('10002','内科体检','sys:form:internal','layui-icon-read','/index/internal','_self','1000',3,2,1,'2021-06-07 10:03:37','2021-06-17 11:45:43',1),('10003','化验科体检','sys:form:assay','layui-icon-read','/index/assay','_self','1000',125,2,1,'2021-06-08 14:23:38','2021-06-16 09:45:33',1),('10004','眼科体检','sys:form:eye','layui-icon-read','/index/eye','_self','1000',5,2,1,'2021-06-16 09:46:51','2021-06-17 11:46:00',1),('10005','牙科体检','sys:form:tooth','layui-icon-read','/index/tooth','_self','1000',1,2,1,'2021-06-10 16:56:47','2021-06-17 11:45:29',1),('10006','耳鼻喉体检','sys:form:EBH','layui-icon-read','/index/ebh','_self','1000',6,2,1,'2021-06-16 09:48:30','2021-06-17 11:46:11',1),('10007','血压脉搏体检','sys:form:blood','layui-icon-read','/index/blood','_self','1000',4,2,1,'2021-06-07 10:03:37','2021-06-17 11:45:50',1),('10008','其他体检','sys:form:other','layui-icon-read','/index/other','_self','1000',124,2,1,'2021-06-07 10:03:37','2021-06-16 09:44:47',1),('10009','体检负责医生','sys:form:obligation','layui-icon-read','/index/manage','_self','1000',124,2,1,'2021-06-07 10:03:37','2021-06-16 09:44:47',1),('10010','领导检查','sys:form:boss','layui-icon-read','/index/boss','_self','1000',124,2,1,'2021-06-07 10:03:37','2021-06-16 09:44:47',1),('10011','胸部放射体检','sys:form:chest','layui-icon-read','/index/chest','_self','1000',124,2,1,'2021-06-07 10:03:37','2021-06-16 09:44:47',1),('11','菜单权限管理',NULL,NULL,'/index/menus','_self','51',98,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('12','列表','sys:dept:list',NULL,'/sys/depts',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('123123412','详情','sys:permission:detail','','/sys/permission/*','_self','1401721476860817409',1232,3,1,'2021-06-07 12:01:58','2021-06-07 12:01:58',0),('13','删除','sys:role:deleted',NULL,'/sys/role/*',NULL,'53',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('15','代码生成',NULL,NULL,'/index/sysGenerator','_self','54',1,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('16','列表','sysGenerator:list',NULL,'sysGenerator/listByPage',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('17','详情','sys:permission:detail',NULL,'/sys/permission/*',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('19','列表','sys:role:list',NULL,'/sys/roles',NULL,'53',0,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('2','SQL 监控','','','/druid/sql.html','_self','21',98,2,1,'2020-03-19 13:29:40','2020-05-07 13:36:59',0),('20','修改','sysGenerator:update',NULL,'sysGenerator/update',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('2000','人员管理',NULL,NULL,NULL,NULL,'0',100,1,1,'2021-06-11 20:49:36','2021-06-11 20:49:38',1),('20001','医生管理',NULL,NULL,'/index/users',NULL,'2000',100,2,1,'2021-06-11 20:51:03','2021-06-11 20:51:05',1),('20002','学生管理','sys:stu:detail',NULL,'/index/students',NULL,'2000',100,2,1,'2021-06-11 20:51:03','2021-06-11 20:51:05',1),('21','其他','','layui-icon-list','','_self','0',200,1,1,'2020-03-19 13:29:40','2021-06-17 12:11:34',0),('22','详情','sys:dept:detail',NULL,'/sys/dept/*',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('23','列表','sys:user:list',NULL,'/sys/users',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('24','用户管理',NULL,NULL,'/index/users','_self','51',100,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('25','详情','sys:user:detail',NULL,'/sys/user/*',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('26','删除','sys:permission:deleted',NULL,'/sys/permission/*',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('3','新增','sys:role:add',NULL,'/sys/role',NULL,'53',0,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('36','更新','sys:role:update',NULL,'/sys/role',NULL,'53',0,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('38','更新','sys:dept:update',NULL,'/sys/dept',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('39','详情','sys:role:detail',NULL,'/sys/role/*',NULL,'53',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('4','添加','sysGenerator:add',NULL,'sysGenerator/add',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('40','编辑','sys:permission:update',NULL,'/sys/permission',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('41','部门管理',NULL,NULL,'/index/depts','_self','51',100,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('42','新增','sys:user:add',NULL,'/sys/user',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('43','列表','sys:permission:list',NULL,'/sys/permissions',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('432429446','数据导出','','','','_self','0',5,1,1,'2021-06-17 19:45:42','2021-06-17 19:45:42',1),('432429451','导出体检数据','','','/index/pdf','_self','432429446',1,2,1,'2021-06-17 19:46:13','2021-06-17 19:46:13',1),('44','新增','sys:permission:add',NULL,'/sys/permission',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('45','字典管理',NULL,'','/index/sysDict',NULL,'54',10,2,1,NULL,NULL,0),('46','列表','sysDict:list',NULL,'sysDict/listByPage',NULL,'45',0,3,1,NULL,NULL,0),('47','新增','sysDict:add',NULL,'sysDict/add',NULL,'45',0,3,1,NULL,NULL,0),('48','修改','sysDict:update',NULL,'sysDict/update',NULL,'45',0,3,1,NULL,NULL,0),('49','删除','sysDict:delete',NULL,'sysDict/delete',NULL,'45',0,3,1,NULL,NULL,0),('5','删除','sys:dept:deleted',NULL,'/sys/dept/*',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('50','表单构建','','','/index/build','_self','21',1,2,1,'2020-04-22 13:09:41','2020-05-07 13:36:47',0),('51','组织管理',NULL,'layui-icon-user',NULL,NULL,'0',1,1,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('52','拥有角色','sys:user:role:detail',NULL,'/sys/user/roles/*',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('53','角色管理',NULL,NULL,'/index/roles','_self','51',99,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('54','系统管理',NULL,'layui-icon-set-fill',NULL,NULL,'0',98,1,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('56','更新','sys:user:update',NULL,'/sys/user',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('57','删除','sys:user:deleted',NULL,'/sys/user',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('58','删除','sys:log:deleted',NULL,'/sys/logs',NULL,'8',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('6','接口管理','','','/doc.html','_blank','21',100,2,1,'2020-03-19 13:29:40','2020-05-07 13:36:02',0),('7','列表','sys:log:list',NULL,'/sys/logs',NULL,'8',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('8','日志管理',NULL,NULL,'/index/logs','_self','54',97,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0),('9','新增','sys:dept:add',NULL,'/sys/dept',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',0);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除(1未删除；0已删除)',
  `data_scope` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('1','超级管理员','拥有所有权限-不能删除',1,'2019-11-01 19:26:29','2021-06-17 19:46:28',1,124),('11','眼科医生','眼科医生角色',1,'2021-06-15 19:39:09','2021-06-17 17:15:03',1,124),('12','耳鼻喉医生','耳鼻喉医生',1,'1970-01-01 00:00:00','2021-06-10 16:57:04',1,127),('13','口腔科医生','口腔科医生',1,'1970-01-01 00:00:00','2021-06-15 19:48:17',1,124),('14','外科医生','外科医生',1,'2021-06-16 10:26:30','2021-06-17 19:42:50',1,125),('15','血压脉搏医生','血压脉搏医生',1,'1970-01-01 00:00:00','2021-06-15 19:48:17',1,124),('16','内科医生','内科医生',1,'1970-01-01 00:00:00','2021-06-15 19:48:17',1,11),('17','化验科医生','化验科医生',1,'1970-01-01 00:00:00','2021-06-10 16:57:04',1,127),('18','胸部放射科医生','胸部放射科医生',1,'1970-01-01 00:00:00','2021-06-15 19:48:17',1,124),('19','其他医生','其他医生',1,'1970-01-01 00:00:00','2021-06-10 16:57:04',1,124),('21','体检负责医生','体检负责医生',1,'1970-01-01 00:00:00','2021-06-15 19:48:17',1,124),('22','医院领导','医院领导',1,'1970-01-01 00:00:00','2021-06-10 16:57:04',1,124);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_permission` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES ('1402912686633168915','12','10006','2021-06-10 16:57:04'),('1402912686633168916','13','10005','2021-06-10 16:57:04'),('1402912686633168918','15','10007','2021-06-10 16:57:04'),('1402912686633168919','16','10002','2021-06-10 16:57:04'),('1402912686633168920','17','10003','2021-06-10 16:57:04'),('1402912686633168921','18','10011','2021-06-10 16:57:04'),('1402912686633168922','19','10008','2021-06-16 10:17:45'),('1402912686633168923','20','10009','2021-06-16 10:17:51'),('1402912686633168924','21','10010','2021-06-16 10:17:52'),('1402912686633168926','12','1000','2021-06-10 16:57:04'),('1402912686633168927','13','1000','2021-06-10 16:57:04'),('1402912686633168929','15','1000','2021-06-10 16:57:04'),('1402912686633168930','16','1000','2021-06-10 16:57:04'),('1402912686633168931','17','1000','2021-06-10 16:57:04'),('1402912686633168932','18','1000','2021-06-10 16:57:04'),('1402912686633168933','19','1000','2021-06-16 10:17:45'),('1402912686633168934','20','1000','2021-06-16 10:17:51'),('1402912686633168935','21','1000','2021-06-16 10:17:52'),('423614615','11','1000','2021-06-17 17:15:03'),('423614616','11','10004','2021-06-17 17:15:03'),('432429436','14','1000','2021-06-17 19:42:50'),('432429437','14','10001','2021-06-17 19:42:50'),('432429456','1','51','2021-06-17 19:46:28'),('432429457','1','11','2021-06-17 19:46:28'),('432429458','1','17','2021-06-17 19:46:28'),('432429459','1','26','2021-06-17 19:46:28'),('432429460','1','40','2021-06-17 19:46:28'),('432429461','1','43','2021-06-17 19:46:28'),('432429462','1','44','2021-06-17 19:46:28'),('432429463','1','53','2021-06-17 19:46:28'),('432429464','1','3','2021-06-17 19:46:28'),('432429465','1','19','2021-06-17 19:46:28'),('432429466','1','36','2021-06-17 19:46:28'),('432429467','1','13','2021-06-17 19:46:28'),('432429468','1','39','2021-06-17 19:46:28'),('432429469','1','24','2021-06-17 19:46:28'),('432429470','1','10','2021-06-17 19:46:28'),('432429471','1','23','2021-06-17 19:46:28'),('432429472','1','25','2021-06-17 19:46:28'),('432429473','1','42','2021-06-17 19:46:28'),('432429474','1','52','2021-06-17 19:46:28'),('432429475','1','56','2021-06-17 19:46:28'),('432429476','1','57','2021-06-17 19:46:28'),('432429483','1','432429446','2021-06-17 19:46:28'),('432429484','1','432429451','2021-06-17 19:46:28'),('432429499','1','1000','2021-06-17 19:46:28'),('432429500','1','10005','2021-06-17 19:46:28'),('432429501','1','10001','2021-06-17 19:46:28'),('432429502','1','10002','2021-06-17 19:46:28'),('432429503','1','10007','2021-06-17 19:46:28'),('432429504','1','10004','2021-06-17 19:46:28'),('432429505','1','10006','2021-06-17 19:46:28'),('432429506','1','10008','2021-06-17 19:46:28'),('432429507','1','10009','2021-06-17 19:46:28'),('432429508','1','10010','2021-06-17 19:46:28'),('432429509','1','10011','2021-06-17 19:46:28'),('432429510','1','10003','2021-06-17 19:46:28'),('432429511','1','2000','2021-06-17 19:46:28'),('432429512','1','20001','2021-06-17 19:46:28'),('432429513','1','20002','2021-06-17 19:46:28');
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '账户名称',
  `salt` varchar(20) DEFAULT NULL COMMENT '加密盐值',
  `password` varchar(200) NOT NULL COMMENT '用户密码密文',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `dept_id` varchar(64) DEFAULT NULL COMMENT '部门id',
  `real_name` varchar(60) DEFAULT NULL COMMENT '真实名称',
  `nick_name` varchar(60) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱(唯一)',
  `status` tinyint DEFAULT NULL COMMENT '账户状态(1.正常 2.锁定 )',
  `sex` tinyint DEFAULT NULL COMMENT '性别(1.男 2.女)',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除(1未删除；0已删除)',
  `create_id` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_id` varchar(64) DEFAULT NULL COMMENT '更新人',
  `create_where` tinyint DEFAULT NULL COMMENT '创建来源(1.web 2.android 3.ios )',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1','admin','324ce32d86224b00a02b','2102b59a75ab87616b62d0b9432569d0','13888888888','1','爱糖宝','爱糖宝','xxxxxx@163.com',1,2,1,'1','1',3,'2019-09-22 19:38:05','2020-03-18 09:15:22');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL COMMENT '用户id',
  `user_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('1','1','1','2020-03-19 02:23:13'),('1405433786264231938','100001','11','2021-06-17 15:55:01'),('1405434062538842114','100002','11','2021-06-17 15:56:07'),('1405434583802748929','100003','12','2021-06-17 15:58:11'),('1405434676110991362','100006','13','2021-06-17 15:58:33'),('1405435711663677441','100008','14','2021-06-17 16:02:40'),('2','100022','18','2021-06-17 18:32:47'),('259692278','100012','15','2021-06-15 19:45:14'),('259692282','100017','16','2021-06-15 19:45:14'),('259692288','100019','17','2021-06-15 19:45:14'),('3','100025','19','2021-06-17 18:32:50'),('4','100027','20','2021-06-17 18:32:51'),('5','100029','21','2021-06-17 18:32:52');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tooth`
--

DROP TABLE IF EXISTS `tooth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tooth` (
  `Tooth_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Tooth_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Tooth_decayed` varchar(32) DEFAULT NULL COMMENT '龋齿',
  `Tooth_hypodontia` varchar(32) DEFAULT NULL COMMENT '缺齿',
  `Tooth_tooth_space` varchar(32) DEFAULT NULL COMMENT '齿槽',
  `Tooth_other` varchar(32) DEFAULT NULL COMMENT '其他',
  `Tooth_idea` varchar(32) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医师意见',
  `Tooth_all` varchar(1) DEFAULT NULL COMMENT '是否全部',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Tooth_error` varchar(1) DEFAULT NULL COMMENT '是否处于驳回状态',
  PRIMARY KEY (`Stu_id`),
  KEY `tooth_doctor_Doctor_id_fk` (`Tooth_doctor_id`),
  CONSTRAINT `tooth_doctor_Doctor_id_fk` FOREIGN KEY (`Tooth_doctor_id`) REFERENCES `doctor` (`Doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='口腔';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tooth`
--

LOCK TABLES `tooth` WRITE;
/*!40000 ALTER TABLE `tooth` DISABLE KEYS */;
INSERT INTO `tooth` VALUES (100006,'2021-06-17 19:17:45','正常','正常','正常','无','正常','1',1,'1');
/*!40000 ALTER TABLE `tooth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `Account_name` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `Password` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `User_id` int NOT NULL COMMENT '用户id',
  `User_type` int DEFAULT NULL COMMENT '用户类型 0表示管理员 1X表示医生 11表示眼科 12表示耳鼻喉科 13表示口腔科 14表示外科 15表示血压脉搏科 16内科 17化验科 18胸部放射科 19其他 20体检负责 21体检负责医师 22医院领导',
  `salt` varchar(20) DEFAULT NULL,
  `status` int DEFAULT '1',
  `sex` int DEFAULT '1',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES ('admin','2102b59a75ab87616b62d0b9432569d0',1,1,'324ce32d86224b00a02b',1,1,'2021-06-15 11:11:59'),('doctor','2102b59a75ab87616b62d0b9432569d0',2,2,'324ce32d86224b00a02b',1,1,'2021-06-15 11:12:01'),('Eye01','8cd3e58d49b5ccbb74fb925eb885c3b7',100001,11,'e42dd3059da6451daf20',1,1,'2021-06-17 17:11:52'),('EBH01','f8c4e0a3461e2078064363ed17a796a4',100003,12,'c8911576009f4224b239',1,1,'2021-06-17 17:11:55'),('Tooth01','727e9a9ccaccd787a36c5f23fbb4c742',100006,13,'2ee2c025e1e2406393ed',1,1,'2021-06-17 17:11:56'),('Surgery01','c50255a4a9df38a41271c2b6a59a2131',100008,14,'afd8958fa4cd46379a31',1,1,'2021-06-17 17:11:57'),('Blood01','376b07976afa71f2f63bfaede356d1b5',100012,15,'a5cda9b59a3d47faa7ad',1,1,'2021-06-17 17:11:58'),('Internal01','59a8496a11c3ad7f34aee0759ca2dee6',100017,16,'0e15e1c169c8449ca1be',1,1,'2021-06-17 17:11:59'),('Assay01','e1df61aa187bcf1ac9cfd582f80e7b89',100019,17,'c5413a644a2547ab8c4c',1,1,'2021-06-17 17:12:00'),('Chest01','7fe7718bef51249d31a49373b70544d5',100022,18,'95418659c0b1419c81b3',1,1,'2021-06-17 17:12:00'),('Other01','f8ae6c894e2961925ccbc311d2c48be3',100025,19,'b7db38d4bb9f4dcbb5ce',1,1,'2021-06-17 17:12:01'),('Manage01','cd6e25d928130cd7cd7d4e7bdf43ede6',100027,20,'1d990437754d49a8a27c',1,1,'2021-06-17 17:12:02'),('Boss01','0ecce1ead571ecc76e81de598be69c00',100029,21,'b9dbf39600984934a836',1,1,'2021-06-17 17:12:02');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-18 10:38:31
