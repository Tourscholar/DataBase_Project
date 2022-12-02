/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : db_student

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2022-12-02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_admin
-- ----------------------------
DROP TABLE IF EXISTS `s_admin`;
CREATE TABLE `s_admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_admin
-- ----------------------------
INSERT INTO `s_admin` VALUES ('1', '该号违规', '422227', '2022-12-02 14:24:09');
INSERT INTO `s_admin` VALUES ('2', '小丑科技', '2', '2022-12-03 01:56:38');
INSERT INTO `s_admin` VALUES ('3', 'admin', '123456', '2022-12-04 09:26:26');

-- ----------------------------
-- Table structure for s_attendance
-- ----------------------------
DROP TABLE IF EXISTS `s_attendance`;
CREATE TABLE `s_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `attendance_date` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_attendance_foreign` (`student_id`),
  KEY `course_attendance_foreign` (`course_id`),
  CONSTRAINT `course_attendance_foreign` FOREIGN KEY (`course_id`) REFERENCES `s_course` (`id`),
  CONSTRAINT `student_attendance_foreign` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_attendance
-- ----------------------------
INSERT INTO `s_attendance` VALUES ('1', '1', '1', '');
INSERT INTO `s_attendance` VALUES ('2', '1', '2', '0');
INSERT INTO `s_attendance` VALUES ('28', '1', '3', '2020-12-24');
INSERT INTO `s_attendance` VALUES ('29', '1', '4', '2020-12-24');

-- ----------------------------
-- Table structure for s_class
-- ----------------------------
DROP TABLE IF EXISTS `s_class`;
CREATE TABLE `s_class` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `info` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_class
-- ----------------------------
INSERT INTO `s_class` VALUES ('1', '1班', '1班1班，我是1班。再说一遍，我是1班！');
INSERT INTO `s_class` VALUES ('2', '2班', '2班2班，我是2班。再说一遍，我是2班！');
INSERT INTO `s_class` VALUES ('3', '3班', '3班3班，我是3班。再说一遍，我是3班！');
INSERT INTO `s_class` VALUES ('4', '4班', '4班4班，我是4班。再说一遍，我是4班！');
INSERT INTO `s_class` VALUES ('5', '5班', '5班5班，我是5班。再说一遍，我是5班！');

-- ----------------------------
-- Table structure for s_course
-- ----------------------------
DROP TABLE IF EXISTS `s_course`;
CREATE TABLE `s_course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `teacher_id` int(5) NOT NULL,
  `max_student_num` int(3) NOT NULL,
  `info` varchar(512) DEFAULT NULL,
  `selected_num` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `teacher_foreign` (`teacher_id`),
  CONSTRAINT `teacher_foreign` FOREIGN KEY (`teacher_id`) REFERENCES `s_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_course
-- ----------------------------
INSERT INTO `s_course` VALUES ('1', 'MySQL数据库', '1', '60', '很重要哦，必拿满分！', '0');
INSERT INTO `s_course` VALUES ('2', 'JavaScript开发', '2', '60', '网页设计的辅助在我看来', '0');
INSERT INTO `s_course` VALUES ('3', '网页设计', '4', '60', '建设了第一个自己的网站：littleugly.cn', '0');
INSERT INTO `s_course` VALUES ('4', 'Linux', '6', '60', '学习一门新系统，为以后云服务器打下基础', '0');
INSERT INTO `s_course` VALUES ('5', '计算机网络基础', '3', '60', '网络的常识课', '0');

-- ----------------------------
-- Table structure for s_score
-- ----------------------------
DROP TABLE IF EXISTS `s_score`;
CREATE TABLE `s_score` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `student_id` int(5) NOT NULL,
  `course_id` int(5) NOT NULL,
  `score` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_score_foreign` (`student_id`),
  KEY `course_id_score_foreign` (`course_id`),
  CONSTRAINT `course_id_score_foreign` FOREIGN KEY (`course_id`) REFERENCES `s_course` (`id`),
  CONSTRAINT `student_score_foreign` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_score
-- ----------------------------
INSERT INTO `s_score` VALUES ('16', '1', '1', '100');
INSERT INTO `s_score` VALUES ('17', '1', '2', '100');
INSERT INTO `s_score` VALUES ('18', '1', '4', '100');
INSERT INTO `s_score` VALUES ('19', '1', '5', '100');

