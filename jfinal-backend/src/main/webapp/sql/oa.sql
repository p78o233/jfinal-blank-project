/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-05-27 14:14:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `cdNum` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '123', null, null, null);
INSERT INTO `test` VALUES ('2', '123', '1', '2018-11-30 17:04:50', '5.55');
INSERT INTO `test` VALUES ('3', '123', '1', '2018-11-30 17:04:50', '5.55');
INSERT INTO `test` VALUES ('7', null, null, null, null);
INSERT INTO `test` VALUES ('8', null, null, null, null);
INSERT INTO `test` VALUES ('9', null, null, null, null);
INSERT INTO `test` VALUES ('10', null, null, null, null);
INSERT INTO `test` VALUES ('11', '吴翰文', '1', '2020-05-26 02:46:06', '0.5');
INSERT INTO `test` VALUES ('13', '吴翰文', '1', '2020-05-26 02:46:06', '0.5');
INSERT INTO `test` VALUES ('14', '吴翰文', '1', '2020-05-26 02:46:06', '0.5');

-- ----------------------------
-- Table structure for `testc`
-- ----------------------------
DROP TABLE IF EXISTS `testc`;
CREATE TABLE `testc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'test表关联表',
  `testId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testc
-- ----------------------------
INSERT INTO `testc` VALUES ('1', '13');
INSERT INTO `testc` VALUES ('2', '13');
INSERT INTO `testc` VALUES ('3', '13');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createUser` int(11) DEFAULT NULL COMMENT '创建人',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `modifyUser` int(11) DEFAULT NULL COMMENT '修改人',
  `pwd` varchar(32) DEFAULT NULL COMMENT '登录密码的md5',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `districts` varchar(50) DEFAULT NULL COMMENT '区',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `userName` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `userTel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `signTime` datetime DEFAULT NULL COMMENT '签约时间',
  `entryTime` datetime DEFAULT NULL COMMENT '入职时间',
  `interViewTime` datetime DEFAULT NULL COMMENT '面试时间',
  `interViewWay` varchar(36) DEFAULT NULL COMMENT '获取职位信息途径',
  `webToken` varchar(32) DEFAULT NULL COMMENT 'web的token',
  `appToken` varchar(32) DEFAULT NULL COMMENT 'app的token',
  `departmentId` varchar(36) DEFAULT NULL COMMENT '部门id',
  `stationId` varchar(36) DEFAULT NULL COMMENT '岗位id',
  `userLevel` int(11) DEFAULT NULL COMMENT '员工等级',
  `interViewUserFirst` int(11) DEFAULT NULL COMMENT '面试人1',
  `interViewUserSecond` int(11) NOT NULL DEFAULT '0' COMMENT '面试人2',
  `interViewUserThird` int(11) DEFAULT NULL COMMENT '面试人3',
  `graduatSchool` varchar(50) DEFAULT NULL COMMENT '毕业学校',
  `education` varchar(5) DEFAULT NULL COMMENT '学历',
  `emergencyContactFirst` varchar(20) DEFAULT NULL COMMENT '紧急联系人1',
  `emergencyContactFirstTel` varchar(10) DEFAULT NULL COMMENT '紧急联系人1电话',
  `emergencyContactSecond` varchar(20) DEFAULT NULL COMMENT '紧急联系人2',
  `emergencyContactSecondTel` varchar(10) DEFAULT NULL COMMENT '紧急联系人2电话',
  `userCategory` int(11) DEFAULT NULL COMMENT '人员类别',
  `isdel` varchar(1) DEFAULT '0' COMMENT '逻辑删除0正常1删除',
  `userEmail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `userQQ` varchar(12) DEFAULT NULL COMMENT 'QQ',
  `userWX` varchar(50) DEFAULT NULL COMMENT '微信',
  PRIMARY KEY (`userId`,`interViewUserSecond`)
) ENGINE=MyISAM AUTO_INCREMENT=2352 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2018-03-28 18:19:47', '1', '2018-04-26 15:55:40', '1', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, 'p78o2', 'admin', '2018-03-30 11:03:13', '2018-03-30 11:03:13', '2018-03-30 11:03:13', '拉钩', '276928ae159cbe2c24e1db3a2c8b118c', null, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', '0', '1', '1', '1', '广州大学华软软件学院', '本科', '我自己', null, null, null, '0', '0', 'p78o2@126.com', '953712390', 'p78o233');
INSERT INTO `user` VALUES ('2', '2018-03-30 18:01:59', '1', '2018-04-26 14:03:08', '1', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, 'p', '133', '2018-03-30 18:02:15', '2018-03-30 18:02:15', '2018-03-30 18:02:15', 'dfs', null, null, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', '1', '0', '0', '0', 'g', 'fd', 'd', null, null, null, '0', '0', 'p78o2@12321321.com', 'w', 'w');
INSERT INTO `user` VALUES ('3', '2018-04-26 15:12:11', '1', null, null, null, null, null, null, null, '123', '213', '2018-04-26 15:12:05', '2018-04-26 15:12:06', '2018-04-26 15:12:09', '213', null, null, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', null, null, '0', null, '123', '213', null, null, null, null, null, '0', '123', '213', '23');
INSERT INTO `user` VALUES ('2351', '2018-04-26 16:45:00', '1', '2018-04-26 16:45:23', '1', null, null, null, null, null, '测试2', '测的是', '2018-04-26 16:03:21', '2018-04-26 16:03:21', '2018-04-26 16:03:21', '的', null, null, '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000', null, null, '0', null, '的', ' 的', null, null, null, null, null, '1', '测定', '长达', ' 发动');

-- ----------------------------
-- View structure for `departview`
-- ----------------------------
DROP VIEW IF EXISTS `departview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `departview` AS select `department`.`departmentId` AS `departmentId`,`department`.`departmentName` AS `departmentName`,`department`.`createDate` AS `createDate`,`department`.`createUser` AS `createUser`,`department`.`modifyDate` AS `modifyDate`,`department`.`modifyUser` AS `modifyUser`,`department`.`departmentManager` AS `departmentManager`,`department`.`isdel` AS `isdel`,`user`.`userName` AS `userName` from (`department` join `user` on((`department`.`departmentManager` = `user`.`userId`))) ;

-- ----------------------------
-- View structure for `userview`
-- ----------------------------
DROP VIEW IF EXISTS `userview`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `userview` AS select `user`.`userId` AS `userId`,`user`.`userName` AS `userName`,`user`.`userTel` AS `userTel`,`user`.`departmentId` AS `departmentId`,`user`.`stationId` AS `stationId`,`user`.`userEmail` AS `userEmail`,`user`.`userQQ` AS `userQQ`,`user`.`isdel` AS `isdel`,`user`.`userWX` AS `userWX`,`department`.`departmentName` AS `departmentName`,`station`.`stationName` AS `stationName`,`user`.`graduatSchool` AS `graduatSchool`,`user`.`education` AS `education`,`user`.`interViewWay` AS `interViewWay` from ((`user` join `department` on((`user`.`departmentId` = `department`.`departmentId`))) join `station` on((`user`.`stationId` = `station`.`stationId`))) ;
