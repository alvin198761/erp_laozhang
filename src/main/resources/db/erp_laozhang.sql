/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : erp_laozhang

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2018-11-27 17:03:41
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收寄信息';

-- ----------------------------
-- Records of address
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开票信息';

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for `cus_person`
-- ----------------------------
DROP TABLE IF EXISTS `cus_person`;
CREATE TABLE `cus_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `address_id` bigint(20) DEFAULT NULL COMMENT '收寄方ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户联系人';

-- ----------------------------
-- Records of cus_person
-- ----------------------------

-- ----------------------------
-- Table structure for `inbound`
-- ----------------------------
DROP TABLE IF EXISTS `inbound`;
CREATE TABLE `inbound` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` varchar(500) DEFAULT NULL COMMENT '产品id',
  `date` datetime DEFAULT NULL COMMENT '入库日期',
  `ticket_type` tinyint(4) DEFAULT NULL COMMENT '发票类型',
  `ticket_status` tinyint(4) DEFAULT NULL COMMENT '发票状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库记录';

-- ----------------------------
-- Records of inbound
-- ----------------------------

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
  `status` tinyint(4) DEFAULT NULL COMMENT '送货状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库记录';

-- ----------------------------
-- Records of outbound
-- ----------------------------

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
  `sell_price_yes` float DEFAULT NULL COMMENT '售卖价(含税)',
  `sell_price_no` float DEFAULT NULL COMMENT '售卖价(不含税)',
  `mark_price_yes` float DEFAULT NULL COMMENT '市场价(不含税)',
  `mark_price_no` float DEFAULT NULL COMMENT '市场价(含税)',
  `price_mode` tinyint(4) DEFAULT NULL COMMENT '价格类型',
  `vendor_id` bigint(20) DEFAULT NULL COMMENT '货物来源',
  `pic1` varchar(100) DEFAULT NULL COMMENT '产品图片1',
  `pic2` varchar(100) DEFAULT NULL COMMENT '产品图片2',
  `pic3` varchar(100) DEFAULT NULL COMMENT '产品图片3',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------

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
  `priice` float DEFAULT NULL COMMENT '单价',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '单据状态',
  `num` float DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报价';

-- ----------------------------
-- Records of quote
-- ----------------------------

-- ----------------------------
-- Table structure for `ticket`
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `tax_type` varchar(20) DEFAULT NULL COMMENT '税收类型',
  `prod_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `target_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `target_id` bigint(20) DEFAULT NULL COMMENT '收寄方id',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `num` float DEFAULT NULL COMMENT '数量',
  `price` float DEFAULT NULL COMMENT '单价',
  `total` float DEFAULT NULL COMMENT '总价',
  `ticket_type` tinyint(4) DEFAULT NULL COMMENT '发票类型',
  `rate` double DEFAULT NULL COMMENT '税率',
  `type` char(10) DEFAULT NULL COMMENT '进出类型',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进销项发票录入';

-- ----------------------------
-- Records of ticket
-- ----------------------------

-- ----------------------------
-- Table structure for `type_dict`
-- ----------------------------
DROP TABLE IF EXISTS `type_dict`;
CREATE TABLE `type_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类型字典';

-- ----------------------------
-- Records of type_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `vendor`
-- ----------------------------
DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_no` varchar(100) DEFAULT NULL COMMENT '供应商编号',
  `vendor_name` varchar(200) DEFAULT NULL COMMENT '供应商名称',
  `address` varchar(200) DEFAULT NULL COMMENT '供应商地址',
  `level` tinyint(4) DEFAULT NULL COMMENT '供应商级别',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `concat_id` bigint(20) DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商';

-- ----------------------------
-- Records of vendor
-- ----------------------------
