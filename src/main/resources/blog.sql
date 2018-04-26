/*
 Navicat Premium Data Transfer

 Source Server         : blog
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 19/04/2018 16:50:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `create_time` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `title` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `status` INT(20) NULL DEFAULT NULL COMMENT '是否正常显示',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `blog_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =4 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'blog' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1,NULL,'test1',NULL,1);
INSERT INTO `blog` VALUES (2,NULL,'test2',NULL,2);
INSERT INTO `blog` VALUES (3,NULL,'test3','',3);

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `blog_id` INT(20) NULL DEFAULT NULL COMMENT '文章表主键',
  `comment_id` INT(20) NULL DEFAULT NULL COMMENT '评论表主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `blog_comment_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =3 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'comment' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES (1,1,1);
INSERT INTO `blog_comment` VALUES (2,1,2);

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `blog_id` INT(20) NULL DEFAULT NULL COMMENT '文章主键',
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户主键',
  `status` INT(20) NULL DEFAULT NULL COMMENT '赞还是举报   赞是0，举报是1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =2 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'blog_status' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES (1,1,1,1);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `create_time` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `content` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `status` INT(20) NULL DEFAULT NULL COMMENT '状态  被举报/点赞',
  `parent_id` INT(20) NULL DEFAULT NULL COMMENT '树状显示用到的父级评论',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `comment_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =3 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1,NULL,'test1',0,0);
INSERT INTO `comment` VALUES (2,NULL,'test2',0,1);

-- ----------------------------
-- Table structure for comment_user
-- ----------------------------
DROP TABLE IF EXISTS `comment_user`;
CREATE TABLE `comment_user` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `comment_id` INT(20) NULL DEFAULT NULL COMMENT '评论主键',
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户主键',
  `status` INT(20) NULL DEFAULT NULL COMMENT '赞还是举报  赞是0，举报是1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_comment_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =3 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment_user
-- ----------------------------
INSERT INTO `comment_user` VALUES (1,1,1,NULL);
INSERT INTO `comment_user` VALUES (2,2,2,NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `create_time` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布的时间',
  `msg` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `public_message_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =1 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `permission_name` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `permission_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =5 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'permission' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1,'写作');
INSERT INTO `permission` VALUES (2,'评论');
INSERT INTO `permission` VALUES (3,'审核');
INSERT INTO `permission` VALUES (4,'查看信息');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =4 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'role' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1,'普通用户');
INSERT INTO `role` VALUES (2,'文章评论员');
INSERT INTO `role` VALUES (3,'超级管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `role_id` INT(20) NULL DEFAULT NULL COMMENT '角色的id',
  `permission_id` INT(20) NULL DEFAULT NULL COMMENT '权限的id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_permission_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =9 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'role_permission' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1,1,1);
INSERT INTO `role_permission` VALUES (2,1,2);
INSERT INTO `role_permission` VALUES (3,2,1);
INSERT INTO `role_permission` VALUES (4,2,2);
INSERT INTO `role_permission` VALUES (5,2,3);
INSERT INTO `role_permission` VALUES (6,3,1);
INSERT INTO `role_permission` VALUES (7,3,2);
INSERT INTO `role_permission` VALUES (8,3,4);

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `sort_name` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sort_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =2 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'sort' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (1,'Android');

-- ----------------------------
-- Table structure for sort_blog
-- ----------------------------
DROP TABLE IF EXISTS `sort_blog`;
CREATE TABLE `sort_blog` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `sort_id` INT(20) NULL DEFAULT NULL COMMENT '分类表主键',
  `blog_id` INT(20) NULL DEFAULT NULL COMMENT '文章表主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `blog_sort_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =2 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sort_blog
-- ----------------------------
INSERT INTO `sort_blog` VALUES (1,1,1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'username',
  `password` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'password',
  `email` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'email',
  `mobile` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mobile',
  `sex` INT(20) NULL DEFAULT NULL COMMENT 'sex',
  `description` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'description',
  `img_url` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'imgUrl',
  `country` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'country',
  `birthday` VARCHAR(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'birthday',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_email_uindex`(`email`) USING BTREE,
  UNIQUE INDEX `user_mobile_uindex`(`mobile`) USING BTREE,
  UNIQUE INDEX `user_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =6 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'user' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1,'admin','admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (2,'test2','test2',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (3,'test3','test3',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (4,'test4','test4',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `user` VALUES (5,'test5','test5',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

-- ----------------------------
-- Table structure for user_blog
-- ----------------------------
DROP TABLE IF EXISTS `user_blog`;
CREATE TABLE `user_blog` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户的主键',
  `blog_id` INT(20) NULL DEFAULT NULL COMMENT '文章的主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_blog_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =2 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_blog
-- ----------------------------
INSERT INTO `user_blog` VALUES (1,1,1);

-- ----------------------------
-- Table structure for user_comment
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户的主键',
  `comment_id` INT(20) NULL DEFAULT NULL COMMENT '评论的主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_comment_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =3 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_comment
-- ----------------------------
INSERT INTO `user_comment` VALUES (1,1,1);
INSERT INTO `user_comment` VALUES (2,2,2);

-- ----------------------------
-- Table structure for user_fan
-- ----------------------------
DROP TABLE IF EXISTS `user_fan`;
CREATE TABLE `user_fan` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户的主键',
  `fan_id` INT(20) NULL DEFAULT NULL COMMENT '粉丝的主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_fan_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =25 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_fan
-- ----------------------------
INSERT INTO `user_fan` VALUES (2,1,2);
INSERT INTO `user_fan` VALUES (3,1,3);
INSERT INTO `user_fan` VALUES (4,1,4);
INSERT INTO `user_fan` VALUES (5,1,5);
INSERT INTO `user_fan` VALUES (6,2,1);
INSERT INTO `user_fan` VALUES (8,2,3);
INSERT INTO `user_fan` VALUES (9,2,4);
INSERT INTO `user_fan` VALUES (10,2,5);
INSERT INTO `user_fan` VALUES (11,3,1);
INSERT INTO `user_fan` VALUES (12,3,2);
INSERT INTO `user_fan` VALUES (14,3,4);
INSERT INTO `user_fan` VALUES (15,3,5);
INSERT INTO `user_fan` VALUES (16,4,1);
INSERT INTO `user_fan` VALUES (17,4,2);
INSERT INTO `user_fan` VALUES (18,4,3);
INSERT INTO `user_fan` VALUES (20,4,5);
INSERT INTO `user_fan` VALUES (21,5,1);
INSERT INTO `user_fan` VALUES (22,5,2);
INSERT INTO `user_fan` VALUES (23,5,3);
INSERT INTO `user_fan` VALUES (24,5,4);

-- ----------------------------
-- Table structure for user_focus
-- ----------------------------
DROP TABLE IF EXISTS `user_focus`;
CREATE TABLE `user_focus` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户的主键',
  `focus_id` INT(20) NULL DEFAULT NULL COMMENT '关注人的主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_focus_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =24 CHARACTER SET =utf8 COLLATE =utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_focus
-- ----------------------------
INSERT INTO `user_focus` VALUES (2,1,2);
INSERT INTO `user_focus` VALUES (3,1,3);
INSERT INTO `user_focus` VALUES (4,1,4);
INSERT INTO `user_focus` VALUES (5,1,5);
INSERT INTO `user_focus` VALUES (6,2,1);
INSERT INTO `user_focus` VALUES (8,2,3);
INSERT INTO `user_focus` VALUES (9,2,4);
INSERT INTO `user_focus` VALUES (10,2,5);
INSERT INTO `user_focus` VALUES (11,3,1);
INSERT INTO `user_focus` VALUES (12,3,2);
INSERT INTO `user_focus` VALUES (14,3,4);
INSERT INTO `user_focus` VALUES (15,3,5);
INSERT INTO `user_focus` VALUES (16,4,1);
INSERT INTO `user_focus` VALUES (17,4,2);
INSERT INTO `user_focus` VALUES (18,4,3);
INSERT INTO `user_focus` VALUES (19,4,5);
INSERT INTO `user_focus` VALUES (20,5,1);
INSERT INTO `user_focus` VALUES (21,5,2);
INSERT INTO `user_focus` VALUES (22,5,3);
INSERT INTO `user_focus` VALUES (23,5,4);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `user_id` INT(20) NULL DEFAULT NULL COMMENT '用户的主键',
  `role_id` INT(20) NULL DEFAULT NULL COMMENT '用户的主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_role_id_uindex`(`id`) USING BTREE
) ENGINE =InnoDB AUTO_INCREMENT =8 CHARACTER SET =utf8 COLLATE =utf8_general_ci COMMENT = 'user_role' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1,1,3);
INSERT INTO `user_role` VALUES (3,2,1);
INSERT INTO `user_role` VALUES (4,3,1);
INSERT INTO `user_role` VALUES (5,3,2);
INSERT INTO `user_role` VALUES (6,4,1);
INSERT INTO `user_role` VALUES (7,5,1);

SET FOREIGN_KEY_CHECKS = 1;
