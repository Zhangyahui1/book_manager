/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 02/04/2020 21:49:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'bid',
  `book_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '出版日期',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书简介',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书的类型',
  `borrow_times` int(11) NULL DEFAULT NULL COMMENT '被借阅次数',
  `picture` varchar(123) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书籍图片',
  `srid` int(11) NULL DEFAULT NULL COMMENT '书库id',
  `is_borrow` int(2) NULL DEFAULT NULL COMMENT '是否已经被借出（1是预约，2是借出）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '操作系统', '张老师', '2020-03-17 00:00:00', '计算机入门必学,计算机大牛必精', '基础', 0, '/pic/5c3ba4fa-5abd-475c-806d-77bc6fa724dc.jpg', 2, 0);
INSERT INTO `book` VALUES (2, '计算机网络', '唐老师', '2020-03-17 00:00:00', '计算机入门必学', '认知', 0, '/pic/50c17c28-5bee-4514-9f80-c3b484c5adce.jpg', 2, 0);
INSERT INTO `book` VALUES (3, 'Thinking In Java', '高司令', '2020-03-18 00:00:00', '四大名著', 'Java', 1, '/pic/069c9097-6764-4617-ad9c-b57c64d6d161.jpg', 7, 0);
INSERT INTO `book` VALUES (7, 'Effective Java', '乔治', '2020-01-06 12:31:35', 'Java四大名著', 'Java', 0, '/pic/aecafa73-f80b-444e-98c3-18ee2ae067cf.jpg', 1, 0);
INSERT INTO `book` VALUES (8, 'C++ primer plus', '林纳斯', '2020-03-10 00:00:00', '我最年长', '技术', 0, '/pic/library.jpg', 7, 0);
INSERT INTO `book` VALUES (9, 'php', '皮特', '2020-03-03 12:33:03', '世界上最好的语言', '技术', 3, '/pic/library.jpg', 2, 0);
INSERT INTO `book` VALUES (10, 'python', '詹姆士', '2020-03-02 12:33:07', '蟒蛇语言', '基础', 3, '/pic/library.jpg', 1, 2);
INSERT INTO `book` VALUES (11, 'go', '李老师', '2020-03-01 12:33:11', '微服务我来搞', '基础', 1, '/pic/library.jpg', 7, 0);
INSERT INTO `book` VALUES (12, 'javascript', '隔壁老王', '2020-03-01 12:33:16', '浏览器上的霸主', '提升', 2, '/pic/library.jpg', 2, 1);
INSERT INTO `book` VALUES (14, '人类简史', '赫拉利', '2018-01-01 00:00:00', '人类历史长河中的兴衰荣辱', '历史', 0, '/pic/library.jpg', 2, 0);
INSERT INTO `book` VALUES (15, '张宇18讲', '张宇', '2019-01-16 00:00:00', '跟宇哥考研...Happy', '考研', 0, '/pic/library.jpg', 7, 0);
INSERT INTO `book` VALUES (16, '人类简史', '赫拉利', '2018-01-01 00:00:00', '人类历史长河中的兴衰荣辱', '历史', 0, '/pic/library.jpg', 7, 0);

-- ----------------------------
-- Table structure for book_donation
-- ----------------------------
DROP TABLE IF EXISTS `book_donation`;
CREATE TABLE `book_donation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `publish_date` datetime(0) NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_donation
-- ----------------------------
INSERT INTO `book_donation` VALUES (1, 5, '人类简史', '赫拉利', '2018-01-01 00:00:00', '人类历史长河中的兴衰荣辱', '历史', '/pic/library.jpg', 1);
INSERT INTO `book_donation` VALUES (2, 5, '张宇18讲', '张宇', '2019-01-16 00:00:00', '跟宇哥考研...Happy', '考研', '/pic/library.jpg', 1);
INSERT INTO `book_donation` VALUES (3, 5, '人类简史', '赫拉利', '2018-01-01 00:00:00', '人类历史长河中的兴衰荣辱', '历史', '/pic/library.jpg', 1);

