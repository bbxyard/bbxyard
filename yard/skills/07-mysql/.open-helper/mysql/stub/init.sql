-- ----------------------------
-- Table structure for node_user
-- ----------------------------
DROP TABLE IF EXISTS `node_user`;
CREATE TABLE `node_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of node_user
-- ----------------------------
INSERT INTO `node_user` VALUES ('1', 'alpha', '2019');
INSERT INTO `node_user` VALUES ('2', 'beta', '4096');
INSERT INTO `node_user` VALUES ('3', '张三丰', '149');
INSERT INTO `node_user` VALUES ('4', '法家李斯', '9999');
INSERT INTO `node_user` VALUES ('5', '吴王阖闾', '8888');


-- ----------------------------
-- Table structure for go_xorm_user
-- ----------------------------
-- `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
DROP TABLE IF EXISTS `go_xorm_user`;
CREATE TABLE `go_xorm_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `age` int(8) DEFAULT NULL,
  `c_at` bigint(20) unsigned DEFAULT '0',
  `m_at` bigint(20) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of go_xorm_user
-- ----------------------------
INSERT INTO `go_xorm_user` VALUES ('1', 'alpha', '2019', 0, 0);
INSERT INTO `go_xorm_user` VALUES ('2', 'beta', '4096', 0, 0);
INSERT INTO `go_xorm_user` VALUES ('3', '张三丰', '149', 0, 0);
INSERT INTO `go_xorm_user` VALUES ('4', '法家李斯', '9999', 0, 0);
INSERT INTO `go_xorm_user` VALUES ('5', '吴王阖闾', '8888', 0, 0);

