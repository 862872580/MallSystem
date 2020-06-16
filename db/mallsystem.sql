/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50728
Source Host           : 127.0.0.1:3306
Source Database       : mallsystem

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-06-12 18:32:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `phonenumber` varchar(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `realname` varchar(18) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `del_flag` int(1) NOT NULL,
  `perms` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zs', '123456', null, null, null, '', '', '2020-05-06', '2020-05-06', '1', 'sysUser:vip');
INSERT INTO `sys_user` VALUES ('2', 'lisi', '123456', null, null, null, '', '', '2020-05-06', '2020-05-06', '1', 'no');
INSERT INTO `sys_user` VALUES ('4', 'cc', '1bddc1a2c3f1e97b94a50310ea94308c', null, null, null, '', '', '2020-05-07', '2020-05-07', '1', 'sysUser:vip');
INSERT INTO `sys_user` VALUES ('5', '123456', '35314df2291a7ba05851ec60beef5a35', null, null, null, '', '', '2020-05-07', '2020-05-07', '1', 'common');
INSERT INTO `sys_user` VALUES ('7', '777', 'cfff9f8deb54efea304e5a9fa6646157', null, null, null, '', '', '2020-05-09', '2020-05-09', '1', 'common');
INSERT INTO `sys_user` VALUES ('8', 'miao1', '04d8d3102dadefcebb7799bb9309ff95', '18858312781', null, null, '', '', '2020-05-09', '2020-05-09', '1', 'common');
INSERT INTO `sys_user` VALUES ('9', '阿萨德啊', '4cc0291918772f21d278f75013cdf0e1', '18858312781', null, '2020-05-06', '', '', '2020-05-09', '2020-05-09', '1', 'common');
INSERT INTO `sys_user` VALUES ('12', 'miao', '3850b0f93d4d44a3f737681805ab1bec', '18858312781', null, '2020-05-05', '862872580@qq.com', '缪业成', '2020-05-10', '2020-05-10', '1', 'sysUser:vip');

-- ----------------------------
-- Table structure for sys_userdata
-- ----------------------------
DROP TABLE IF EXISTS `sys_userdata`;
CREATE TABLE `sys_userdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `nickname` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `occupation` varchar(32) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_userdata
-- ----------------------------
INSERT INTO `sys_userdata` VALUES ('1', 'miao', '绸缪', '1', '无', 'Java', '金华职业技术学院');
