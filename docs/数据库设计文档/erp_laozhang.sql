/*
Navicat MySQL Data Transfer

Source Server         : webosapp_db
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : erp_laozhang

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-01-14 17:02:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `target_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `target_addr` varchar(200) DEFAULT NULL COMMENT '地址',
  `target_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `target_id` bigint(20) DEFAULT NULL COMMENT '收寄方id',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='收寄信息';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', '1', '123213321', '123213213', '123213213', '1', 'asdfsadfdsf');
INSERT INTO `address` VALUES ('2', '1', 'dfsadsfdsf', 'adfssdfsdf', 'sdfasdfdsf', '2', 'asdfdsffds');
INSERT INTO `address` VALUES ('3', '2', '123213', '324adfsdsfdsf', '2234', '1', 'gfddsfg');
INSERT INTO `address` VALUES ('4', '2', 'dasdfsdsf', 'assdfdsf', 'fsdasdf', '2', 'asdffdssfadsf');
INSERT INTO `address` VALUES ('5', '1', '客户联系方式003', '客户联系方式003', '客户联系方式003', '3', '客户联系方式003');
INSERT INTO `address` VALUES ('6', '2', '供应商联系方式003', '供应商联系方式003', '供应商联系方式003', '2', '供应商联系方式003');

-- ----------------------------
-- Table structure for `admin_dept`
-- ----------------------------
DROP TABLE IF EXISTS `admin_dept`;
CREATE TABLE `admin_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `enable` tinyint(4) DEFAULT NULL COMMENT '可用标志',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_dept
-- ----------------------------
INSERT INTO `admin_dept` VALUES ('1', '0', '总部', '1', '初始');

-- ----------------------------
-- Table structure for `admin_func`
-- ----------------------------
DROP TABLE IF EXISTS `admin_func`;
CREATE TABLE `admin_func` (
  `func_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父结点ID',
  `tip` varchar(50) DEFAULT NULL COMMENT '提示信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `order_num` smallint(5) DEFAULT NULL COMMENT '排序编号',
  PRIMARY KEY (`func_id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of admin_func
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_menu`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(150) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `tip` varchar(255) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_menu_rule`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu_rule`;
CREATE TABLE `admin_menu_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `did` bigint(20) DEFAULT NULL,
  `mid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_menu_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_dept`;
CREATE TABLE `admin_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `dept_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role_func`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_func`;
CREATE TABLE `admin_role_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `user_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_func
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_sys_user`;
CREATE TABLE `admin_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '会员编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_sys_user
-- ----------------------------
INSERT INTO `admin_sys_user` VALUES ('1004', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, '1', '1', null, null, null);

-- ----------------------------
-- Table structure for `base_dict`
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_key` bigint(20) DEFAULT NULL COMMENT '键',
  `data_value` varchar(50) DEFAULT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `type_code` bigint(20) DEFAULT NULL COMMENT '分类',
  `type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `author` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `bill`
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_id` bigint(20) DEFAULT NULL COMMENT '供应商',
  `bank` varchar(100) DEFAULT NULL COMMENT '开户行',
  `account` varchar(100) DEFAULT NULL COMMENT '账号',
  `taxpayer_no` varchar(100) DEFAULT NULL COMMENT '纳税人识别号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='开票信息';

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '1', 'afdssdfadfs', 'asdf', 'adsffdsdsf', 'adsfdfs');
INSERT INTO `bill` VALUES ('2', '2', 'asdf', '2344', 'fadfs', 'adf');
INSERT INTO `bill` VALUES ('3', '3', '开票--003', '开票--003', '开票--003', '开票--003');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_no` varchar(100) DEFAULT NULL COMMENT '客户编号',
  `cus_name` varchar(200) DEFAULT NULL COMMENT '单位名称',
  `address` varchar(200) DEFAULT NULL COMMENT '客户地址',
  `cus_type` tinyint(4) DEFAULT NULL COMMENT '客户性质',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='客户';

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'qqq', 'sdf', 'sfddsf', '7', 'sdfsdfsdf');
INSERT INTO `customer` VALUES ('2', 'afds', 'afds', 'adsffassdf', '1', 'adsfsfda');
INSERT INTO `customer` VALUES ('3', '客户——001', '客户——001', '客户——001', '34', '客户——001');

-- ----------------------------
-- Table structure for `inbound`
-- ----------------------------
DROP TABLE IF EXISTS `inbound`;
CREATE TABLE `inbound` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` varchar(500) DEFAULT NULL COMMENT '产品id',
  `date` datetime DEFAULT NULL COMMENT '入库日期',
  `ticket_type` varchar(50) DEFAULT NULL COMMENT '发票类型',
  `ticket_status` varchar(50) DEFAULT NULL COMMENT '发票状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='入库记录';

-- ----------------------------
-- Records of inbound
-- ----------------------------
INSERT INTO `inbound` VALUES ('1', '3', '1970-01-01 08:00:00', '发票类型2', '发票状态1', '133333');
INSERT INTO `inbound` VALUES ('2', '2', '2019-01-14 00:00:00', '发票类型1', '发票状态2', 'asdfdsf');
INSERT INTO `inbound` VALUES ('3', '4', '2019-01-14 16:32:42', '发票类型3--', '发票状态3--', '供应商联系方式003');

-- ----------------------------
-- Table structure for `outbound`
-- ----------------------------
DROP TABLE IF EXISTS `outbound`;
CREATE TABLE `outbound` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `prod_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `price` float DEFAULT NULL COMMENT '单价',
  `num` float DEFAULT NULL COMMENT '数量',
  `total` float DEFAULT NULL COMMENT '总价',
  `status` varchar(50) DEFAULT NULL COMMENT '送货状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='出库记录';

-- ----------------------------
-- Records of outbound
-- ----------------------------
INSERT INTO `outbound` VALUES ('1', '2', '2', '0', '0', '0', '送货状态2', '12132213213');
INSERT INTO `outbound` VALUES ('2', '1', '3', '123', '234', '28782', '送货状态2', 'adsf');
INSERT INTO `outbound` VALUES ('3', '3', '4', '12', '23', '276', '送货状态3--', '出库测试');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '联系人姓名',
  `phone_no` varchar(200) DEFAULT NULL COMMENT '联系人电话',
  `bus_id` bigint(20) DEFAULT NULL COMMENT '业务id',
  `bus_type` tinyint(4) DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='联系人';

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('2', 'sdfdsfsfd', 'sdfsdfsdf', '1', '1');
INSERT INTO `person` VALUES ('3', '111', '111', '1', '1');
INSERT INTO `person` VALUES ('4', '122', '122', '1', '2');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_no` varchar(100) DEFAULT NULL COMMENT '货号',
  `tax_type` varchar(100) DEFAULT NULL COMMENT '税收类型',
  `prod_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `spec_no` varchar(50) DEFAULT NULL COMMENT '规格型号',
  `note` varchar(500) DEFAULT NULL COMMENT '产品说明',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `price` float DEFAULT NULL COMMENT '底价',
  `sell_price` float DEFAULT NULL COMMENT '售卖价(含税)',
  `mark_price` float DEFAULT NULL COMMENT '市场价(不含税)',
  `has_tax` varchar(50) DEFAULT NULL COMMENT '市场价(含税)',
  `price_mode` varchar(50) DEFAULT NULL COMMENT '价格类型',
  `vendor_id` bigint(20) DEFAULT NULL COMMENT '货物来源',
  `pic1` varchar(100) DEFAULT NULL COMMENT '产品图片1',
  `pic2` varchar(100) DEFAULT NULL COMMENT '产品图片2',
  `pic3` varchar(100) DEFAULT NULL COMMENT '产品图片3',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('2', '12', '税收分类2', '123', '1', '2213wer', '张', '123', '234', '543', '含税', '价格类型2', '1', '891af57a-8ace-4092-a6ca-6b70087735d3.png', '6ca4e891-422c-4937-9319-3b9548b7d4b6.jpg', 'f71cf19a-2419-4c73-bcaa-03cf19e29885.bmp', 'asdfdfsa');
INSERT INTO `product` VALUES ('3', '2223', '税收分类2', '123', '234', '234', '张', '123', '234', '34', '含税', '价格类型2', '1', '1e7d9f35-ab2a-4dfd-a27a-7ee628de49a8.png', '1db4bf5f-c93c-48b1-959d-48c3dd7b8944.gif', '82b2d90d-f5dd-4bb2-a5b7-482bc5f2123c.png', '345');
INSERT INTO `product` VALUES ('4', '产品003', '税收分类3--', '产品003', '产品003', '产品003', '袋', '12', '23', '354', '含税', '价格类型3--', '3', '71776368-e1ed-4d76-8120-407c160a9042.bmp', '92088967-487b-4128-82e0-fbd70467acd2.jpg', 'd0c5dcb2-9dd0-4c04-9ccb-44f9e0ef8cfb.gif', '产品003产品003产品003');

-- ----------------------------
-- Table structure for `quote`
-- ----------------------------
DROP TABLE IF EXISTS `quote`;
CREATE TABLE `quote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `date` datetime DEFAULT NULL COMMENT '报价日期',
  `prod_id` tinyint(4) DEFAULT NULL COMMENT '产品id',
  `total` float DEFAULT NULL COMMENT '总价',
  `price` float DEFAULT NULL COMMENT '单价',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` varchar(50) DEFAULT NULL COMMENT '单据状态',
  `num` float DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='报价';

-- ----------------------------
-- Records of quote
-- ----------------------------
INSERT INTO `quote` VALUES ('1', '1', '2019-01-14 13:32:45', '2', '3', '1', 'adsf', '单据状态2', '3');
INSERT INTO `quote` VALUES ('2', '1', '2019-01-14 13:50:42', '2', '516', '12', 'sgdf', '单据状态2', '43');
INSERT INTO `quote` VALUES ('3', '3', '2019-01-14 16:33:27', '4', '41208', '12', '报价测试', '单据状态3--', '3434');

-- ----------------------------
-- Table structure for `ticket`
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `tax_type` varchar(50) DEFAULT NULL COMMENT '税收类型',
  `prod_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `target_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `target_id` bigint(20) DEFAULT NULL COMMENT '收寄方id',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `num` float DEFAULT NULL COMMENT '数量',
  `price` float DEFAULT NULL COMMENT '单价',
  `total` float DEFAULT NULL COMMENT '总价',
  `ticket_type` varchar(50) DEFAULT NULL COMMENT '发票类型',
  `rate` double DEFAULT NULL COMMENT '税率',
  `type` tinyint(10) DEFAULT NULL COMMENT '进出类型',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='进销项发票录入';

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', '1', '税收分类2', '2', 'dfs', '1', 'asdf', '23', '21', '483', '发票类型2', '0.6', '1', null);
INSERT INTO `ticket` VALUES ('2', '1', '税收分类2', '2', 'afd', '1', 'afd', '0', '0', '0', '发票类型2', '0', '1', null);
INSERT INTO `ticket` VALUES ('3', null, '税收分类1', '2', 'adsf', '1', 'asfdfds', '32', '12', '384', '发票类型2', '0.6', '1', '1');
INSERT INTO `ticket` VALUES ('4', null, '税收分类2', '2', 'afdfdassfd', '1', 'asdfdsffds', '23', '2', '46', '发票类型2', '0.6', '2', '1');
INSERT INTO `ticket` VALUES ('5', null, '税收分类2', '2', 'adf', '1', 'afdf', '123', '123', '15129', '发票类型2', '0.5', '2', '1');

-- ----------------------------
-- Table structure for `type_dict`
-- ----------------------------
DROP TABLE IF EXISTS `type_dict`;
CREATE TABLE `type_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='类型字典';

-- ----------------------------
-- Records of type_dict
-- ----------------------------
INSERT INTO `type_dict` VALUES ('1', '4', '客户性质1');
INSERT INTO `type_dict` VALUES ('2', '4', '客户性质2');
INSERT INTO `type_dict` VALUES ('3', '5', '供应商级别1');
INSERT INTO `type_dict` VALUES ('4', '5', '供应商级别2');
INSERT INTO `type_dict` VALUES ('5', '6', '价格类型1');
INSERT INTO `type_dict` VALUES ('6', '6', '价格类型2');
INSERT INTO `type_dict` VALUES ('7', '4', '客户性质3');
INSERT INTO `type_dict` VALUES ('8', '4', '客户性质4');
INSERT INTO `type_dict` VALUES ('9', '7', '发票类型1');
INSERT INTO `type_dict` VALUES ('10', '7', '发票类型2');
INSERT INTO `type_dict` VALUES ('11', '8', '发票状态1');
INSERT INTO `type_dict` VALUES ('12', '8', '发票状态2');
INSERT INTO `type_dict` VALUES ('14', '9', '单据状态1');
INSERT INTO `type_dict` VALUES ('15', '9', '单据状态2');
INSERT INTO `type_dict` VALUES ('16', '10', '送货状态1');
INSERT INTO `type_dict` VALUES ('17', '10', '送货状态2');
INSERT INTO `type_dict` VALUES ('18', '11', '税收分类1');
INSERT INTO `type_dict` VALUES ('19', '11', '税收分类2');
INSERT INTO `type_dict` VALUES ('20', '12', '个');
INSERT INTO `type_dict` VALUES ('21', '12', '张');
INSERT INTO `type_dict` VALUES ('34', '4', '客户性质5--');
INSERT INTO `type_dict` VALUES ('35', '5', '供应商级别3--');
INSERT INTO `type_dict` VALUES ('36', '6', '价格类型3--');
INSERT INTO `type_dict` VALUES ('37', '7', '发票类型3--');
INSERT INTO `type_dict` VALUES ('38', '8', '发票状态3--');
INSERT INTO `type_dict` VALUES ('39', '9', '单据状态3--');
INSERT INTO `type_dict` VALUES ('40', '10', '送货状态3--');
INSERT INTO `type_dict` VALUES ('41', '11', '税收分类3--');
INSERT INTO `type_dict` VALUES ('42', '12', '袋');
INSERT INTO `type_dict` VALUES ('43', '1', '老张的公司1');
INSERT INTO `type_dict` VALUES ('44', '2', '诺亚方舟1');
INSERT INTO `type_dict` VALUES ('45', '3', '老张1');

-- ----------------------------
-- Table structure for `vendor`
-- ----------------------------
DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_no` varchar(100) DEFAULT NULL COMMENT '供应商编号',
  `vendor_name` varchar(200) DEFAULT NULL COMMENT '供应商名称',
  `address` varbinary(200) DEFAULT NULL COMMENT '供应商地址',
  `level` tinyint(4) DEFAULT NULL COMMENT '供应商级别',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='供应商';

-- ----------------------------
-- Records of vendor
-- ----------------------------
INSERT INTO `vendor` VALUES ('1', '123', '213', 0x323133, '3', null);
INSERT INTO `vendor` VALUES ('2', 'adfs', 'adf', 0x61647366, '4', 'adfsafds');
INSERT INTO `vendor` VALUES ('3', '供应商003', '供应商003', 0xE4BE9BE5BA94E59586303033, '35', '供应商003');
