/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ company_project /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE company_project;

DROP TABLE IF EXISTS assay;
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

DROP TABLE IF EXISTS blood;
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

DROP TABLE IF EXISTS boss;
CREATE TABLE `boss` (
  `Boss_doctor_id` varchar(32) DEFAULT NULL COMMENT '检查的医生ID',
  `Boss_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Boss_conclusion` varchar(128) DEFAULT NULL COMMENT '检查医院意见',
  `Boss_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Boss_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  CONSTRAINT `boss_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='医院领导信息录入';

DROP TABLE IF EXISTS chest;
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

DROP TABLE IF EXISTS doctor;
CREATE TABLE `doctor` (
  `Doctor_id` int NOT NULL COMMENT '编号',
  `Doctor_name` varchar(32) DEFAULT NULL COMMENT '医生姓名',
  `Doctor_card` varchar(32) DEFAULT NULL COMMENT '医生身份证号',
  `Doctor_department` varchar(32) DEFAULT NULL COMMENT '医生科室',
  PRIMARY KEY (`Doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='医生表';

DROP TABLE IF EXISTS ebh;
CREATE TABLE `ebh` (
  `EBH_doctor_id` varchar(32) DEFAULT NULL COMMENT '检查的医生ID',
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
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='耳鼻喉科体检信息';

DROP TABLE IF EXISTS eye;
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
  `Eye_doctor_id` varchar(32) DEFAULT NULL COMMENT '检查的医生ID',
  `Eye_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='眼科信息表';

DROP TABLE IF EXISTS internal;
CREATE TABLE `internal` (
  `Internal_doctor_id` varchar(32) DEFAULT NULL COMMENT '检查的医生ID',
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
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='内科医生';

DROP TABLE IF EXISTS manage;
CREATE TABLE `manage` (
  `Manage_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Manage_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Manage_conclusion` varchar(128) DEFAULT NULL COMMENT '检查结论',
  `Manage_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Manage_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  CONSTRAINT `manage_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体检负责医生';

DROP TABLE IF EXISTS other;
CREATE TABLE `other` (
  `Other_doctor_id` int DEFAULT NULL COMMENT '检查的医生ID',
  `Other_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Other_test` varchar(32) DEFAULT NULL COMMENT '其他检查',
  `Other_idea` varchar(128) DEFAULT NULL COMMENT '医师意见',
  `Other_all` varchar(1) DEFAULT NULL COMMENT '是否全部填写完毕',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Other_error` varchar(1) DEFAULT NULL COMMENT '是否处于被驳回状态',
  PRIMARY KEY (`Stu_id`),
  CONSTRAINT `other_ibfk_1` FOREIGN KEY (`Stu_id`) REFERENCES `student` (`Stu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='其他检查';

DROP TABLE IF EXISTS stu_test;
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

DROP TABLE IF EXISTS student;
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

DROP TABLE IF EXISTS surgery;
CREATE TABLE `surgery` (
  `Surgery_doctor_id` varchar(32) DEFAULT NULL COMMENT '检查的医生ID',
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
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='外科';

DROP TABLE IF EXISTS sys_content;
CREATE TABLE `sys_content` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `type` int DEFAULT NULL COMMENT '文章类型',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='文章管理';

DROP TABLE IF EXISTS sys_dept;
CREATE TABLE `sys_dept` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `dept_no` varchar(18) DEFAULT NULL COMMENT '部门编号(规则：父级关系编码+自己的编码)',
  `name` varchar(300) DEFAULT NULL COMMENT '部门名称',
  `pid` varchar(64) NOT NULL COMMENT '父级id',
  `status` tinyint DEFAULT NULL COMMENT '状态(1:正常；0:弃用)',
  `relation_code` varchar(3000) DEFAULT NULL COMMENT '为了维护更深层级关系',
  `dept_manager_id` varchar(64) DEFAULT NULL COMMENT '部门经理user_id',
  `manager_name` varchar(255) DEFAULT NULL COMMENT '部门经理名称',
  `phone` varchar(20) DEFAULT NULL COMMENT '部门经理联系电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统部门';

DROP TABLE IF EXISTS sys_dict;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='数据字典表';

DROP TABLE IF EXISTS sys_dict_detail;
CREATE TABLE `sys_dict_detail` (
  `id` varchar(50) NOT NULL,
  `label` varchar(255) NOT NULL COMMENT '字典标签',
  `value` varchar(255) NOT NULL COMMENT '字典值',
  `sort` smallint DEFAULT NULL COMMENT '排序',
  `dict_id` varchar(50) DEFAULT NULL COMMENT '字典id',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='数据字典详情';

DROP TABLE IF EXISTS sys_files;
CREATE TABLE `sys_files` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='文件上传';

DROP TABLE IF EXISTS sys_job;
CREATE TABLE `sys_job` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='定时任务';

DROP TABLE IF EXISTS sys_job_log;
CREATE TABLE `sys_job_log` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务日志id',
  `job_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数',
  `status` tinyint NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '失败信息',
  `times` int NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `job_id` (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='定时任务日志';

DROP TABLE IF EXISTS sys_log;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统日志';

DROP TABLE IF EXISTS sys_permission;
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='系统权限';

DROP TABLE IF EXISTS sys_role;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) DEFAULT NULL,
  `status` tinyint DEFAULT NULL COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统角色';

DROP TABLE IF EXISTS sys_role_dept;
CREATE TABLE `sys_role_dept` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='角色部门';

DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE `sys_role_permission` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS sys_user;
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

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL COMMENT '用户id',
  `user_id` varchar(64) DEFAULT NULL,
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='系统用户角色';

DROP TABLE IF EXISTS tooth;
CREATE TABLE `tooth` (
  `Tooth_doctor_id` varchar(32) DEFAULT NULL COMMENT '检查的医生ID',
  `Tooth_operation_time` datetime DEFAULT NULL COMMENT '体检信息提交时间',
  `Tooth_decayed` varchar(32) DEFAULT NULL COMMENT '龋齿',
  `Tooth_hypodontia` varchar(32) DEFAULT NULL COMMENT '缺齿',
  `Tooth_tooth_space` varchar(32) DEFAULT NULL COMMENT '齿槽',
  `Tooth_other` varchar(32) DEFAULT NULL COMMENT '其他',
  `Tooth_idea` varchar(32) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '医师意见',
  `Tooth_all` varchar(1) DEFAULT NULL COMMENT '是否全部',
  `Stu_id` int NOT NULL COMMENT '学生ID',
  `Tooth_error` varchar(1) DEFAULT NULL COMMENT '是否处于驳回状态',
  PRIMARY KEY (`Stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='口腔';

DROP TABLE IF EXISTS user_account;
CREATE TABLE `user_account` (
  `Account_name` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `Password` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `User_id` int NOT NULL COMMENT '用户id',
  `User_type` int DEFAULT NULL COMMENT '用户类型 0表示管理员 1X表示医生 11表示眼科 12表示耳鼻喉科 13表示口腔科 14表示外科 15表示血压脉搏科 16内科 17化验科 18胸部放射科 19其他 20体检负责 21体检负责医师 22医院领导',
  `salt` varchar(20) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '1',
  `sex` int DEFAULT '1',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户账号表';

INSERT INTO assay(Assay_doctor_id,Assay_operation_time,Assay_test,Assay_idea,Assay_all,Stu_id,Assay_error) VALUES(100001,'2021-06-16 15:19:58','无异样的','没问题嗷嗷','1',1,'0'),(100020,'2021-06-08 20:32:11','加强锻炼','今晚一起玩','1',2,'0'),(100021,'2021-06-07 22:01:03','挺健康','没啥毛病','1',3,'0'),(100019,'2021-06-13 09:32:54','身体健康，没有问题','可以的，身体健康','1',4,'0'),(100002,'2021-06-13 09:37:02','还可以哈哈哈','没啥问题的的','1',5,'0'),(100006,'2021-06-13 16:27:03','没啥大问题','无异样嗷嗷','1',6,'0'),(100005,'2021-06-13 16:33:40','化验科正常','没问题','1',7,'0'),(100005,'2021-06-13 16:35:38','化验可以的','嗯嗯','1',8,'0'),(100004,'2021-06-13 16:40:24','检查没问题','哈哈哈哈','1',9,'0'),(100015,'2021-06-16 15:08:21','很好','很好','1',10,'0'),(100015,'2021-06-16 15:12:53','很好很好','很好','1',18,'0'),(100015,'2021-06-16 15:12:26','很好啊很好','很好','1',19,'0'),(100015,'2021-06-16 15:15:47','很好啊','很好啊','1',20,'0');

INSERT INTO blood(Blood_doctor_id,Blood_operation_time,Blood_pressure,Blood_pulse,Blood_idea,Stu_id,Blood_error,Blood_all) VALUES(100012,'2021-06-19 23:40:40','120','80','正常',1,'1','1');

INSERT INTO boss(Boss_doctor_id,Boss_operation_time,Boss_conclusion,Boss_all,Stu_id,Boss_error) VALUES('100029','2020-11-13 14:12:19','没问题','1',1,'0');

INSERT INTO chest(Chest_doctor_id,Chest_operation_time,Chest_test,Chest_idea,Chest_all,Stu_id,Chest_error) VALUES(100022,'2021-06-11 15:43:11','体检基本正常','此次检查无明显异常','1',1,'1'),(100023,'2021-06-11 16:36:00','正常','无异样','1',4,'0'),(100024,'2021-05-11 16:36:10','胸部检查正常','身体健康','1',5,'0'),(100022,'2021-06-13 16:31:46','哈哈哈没有任何问题嗷','没毛病啊啊啊','1',6,'0'),(100023,'2021-06-13 16:33:14','检查无异样','无异样','1',7,'0'),(100024,'2021-06-13 16:36:08','胸部正常噻','嗯嗯可以的','1',8,'0'),(100023,'2021-06-13 16:40:55','胸部没问题吖','可以的','1',9,'0');

INSERT INTO doctor(Doctor_id,Doctor_name,Doctor_card,Doctor_department) VALUES(100000,'王洪侠','210124198508162281','眼科'),(100001,'付文文','150429800509501','眼科'),(100002,'史莹','210411198504282942','眼科'),(100003,'白瑞峰','622723198602013412','耳鼻喉科'),(100004,'兴明明','210304198504260488 ','耳鼻喉科'),(100005,'刘颖','210421198403162020','耳鼻喉科'),(100006,'孙雪','210303198412082729 ','口腔科'),(100007,'孙源龙','210302198607160938 ','口腔科'),(100008,'朱振华','211003198407230111','外科'),(100009,'佟琳','210303198508131214','外科'),(100010,'吴春雨','210111198503063012 ','外科'),(100011,'宋林良','210304198503040045','外科'),(100012,'张帆','152801198703025310 ','血压脉搏科'),(100013,'张纯华','411422198412055424 ','血压脉搏科'),(100014,'张泽利','370205197405213513 ','血压脉搏科'),(100015,'张家超','37010219680709292X','血压脉搏科'),(100016,'苏士超','411422198412055424','血压脉搏科'),(100017,'徐宁','21031119850417003x','内科'),(100018,'张志军','511428196305026357','内科'),(100019,'张志新','37142819800508053x','化验科'),(100020,'张忠荣','370283790911703 ','化验科'),(100021,'张子贵','210905197807210546','化验科'),(100022,'张宗敏','370205197405213513 ','胸部放射科'),(100023,'张作芹','370284800121002','胸部放射科'),(100024,'章莉','370727791118517 ','胸部放射科'),(100025,'赵标','370284197901130819','其他科'),(100026,'赵宾','370284801127364 ','体检负责医师'),(100027,'赵伯佳','370203800901162','体检负责医师'),(100028,'赵春风','372922198012224773','体检负责医师'),(100029,'赵丹','370722197812222517 ','医院领导');

INSERT INTO ebh(EBH_doctor_id,EBH_operation_time,EBH_throat,EBH_stammer,EBH_idea,EBH_all,Stu_id,EBH_error,EBH_hearing_left,EBH_hearing_right,EBH_ear_disease,EBH_smell_sense,EBH_nose_disease) VALUES('100003','2021-06-13 21:45:04','无异样','无异样','没啥毛病','1',1,'1',20.0,20.0,'无','无','暂无');

INSERT INTO eye(Eye_insight_left,Eye_insight_right,Eye_insight_left_correct,Eye_insight_right_correct,Eye_other,Eye_red,Eye_green,Eye_purple,Eye_blue,Eye_yellow,Eye_idea,Eye_all,Eye_error,Stu_id,Eye_doctor_id,Eye_operation_time) VALUES(5.0,5.0,3.0,3.0,'暂无','1','0','1','1','0','无异样','1','0',1,'100001','2021-06-13 21:47:16');

INSERT INTO internal(Internal_doctor_id,Internal_operation_time,Internal_nutrition,Internal_nerve_spirit,Internal_lung_respiratory,Internal_heart_blood,Internal_liver,Internal_spleen,Internal_other,Internal_idea,Stu_id,Internal_all,Internal_error) VALUES('100017','2021-06-13 21:48:53','营养正常','精神正常','肺及呼吸道正常','心脏及血管正常','正常','正常','无其它内科疾病','无异样',1,'1','0');

INSERT INTO manage(Manage_doctor_id,Manage_operation_time,Manage_conclusion,Manage_all,Stu_id,Manage_error) VALUES(100027,'2021-06-15 09:53:44','检查无异样','1',1,'1');

INSERT INTO other(Other_doctor_id,Other_operation_time,Other_test,Other_idea,Other_all,Stu_id,Other_error) VALUES(100025,'2020-09-03 09:54:12','正常','无异样','1',1,'0');

INSERT INTO stu_test(Stu_id,Eye_idea,Eye_doctor_name,Eye_doctor_id,Eye_operation_time,EBH_idea,EBH_doctor_name,EBH_doctor_id,EBH_operation_time,Tooth_idea,Tooth_doctor_id,Tooth_doctor_name,Tooth_operation_time,Surgery_idea,Surgery_doctor_id,Surgery_doctor_name,Surgery_operation_time,Blood_idea,Blood_doctor_id,Blood_doctor_name,Blood_operation_time,Internal_idea,Internal_doctor_id,Internal_doctor_name,Internal_operation_time,Assay_idea,Assay_doctor_id,Assay_doctor_name,Assay_operation_time,Chest_idea,Chest_doctor_id,Chest_doctor_name,Chest_operation_time,Other_idea,Other_doctor_id,Other_doctor_name,Other_operation_time,Manage_conclusion,Manage_doctor_id,Manage_doctor_name,Manage_operation_time,Stu_test_count) VALUES(1,'眼科',NULL,NULL,NULL,'耳鼻喉科',NULL,NULL,NULL,'口腔科',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'没问题嗷嗷',100001,'付文文','2021-06-16 15:19:58',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10),(5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'没啥问题的的',100002,'史莹',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(6,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'无异样嗷嗷',100006,'孙雪','2021-06-13 16:27:03','没毛病啊啊啊',100010,'吴春雨','2021-06-13 16:31:46',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'没问题',100005,'刘颖','2021-06-13 16:33:40','无异样',100009,'佟琳','2021-06-13 16:33:14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'嗯嗯',100005,'刘颖','2021-06-13 16:35:38','嗯嗯可以的',100009,'佟琳','2021-06-13 16:36:08',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'哈哈哈哈',100004,'兴明明','2021-06-13 16:40:24','可以的',100009,'佟琳','2021-06-13 16:40:55',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'很好',100015,'张家超','2021-06-16 15:08:21',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'很好',100015,'张家超','2021-06-16 15:12:53',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'很好',100015,'张家超','2021-06-16 15:12:26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'很好啊',100015,'张家超','2021-06-16 15:15:47',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);

INSERT INTO student(Stu_name,Stu_id,Stu_card,Stu_college,Stu_major,Stu_class,Stu_num,Stu_sex,Stu_birth,Stu_age,Stu_education,Stu_nation,Stu_profession,Stu_native,Stu_location,Stu_graduate_job,Stu_anamnesis,Stu_test_all) VALUES('孔霆麟',1,'230882198906222898','软件学院','软件工程','1班','2018171931269','男','2020-06-08',20,'本科','汉','学生','广东','广东省廉江市车板镇大坝村委员会文头岭村96号\n','四川大学','无','1'),('劳家博',2,'371402197508174273','软件学院','软件工程','1班','2018967931269','男','2020-06-01',20,'本科','汉','学生','四川','四川省广安市广安区协兴镇果山村7组16-1号\n','四川大学','无','0'),('张宏斌',3,'340822198002250772','软件学院','软件工程','1班','2018171856979','女','2020-06-02',20,'本科','汉','学生','湖南','湖南省沅江市阳罗洲镇兴隆村十一村民组240\n','四川大学','无','0'),('林晓洁',4,'230127199105261557','软件学院','软件工程','1班','2018171931269','女','2020-04-15',20,'本科','汉','学生','广东','广东省茂名市七逢镇柏坡村1号\n','四川大学','无','0'),('聂诗军',5,'130133198712084731','软件学院','软件工程','1班','2018171657821','男','2020-12-09',20,'本科','汉','学生','广东','广东省佛山市沧江路110号3座305\n','四川大学','无','0'),('郜学来',6,'140424199005237635','软件学院','软件工程','1班','2018171931299','男','2020-06-05',20,'本科','汉','学生','河南','河南省光山县城关镇文化街破堰\n','四川大学','无','0'),('叶志雄',7,'330226197203032890','软件学院','软件工程','1班','2018171953169','男','2014-06-11',20,'本科','满','学生','河南','河南省息县白土店乡王大围孜村孙庄\n','四川大学','无','0'),('谭平升',8,'37010319840920387X','软件学院','软件工程','1班','2018171931269','男','2013-06-11',21,'本科','汉','学生','湖南','湖南省邵东县杨桥镇前丰村兴德组9号\n','四川大学','无','0'),('高麟傑',9,'230716198402299075','软件学院','软件工程','1班','2018171931111','女','2009-06-20',19,'本科','汉','学生','广东','广东省化州市下郭街道办坡尾旺跟岭村41号\n','四川大学','无','0'),('华子纲',10,'340822199301200235','软件学院','软件工程','1班','2018746981269','女','2007-03-16',20,'本科','汉','学生','广东','广东省惠来县隆江镇见龙管区见中井头东三直巷17号之一\n','四川大学','无','0'),('柴小宏',11,'230716197509268477','软件学院','软件工程','1班','2018171931269','男','2010-06-16',20,'本科','汉','学生','广东','广东省陆丰市河东镇欧厝村委会欧厝二村\n','四川大学','无','0'),('毛诗源',12,'411402198001142333','软件学院','软件工程','2班','2018171931269','男','2011-06-22',20,'本科','汉','学生','湖南','湖南省常宁市宜阳镇裕民电站\n','四川大学','无','0'),('高子桐',13,'13022419850331136X','软件学院','软件工程','2班','2018732931269','女','2009-06-02',21,'本科','汉','学生','湖南','湖南省东安县南桥镇上塘村6组-37\n','四川大学','无','0'),('吴琼珠',14,'130684198011058087','软件学院','软件工程','2班','2018171974269','男','2021-06-01',21,'本科','汉','学生','广东','广东省普宁市高埔镇山下村80号\n','四川大学','无','0'),('温小雨',15,'130526198507215964','软件学院','软件工程','2班','2018171931269','男','2012-06-25',21,'本科','汉','学生','广西','广西桂林市秀峰区丽中路22号2单元7-1\n','四川大学','无','0'),('胡雪婷',16,'13013119860128026X','软件学院','软件工程','2班','2018171931269','男','2004-06-01',21,'本科','汉','学生','湖南','湖南省洞口县又兰镇凤凰村8组12号\n','四川大学','无','0'),('方燕华',17,'231085199010090184','软件学院','软件工程','2班','2018171931269','男','2008-06-21',22,'本科','汉','学生','广东','广东广州市越秀区\r\n中山六路238号越秀新都会大厦','四川大学','无','0'),('赵汀婷',18,'330902197508012927','软件学院','软件工程','2班','2018000251269','男','2008-06-27',19,'本科','汉','学生','广东','广东广州市荔湾区\r\n芳村大道东200号1850创意园72栋A','四川大学','无','0'),('董文洁',19,'370983199104088348','软件学院','软件工程','3班','2018171931269','男','2008-06-20',22,'本科','汉','学生','四川','四川省合江县实录乡慈竹林村六社103号\n','四川大学','无','0'),('翟蕾蕾',20,'330283198509065769','软件学院','软件工程','3班','2018171931269','男','2009-06-26',21,'本科','汉','学生','四川','四川省广安市广安区协兴镇果山村7组16-1号\n','四川大学','无','0');

INSERT INTO surgery(Surgery_doctor_id,Surgery_operation_time,Surgery_height,Surgery_waistline,Surgery_weight,Surgery_skin,Surgery_lymph,Surgery_thyroid,Surgery_spine,Surgery_fours,Surgery_joint,Surgery_flat_extension_foot,Surgery_other,Surgery_idea,Surgery_all,Surgery_error,Stu_id) VALUES('100011','2021-06-15 09:54:56',168.0,702.0,60.0,'正常','正常','正常','正常','正常','正常','正常','无','无异样','1','1',1),('1','2021-06-16 18:43:45',1.0,1.0,1.0,'正常','未触及','正常','正常','正常','正常',NULL,'无','正常','1','0',2);


INSERT INTO sys_dept(id,dept_no,name,pid,status,relation_code,dept_manager_id,manager_name,phone,create_time,update_time,deleted) VALUES('1','D000001','总公司','0',1,'D000001',NULL,'小李','13888888888','2019-11-07 22:43:33',NULL,1);

INSERT INTO sys_dict(id,name,remark,create_time) VALUES('1255790029680242690','sex','性别','2020-04-30 17:24:09'),('1282504369620430849','content_type','文章类型略略略','2020-07-13 10:37:24');

INSERT INTO sys_dict_detail(id,label,value,sort,dict_id,create_time) VALUES('1255790073535885314','男','1',1,'1255790029680242690','2020-04-30 17:24:19'),('1255790100115189761','女','2',2,'1255790029680242690','2020-04-30 17:24:25'),('1282504475715350530','诗词','1',1,'1282504369620430849','2020-07-13 10:37:49'),('1282504651729317889','散文','2',2,'1282504369620430849','2020-07-13 10:38:31'),('1282846022950842369','剧本','3',3,'1282504369620430849','2020-07-14 09:15:01');


INSERT INTO sys_job(id,bean_name,params,cron_expression,status,remark,create_time) VALUES('1252884495040782337','testTask','1','0 */1 * * * ?',0,'1','2020-04-22 16:58:35');

INSERT INTO sys_job_log(id,job_id,bean_name,params,status,error,times,create_time) VALUES('1402889769457369089','1252884495040782337','testTask','1',0,NULL,1,'2021-06-10 15:26:00'),('1402890020629069825','1252884495040782337','testTask','1',0,NULL,1,'2021-06-10 15:27:00');

INSERT INTO sys_log(id,user_id,username,operation,time,method,params,ip,create_time) VALUES('1401359311559467009','1','admin','系统操作日志管理-分页查询系统操作日志',8,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:04:31'),('1401359698697920513','1','admin','角色管理-分页获取角色信息',13,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:06:03'),('1401359703647199234','1','admin','菜单权限管理-获取所有菜单权限',83,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-06 10:06:04'),('1401359706297999362','1','admin','角色管理-分页获取角色信息',3,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:06:05'),('1401367078240325633','1','admin','系统操作日志管理-分页查询系统操作日志',11,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:22'),('1401367088180826113','1','admin','系统操作日志管理-分页查询系统操作日志',7,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:25'),('1401367097567678465','1','admin','系统操作日志管理-分页查询系统操作日志',6,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:27'),('1401367103116742658','1','admin','机构管理-获取所有组织机构',14,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.177','2021-06-06 10:35:28'),('1401367111853477890','1','admin','机构管理-树型组织列表',11,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-06 10:35:30'),('1401367111920586754','1','admin','用户管理-分页获取用户列表',21,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:30'),('1401367117020860417','1','admin','角色管理-分页获取角色信息',12,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:32'),('1401367124742574081','1','admin','角色管理-查询角色详情',113,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.1.177','2021-06-06 10:35:33'),('1401367132531396609','1','admin','机构管理-树型组织列表',2,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-06 10:35:35'),('1401367132531396610','1','admin','用户管理-分页获取用户列表',12,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:35'),('1401367165200830465','1','admin','系统操作日志管理-分页查询系统操作日志',6,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:43'),('1401367194233802753','1','admin','机构管理-树型组织列表',1,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-06 10:35:50'),('1401367194233802754','1','admin','用户管理-分页获取用户列表',7,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:50'),('1401367197366947842','1','admin','角色管理-分页获取角色信息',6,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-06 10:35:51'),('1401367201452199937','1','admin','菜单权限管理-获取所有菜单权限',80,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-06 10:35:52'),('1401513871066345473','1','admin','机构管理-获取所有组织机构',8,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.0.135','2021-06-06 20:18:40'),('1401513876506357762','1','admin','用户管理-分页获取用户列表',11,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.0.135','2021-06-06 20:18:42'),('1401513876506357763','1','admin','机构管理-树型组织列表',13,'com.company.project.controller.DeptController.getTree()','[null]','192.168.0.135','2021-06-06 20:18:42'),('1401513882814590977','1','admin','角色管理-分页获取角色信息',8,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.135','2021-06-06 20:18:43'),('1401513889131212801','1','admin','菜单权限管理-获取所有菜单权限',79,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.135','2021-06-06 20:18:45'),('1401513900397113346','1','admin','系统操作日志管理-分页查询系统操作日志',9,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.0.135','2021-06-06 20:18:47'),('1401513956147802114','1','admin','系统操作日志管理-分页查询系统操作日志',6,'com.company.project.controller.SysLogController.pageInfo()','[{}]','192.168.0.135','2021-06-06 20:19:01'),('1401513983771488258','1','admin','代码生成-代码生成',107,'com.company.project.controller.SysGeneratorController.code()',NULL,'192.168.0.135','2021-06-06 20:19:07'),('1401513984299970561','1','admin','代码生成-代码生成',14,'com.company.project.controller.SysGeneratorController.code()',NULL,'192.168.0.135','2021-06-06 20:19:07'),('1401514175161774081','1','admin','代码生成-代码生成',10,'com.company.project.controller.SysGeneratorController.code()',NULL,'192.168.0.135','2021-06-06 20:19:53'),('1401514175560232961','1','admin','代码生成-代码生成',12,'com.company.project.controller.SysGeneratorController.code()',NULL,'192.168.0.135','2021-06-06 20:19:53'),('1401533483879321602','1','admin','角色管理-分页获取角色信息',34,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.135','2021-06-06 21:36:36'),('1401533559167078402','1','admin','菜单权限管理-获取所有菜单权限',90,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.135','2021-06-06 21:36:54'),('1401533587768037377','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.135','2021-06-06 21:37:01'),('1401533754726502402','1','admin','机构管理-树型组织列表',12,'com.company.project.controller.DeptController.getTree()','[null]','192.168.0.135','2021-06-06 21:37:41'),('1401533754726502403','1','admin','用户管理-分页获取用户列表',12,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.0.135','2021-06-06 21:37:41'),('1401533765266788353','1','admin','角色管理-分页获取角色信息',6,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.135','2021-06-06 21:37:44'),('1401533774624280577','1','admin','角色管理-查询角色详情',80,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.0.135','2021-06-06 21:37:46'),('1401533845172473857','1','admin','角色管理-分页获取角色信息',6,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.135','2021-06-06 21:38:03'),('1401746765125611521','1','admin','角色管理-分页获取角色信息',38,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.137.1','2021-06-07 11:44:07'),('1401746793479106562','1','admin','机构管理-树型组织列表',14,'com.company.project.controller.DeptController.getTree()','[null]','192.168.137.1','2021-06-07 11:44:13'),('1401746793479106563','1','admin','用户管理-分页获取用户列表',16,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.137.1','2021-06-07 11:44:13'),('1401746884730384385','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.137.1','2021-06-07 11:44:35'),('1401746898131185665','1','admin','角色管理-分页获取角色信息',4,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.137.1','2021-06-07 11:44:38'),('1401747276637691905','1','admin','角色管理-分页获取角色信息',32,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.137.1','2021-06-07 11:46:09'),('1401747436172238850','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.137.1','2021-06-07 11:46:47'),('1401789602669342721','1','admin','机构管理-获取所有组织机构',9,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.177','2021-06-07 14:34:20'),('1401789632650227713','1','admin','机构管理-树型组织列表',14,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-07 14:34:27'),('1401789632784445442','1','admin','用户管理-分页获取用户列表',41,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:34:27'),('1401789638228652034','1','admin','角色管理-分页获取角色信息',8,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:34:28'),('1401789643769327618','1','admin','菜单权限管理-获取所有菜单权限',85,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-07 14:34:30'),('1401790173719638018','1','admin','角色管理-分页获取角色信息',4,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:36:36'),('1401790179990122497','1','admin','机构管理-树型组织列表',6,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-07 14:36:38'),('1401790179990122498','1','admin','用户管理-分页获取用户列表',7,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:36:38'),('1401790185736318978','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:36:39'),('1401790264136249346','1','admin','机构管理-树型组织列表',2,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-07 14:36:58'),('1401790264203358210','1','admin','用户管理-分页获取用户列表',7,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:36:58'),('1401790269211357185','1','admin','角色管理-分页获取角色信息',3,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:36:59'),('1401791088690216961','1','admin','角色管理-分页获取角色信息',28,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:40:14'),('1401792176441126913','1','admin','角色管理-分页获取角色信息',27,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:44:34'),('1401793318797570049','1','admin','角色管理-分页获取角色信息',3,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:49:06'),('1401793902934061058','1','admin','角色管理-分页获取角色信息',28,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:51:25'),('1401794925077491714','1','admin','菜单权限管理-获取所有菜单权限',79,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-07 14:55:29'),('1401794932325249025','1','admin','用户管理-分页获取用户列表',11,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-07 14:55:31'),('1401794932325249026','1','admin','机构管理-树型组织列表',10,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-07 14:55:31'),('1401798685073940482','1','admin','角色管理-分页获取角色信息',35,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 15:10:25'),('1401798807476314113','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 15:10:55'),('1401802307786559490','1','admin','角色管理-分页获取角色信息',38,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 15:24:49'),('1401817362875850754','1','admin','角色管理-分页获取角色信息',39,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 16:24:38'),('1401817368580104193','1','admin','菜单权限管理-获取所有菜单权限',92,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-07 16:24:40'),('1401817372392726529','1','admin','角色管理-分页获取角色信息',7,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.1.177','2021-06-07 16:24:41'),('1401839898022690817','1','admin','角色管理-分页获取角色信息',42,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.0.146','2021-06-07 17:54:11'),('1401839900790931458','1','admin','菜单权限管理-获取所有菜单权限',84,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.146','2021-06-07 17:54:12'),('1401839904263815170','1','admin','角色管理-分页获取角色信息',6,'com.company.project.controller.StudentController.pageInfo()','[{}]','192.168.0.146','2021-06-07 17:54:13'),('1401864202644152322','1','admin','菜单权限管理-获取所有菜单权限',90,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.147','2021-06-07 19:30:46'),('1401864213763252225','1','admin','菜单权限管理-获取所有目录菜单树',75,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.0.147','2021-06-07 19:30:49'),('1401864432525565955','1','admin','菜单权限管理-新增菜单权限',9,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623065500753,\"deleted\":1,\"icon\":\"layui-icon-survey\",\"id\":\"1401864432525565954\",\"name\":\"体检系统\",\"orderNum\":123,\"perms\":\"\",\"pid\":\"0\",\"pidName\":\"默认顶级菜单\",\"status\":1,\"target\":\"_self\",\"type\":1,\"updateTime\":1623065500753,\"url\":\"\"}]','192.168.0.147','2021-06-07 19:31:41'),('1401864432928219137','1','admin','菜单权限管理-获取所有菜单权限',70,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.147','2021-06-07 19:31:41'),('1401864444596772866','1','admin','菜单权限管理-获取所有目录菜单树',64,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.0.147','2021-06-07 19:31:44'),('1401864747605876737','1','admin','菜单权限管理-新增菜单权限',12,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623065575858,\"deleted\":1,\"icon\":\"\",\"id\":\"1401864747538767874\",\"name\":\"化验科体检\",\"orderNum\":124,\"perms\":\"\",\"pid\":\"1401864432525565954\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623065575858,\"url\":\"/index/surgery\"}]','192.168.0.147','2021-06-07 19:32:56'),('1401864747945615361','1','admin','菜单权限管理-获取所有菜单权限',73,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.147','2021-06-07 19:32:56'),('1401864758926303234','1','admin','角色管理-分页获取角色信息',10,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 19:32:59'),('1401864765322616833','1','admin','菜单权限管理-获取所有菜单权限',64,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.147','2021-06-07 19:33:00'),('1401864785425915906','1','admin','菜单权限管理-获取所有菜单权限',63,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.147','2021-06-07 19:33:05'),('1401864786898116609','1','admin','角色管理-分页获取角色信息',6,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 19:33:05'),('1401864803855687682','1','admin','角色管理-分页获取角色信息',6,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 19:33:09'),('1401865189517754369','1','admin','菜单权限管理-获取所有菜单权限',99,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.147','2021-06-07 19:34:41'),('1401865200594911234','1','admin','角色管理-分页获取角色信息',35,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.147','2021-06-07 19:34:44'),('1401865371386953730','1','admin','角色管理-分页获取角色信息',37,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.147','2021-06-07 19:35:25'),('1401865386083794945','1','admin','角色管理-查询角色详情',106,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.0.147','2021-06-07 19:35:28'),('1401865402902953985','1','admin','角色管理-更新角色信息',57,'com.company.project.controller.RoleController.updateDept()','[{\"description\":\"拥有所有权限-不能删除\",\"id\":\"1\",\"name\":\"超级管理员\",\"permissions\":[\"51\",\"11\",\"17\",\"26\",\"40\",\"43\",\"44\",\"53\",\"3\",\"19\",\"36\",\"13\",\"39\",\"24\",\"10\",\"23\",\"25\",\"42\",\"52\",\"56\",\"57\",\"41\",\"5\",\"9\",\"12\",\"22\",\"38\",\"54\",\"15\",\"1\",\"4\",\"16\",\"20\",\"45\",\"46\",\"47\",\"48\",\"49\",\"8\",\"7\",\"58\",\"1401864432525565954\",\"1401864747538767874\",\"21\",\"50\",\"2\",\"6\"],\"status\":1,\"updateTime\":1623065732065}]','192.168.0.147','2021-06-07 19:35:32'),('1401865403104280578','1','admin','角色管理-分页获取角色信息',3,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.0.147','2021-06-07 19:35:32'),('1401866731016794114','1','admin','角色管理-分页获取角色信息',12,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 19:40:49'),('1401866953558167554','1','admin','角色管理-分页获取角色信息',9,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 19:41:42'),('1401867488587694081','1','admin','机构管理-树型组织列表',14,'com.company.project.controller.DeptController.getTree()','[null]','192.168.0.147','2021-06-07 19:43:49'),('1401867488700940289','1','admin','用户管理-分页获取用户列表',50,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.0.147','2021-06-07 19:43:49'),('1401867492349984770','1','admin','角色管理-分页获取角色信息',11,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.147','2021-06-07 19:43:50'),('1401867498209427457','1','admin','角色管理-分页获取角色信息',12,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 19:43:52'),('1401871079524364289','1','admin','角色管理-分页获取角色信息',39,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.147','2021-06-07 19:58:06'),('1401871356570726401','1','admin','角色管理-分页获取角色信息',9,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.147','2021-06-07 19:59:12'),('1401871571910434818','1','admin','角色管理-分页获取角色信息',10,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.0.147','2021-06-07 20:00:03'),('1401894809839382529','1','admin','角色管理-分页获取角色信息',7,'com.company.project.controller.StudentController.ShowList()',NULL,'169.254.42.84','2021-06-07 21:32:23'),('1401896696227684354','1','admin','角色管理-分页获取角色信息',9,'com.company.project.controller.StudentController.ShowList()',NULL,'169.254.42.84','2021-06-07 21:39:53'),('1402096864561258497','1','admin','角色管理-分页获取角色信息',24,'com.company.project.controller.StudentController.ShowList()',NULL,'192.168.137.1','2021-06-08 10:55:17'),('1402101223739117569','1','admin','菜单权限管理-获取所有菜单权限',83,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.137.1','2021-06-08 11:12:36'),('1402101278390898690','1','admin','菜单权限管理-获取所有目录菜单树',72,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"1401864747538767874\"]','192.168.137.1','2021-06-08 11:12:49'),('1402101298557112322','1','admin','菜单权限管理-更新菜单权限',15,'com.company.project.controller.PermissionController.updatePermission()','[{\"icon\":\"\",\"id\":\"1401864747538767874\",\"name\":\"化验科体检\",\"orderNum\":124,\"perms\":\"\",\"pid\":\"1401864432525565954\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623121974017,\"url\":\"/index/assay\"}]','192.168.137.1','2021-06-08 11:12:54'),('1402101298959765506','1','admin','菜单权限管理-获取所有菜单权限',67,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.137.1','2021-06-08 11:12:54'),('1402101765345447938','1','admin','化验科体检-分页获取待化验学生信息',18,'com.company.project.controller.AssayController.ShowList()',NULL,'192.168.137.1','2021-06-08 11:14:45'),('1402149204267167746','1','admin','角色管理-分页获取角色信息',39,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.137.1','2021-06-08 14:23:16'),('1402149214429966338','1','admin','机构管理-树型组织列表',14,'com.company.project.controller.DeptController.getTree()','[null]','192.168.137.1','2021-06-08 14:23:18'),('1402149214429966339','1','admin','用户管理-分页获取用户列表',16,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.137.1','2021-06-08 14:23:18'),('1402149217214984194','1','admin','菜单权限管理-获取所有菜单权限',93,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.137.1','2021-06-08 14:23:19'),('1402149240896024577','1','admin','机构管理-树型组织列表',5,'com.company.project.controller.DeptController.getTree()','[null]','192.168.137.1','2021-06-08 14:23:24'),('1402149240896024578','1','admin','用户管理-分页获取用户列表',9,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.137.1','2021-06-08 14:23:24'),('1402149243437772801','1','admin','机构管理-获取所有组织机构',5,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.137.1','2021-06-08 14:23:25'),('1402149246734495746','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.137.1','2021-06-08 14:23:26'),('1402149249595011074','1','admin','菜单权限管理-获取所有菜单权限',76,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.137.1','2021-06-08 14:23:26'),('1402149290153930753','1','admin','菜单权限管理-获取所有目录菜单树',70,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"1401864747538767874\"]','192.168.137.1','2021-06-08 14:23:36'),('1402179403037908993','1','admin','角色管理-分页获取角色信息',38,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.143','2021-06-08 16:23:16'),('1402222536043896833','1','admin','角色管理-分页获取角色信息',32,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-08 19:14:39'),('1402880700422643713','1','admin','机构管理-获取所有组织机构',8,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.177','2021-06-10 14:49:58'),('1402880710602219522','1','admin','机构管理-获取所有组织机构',6,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.177','2021-06-10 14:50:00'),('1402880728222490626','1','admin','机构管理-获取所有组织机构',5,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.177','2021-06-10 14:50:05'),('1402885063267340289','1','admin','角色管理-分页获取角色信息',105,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-10 15:07:18'),('1402885075300798466','1','admin','菜单权限管理-获取所有菜单权限',252,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-10 15:07:21'),('1402885084465352705','1','admin','角色管理-分页获取角色信息',12,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-10 15:07:23'),('1402885095571869697','1','admin','角色管理-查询角色详情',164,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.1.177','2021-06-10 15:07:26'),('1402885193999601665','1','admin','菜单权限管理-获取所有菜单权限',173,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-10 15:07:49'),('1402888455514550274',NULL,NULL,'用户管理-退出',8,'com.company.project.controller.UserController.logout()',NULL,'192.168.1.65','2021-06-10 15:20:47'),('1403177747138740226','1','admin','角色管理-分页获取角色信息',39,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.127','2021-06-11 10:30:19'),('1403177757242818561','1','admin','机构管理-树型组织列表',14,'com.company.project.controller.DeptController.getTree()','[null]','192.168.0.127','2021-06-11 10:30:22'),('1403177757242818562','1','admin','用户管理-分页获取用户列表',17,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.0.127','2021-06-11 10:30:22'),('1403307523254005761','1','admin','角色管理-分页获取角色信息',36,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-11 19:06:00'),('1403307529943920641','1','admin','机构管理-树型组织列表',15,'com.company.project.controller.DeptController.getTree()','[null]','192.168.1.177','2021-06-11 19:06:02'),('1403307530006835202','1','admin','用户管理-分页获取用户列表',16,'com.company.project.controller.UserController.pageInfo()','[{}]','192.168.1.177','2021-06-11 19:06:02'),('1403307807191609346','1','admin','菜单权限管理-获取所有菜单权限',82,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-11 19:07:08'),('1403527656287289346','1','admin','代码生成-代码生成',106,'com.company.project.controller.SysGeneratorController.code()',NULL,'192.168.0.127','2021-06-12 09:40:44'),('1403527658074062849','1','admin','代码生成-代码生成',14,'com.company.project.controller.SysGeneratorController.code()',NULL,'192.168.0.127','2021-06-12 09:40:45'),('1403541118015102978','1','admin','角色管理-分页获取角色信息',34,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.0.127','2021-06-12 10:34:14'),('1403541128194678785','1','admin','角色管理-查询角色详情',103,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.0.127','2021-06-12 10:34:16'),('1403541142312706050','1','admin','角色管理-更新角色信息',64,'com.company.project.controller.RoleController.updateDept()','[{\"description\":\"拥有所有权限-不能删除\",\"id\":\"1\",\"name\":\"超级管理员\",\"permissions\":[\"51\",\"11\",\"17\",\"26\",\"40\",\"43\",\"44\",\"53\",\"3\",\"19\",\"36\",\"13\",\"39\",\"24\",\"10\",\"23\",\"25\",\"42\",\"52\",\"56\",\"57\",\"41\",\"5\",\"9\",\"12\",\"22\",\"38\",\"54\",\"15\",\"1\",\"4\",\"16\",\"20\",\"45\",\"46\",\"47\",\"48\",\"49\",\"8\",\"7\",\"58\",\"1401719419462430722\",\"1401721476860817409\",\"1401751260848394242\",\"1402149299920535554\",\"1403280343000367106\",\"1403341363181912065\",\"1403533694707412994\",\"21\",\"50\",\"2\",\"6\"],\"status\":1,\"updateTime\":1623465259469}]','192.168.0.127','2021-06-12 10:34:20'),('1403541142514032642','1','admin','角色管理-分页获取角色信息',3,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.0.127','2021-06-12 10:34:20'),('1403625805714620418','1','admin','菜单权限管理-获取所有菜单权限',107,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.0.127','2021-06-12 16:10:45'),('1404038460521390081','1','admin','菜单权限管理-获取所有菜单权限',108,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-13 19:30:29'),('1404038531572899841','1','admin','角色管理-分页获取角色信息',35,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-13 19:30:46'),('1404038544394887169','1','admin','角色管理-查询角色详情',86,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.1.177','2021-06-13 19:30:49'),('1404038593166254082','1','admin','菜单权限管理-获取所有菜单权限',73,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-13 19:31:01'),('1404038599688396802','1','admin','菜单权限管理-获取所有目录菜单树',69,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.177','2021-06-13 19:31:03'),('1404038916555481090','1','admin','菜单权限管理-新增菜单权限',10,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623583938142,\"deleted\":1,\"icon\":\"\",\"id\":\"1404038916488372226\",\"name\":\"检查医院\",\"orderNum\":128,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623583938142,\"url\":\"/index/boss\"}]','192.168.1.177','2021-06-13 19:32:18'),('1404038916949745665','1','admin','菜单权限管理-获取所有菜单权限',76,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-13 19:32:18'),('1404038928056262658','1','admin','角色管理-分页获取角色信息',5,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-13 19:32:21'),('1404038933341085698','1','admin','角色管理-查询角色详情',62,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.1.177','2021-06-13 19:32:22'),('1404038944997056513','1','admin','角色管理-更新角色信息',60,'com.company.project.controller.RoleController.updateDept()','[{\"description\":\"拥有所有权限-不能删除\",\"id\":\"1\",\"name\":\"超级管理员\",\"permissions\":[\"51\",\"11\",\"17\",\"26\",\"40\",\"43\",\"44\",\"53\",\"3\",\"19\",\"36\",\"13\",\"39\",\"24\",\"10\",\"23\",\"25\",\"42\",\"52\",\"56\",\"57\",\"41\",\"5\",\"9\",\"12\",\"22\",\"38\",\"54\",\"15\",\"1\",\"4\",\"16\",\"20\",\"45\",\"46\",\"47\",\"48\",\"49\",\"8\",\"7\",\"58\",\"1401719419462430722\",\"1401721476860817409\",\"1401751260848394242\",\"1402149299920535554\",\"1403280343000367106\",\"1403341363181912065\",\"1403533694707412994\",\"1404038916488372226\",\"21\",\"50\",\"2\",\"6\"],\"status\":1,\"updateTime\":1623583944887}]','192.168.1.177','2021-06-13 19:32:25'),('1404038945257103361','1','admin','角色管理-分页获取角色信息',4,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.1.177','2021-06-13 19:32:25'),('1404654957295902722','1','admin','用户管理-分页获取用户列表',31,'com.company.project.controller.UserAccountController.pageInfo()','[{}]','192.168.137.1','2021-06-15 12:20:14'),('1404697562961690626','1','admin','菜单权限管理-获取所有菜单权限',110,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-15 15:09:32'),('1404697695677857793','1','admin','菜单权限管理-获取所有目录菜单树',80,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.177','2021-06-15 15:10:03'),('1404697926515572737','1','admin','菜单权限管理-新增菜单权限',8,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623741058361,\"deleted\":1,\"icon\":\"\",\"id\":\"1404697926452658177\",\"name\":\"导出PDF\",\"orderNum\":129,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623741058361,\"url\":\"/index/PDF\"}]','192.168.1.177','2021-06-15 15:10:58'),('1404697926909837314','1','admin','菜单权限管理-获取所有菜单权限',77,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-15 15:10:58'),('1404697945327026178','1','admin','角色管理-分页获取角色信息',37,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.177','2021-06-15 15:11:03'),('1404697952788692993','1','admin','角色管理-查询角色详情',77,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.1.177','2021-06-15 15:11:05'),('1404697965958807554','1','admin','角色管理-更新角色信息',54,'com.company.project.controller.RoleController.updateDept()','[{\"description\":\"拥有所有权限-不能删除\",\"id\":\"1\",\"name\":\"超级管理员\",\"permissions\":[\"51\",\"11\",\"17\",\"26\",\"40\",\"43\",\"44\",\"53\",\"3\",\"19\",\"36\",\"13\",\"39\",\"24\",\"10\",\"23\",\"25\",\"42\",\"52\",\"56\",\"57\",\"41\",\"5\",\"9\",\"12\",\"22\",\"38\",\"54\",\"15\",\"1\",\"4\",\"16\",\"20\",\"45\",\"46\",\"47\",\"48\",\"49\",\"8\",\"7\",\"58\",\"1401719419462430722\",\"1401721476860817409\",\"1401751260848394242\",\"1402149299920535554\",\"1403280343000367106\",\"1403341363181912065\",\"1403533694707412994\",\"1404038916488372226\",\"1404697926452658177\",\"21\",\"50\",\"2\",\"6\"],\"status\":1,\"updateTime\":1623741067726}]','192.168.1.177','2021-06-15 15:11:08'),('1404697966160134145','1','admin','角色管理-分页获取角色信息',4,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.1.177','2021-06-15 15:11:08'),('1404698066861178881','1','admin','菜单权限管理-获取所有菜单权限',64,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-15 15:11:32'),('1404698086930923522','1','admin','菜单权限管理-获取所有目录菜单树',64,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"1404697926452658177\"]','192.168.1.177','2021-06-15 15:11:37'),('1404698105947897858','1','admin','菜单权限管理-更新菜单权限',9,'com.company.project.controller.PermissionController.updatePermission()','[{\"icon\":\"\",\"id\":\"1404697926452658177\",\"name\":\"导出PDF\",\"orderNum\":129,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623741101141,\"url\":\"/index/pdf\"}]','192.168.1.177','2021-06-15 15:11:41'),('1404698106283442178','1','admin','菜单权限管理-获取所有菜单权限',60,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.177','2021-06-15 15:11:41'),('1404728671073017857','1','admin','菜单权限管理-获取所有菜单权限',104,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.137.1','2021-06-15 17:13:08'),('1405114710408007682','1','admin','菜单权限管理-获取所有菜单权限',358,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:47:07'),('1405114727671762945','1','admin','菜单权限管理-获取所有目录菜单树',317,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.60','2021-06-16 18:47:12'),('1405114767710588929','1','admin','菜单权限管理-获取所有目录菜单树',209,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.60','2021-06-16 18:47:21'),('1405114867887345665','1','admin','菜单权限管理-新增菜单权限',23,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623840464911,\"deleted\":1,\"icon\":\"\",\"id\":\"1405114867765710849\",\"name\":\"耳鼻喉科体检\",\"orderNum\":1,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623840464911,\"url\":\"/index/ebh\"}]','192.168.1.60','2021-06-16 18:47:45'),('1405114868680069121','1','admin','菜单权限管理-获取所有菜单权限',152,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:47:45'),('1405114903832530946','1','admin','菜单权限管理-获取所有目录菜单树',137,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.60','2021-06-16 18:47:54'),('1405114993745825793','1','admin','菜单权限管理-新增菜单权限',17,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623840494926,\"deleted\":1,\"icon\":\"\",\"id\":\"1405114993687105537\",\"name\":\"内科体检\",\"orderNum\":2,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623840494926,\"url\":\"/index/internal\"}]','192.168.1.60','2021-06-16 18:48:15'),('1405114994614046722','1','admin','菜单权限管理-获取所有菜单权限',161,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:48:15'),('1405115228954005505','1','admin','菜单权限管理-获取所有目录菜单树',163,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.60','2021-06-16 18:49:11'),('1405115408893841410','1','admin','菜单权限管理-新增菜单权限',17,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623840593903,\"deleted\":1,\"icon\":\"\",\"id\":\"1405115408763817986\",\"name\":\"血压脉搏科体检\",\"orderNum\":3,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623840593903,\"url\":\"/index/blood\"}]','192.168.1.60','2021-06-16 18:49:54'),('1405115409560735746','1','admin','菜单权限管理-获取所有菜单权限',142,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:49:54'),('1405115469363122178','1','admin','菜单权限管理-获取所有菜单权限',166,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:50:08'),('1405115474140434434','1','admin','菜单权限管理-获取所有目录菜单树',173,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.60','2021-06-16 18:50:09'),('1405115568554217474','1','admin','菜单权限管理-新增菜单权限',18,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623840631972,\"deleted\":1,\"icon\":\"\",\"id\":\"1405115568491302914\",\"name\":\"牙科体检\",\"orderNum\":4,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623840631972,\"url\":\"/index/tooth\"}]','192.168.1.60','2021-06-16 18:50:32'),('1405115569363718145','1','admin','菜单权限管理-获取所有菜单权限',155,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:50:32'),('1405115639953854465','1','admin','菜单权限管理-获取所有目录菜单树',159,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"\"]','192.168.1.60','2021-06-16 18:50:49'),('1405115722346762242','1','admin','菜单权限管理-新增菜单权限',24,'com.company.project.controller.PermissionController.addPermission()','[{\"createTime\":1623840668633,\"deleted\":1,\"icon\":\"\",\"id\":\"1405115722267070465\",\"name\":\"眼科体检\",\"orderNum\":5,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623840668633,\"url\":\"/index/eye\"}]','192.168.1.60','2021-06-16 18:51:09'),('1405115723080765441','1','admin','菜单权限管理-获取所有菜单权限',139,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:51:09'),('1405115910683594753','1','admin','用户管理-分页获取用户列表',111,'com.company.project.controller.UserAccountController.pageInfo()','[{}]','192.168.1.60','2021-06-16 18:51:54'),('1405115924101173250','1','admin','角色管理-分页获取角色信息',26,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.60','2021-06-16 18:51:57'),('1405115929276944385','1','admin','用户管理-分页获取用户列表',3,'com.company.project.controller.UserAccountController.pageInfo()','[{}]','192.168.1.60','2021-06-16 18:51:58'),('1405115931793526786','1','admin','机构管理-获取所有组织机构',10,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.60','2021-06-16 18:51:59'),('1405115935115415553','1','admin','用户管理-分页获取用户列表',7,'com.company.project.controller.UserAccountController.pageInfo()','[{}]','192.168.1.60','2021-06-16 18:51:59'),('1405115938772848642','1','admin','角色管理-分页获取角色信息',8,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.60','2021-06-16 18:52:00'),('1405115943957008386','1','admin','角色管理-查询角色详情',133,'com.company.project.controller.RoleController.detailInfo()','[\"1\"]','192.168.1.60','2021-06-16 18:52:01'),('1405115963217252353','1','admin','角色管理-更新角色信息',112,'com.company.project.controller.RoleController.updateDept()','[{\"description\":\"拥有所有权限-不能删除\",\"id\":\"1\",\"name\":\"超级管理员\",\"permissions\":[\"51\",\"11\",\"40\",\"43\",\"44\",\"17\",\"26\",\"53\",\"19\",\"3\",\"36\",\"13\",\"39\",\"24\",\"10\",\"25\",\"23\",\"42\",\"52\",\"56\",\"57\",\"41\",\"12\",\"38\",\"22\",\"9\",\"5\",\"54\",\"15\",\"1\",\"20\",\"4\",\"16\",\"45\",\"46\",\"49\",\"48\",\"47\",\"8\",\"7\",\"58\",\"1401719419462430722\",\"1405114867765710849\",\"1405114993687105537\",\"1405115408763817986\",\"1405115568491302914\",\"1405115722267070465\",\"1401721476860817409\",\"1402149299920535554\",\"1403280343000367106\",\"1403341363181912065\",\"1404038916488372226\",\"1403533694707412994\",\"1404697926452658177\",\"21\",\"50\",\"2\",\"6\"],\"status\":1,\"updateTime\":1623840725994}]','192.168.1.60','2021-06-16 18:52:06'),('1405115963695403009','1','admin','角色管理-分页获取角色信息',8,'com.company.project.controller.RoleController.pageInfo()','[{\"name\":\"\"}]','192.168.1.60','2021-06-16 18:52:06'),('1405115993063919618','1','admin','菜单权限管理-获取所有菜单权限',158,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:52:13'),('1405117829850202114','1','admin','菜单权限管理-获取所有菜单权限',246,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:59:31'),('1405117875652001793','1','admin','菜单权限管理-删除菜单权限',66,'com.company.project.controller.PermissionController.deleted()','[\"1401751260848394242\"]','192.168.1.60','2021-06-16 18:59:42'),('1405117876838989825','1','admin','菜单权限管理-获取所有菜单权限',262,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:59:42'),('1405117914130546689','1','admin','菜单权限管理-获取所有目录菜单树',160,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"1401721476860817409\"]','192.168.1.60','2021-06-16 18:59:51'),('1405117942869917697','1','admin','菜单权限管理-更新菜单权限',28,'com.company.project.controller.PermissionController.updatePermission()','[{\"icon\":\"\",\"id\":\"1401721476860817409\",\"name\":\"外科体检\",\"orderNum\":124,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623841198054,\"url\":\"/index/Surgery_list\"}]','192.168.1.60','2021-06-16 18:59:58'),('1405117943683612673','1','admin','菜单权限管理-获取所有菜单权限',155,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 18:59:58'),('1405118044581789697','1','admin','菜单权限管理-获取所有菜单权限',162,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:00:22'),('1405118059589009410','1','admin','菜单权限管理-获取所有目录菜单树',146,'com.company.project.controller.PermissionController.getAllMenusPermissionTree()','[\"1401721476860817409\"]','192.168.1.60','2021-06-16 19:00:26'),('1405118084327014402','1','admin','菜单权限管理-更新菜单权限',21,'com.company.project.controller.PermissionController.updatePermission()','[{\"icon\":\"\",\"id\":\"1401721476860817409\",\"name\":\"外科体检\",\"orderNum\":124,\"perms\":\"\",\"pid\":\"1401719419462430722\",\"pidName\":\"体检系统\",\"status\":1,\"target\":\"_self\",\"type\":2,\"updateTime\":1623841231775,\"url\":\"/index/surgery\"}]','192.168.1.60','2021-06-16 19:00:32'),('1405118084993908738','1','admin','菜单权限管理-获取所有菜单权限',130,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:00:32'),('1405118140157394946','1','admin','菜单权限管理-获取所有菜单权限',165,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:00:45'),('1405122039857790977','1','admin','菜单权限管理-获取所有菜单权限',162,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:16:15'),('1405122044882567169','1','admin','角色管理-分页获取角色信息',72,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.60','2021-06-16 19:16:16'),('1405122048468697089','1','admin','机构管理-获取所有组织机构',9,'com.company.project.controller.DeptController.getDeptAll()',NULL,'192.168.1.60','2021-06-16 19:16:17'),('1405122058241425410','1','admin','用户管理-分页获取用户列表',8,'com.company.project.controller.UserAccountController.pageInfo()','[{}]','192.168.1.60','2021-06-16 19:16:19'),('1405122063874375682','1','admin','角色管理-分页获取角色信息',7,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.60','2021-06-16 19:16:21'),('1405122110884134913','1','admin','菜单权限管理-获取所有菜单权限',159,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:16:32'),('1405122359652499458','1','admin','角色管理-分页获取角色信息',7,'com.company.project.controller.RoleController.pageInfo()','[{}]','192.168.1.60','2021-06-16 19:17:31'),('1405122365906206722','1','admin','菜单权限管理-获取所有菜单权限',146,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:17:33'),('1405122894577254401','1','admin','菜单权限管理-获取所有菜单权限',163,'com.company.project.controller.PermissionController.getAllMenusPermission()',NULL,'192.168.1.60','2021-06-16 19:19:39');

INSERT INTO sys_permission(id,name,perms,icon,url,target,pid,order_num,type,status,create_time,update_time,deleted) VALUES('1','删除','sysGenerator:delete',NULL,'sysGenerator/delete',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('10','赋予角色','sys:user:role:update',NULL,'/sys/user/roles/*',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('11','菜单权限管理',NULL,NULL,'/index/menus','_self','51',98,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('12','列表','sys:dept:list',NULL,'/sys/depts',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('13','删除','sys:role:deleted',NULL,'/sys/role/*',NULL,'53',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('1401719419462430722','体检系统','','layui-icon-survey','','_self','0',123,1,1,'2021-06-07 09:55:26','2021-06-07 09:55:26',1),('1401721476860817409','外科体检','','','/index/surgery','_self','1401719419462430722',124,2,1,'2021-06-07 10:03:37','2021-06-16 19:00:32',1),('1401751260848394242','详情','sys:permission:detail','','/sys/permission/*','_self','1401721476860817409',1232,3,1,'2021-06-07 12:01:58','2021-06-07 12:01:58',0),('1402149299920535554','化验科体检','','','/index/assay','_self','1401719419462430722',125,2,1,'2021-06-08 14:23:38','2021-06-08 14:23:38',1),('1403280343000367106','胸部放射科体检','','','/index/chest','_self','1401719419462430722',126,2,1,'2021-06-11 17:18:00','2021-06-11 17:18:13',1),('1403341363181912065','其他科体检','','','/index/other','_self','1401719419462430722',127,2,1,'2021-06-11 21:20:28','2021-06-11 21:28:12',1),('1403533694707412994','体检负责医生','','','/index/manage','_self','1401719419462430722',128,2,1,'2021-06-12 10:04:43','2021-06-12 10:04:43',1),('1404038916488372226','检查医院','','','/index/boss','_self','1401719419462430722',128,2,1,'2021-06-13 19:32:18','2021-06-13 19:32:18',1),('1404697926452658177','导出PDF','','','/index/pdf','_self','1401719419462430722',129,2,1,'2021-06-15 15:10:58','2021-06-15 15:11:41',1),('1405114867765710849','耳鼻喉科体检','','','/index/ebh','_self','1401719419462430722',1,2,1,'2021-06-16 18:47:45','2021-06-16 18:47:45',1),('1405114993687105537','内科体检','','','/index/internal','_self','1401719419462430722',2,2,1,'2021-06-16 18:48:15','2021-06-16 18:48:15',1),('1405115408763817986','血压脉搏科体检','','','/index/blood','_self','1401719419462430722',3,2,1,'2021-06-16 18:49:54','2021-06-16 18:49:54',1),('1405115568491302914','牙科体检','','','/index/tooth','_self','1401719419462430722',4,2,1,'2021-06-16 18:50:32','2021-06-16 18:50:32',1),('1405115722267070465','眼科体检','','','/index/eye','_self','1401719419462430722',5,2,1,'2021-06-16 18:51:09','2021-06-16 18:51:09',1),('15','代码生成',NULL,NULL,'/index/sysGenerator','_self','54',1,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('16','列表','sysGenerator:list',NULL,'sysGenerator/listByPage',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('17','详情','sys:permission:detail',NULL,'/sys/permission/*',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('19','列表','sys:role:list',NULL,'/sys/roles',NULL,'53',0,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('2','SQL 监控','','','/druid/sql.html','_self','21',98,2,1,'2020-03-19 13:29:40','2020-05-07 13:36:59',1),('20','修改','sysGenerator:update',NULL,'sysGenerator/update',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('21','其他',NULL,'layui-icon-list',NULL,NULL,'0',200,1,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('22','详情','sys:dept:detail',NULL,'/sys/dept/*',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('23','列表','sys:user:list',NULL,'/sys/users',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('24','用户管理',NULL,NULL,'/index/users','_self','51',100,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('25','详情','sys:user:detail',NULL,'/sys/user/*',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('26','删除','sys:permission:deleted',NULL,'/sys/permission/*',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('3','新增','sys:role:add',NULL,'/sys/role',NULL,'53',0,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('36','更新','sys:role:update',NULL,'/sys/role',NULL,'53',0,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('38','更新','sys:dept:update',NULL,'/sys/dept',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('39','详情','sys:role:detail',NULL,'/sys/role/*',NULL,'53',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('4','添加','sysGenerator:add',NULL,'sysGenerator/add',NULL,'15',1,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('40','编辑','sys:permission:update',NULL,'/sys/permission',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('41','部门管理',NULL,NULL,'/index/depts','_self','51',100,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('42','新增','sys:user:add',NULL,'/sys/user',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('43','列表','sys:permission:list',NULL,'/sys/permissions',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('44','新增','sys:permission:add',NULL,'/sys/permission',NULL,'11',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('45','字典管理',NULL,'','/index/sysDict',NULL,'54',10,2,1,NULL,NULL,1),('46','列表','sysDict:list',NULL,'sysDict/listByPage',NULL,'45',0,3,1,NULL,NULL,1),('47','新增','sysDict:add',NULL,'sysDict/add',NULL,'45',0,3,1,NULL,NULL,1),('48','修改','sysDict:update',NULL,'sysDict/update',NULL,'45',0,3,1,NULL,NULL,1),('49','删除','sysDict:delete',NULL,'sysDict/delete',NULL,'45',0,3,1,NULL,NULL,1),('5','删除','sys:dept:deleted',NULL,'/sys/dept/*',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('50','表单构建','','','/index/build','_self','21',1,2,1,'2020-04-22 13:09:41','2020-05-07 13:36:47',1),('51','组织管理',NULL,'layui-icon-user',NULL,NULL,'0',1,1,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('52','拥有角色','sys:user:role:detail',NULL,'/sys/user/roles/*',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('53','角色管理',NULL,NULL,'/index/roles','_self','51',99,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('54','系统管理',NULL,'layui-icon-set-fill',NULL,NULL,'0',98,1,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('56','更新','sys:user:update',NULL,'/sys/user',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('57','删除','sys:user:deleted',NULL,'/sys/user',NULL,'24',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('58','删除','sys:log:deleted',NULL,'/sys/logs',NULL,'8',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('6','接口管理','','','/doc.html','_blank','21',100,2,1,'2020-03-19 13:29:40','2020-05-07 13:36:02',1),('7','列表','sys:log:list',NULL,'/sys/logs',NULL,'8',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('8','日志管理',NULL,NULL,'/index/logs','_self','54',97,2,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1),('9','新增','sys:dept:add',NULL,'/sys/dept',NULL,'41',100,3,1,'2020-03-19 13:29:40','2020-03-19 13:29:40',1);

INSERT INTO sys_role(id,name,description,status,create_time,update_time,deleted) VALUES('1','超级管理员','拥有所有权限-不能删除',1,'2019-11-01 19:26:29','2021-06-16 18:52:06',1);


INSERT INTO sys_role_permission(id,role_id,permission_id,create_time) VALUES('1405115962953011202','1','51','2021-06-16 18:52:06'),('1405115962953011203','1','11','2021-06-16 18:52:06'),('1405115962953011204','1','40','2021-06-16 18:52:06'),('1405115962953011205','1','43','2021-06-16 18:52:06'),('1405115962953011206','1','44','2021-06-16 18:52:06'),('1405115962953011207','1','17','2021-06-16 18:52:06'),('1405115962953011208','1','26','2021-06-16 18:52:06'),('1405115962953011209','1','53','2021-06-16 18:52:06'),('1405115962953011210','1','19','2021-06-16 18:52:06'),('1405115962953011211','1','3','2021-06-16 18:52:06'),('1405115962953011212','1','36','2021-06-16 18:52:06'),('1405115962953011213','1','13','2021-06-16 18:52:06'),('1405115962953011214','1','39','2021-06-16 18:52:06'),('1405115962953011215','1','24','2021-06-16 18:52:06'),('1405115962953011216','1','10','2021-06-16 18:52:06'),('1405115962953011217','1','25','2021-06-16 18:52:06'),('1405115962953011218','1','23','2021-06-16 18:52:06'),('1405115962953011219','1','42','2021-06-16 18:52:06'),('1405115962953011220','1','52','2021-06-16 18:52:06'),('1405115962953011221','1','56','2021-06-16 18:52:06'),('1405115962953011222','1','57','2021-06-16 18:52:06'),('1405115962953011223','1','41','2021-06-16 18:52:06'),('1405115962953011224','1','12','2021-06-16 18:52:06'),('1405115962953011225','1','38','2021-06-16 18:52:06'),('1405115962953011226','1','22','2021-06-16 18:52:06'),('1405115962953011227','1','9','2021-06-16 18:52:06'),('1405115962953011228','1','5','2021-06-16 18:52:06'),('1405115962953011229','1','54','2021-06-16 18:52:06'),('1405115962953011230','1','15','2021-06-16 18:52:06'),('1405115962953011231','1','1','2021-06-16 18:52:06'),('1405115962953011232','1','20','2021-06-16 18:52:06'),('1405115962953011233','1','4','2021-06-16 18:52:06'),('1405115962953011234','1','16','2021-06-16 18:52:06'),('1405115962953011235','1','45','2021-06-16 18:52:06'),('1405115962953011236','1','46','2021-06-16 18:52:06'),('1405115962953011237','1','49','2021-06-16 18:52:06'),('1405115962953011238','1','48','2021-06-16 18:52:06'),('1405115963015925762','1','47','2021-06-16 18:52:06'),('1405115963015925763','1','8','2021-06-16 18:52:06'),('1405115963015925764','1','7','2021-06-16 18:52:06'),('1405115963015925765','1','58','2021-06-16 18:52:06'),('1405115963015925766','1','1401719419462430722','2021-06-16 18:52:06'),('1405115963015925767','1','1405114867765710849','2021-06-16 18:52:06'),('1405115963015925768','1','1405114993687105537','2021-06-16 18:52:06'),('1405115963015925769','1','1405115408763817986','2021-06-16 18:52:06'),('1405115963015925770','1','1405115568491302914','2021-06-16 18:52:06'),('1405115963015925771','1','1405115722267070465','2021-06-16 18:52:06'),('1405115963015925772','1','1401721476860817409','2021-06-16 18:52:06'),('1405115963015925773','1','1402149299920535554','2021-06-16 18:52:06'),('1405115963015925774','1','1403280343000367106','2021-06-16 18:52:06'),('1405115963015925775','1','1403341363181912065','2021-06-16 18:52:06'),('1405115963015925776','1','1404038916488372226','2021-06-16 18:52:06'),('1405115963015925777','1','1403533694707412994','2021-06-16 18:52:06'),('1405115963015925778','1','1404697926452658177','2021-06-16 18:52:06'),('1405115963015925779','1','21','2021-06-16 18:52:06'),('1405115963015925780','1','50','2021-06-16 18:52:06'),('1405115963015925781','1','2','2021-06-16 18:52:06'),('1405115963015925782','1','6','2021-06-16 18:52:06');

INSERT INTO sys_user(id,username,salt,password,phone,dept_id,real_name,nick_name,email,status,sex,deleted,create_id,update_id,create_where,create_time,update_time) VALUES('1','admin','324ce32d86224b00a02b','2102b59a75ab87616b62d0b9432569d0','13888888888','1','爱糖宝','爱糖宝','xxxxxx@163.com',1,2,1,'1','1',3,'2019-09-22 19:38:05','2020-03-18 09:15:22');

INSERT INTO sys_user_role(id,user_id,role_id,create_time) VALUES('1','1','1','2020-03-19 02:23:13');

INSERT INTO tooth(Tooth_doctor_id,Tooth_operation_time,Tooth_decayed,Tooth_hypodontia,Tooth_tooth_space,Tooth_other,Tooth_idea,Tooth_all,Stu_id,Tooth_error) VALUES('100007','2019-06-15 09:56:46','正常','正常','正常','正常','无异样','1',1,'0');
INSERT INTO user_account(Account_name,Password,User_id,User_type,salt,status,sex,createTime) VALUES('admin','2102b59a75ab87616b62d0b9432569d0',1,1,'324ce32d86224b00a02b',1,1,'2021-06-15 11:11:59'),('doctor','2102b59a75ab87616b62d0b9432569d0',2,2,'324ce32d86224b00a02b',1,1,'2021-06-15 11:12:01');