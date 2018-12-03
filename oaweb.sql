/*
Navicat MySQL Data Transfer

Source Server         : oaDB
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : oaweb

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-03 17:08:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_file_manage
-- ----------------------------
DROP TABLE IF EXISTS `oa_file_manage`;
CREATE TABLE `oa_file_manage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` bigint(20) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_uri` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `part_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_file_manage
-- ----------------------------
INSERT INTO `oa_file_manage` VALUES ('1', '2018-11-19 22:26:36', '1', 'chrome.dll.sig', '/oa/internal/chrome.dll.sig', '1', 'internal');
INSERT INTO `oa_file_manage` VALUES ('2', '2018-11-19 22:26:42', '1', 'chrome_100_percent.pak', '/oa/internal/chrome_100_percent.pak', '', 'internal');
INSERT INTO `oa_file_manage` VALUES ('3', '2018-11-19 15:14:25', '1', 'chrome.exe.sig', '/oa/internal/chrome.exe.sig', '1', 'internal');
INSERT INTO `oa_file_manage` VALUES ('4', '2018-11-19 23:16:35', '1', 'libegl.dll', '/oa/internal/libegl.dll', '2', 'internal');

-- ----------------------------
-- Table structure for oa_keyword
-- ----------------------------
DROP TABLE IF EXISTS `oa_keyword`;
CREATE TABLE `oa_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_word` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_keyword
-- ----------------------------
INSERT INTO `oa_keyword` VALUES ('1', '主关键词1', '2018-11-15 17:06:49', '1');
INSERT INTO `oa_keyword` VALUES ('4', '哟哟哟哟2', '2018-11-15 17:23:02', '1');

-- ----------------------------
-- Table structure for oa_order
-- ----------------------------
DROP TABLE IF EXISTS `oa_order`;
CREATE TABLE `oa_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service_id` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `get_order_date` datetime DEFAULT NULL,
  `customer_im` varchar(255) DEFAULT NULL,
  `order_number` varchar(255) NOT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `customer_mail` varchar(255) DEFAULT NULL,
  `order_content` varchar(255) DEFAULT NULL COMMENT '考核标准',
  `order_price` varchar(255) DEFAULT NULL,
  `pay_state` varchar(255) DEFAULT NULL,
  `pay_progress` varchar(255) DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `due_money` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `recommend_im` varchar(255) DEFAULT NULL,
  `customer_account` varchar(255) DEFAULT NULL,
  `refund_way` varchar(255) DEFAULT NULL,
  `refund_money` varchar(255) DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  `refund_remark` varchar(255) DEFAULT NULL,
  `audit` varchar(255) DEFAULT NULL,
  `part_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_order
-- ----------------------------
INSERT INTO `oa_order` VALUES ('1', '22', 'aaaa', '2018-07-31 15:08:36', 'ggaf', '1107', '2018-07-31 15:08:36', null, null, null, '2', null, null, null, null, null, null, null, null, '2018-07-31 15:08:36', null, '0', '1');
INSERT INTO `oa_order` VALUES ('3', '22', 'cccc', '2018-07-31 15:13:10', 'sghh', '1108', '2018-11-07 21:30:00', '2434', 'fff', '155', '-1', null, '2018-11-12 00:00:00', '215', 'tt', 'tt', 'ttt', '淘宝', 'tt', '2018-07-31 00:00:00', 'tt', '1', '1');
INSERT INTO `oa_order` VALUES ('4', '25', 'faf', '2018-07-31 15:08:37', 'dd', '1120', '2018-07-31 15:08:37', '', '', '', '0', '', null, '', '', '', '', '1', '', '2018-07-31 15:08:37', '', '-1', '1');
INSERT INTO `oa_order` VALUES ('5', null, null, null, 'tttdd', '3159', null, 'www.51525525@qq,com', 'aaa', '1414', '1', null, '2018-11-06 00:00:00', '15', 'ffff', 'fff', 'fff', '淘宝', '124', '2018-07-31 00:00:00', 'fff', null, null);
INSERT INTO `oa_order` VALUES ('13', null, '管理员', null, 'f11f1', 'f1f1', '2018-11-09 17:30:00', 'rwwdsa124', 'f1', '5121', '1', null, '2018-11-27 00:00:00', '1252', 'tte', 'ert', 'tert', '微', '55', '2018-11-27 00:00:00', 'fsdf23', '0', '2');

-- ----------------------------
-- Table structure for oa_part_time
-- ----------------------------
DROP TABLE IF EXISTS `oa_part_time`;
CREATE TABLE `oa_part_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `part_qq` varchar(255) DEFAULT NULL,
  `submit_state` varchar(255) DEFAULT NULL,
  `part_phone` varchar(255) DEFAULT NULL,
  `part_alipay` varchar(255) DEFAULT NULL,
  `part_money` varchar(255) DEFAULT NULL COMMENT '稿酬',
  `deduct` varchar(255) DEFAULT NULL,
  `settle_date` datetime DEFAULT NULL,
  `part_remark` varchar(255) DEFAULT NULL COMMENT '客服说明',
  `part_audit` varchar(255) DEFAULT NULL,
  `part_settle_state` varchar(255) DEFAULT NULL,
  `finance_remark` varchar(255) DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `send_service_name` varchar(255) DEFAULT NULL,
  `part_money_real` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `part_money_finance` varchar(255) DEFAULT NULL COMMENT '稿酬（接单）',
  `part_audit_finance` varchar(255) DEFAULT NULL COMMENT '审核（接单）',
  `part_settle_state_finance` varchar(255) DEFAULT NULL COMMENT '结算状态（接单）',
  `part_finance_remark` varchar(255) DEFAULT NULL COMMENT '财务备注(接单)',
  `part_user_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_part_time
-- ----------------------------
INSERT INTO `oa_part_time` VALUES ('1', '91304', '1', '157856', 'afg', '115', null, '2018-07-15 10:47:44', '可以1', '0', '0', 'cccc', '1107', 'bbb', 'aaa', '5', '2018-12-03 16:19:20', null, '0', null, null, null);
INSERT INTO `oa_part_time` VALUES ('2', '5542', '1', '15625125', 'afg', '115', null, '2018-07-15 10:47:44', '可以1', '1', '1', 'ccc', '1107', 'bbb', 'aaa', '5', '2018-12-03 16:19:20', null, '0', null, null, null);
INSERT INTO `oa_part_time` VALUES ('4', '52515', '0', '139615', 'ffagg', '152', null, '2018-11-10 00:00:00', '可以1', '1', '1', 'cccc', '1108', 'bbb', 'aaa', '5', '2018-12-03 16:19:21', null, '0', null, null, null);
INSERT INTO `oa_part_time` VALUES ('5', '4555555', '0', '13659845685', '13659845685', '501', '60', null, '可以1', null, '0', 'cccc', '1108', null, null, '5', '2018-12-03 16:49:54', '50', '0', null, null, '可以哦');

-- ----------------------------
-- Table structure for oa_part_user
-- ----------------------------
DROP TABLE IF EXISTS `oa_part_user`;
CREATE TABLE `oa_part_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_job_date` datetime DEFAULT NULL,
  `part_qq` varchar(255) DEFAULT NULL,
  `recent_order_date` datetime DEFAULT NULL,
  `get_order_number` int(11) DEFAULT NULL,
  `problem_rate` varchar(255) DEFAULT NULL,
  `out_settle_count` int(11) DEFAULT NULL,
  `out_delivery_count` int(11) DEFAULT NULL,
  `total_reward` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `english_level` varchar(255) DEFAULT NULL,
  `acceptable_subject` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `part_phone` varchar(255) DEFAULT NULL,
  `part_alipay` varchar(255) DEFAULT NULL,
  `referrer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_part_user
-- ----------------------------
INSERT INTO `oa_part_user` VALUES ('1', '2018-11-20 10:28:31', '4555555', '2018-11-20 11:47:32', '1', '3', null, null, null, '英语', '4', '英语', '大学', '中华大学', '18', '13659845685', '13659845685', 'cjy');

-- ----------------------------
-- Table structure for oa_pay_process
-- ----------------------------
DROP TABLE IF EXISTS `oa_pay_process`;
CREATE TABLE `oa_pay_process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  `pay_way` varchar(255) DEFAULT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `money_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_pay_process
-- ----------------------------
INSERT INTO `oa_pay_process` VALUES ('1', '1', '2018-07-10 16:04:50', '微', '90', '人民币');
INSERT INTO `oa_pay_process` VALUES ('2', '13', '2018-11-21 08:00:00', '微', '125', '人民币');
INSERT INTO `oa_pay_process` VALUES ('3', '13', '2018-11-25 08:00:00', '微', '555', '人民币');

-- ----------------------------
-- Table structure for oa_temp_task
-- ----------------------------
DROP TABLE IF EXISTS `oa_temp_task`;
CREATE TABLE `oa_temp_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `executor` varchar(255) DEFAULT NULL,
  `complete_date` datetime DEFAULT NULL,
  `fill_form_date` datetime DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `assess_standard` varchar(255) DEFAULT NULL COMMENT '考核标准',
  `task_weight` double DEFAULT NULL,
  `resource_support` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `audit` varchar(255) DEFAULT NULL,
  `self_score` double DEFAULT NULL,
  `leader_score` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_temp_task
-- ----------------------------
INSERT INTO `oa_temp_task` VALUES ('1', 'David', '2018-05-17 11:54:51', '2018-05-14 11:54:56', 'xxxx', 'xxxx', '40', 'xxxx', 'xxxx', '待批', '80', '90');
INSERT INTO `oa_temp_task` VALUES ('2', 'CJy', '2018-05-29 19:09:35', '2018-05-29 19:09:35', 'ccc', '', null, '', '', null, null, null);
INSERT INTO `oa_temp_task` VALUES ('3', 'Cjy', '2018-05-29 19:09:38', '2018-05-29 19:09:38', 'zuzuzuz', '', null, '', '', null, null, null);
INSERT INTO `oa_temp_task` VALUES ('4', '管理员', '2018-05-09 00:00:00', '2018-05-29 23:14:49', 'ccc', '', null, '', '', null, null, null);

-- ----------------------------
-- Table structure for oa_work
-- ----------------------------
DROP TABLE IF EXISTS `oa_work`;
CREATE TABLE `oa_work` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pay_date` datetime DEFAULT NULL,
  `pay_user` varchar(255) DEFAULT NULL,
  `pay_project` varchar(255) DEFAULT NULL,
  `pay_money` varchar(255) DEFAULT NULL COMMENT '考核标准',
  `remark` varchar(255) DEFAULT NULL,
  `get_user` varchar(255) DEFAULT NULL,
  `audit` varchar(255) DEFAULT NULL,
  `settle` varchar(255) DEFAULT NULL,
  `part_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_work
-- ----------------------------
INSERT INTO `oa_work` VALUES ('12', '2018-06-11 00:00:00', 'admin', 'a111', '11d', 'zzzz', '2', '1', '1', '1');
INSERT INTO `oa_work` VALUES ('13', '2018-07-31 16:06:56', 'admin', 'dd22', '142', 'ffff', '1', '1', '0', '1');
INSERT INTO `oa_work` VALUES ('14', '2018-04-07 16:47:04', 'admin', 'asdd', '125d', 'af', '2', '0', '14', '1');
INSERT INTO `oa_work` VALUES ('15', '2018-04-07 16:47:05', 'admin', 'dd22', '142', 'ffff', '2', '0', '0', '1');
INSERT INTO `oa_work` VALUES ('16', '2018-06-07 16:20:54', 'admin', 'dd22', '142', 'ffff', '1', '0', '0', '1');
INSERT INTO `oa_work` VALUES ('17', '2018-06-07 16:20:55', 'admin', 'dd22', '142', 'ffff', '1', '1', '0', '2');
INSERT INTO `oa_work` VALUES ('18', '2018-07-31 16:05:56', 'admin', 'dd22', '142', 'ffff', '1', '1', '0', '2');
INSERT INTO `oa_work` VALUES ('19', '2018-06-07 16:47:29', 'admin', 'dd22', '142', 'ffff', '2', '1', '1', '2');
INSERT INTO `oa_work` VALUES ('20', '2018-07-31 16:04:00', 'admin', 'dd22', '142', 'ffff', '2', '1', '1', '3');
INSERT INTO `oa_work` VALUES ('21', '2018-07-31 16:19:13', 'admin', 'dd22', '142', 'ffff', '2', '1', '0', '3');
INSERT INTO `oa_work` VALUES ('22', '2018-07-31 15:57:15', 'admin', 'dd22', '142', 'ffff', '1', '1', '0', '3');
INSERT INTO `oa_work` VALUES ('23', '2018-07-31 15:57:40', 'admin', 'gfhg', '2165l', 'c gfcgh', '1', '1', '0', '3');
INSERT INTO `oa_work` VALUES ('24', '2018-06-11 00:00:00', 'admin', 'ddd', 'ff11', 'fff', '2', '1', '0', '2');
INSERT INTO `oa_work` VALUES ('25', '2018-07-31 16:20:49', 'admin', 'asfasf', '513raf', 'dddd', '1', '1', '0', '2');
INSERT INTO `oa_work` VALUES ('26', '2018-07-31 16:05:23', 'admin', 'ccc', 'ccc', 'ccccc', '1', '1', '0', '2');
INSERT INTO `oa_work` VALUES ('27', '2018-06-05 00:00:00', 'admin', 'aaa', 'aaa', 'aaa', '1', '0', '0', '2');
INSERT INTO `oa_work` VALUES ('28', '2018-06-11 00:00:00', 'admin', 'cccc', 'ccc', 'ccc', '1', '1', '0', 'marketing');
INSERT INTO `oa_work` VALUES ('29', '2018-06-04 00:00:00', 'admin', 'ccc', 'c', 'ccccccc', '1', '0', '0', 'marketing');
INSERT INTO `oa_work` VALUES ('30', '2018-07-31 16:07:55', 'admin', 'ccc', 'c', 'cc', '1', '1', '0', 'marketing');
INSERT INTO `oa_work` VALUES ('31', '2018-06-11 00:00:00', 'admin', 'ccc12', 'ccc11', 'cc', '1', '1', '0', 'marketing');
INSERT INTO `oa_work` VALUES ('32', '2018-07-31 15:55:40', 'admin', 'ccc', 'ccc', 'ccc', '2', '1', '0', 'internal');
INSERT INTO `oa_work` VALUES ('33', '2018-05-07 00:00:00', 'admin', 'ccc', 'cccc', 'ccccccccccc', '1', '0', '0', 'internal');
INSERT INTO `oa_work` VALUES ('34', '2018-07-31 16:17:27', 'admin', 'ccc', 'ccc', 'ccc', '1', '1', '0', 'internal');
INSERT INTO `oa_work` VALUES ('35', '2018-07-31 15:55:29', 'admin', 'ccc24', 'aaa25', 'ssss55', '1', '0', '1', 'internal');
INSERT INTO `oa_work` VALUES ('36', '2018-12-12 00:00:00', 'admin', 'aa', '44', 'fff', '1', '0', '0', 'apply');
INSERT INTO `oa_work` VALUES ('37', '2018-11-12 00:00:00', 'admin', 'rrrr', '13331', 'xxxx', '1', '0', '0', 'apply');
INSERT INTO `oa_work` VALUES ('38', null, 'admin', null, null, '', null, '0', '0', 'finance');
INSERT INTO `oa_work` VALUES ('39', '2018-11-13 00:00:00', 'admin', 'gggg', '5555', 'aaaa', '1', '0', '0', 'apply');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '', '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO `sys_permission` VALUES ('2', '', '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO `sys_permission` VALUES ('3', '', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
INSERT INTO `sys_permission` VALUES ('4', '', '测试用户信息', '0', '0/1', 'userInfo:test', 'menu', 'userInfo/test');
INSERT INTO `sys_permission` VALUES ('5', '', '界面显示权限', '0', '0', 'menu', 'menu', 'viewshow');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', '管理员', 'admin');
INSERT INTO `sys_role` VALUES ('2', '', 'VIP会员', 'vip');
INSERT INTO `sys_role` VALUES ('3', '', '兼职人员', 'part');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`),
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  CONSTRAINT `FK9q28ewrhntqeipl1t04kh1be7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKomxrs8a388bknvhjokh440waq` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`),
  CONSTRAINT `FKgkmyslkrfeyn9ukmolvek8b8f` FOREIGN KEY (`uid`) REFERENCES `user_info` (`uid`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('5', '3');
INSERT INTO `sys_user_role` VALUES ('6', '3');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd57ipfq9myr64` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '管理员', '8f4M1hwFZHzm4znf7M+qiw==', 'woleyi', '1', 'admin');
INSERT INTO `user_info` VALUES ('2', '普通用户1', '8f4M1hwFZHzm4znf7M+qiw==', 'woleyi', '1', 'normal');
INSERT INTO `user_info` VALUES ('3', '912515', 'v5Vvwx0ur6RNLa9JlqaHoA==', 'part', '1', null);
INSERT INTO `user_info` VALUES ('4', '912515', 'v5Vvwx0ur6RNLa9JlqaHoA==', 'part', '1', null);
INSERT INTO `user_info` VALUES ('5', '912515', 'v5Vvwx0ur6RNLa9JlqaHoA==', 'part', '1', null);
INSERT INTO `user_info` VALUES ('6', '4555555', '1SoXfzyY1J7vXro3NBN5bQ==', 'part', '1', null);
