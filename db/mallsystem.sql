/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50641
Source Host           : localhost:3306
Source Database       : mallsystem

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2020-05-07 17:15:38
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
  `age` int(4) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `realname` varchar(18) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `del_flag` int(1) NOT NULL,
  `perms` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'zs', '123456', null, '', '', '2020-05-06 17:11:34', '2020-05-06 17:11:34', '1', 'sysUser:vip');
INSERT INTO `sys_user` VALUES ('2', 'lisi', '123456', null, '', '', '2020-05-07 10:54:28', '2020-05-07 14:10:23', '1', 'sysUser:vip');
INSERT INTO `sys_user` VALUES ('3', 'zs1', '123456', null, '', '', '2020-05-07 10:57:08', '2020-05-07 10:57:08', '1', 'common');
INSERT INTO `sys_user` VALUES ('4', 'zs2', '123456', null, '', '', '2020-05-07 14:20:49', '2020-05-07 14:20:49', '0', 'common');
INSERT INTO `sys_user` VALUES ('5', 'zs3', '123456', null, '', '', '2020-05-07 14:22:05', '2020-05-07 14:22:05', '0', 'common');
INSERT INTO `sys_user` VALUES ('6', 'zs4', '123456', null, '', '', '2020-05-07 14:22:58', '2020-05-07 14:22:58', '0', 'common');
INSERT INTO `sys_user` VALUES ('7', 'zs5', '123456', null, '', '', '2020-05-07 14:25:08', '2020-05-07 14:25:08', '0', 'common');
INSERT INTO `sys_user` VALUES ('8', 'zs6', '123456', null, '', '', '2020-05-07 14:26:12', '2020-05-07 14:26:12', '0', 'common');
INSERT INTO `sys_user` VALUES ('9', '', '', null, '', '', '2020-05-07 14:33:18', '2020-05-07 14:33:18', '1', 'common');
INSERT INTO `sys_user` VALUES ('10', 'zs7', '', null, '', '', '2020-05-07 14:35:12', '2020-05-07 14:35:12', '1', 'common');
INSERT INTO `sys_user` VALUES ('11', '李小布', '123456', '22', '', '', '2020-05-07 14:50:52', '2020-05-07 14:51:58', '1', 'sysUser:vip');
INSERT INTO `sys_user` VALUES ('12', 'aa', '0ab44bd43d6b18fcd5cd928d6faab1b8', null, '', '', '2020-05-07 16:26:20', '2020-05-07 16:26:20', '1', 'common');
INSERT INTO `sys_user` VALUES ('13', 'cc', '1bddc1a2c3f1e97b94a50310ea94308c', null, '', '', '2020-05-07 16:34:25', '2020-05-07 16:34:25', '1', 'common');
