/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 30/04/2020 15:02:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `version` int(10) NULL DEFAULT 1 COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'MaYun Jack', 0, 'test1@baomidou.com', 1);
INSERT INTO `user` VALUES (2, 'Jack', 0, 'test2@baomidou.com', 1);
INSERT INTO `user` VALUES (3, 'Tom', 0, 'test3@baomidou.com', 1);
INSERT INTO `user` VALUES (4, 'Sandy', 0, 'test4@baomidou.com', 1);
INSERT INTO `user` VALUES (5, 'Billie', 0, 'test5@baomidou.com', 1);

SET FOREIGN_KEY_CHECKS = 1;
