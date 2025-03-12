/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : cl65780308

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 26/04/2024 15:56:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 'swiper1', 'file/swiperPicture1.jpg');
INSERT INTO `config` VALUES (2, 'swiper2', 'file/swiperPicture2.jpg');
INSERT INTO `config` VALUES (3, 'swiper3', 'file/swiperPicture3.jpg');

-- ----------------------------
-- Table structure for discusstiezi
-- ----------------------------
DROP TABLE IF EXISTS `discusstiezi`;
CREATE TABLE `discusstiezi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `nickname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `reply` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1714103360257 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discusstiezi
-- ----------------------------
INSERT INTO `discusstiezi` VALUES (1713238351797, '2024-04-16 11:32:30', 1713238190167, 1713238328748, 'file/1713238323649.jpg', '001', '飞达深V都是VS的VS的', NULL);
INSERT INTO `discusstiezi` VALUES (1714103360256, '2024-04-26 11:49:20', 33, 1714103209201, '', '11', '我我我', NULL);

-- ----------------------------
-- Table structure for jubaoxinxi
-- ----------------------------
DROP TABLE IF EXISTS `jubaoxinxi`;
CREATE TABLE `jubaoxinxi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `biaoti` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `neirongfenlei` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容分类',
  `yonghuzhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `jubaoyuanyin` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '举报原因',
  `jubaoshijian` datetime(0) NULL DEFAULT NULL COMMENT '举报时间',
  `jubaorenzhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举报人账号',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否审核',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1713238360308 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '举报信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jubaoxinxi
-- ----------------------------
INSERT INTO `jubaoxinxi` VALUES (51, '2024-04-16 11:22:15', '标题1', '内容分类1', '用户账号1', '用户姓名1', '举报原因1', '2024-04-16 11:22:15', '举报人账号1', '', '是');
INSERT INTO `jubaoxinxi` VALUES (52, '2024-04-16 11:22:15', '标题2', '内容分类2', '用户账号2', '用户姓名2', '举报原因2', '2024-04-16 11:22:15', '举报人账号2', '', '是');
INSERT INTO `jubaoxinxi` VALUES (53, '2024-04-16 11:22:15', '标题3', '内容分类3', '用户账号3', '用户姓名3', '举报原因3', '2024-04-16 11:22:15', '举报人账号3', '', '是');
INSERT INTO `jubaoxinxi` VALUES (54, '2024-04-16 11:22:15', '标题4', '内容分类4', '用户账号4', '用户姓名4', '举报原因4', '2024-04-16 11:22:15', '举报人账号4', '', '是');
INSERT INTO `jubaoxinxi` VALUES (55, '2024-04-16 11:22:15', '标题5', '内容分类5', '用户账号5', '用户姓名5', '举报原因5', '2024-04-16 11:22:15', '举报人账号5', '', '是');
INSERT INTO `jubaoxinxi` VALUES (56, '2024-04-16 11:22:15', '标题6', '内容分类6', '用户账号6', '用户姓名6', '举报原因6', '2024-04-16 11:22:15', '举报人账号6', '', '是');
INSERT INTO `jubaoxinxi` VALUES (1713238360307, '2024-04-16 11:32:39', '阿是擦擦vDVD深V', '内容分类1', '123123', '张三', '啊深VDVD是VS的VS的VS', '2024-04-16 11:32:48', '001', '审核通过', '是');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `menujson` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '2024-04-16 11:22:15', '[{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-explore\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"首页总数\"],\"menu\":\"用户\",\"menuJump\":\"列表\",\"tableName\":\"yonghu\"}],\"fontClass\":\"icon-user1\",\"menu\":\"用户管理\",\"unicode\":\"&#xef97;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-news\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"管理员\",\"menuJump\":\"列表\",\"tableName\":\"users\"}],\"fontClass\":\"icon-common19\",\"menu\":\"管理员管理\",\"unicode\":\"&#xee00;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"内容分类\",\"menuJump\":\"列表\",\"tableName\":\"neirongfenlei\"}],\"fontClass\":\"icon-common18\",\"menu\":\"内容分类管理\",\"unicode\":\"&#xedff;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"审核\",\"各内容分类数据统计\",\"日发帖量数据统计\",\"查看评论\",\"首页总数\",\"首页统计\"],\"menu\":\"帖子\",\"menuJump\":\"列表\",\"tableName\":\"tiezi\"},{\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"审核\"],\"menu\":\"举报信息\",\"menuJump\":\"列表\",\"tableName\":\"jubaoxinxi\"}],\"fontClass\":\"icon-common24\",\"menu\":\"帖子信息管理\",\"unicode\":\"&#xee07;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-clothes\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"审核\"],\"menu\":\"用户反馈\",\"menuJump\":\"列表\",\"tableName\":\"yonghufankui\"}],\"fontClass\":\"icon-common23\",\"menu\":\"用户反馈管理\",\"unicode\":\"&#xee05;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-addressbook\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"}],\"fontClass\":\"icon-common25\",\"menu\":\"轮播图管理\",\"unicode\":\"&#xee09;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"查看评论\",\"举报\"],\"fontClass\":\"icon-common1\",\"menu\":\"帖子\",\"menuJump\":\"列表\",\"tableName\":\"tiezi\",\"unicode\":\"&#xeda3;\"}],\"fontClass\":\"icon-common1\",\"menu\":\"帖子\",\"unicode\":\"&#xeda3;\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"查看评论\"],\"menu\":\"帖子\",\"menuJump\":\"列表\",\"tableName\":\"tiezi\"},{\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"查看\",\"修改\"],\"menu\":\"举报信息\",\"menuJump\":\"列表\",\"tableName\":\"jubaoxinxi\"}],\"fontClass\":\"icon-common24\",\"menu\":\"帖子信息管理\",\"unicode\":\"&#xee07;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-clothes\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"用户反馈\",\"menuJump\":\"列表\",\"tableName\":\"yonghufankui\"}],\"fontClass\":\"icon-common23\",\"menu\":\"用户反馈管理\",\"unicode\":\"&#xee05;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-present\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"menu\":\"我的收藏\",\"menuJump\":\"1\",\"tableName\":\"storeup\"}],\"fontClass\":\"icon-common49\",\"menu\":\"我的收藏管理\",\"unicode\":\"&#xef79;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"查看评论\",\"举报\"],\"fontClass\":\"icon-common1\",\"menu\":\"帖子\",\"menuJump\":\"列表\",\"tableName\":\"tiezi\",\"unicode\":\"&#xeda3;\"}],\"fontClass\":\"icon-common1\",\"menu\":\"帖子\",\"unicode\":\"&#xeda3;\"}],\"hasBackLogin\":\"否\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"roleName\":\"用户\",\"tableName\":\"yonghu\"}]');

-- ----------------------------
-- Table structure for neirongfenlei
-- ----------------------------
DROP TABLE IF EXISTS `neirongfenlei`;
CREATE TABLE `neirongfenlei`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `neirongfenlei` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1713238245563 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '内容分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of neirongfenlei
-- ----------------------------
INSERT INTO `neirongfenlei` VALUES (71, '2024-04-16 11:22:15', '内容分类1');
INSERT INTO `neirongfenlei` VALUES (72, '2024-04-16 11:22:15', '内容分类2');
INSERT INTO `neirongfenlei` VALUES (73, '2024-04-16 11:22:15', '内容分类3');
INSERT INTO `neirongfenlei` VALUES (74, '2024-04-16 11:22:15', '内容分类4');
INSERT INTO `neirongfenlei` VALUES (75, '2024-04-16 11:22:15', '内容分类5');
INSERT INTO `neirongfenlei` VALUES (76, '2024-04-16 11:22:15', '内容分类6');
INSERT INTO `neirongfenlei` VALUES (1713238245562, '2024-04-16 11:30:44', '感情');

-- ----------------------------
-- Table structure for storeup
-- ----------------------------
DROP TABLE IF EXISTS `storeup`;
CREATE TABLE `storeup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `refid` bigint(20) NULL DEFAULT NULL COMMENT 'refid',
  `tablename` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `picture` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1713238345964 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '我的收藏' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storeup
