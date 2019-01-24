-- MySQL dump 10.13  Distrib 8.0.0-dmr, for Win64 (x86_64)
--
-- Host: 47.98.132.116    Database: erp_laozhang
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `target_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `target_addr` varchar(200) DEFAULT NULL COMMENT '地址',
  `target_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `target_id` bigint(20) DEFAULT NULL COMMENT '收寄方id',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='收寄信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_dept`
--

DROP TABLE IF EXISTS `admin_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `enable` tinyint(4) DEFAULT NULL COMMENT '可用标志',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_func`
--

DROP TABLE IF EXISTS `admin_func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_menu`
--

DROP TABLE IF EXISTS `admin_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(150) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `tip` varchar(255) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_menu_rule`
--

DROP TABLE IF EXISTS `admin_menu_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_menu_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `did` bigint(20) DEFAULT NULL,
  `mid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_role_dept`
--

DROP TABLE IF EXISTS `admin_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `dept_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_role_func`
--

DROP TABLE IF EXISTS `admin_role_func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_role_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `user_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_sys_user`
--

DROP TABLE IF EXISTS `admin_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base_dict`
--

DROP TABLE IF EXISTS `base_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bus_id` bigint(20) DEFAULT NULL COMMENT '供应商',
  `bank` varchar(100) DEFAULT NULL COMMENT '开户行',
  `account` varchar(100) DEFAULT NULL COMMENT '账号',
  `taxpayer_no` varchar(100) DEFAULT NULL COMMENT '纳税人识别号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `bus_type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='开票信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_no` varchar(100) DEFAULT NULL COMMENT '客户编号',
  `cus_name` varchar(200) DEFAULT NULL COMMENT '单位名称',
  `address` varchar(200) DEFAULT NULL COMMENT '客户地址',
  `cus_type` tinyint(4) DEFAULT NULL COMMENT '客户性质',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='客户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inbound`
--

DROP TABLE IF EXISTS `inbound`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inbound` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` varchar(500) DEFAULT NULL COMMENT '产品id',
  `date` datetime DEFAULT NULL COMMENT '入库日期',
  `ticket_type` varchar(50) DEFAULT NULL COMMENT '发票类型',
  `ticket_status` varchar(50) DEFAULT NULL COMMENT '发票状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='入库记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `outbound`
--

DROP TABLE IF EXISTS `outbound`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `outbound` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `status` varchar(50) DEFAULT NULL COMMENT '送货状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='出库记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '联系人姓名',
  `phone_no` varchar(200) DEFAULT NULL COMMENT '联系人电话',
  `bus_id` bigint(20) DEFAULT NULL COMMENT '业务id',
  `bus_type` tinyint(4) DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='联系人';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prod_record`
--

DROP TABLE IF EXISTS `prod_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prod_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `prod_id` tinyint(4) DEFAULT NULL COMMENT '产品id',
  `total` float DEFAULT NULL COMMENT '总价',
  `priice` float DEFAULT NULL COMMENT '单价',
  `num` float DEFAULT NULL COMMENT '数量',
  `bus_id` bigint(20) DEFAULT NULL COMMENT '业务ID',
  `bus_type` tinyint(4) DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='产品信息记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quote`
--

DROP TABLE IF EXISTS `quote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户id',
  `date` datetime DEFAULT NULL COMMENT '报价日期',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` varchar(50) DEFAULT NULL COMMENT '单据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='报价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tax_category`
--

DROP TABLE IF EXISTS `tax_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tax_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `tax_code` varchar(100) DEFAULT NULL COMMENT '税收编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='税收分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_id` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `tax_type` varchar(20) DEFAULT NULL COMMENT '税收类型',
  `target_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `target_id` bigint(20) DEFAULT NULL COMMENT '收寄方id',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `ticket_type` varchar(50) DEFAULT NULL COMMENT '发票类型',
  `rate` double DEFAULT NULL COMMENT '税率',
  `type` varchar(50) DEFAULT NULL COMMENT '进出类型',
  `cus_id` bigint(20) DEFAULT NULL COMMENT '客户Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='进销项发票录入';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `type_dict`
--

DROP TABLE IF EXISTS `type_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='类型字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vendor_no` varchar(100) DEFAULT NULL COMMENT '供应商编号',
  `vendor_name` varchar(200) DEFAULT NULL COMMENT '供应商名称',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '供应商地址',
  `level` tinyint(4) DEFAULT NULL COMMENT '供应商级别',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='供应商';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-24 18:13:22