-- ----------------------------
-- Table structure for borrow_info
-- ----------------------------
DROP TABLE IF EXISTS `borrow_info`;
CREATE TABLE `borrow_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户的id',
  `bid` int(11) NULL DEFAULT NULL COMMENT '书id',
  `borrow_time` datetime(0) NULL DEFAULT NULL COMMENT '借书时间',
  `return_time` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  `is_return` int(11) NULL DEFAULT NULL COMMENT '是否归还',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow_info
-- ----------------------------
INSERT INTO `borrow_info` VALUES (1, 4, 10, '2020-03-20 15:30:21', '2020-04-03 15:30:23', 0);
INSERT INTO `borrow_info` VALUES (2, 5, 11, '2020-03-20 15:58:37', '2020-03-21 16:38:57', 1);
INSERT INTO `borrow_info` VALUES (3, 5, 8, '2020-02-06 13:57:21', '2020-02-20 13:57:26', 1);
INSERT INTO `borrow_info` VALUES (4, 5, 12, '2020-03-21 21:52:13', '2020-04-11 21:52:13', 0);
INSERT INTO `borrow_info` VALUES (5, 5, 3, '2020-03-22 09:26:57', '2020-03-22 09:27:47', 1);

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `bid` int(11) NULL DEFAULT NULL COMMENT '书籍id',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建评论的时间',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论人的姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES (1, 10, '有人说：“一辈子很长，要跟一个有趣的人在一起”。我想关注我的人，应该是那种喜欢找乐子也乐意分享乐趣的人，你们一定挺优秀的。所以单身的应该在这条留言，互相勾搭一下。特别有钱人又帅可以直接私信我！', '2020-03-20 14:27:30', 'zhulin');
INSERT INTO `discuss` VALUES (2, 10, '哈哈哈哈 你卖什么萌啊! 蠢死了', '2020-03-20 14:28:27', 'zhulin');
INSERT INTO `discuss` VALUES (3, 10, '昨天评论里你见过最“温暖和感人”的诗句，整理其中经典100首，值得你收下学习。', '2020-03-20 14:34:41', 'zhulin');
INSERT INTO `discuss` VALUES (4, 12, '好吃', '2020-03-20 14:52:31', 'zhulin');
INSERT INTO `discuss` VALUES (5, 3, '哈哈哈，真好看', '2020-03-22 09:28:28', 'zhulin');

-- ----------------------------
-- Table structure for stack_room
-- ----------------------------
DROP TABLE IF EXISTS `stack_room`;
CREATE TABLE `stack_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书库id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书库名称',
  `location` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书库地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stack_room
-- ----------------------------
INSERT INTO `stack_room` VALUES (1, '第一书库', '东校区');
INSERT INTO `stack_room` VALUES (2, '第二书库', '西校区');
INSERT INTO `stack_room` VALUES (7, '第四书库', '中心校区');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `level` int(2) NOT NULL COMMENT '用户级别(0普通用户,1管理员,2超级管理员)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1049022252@qq.com', '13355174706', '123', 2);
INSERT INTO `user` VALUES (2, 'zhangsan', '2342@qq.com', '144521232312', '123', 1);
INSERT INTO `user` VALUES (4, '123', '2016207191@qdu.edu.cn', '13355174706', '123', 0);
INSERT INTO `user` VALUES (5, 'zhulin', '123456@qq.com', '1234554431', '123', 0);
INSERT INTO `user` VALUES (10, 'admin2', '123@qq.com', '123', '@12', 1);
INSERT INTO `user` VALUES (11, 'dfkdf', '2323@qq.com', '1232331', '123123', 1);
INSERT INTO `user` VALUES (13, '1234', '1049022252@qq.com', '13355174706', '12', 0);
INSERT INTO `user` VALUES (14, 'zhangsans', '1049022252@qq.cn', '13355174706', '12', 0);

SET FOREIGN_KEY_CHECKS = 1;