-- ----------------------------
INSERT INTO `storeup` VALUES (1713238343739, '2024-04-16 11:32:23', 1713238190167, 'tiezi', '阿是擦擦vDVD深V', 'file/1713238184022.jpeg', '21', NULL, NULL, 1713238328748);
INSERT INTO `storeup` VALUES (1713238345963, '2024-04-16 11:32:25', 1713238190167, 'tiezi', '阿是擦擦vDVD深V', 'file/1713238184022.jpeg', '1', NULL, NULL, 1713238328748);

-- ----------------------------
-- Table structure for tiezi
-- ----------------------------
DROP TABLE IF EXISTS `tiezi`;
CREATE TABLE `tiezi`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `biaoti` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `fengmian` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '封面',
  `neirongfenlei` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容分类',
  `neirongxiangqing` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容详情',
  `fabushijian` date NULL DEFAULT NULL COMMENT '发布时间',
  `yonghuzhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `thumbsupnum` int(11) NULL DEFAULT NULL COMMENT '赞',
  `crazilynum` int(11) NULL DEFAULT NULL COMMENT '踩',
  `storeupnum` int(11) NULL DEFAULT NULL COMMENT '收藏数量',
  `sfsh` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否审核',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  `clicknum` int(11) NULL DEFAULT NULL COMMENT '点击次数',
  `fujian` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1714114772043 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tiezi
-- ----------------------------
INSERT INTO `tiezi` VALUES (31, '2024-04-16 11:22:15', '标题1', 'file/tieziFengmian1.jpg,file/tieziFengmian2.jpg,file/tieziFengmian3.jpg', '内容分类1', '内容详情1', '2024-04-16', '用户账号1', '用户姓名1', 1, 1, 1, '是', '', 2, NULL);
INSERT INTO `tiezi` VALUES (32, '2024-04-16 11:22:15', '标题2', 'file/tieziFengmian2.jpg,file/tieziFengmian3.jpg,file/tieziFengmian4.jpg', '内容分类2', '内容详情2', '2024-04-16', '用户账号2', '用户姓名2', 2, 2, 2, '是', '', 4, NULL);
INSERT INTO `tiezi` VALUES (33, '2024-04-16 11:22:15', '标题3', 'file/tieziFengmian3.jpg,file/tieziFengmian4.jpg,file/tieziFengmian5.jpg', '内容分类3', '内容详情3', '2024-04-16', '用户账号3', '用户姓名3', 3, 3, 3, '是', '', 5, NULL);
INSERT INTO `tiezi` VALUES (34, '2024-04-16 11:22:15', '标题4', 'file/tieziFengmian4.jpg,file/tieziFengmian5.jpg,file/tieziFengmian6.jpg', '内容分类4', '内容详情4', '2024-04-16', '用户账号4', '用户姓名4', 4, 4, 4, '是', '', 4, NULL);
INSERT INTO `tiezi` VALUES (35, '2024-04-16 11:22:15', '标题5', 'file/tieziFengmian5.jpg,file/tieziFengmian6.jpg,file/tieziFengmian7.jpg', '内容分类5', '内容详情5', '2024-04-16', '用户账号5', '用户姓名5', 5, 5, 5, '是', '', 5, NULL);
INSERT INTO `tiezi` VALUES (36, '2024-04-16 11:22:15', '标题6', 'file/tieziFengmian6.jpg,file/tieziFengmian7.jpg,file/tieziFengmian8.jpg', '内容分类6', '内容详情6', '2024-04-16', '用户账号6', '用户姓名6', 6, 6, 6, '是', '', 7, NULL);
INSERT INTO `tiezi` VALUES (1713238190167, '2024-04-16 11:29:49', '阿是擦擦vDVD深V', 'file/1713238184022.jpeg', '内容分类1', '<p>阿是VCD啊v都是VS的VS的VS大V</p>', '2024-04-16', '123123', '张三', 1, 0, 1, '是', '审核通过', 5, NULL);
INSERT INTO `tiezi` VALUES (1714112169253, '2024-04-26 14:16:09', '1', '', '内容分类1', '<p>111</p>', '2024-04-26', '11', '11', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169475, '2024-04-26 14:16:09', '1', '', '内容分类1', '<p>111</p>', '2024-04-26', '11', '11', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169620, '2024-04-26 14:16:09', '1', '', '内容分类1', '<p>111</p>', '2024-04-26', '11', '11', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169640, '2024-04-26 14:16:09', '1', '', '', '<p>1</p>', '2024-04-26', '1', '1', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169649, '2024-04-26 14:16:09', '1', '', '内容分类1', '<p>111</p>', '2024-04-26', '11', '11', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169701, '2024-04-26 14:16:09', '1', '', '', '<p>1</p>', '2024-04-26', '1', '1', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169824, '2024-04-26 14:16:09', '1', '', '', '<p>1</p>', '2024-04-26', '1', '1', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169894, '2024-04-26 14:16:09', '1', '', '内容分类1', '<p>111</p>', '2024-04-26', '11', '11', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112169898, '2024-04-26 14:16:09', '1', '', '', '<p>1</p>', '2024-04-26', '1', '1', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112170094, '2024-04-26 14:16:09', '1', '', '', '<p>1</p>', '2024-04-26', '1', '1', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112170118, '2024-04-26 14:16:09', '1', '', '内容分类1', '<p>111</p>', '2024-04-26', '11', '11', 0, 0, 0, '待审核', '', 0, '');
INSERT INTO `tiezi` VALUES (1714112214698, '2024-04-26 14:16:53', '111', 'file/1714112196582.jpeg', '内容分类2', '<p>大大大</p>', '2024-04-26', '11', '11', 0, 0, 0, '是', '通过', 18, 'file/1714112208898.txt');
INSERT INTO `tiezi` VALUES (1714114560290, '2024-04-26 14:55:59', '这是用户11发布的测试帖子', 'file/1714114539667.jpeg', '内容分类1', '<p>帖子帖子！</p>', '2024-04-26', '11', '11', 0, 0, 0, '是', '好的，通过', 4, 'file/1714114551187.docx');
INSERT INTO `tiezi` VALUES (1714114772042, '2024-04-26 14:59:31', '用户11测试上传附件', 'file/1714114755791.jpg', '内容分类1', '<p>1111</p>', '2024-04-26', '11', '11', 0, 0, 0, '是', '111', 3, 'file/1714114767062.docx');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `tablename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表名',
  `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '新增时间',
  `expiratedtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'token表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES (1, 1713238152941, '123123', 'yonghu', '用户', 'xuwn9mylogpx3devbhctag92x8yzulun', '2024-04-16 11:29:22', '2024-04-16 12:34:23');
