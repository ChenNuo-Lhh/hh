/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : yingxue  -- 库名

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-11-29 12:04:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yx_admin
-- ----------------------------
DROP TABLE IF EXISTS `yx_admin`;
CREATE TABLE `yx_admin` (
  `id` varchar(40) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_admin
-- ----------------------------
INSERT INTO `yx_admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for yx_attention
-- ----------------------------
DROP TABLE IF EXISTS `yx_attention`;
CREATE TABLE `yx_attention` (
  `id` varchar(40) NOT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `attention_user_id` varchar(40) DEFAULT NULL,
  `attention_time` datetime DEFAULT NULL,
  `is_attention` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_attention
-- ----------------------------

-- ----------------------------
-- Table structure for yx_category
-- ----------------------------
DROP TABLE IF EXISTS `yx_category`;
CREATE TABLE `yx_category` (
  `id` varchar(40) NOT NULL,
  `cate_name` varchar(20) DEFAULT NULL,
  `levels` int(10) DEFAULT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_category
-- ----------------------------
INSERT INTO `yx_category` VALUES ('119f5', '傻傻的1', '2', '26585');
INSERT INTO `yx_category` VALUES ('2545r', '一级类别一', '1', null);
INSERT INTO `yx_category` VALUES ('26585', '一级类别', '1', null);
INSERT INTO `yx_category` VALUES ('3564', '阿尔多发哈', '2', '2545r');
INSERT INTO `yx_category` VALUES ('5745', 'd多个地方', '2', '2545r');
INSERT INTO `yx_category` VALUES ('7d5ca', '二和的', '2', '26585');
INSERT INTO `yx_category` VALUES ('a5e24', 'tgfyuh', '2', '26585');

-- ----------------------------
-- Table structure for yx_collect
-- ----------------------------
DROP TABLE IF EXISTS `yx_collect`;
CREATE TABLE `yx_collect` (
  `id` varchar(40) NOT NULL,
  `collect_time` datetime DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `video_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_collect
-- ----------------------------

-- ----------------------------
-- Table structure for yx_comment
-- ----------------------------
DROP TABLE IF EXISTS `yx_comment`;
CREATE TABLE `yx_comment` (
  `id` varchar(40) NOT NULL,
  `content` varchar(50) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `video_id` varchar(40) DEFAULT NULL,
  `comment_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_comment
-- ----------------------------

-- ----------------------------
-- Table structure for yx_feedback
-- ----------------------------
DROP TABLE IF EXISTS `yx_feedback`;
CREATE TABLE `yx_feedback` (
  `id` varchar(40) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_feedback
-- ----------------------------
INSERT INTO `yx_feedback` VALUES ('1234', 'egw', 'werhge', '234');
INSERT INTO `yx_feedback` VALUES ('5234', 'dfgs', '243153453', '234');

-- ----------------------------
-- Table structure for yx_group
-- ----------------------------
DROP TABLE IF EXISTS `yx_group`;
CREATE TABLE `yx_group` (
  `id` varchar(40) NOT NULL,
  `group_name` varchar(10) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_group
-- ----------------------------

-- ----------------------------
-- Table structure for yx_history
-- ----------------------------
DROP TABLE IF EXISTS `yx_history`;
CREATE TABLE `yx_history` (
  `id` varchar(40) NOT NULL,
  `history_time` datetime DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `video_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_history
-- ----------------------------

-- ----------------------------
-- Table structure for yx_like
-- ----------------------------
DROP TABLE IF EXISTS `yx_like`;
CREATE TABLE `yx_like` (
  `id` varchar(255) NOT NULL,
  `video_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_like
-- ----------------------------

-- ----------------------------
-- Table structure for yx_log
-- ----------------------------
DROP TABLE IF EXISTS `yx_log`;
CREATE TABLE `yx_log` (
  `id` varchar(40) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `options` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_log
-- ----------------------------
INSERT INTO `yx_log` VALUES ('91c46c0d6f9e4ce0a5c70c1191b52999', 'admin', '2020-11-24 20:51:45', 'User-添加[addUser]', 'success');
INSERT INTO `yx_log` VALUES ('asf', 'sgfa', '2020-11-24 21:28:01', 'wgfas', 'error');
INSERT INTO `yx_log` VALUES ('e76973cd77b54616919a8787202c9973', 'admin', '2020-11-28 17:24:47', 'Video-添加[addVideo]', 'success');

-- ----------------------------
-- Table structure for yx_user
-- ----------------------------
DROP TABLE IF EXISTS `yx_user`;
CREATE TABLE `yx_user` (
  `id` varchar(40) NOT NULL,
  `nick_name` varchar(10) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `pic_img` varchar(255) DEFAULT NULL,
  `brief` varchar(30) DEFAULT NULL,
  `score` double(10,1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_user
-- ----------------------------
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4730', 'asdf', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'rgsf', '456.0', '2020-09-12 17:04:13', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4733', 'asdf', '17631543117', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'asdf', '0.0', '2020-11-24 20:51:45', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4734', 'gdfg', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'asdf', '45.0', '2020-09-16 17:03:37', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4735', 'sagsa', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'sdf', '45.0', '2020-09-24 17:03:47', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4736', 'sdfs', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'vgbsad', '86.0', '2020-10-15 17:03:54', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4737', 'sdfg', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'fdg4', '234.0', '2020-11-26 17:03:59', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4738', 'dsf', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'asg', '234.0', '2020-11-25 17:04:02', '1');
INSERT INTO `yx_user` VALUES ('4280d7d0c67c4f678b95901569ec4739', 'asdf', '12345678901', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/img/1606222305922-1b649517e1eb33b82410e9cb82fdfc2c.jpg', 'svs', '456.0', '2020-10-31 17:04:08', '1');

-- ----------------------------
-- Table structure for yx_user_con
-- ----------------------------
DROP TABLE IF EXISTS `yx_user_con`;
CREATE TABLE `yx_user_con` (
  `id` varchar(40) NOT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `city` varchar(5) DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `yx_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_user_con
-- ----------------------------
INSERT INTO `yx_user_con` VALUES ('241', '男', '山西', '4280d7d0c67c4f678b95901569ec4733');
INSERT INTO `yx_user_con` VALUES ('325', '女', '深圳', '4280d7d0c67c4f678b95901569ec4737');
INSERT INTO `yx_user_con` VALUES ('345', '女', '陕西', '4280d7d0c67c4f678b95901569ec4738');
INSERT INTO `yx_user_con` VALUES ('453', '女', '黑龙江', '4280d7d0c67c4f678b95901569ec4739');
INSERT INTO `yx_user_con` VALUES ('456', '男', '陕西', '4280d7d0c67c4f678b95901569ec4734');
INSERT INTO `yx_user_con` VALUES ('497', '男', '湖南', '4280d7d0c67c4f678b95901569ec4735');
INSERT INTO `yx_user_con` VALUES ('789', '女', '山西', '4280d7d0c67c4f678b95901569ec4736');
INSERT INTO `yx_user_con` VALUES ('965', '男', '湖北', '4280d7d0c67c4f678b95901569ec4730');

-- ----------------------------
-- Table structure for yx_video
-- ----------------------------
DROP TABLE IF EXISTS `yx_video`;
CREATE TABLE `yx_video` (
  `id` varchar(40) NOT NULL,
  `title` varchar(10) DEFAULT NULL,
  `brief` varchar(50) DEFAULT NULL,
  `cover_path` varchar(300) DEFAULT NULL,
  `video_path` varchar(100) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `category_id` varchar(40) DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `group_id` varchar(40) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yx_video
-- ----------------------------
INSERT INTO `yx_video` VALUES ('43890f5a22a5445baff8514cae2cee5b', 'eagaedf', 'aehgdtg', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/cover/ZPWF.jpg', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/1606205249637-火花.mp4', '2020-11-24 16:07:29', '7d5ca', '4280d7d0c67c4f678b95901569ec4733', '3', '2');
INSERT INTO `yx_video` VALUES ('6892746a2ec543cc99b7b534a2cd12b1', 'gdaffh', 'dhgdg', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/cover/1606555487210-徒步.jpg', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/1606555487210-徒步.mp4', '2020-11-28 17:24:47', '1', '3', '3', '1');
INSERT INTO `yx_video` VALUES ('c8731152996f41e9ab2ec8d452cef200', 'gsdfga', 'fhadghfd', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/cover/ZPWF.jpg', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/1606205815725-抖音视频.mp4', '2020-11-24 16:16:55', '7d5ca', '4280d7d0c67c4f678b95901569ec4733', '3', '2');
INSERT INTO `yx_video` VALUES ('ce450dd4d6e6466dbd0bad57e6602627', 'wshgdt', 'tujyth', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/cover/1606205274865-好看.jpg', 'https://yingxue-cn.oss-cn-beijing.aliyuncs.com/yingxue/video/1606205274865-好看.mp4', '2020-11-24 16:07:54', '7d5ca', '4280d7d0c67c4f678b95901569ec4733', '3', '1');