-- ----------------------------
-- Table structure for s_selected_course
-- ----------------------------
DROP TABLE IF EXISTS `s_selected_course`;
CREATE TABLE `s_selected_course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `student_id` int(5) DEFAULT NULL,
  `course_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_foreign` (`student_id`),
  KEY `course_foreign` (`course_id`),
  CONSTRAINT `course_foreign` FOREIGN KEY (`course_id`) REFERENCES `s_course` (`id`),
  CONSTRAINT `student_foreign` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_selected_course
-- ----------------------------
INSERT INTO `s_selected_course` VALUES ('1', '1', '1');
INSERT INTO `s_selected_course` VALUES ('2', '1', '2');
INSERT INTO `s_selected_course` VALUES ('3', '1', '3');
INSERT INTO `s_selected_course` VALUES ('4', '1', '4');
INSERT INTO `s_selected_course` VALUES ('5', '1', '5');
INSERT INTO `s_selected_course` VALUES ('6', '2', '1');
INSERT INTO `s_selected_course` VALUES ('7', '2', '5');
INSERT INTO `s_selected_course` VALUES ('8', '3', '1');
INSERT INTO `s_selected_course` VALUES ('9', '4', '2');
INSERT INTO `s_selected_course` VALUES ('10', '5', '5');
INSERT INTO `s_selected_course` VALUES ('11', '6', '3');
INSERT INTO `s_selected_course` VALUES ('12', '6', '4');
INSERT INTO `s_selected_course` VALUES ('13', '7', '1');

-- ----------------------------
-- Table structure for s_student
-- ----------------------------
DROP TABLE IF EXISTS `s_student`;
CREATE TABLE `s_student` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `classId` int(5) NOT NULL,
  `password` varchar(32) NOT NULL,
  `sex` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `new` (`classId`) USING BTREE,
  CONSTRAINT `class_foreign` FOREIGN KEY (`classId`) REFERENCES `s_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_student
-- ----------------------------
INSERT INTO `s_student` VALUES ('1', '张三', '3', '1', '女');
INSERT INTO `s_student` VALUES ('2', '李四', '2', '2', '男');
INSERT INTO `s_student` VALUES ('3', '王五', '3', '3', '男');
INSERT INTO `s_student` VALUES ('4', '狗六', '4', '4', '男');
INSERT INTO `s_student` VALUES ('5', '刘七', '5', '5', '男');
INSERT INTO `s_student` VALUES ('6', '王八', '3', '6', '女');
INSERT INTO `s_student` VALUES ('7', '九九归一', '1', '7', '男');

-- ----------------------------
-- Table structure for s_teacher
-- ----------------------------
DROP TABLE IF EXISTS `s_teacher`;
CREATE TABLE `s_teacher` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `title` varchar(32) NOT NULL,
  `age` int(5) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_teacher
-- ----------------------------
INSERT INTO `s_teacher` VALUES ('1', '刘', '女', '数据库教师', '40', '1');
INSERT INTO `s_teacher` VALUES ('2', '母', '女', 'JavaScript教师', '40', '2');
INSERT INTO `s_teacher` VALUES ('3', '鲁', '女', '计算机网络基础教师', '40', '3');
INSERT INTO `s_teacher` VALUES ('4', '邵', '女', '网页设计教师', '40', '4');
INSERT INTO `s_teacher` VALUES ('5', '杨', '男', '校长', '40', '5');
INSERT INTO `s_teacher` VALUES ('6', '张', '女', 'Linux教师', '40', '6');

-- ----------------------------
-- View structure for 查看当前所有学生性别和所在班级，并显示该班级简介
-- ----------------------------
DROP VIEW IF EXISTS `查看当前所有学生性别和所在班级，并显示该班级简介`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `查看当前所有学生性别和所在班级，并显示该班级简介` AS select `s_student`.`sex` AS `sex`,`s_student`.`name` AS `name`,`s_student`.`id` AS `id`,`s_class`.`info` AS `info` from (`s_student` join `s_class` on((`s_student`.`classId` = `s_class`.`id`))) ;

-- ----------------------------
-- Function structure for 查找该学号学生
-- ----------------------------
DROP FUNCTION IF EXISTS `查找该学号学生`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `查找该学号学生`(`id` int) RETURNS varchar(255) CHARSET utf8
BEGIN
	#Routine body goes here...

	RETURN (SELECT NAME FROM s_student WHERE s_student.id=id);
END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for 每5周执行一次添加新同学的事件，名字叫张三
-- ----------------------------
DROP EVENT IF EXISTS `每5周执行一次添加新同学的事件，名字叫张三`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `每5周执行一次添加新同学的事件，名字叫张三` ON SCHEDULE AT '2021-01-28 02:56:24' ON COMPLETION NOT PRESERVE ENABLE DO INSERT INTO s_student (id,name) VALUES('1','张三')
;
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `选课人数增加`;
DELIMITER ;;
CREATE TRIGGER `选课人数增加` AFTER INSERT ON `s_course` FOR EACH ROW insert into oldtable(code) values(newtable.code)
;;
DELIMITER ;
