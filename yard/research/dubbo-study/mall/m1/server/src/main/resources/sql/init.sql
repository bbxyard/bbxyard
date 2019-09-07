/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : mooc_one

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-01-13 17:06:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `price` decimal(15,2) DEFAULT NULL COMMENT '销售价',
  `deleted` int(11) DEFAULT '0' COMMENT '逻辑删除（0=否；1=删除）',
  `version` int(11) DEFAULT '1' COMMENT '乐观锁版本',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`) USING BTREE COMMENT '商品编码唯一'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of item_info
-- ----------------------------
INSERT INTO `item_info` VALUES ('1', '10010', 'Java编程思想', '58.50', '0', '1', '2019-01-13 14:07:58', null);
INSERT INTO `item_info` VALUES ('2', '10011', '由浅入深实战分布式中间件', '45.50', '0', '1', '2019-01-13 14:07:59', null);
INSERT INTO `item_info` VALUES ('3', '10012', 'Dubbo实战教程', '68.00', '0', '1', '2019-01-13 14:07:59', null);
INSERT INTO `item_info` VALUES ('4', '10013', 'JVM深入实战指南', '38.50', '0', '1', '2019-01-13 14:07:59', null);
INSERT INTO `item_info` VALUES ('5', '10014', 'SpringBoot实战', '56.50', '0', '1', '2019-01-13 14:07:59', null);
INSERT INTO `item_info` VALUES ('6', '10015', 'SpringCloud实战', '64.00', '0', '1', '2019-01-13 14:07:59', null);
INSERT INTO `item_info` VALUES ('7', '10017', 'Mysql实战指南', '75.50', '0', '1', '2019-01-13 14:07:59', null);