INSERT INTO `token` VALUES (2, 1, 'admin', 'users', '管理员', 'tjljj283sbfkmo9n7j6otezak5ew3585', '2024-04-16 11:30:20', '2024-04-26 15:59:57');
INSERT INTO `token` VALUES (3, 1713238328748, '001', 'yonghu', '用户', '0vwkcwkze28f44ha3sgdefte5ld3i201', '2024-04-16 11:32:15', '2024-04-16 12:32:16');
INSERT INTO `token` VALUES (4, 1714103209201, '11', 'yonghu', '用户', '0na684uqjt1ccl6msndruh9xzrfbgst9', '2024-04-26 11:46:54', '2024-04-26 15:58:57');
INSERT INTO `token` VALUES (5, 1714113600544, '22', 'yonghu', '用户', 'vmyu7ja6e59jtszh972n4zhwu508sd6c', '2024-04-26 14:40:06', '2024-04-26 16:00:20');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '2024-04-16 11:22:15', 'admin', 'admin', '管理员');

-- ----------------------------
-- Table structure for yonghu
-- ----------------------------
DROP TABLE IF EXISTS `yonghu`;
CREATE TABLE `yonghu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `yonghuzhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `yonghumima` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `yonghuxingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `touxiang` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `xingbie` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `dianhuahaoma` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `yonghuzhanghao`(`yonghuzhanghao`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1714113600545 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghu
-- ----------------------------
INSERT INTO `yonghu` VALUES (61, '2024-04-16 11:22:15', '用户账号1', '123456', '用户姓名1', 'file/yonghuTouxiang1.jpg', '男', '19819881111');
INSERT INTO `yonghu` VALUES (62, '2024-04-16 11:22:15', '用户账号2', '123456', '用户姓名2', 'file/yonghuTouxiang2.jpg', '男', '19819881112');
INSERT INTO `yonghu` VALUES (63, '2024-04-16 11:22:15', '用户账号3', '123456', '用户姓名3', 'file/yonghuTouxiang3.jpg', '男', '19819881113');
INSERT INTO `yonghu` VALUES (64, '2024-04-16 11:22:15', '用户账号4', '123456', '用户姓名4', 'file/yonghuTouxiang4.jpg', '男', '19819881114');
INSERT INTO `yonghu` VALUES (65, '2024-04-16 11:22:15', '用户账号5', '123456', '用户姓名5', 'file/yonghuTouxiang5.jpg', '男', '19819881115');
INSERT INTO `yonghu` VALUES (66, '2024-04-16 11:22:15', '用户账号6', '123456', '用户姓名6', 'file/yonghuTouxiang6.jpg', '男', '19819881116');
INSERT INTO `yonghu` VALUES (1713238152941, '2024-04-16 11:29:12', '123123', '123456', '张三', 'file/1713238147335.jpeg', '男', '13548235685');
INSERT INTO `yonghu` VALUES (1713238328748, '2024-04-16 11:32:08', '001', '123456', '李斯', 'file/1713238323649.jpg', '男', '13548235685');
INSERT INTO `yonghu` VALUES (1714103209201, '2024-04-26 11:46:49', '11', '11', '11', '', '', NULL);
INSERT INTO `yonghu` VALUES (1714113600544, '2024-04-26 14:40:00', '22', '22', '22', '', '', NULL);

-- ----------------------------
-- Table structure for yonghufankui
-- ----------------------------
DROP TABLE IF EXISTS `yonghufankui`;
CREATE TABLE `yonghufankui`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `yonghuzhanghao` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `fankuixiangqing` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '反馈详情',
  `fankuishijian` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间',
  `shhf` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '回复内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1713238409352 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yonghufankui
-- ----------------------------
INSERT INTO `yonghufankui` VALUES (81, '2024-04-16 11:22:15', '用户账号1', '用户姓名1', '反馈详情1', '2024-04-16 11:22:15', '');
INSERT INTO `yonghufankui` VALUES (82, '2024-04-16 11:22:15', '用户账号2', '用户姓名2', '反馈详情2', '2024-04-16 11:22:15', '');
INSERT INTO `yonghufankui` VALUES (83, '2024-04-16 11:22:15', '用户账号3', '用户姓名3', '反馈详情3', '2024-04-16 11:22:15', '');
INSERT INTO `yonghufankui` VALUES (84, '2024-04-16 11:22:15', '用户账号4', '用户姓名4', '反馈详情4', '2024-04-16 11:22:15', '');
INSERT INTO `yonghufankui` VALUES (85, '2024-04-16 11:22:15', '用户账号5', '用户姓名5', '反馈详情5', '2024-04-16 11:22:15', '');
INSERT INTO `yonghufankui` VALUES (86, '2024-04-16 11:22:15', '用户账号6', '用户姓名6', '反馈详情6', '2024-04-16 11:22:15', '');
INSERT INTO `yonghufankui` VALUES (1713238409351, '2024-04-16 11:33:28', '001', '李斯', '阿是VCD啊深V收到VS放大VB说法办法是对吧说法', '2024-04-16 11:33:38', '好的已经处理');

SET FOREIGN_KEY_CHECKS = 1;
