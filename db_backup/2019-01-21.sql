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
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (11,2,'ka001','ka001','ka001',11,'ka001'),(13,1,'ga002','ga002','ga002',8,'ga002');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_dept`
--

LOCK TABLES `admin_dept` WRITE;
/*!40000 ALTER TABLE `admin_dept` DISABLE KEYS */;
INSERT INTO `admin_dept` VALUES (1,0,'总部',1,'初始');
/*!40000 ALTER TABLE `admin_dept` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_func`
--

LOCK TABLES `admin_func` WRITE;
/*!40000 ALTER TABLE `admin_func` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_func` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_menu`
--

LOCK TABLES `admin_menu` WRITE;
/*!40000 ALTER TABLE `admin_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_menu_rule`
--

LOCK TABLES `admin_menu_rule` WRITE;
/*!40000 ALTER TABLE `admin_menu_rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_menu_rule` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_role`
--

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_role_dept`
--

LOCK TABLES `admin_role_dept` WRITE;
/*!40000 ALTER TABLE `admin_role_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_role_func`
--

LOCK TABLES `admin_role_func` WRITE;
/*!40000 ALTER TABLE `admin_role_func` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_role_func` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `admin_sys_user`
--

LOCK TABLES `admin_sys_user` WRITE;
/*!40000 ALTER TABLE `admin_sys_user` DISABLE KEYS */;
INSERT INTO `admin_sys_user` VALUES (1004,'admin','21232f297a57a5a743894a0e4a801fc3',NULL,1,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `admin_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `base_dict`
--

LOCK TABLES `base_dict` WRITE;
/*!40000 ALTER TABLE `base_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_dict` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='开票信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (11,'001','001客户','001地址',54,'001');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='入库记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbound`
--

LOCK TABLES `inbound` WRITE;
/*!40000 ALTER TABLE `inbound` DISABLE KEYS */;
/*!40000 ALTER TABLE `inbound` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='出库记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outbound`
--

LOCK TABLES `outbound` WRITE;
/*!40000 ALTER TABLE `outbound` DISABLE KEYS */;
/*!40000 ALTER TABLE `outbound` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (2,'sdfdsfsfd','sdfsdfsdf',1,1),(3,'111','111',1,1),(4,'122','122',1,2),(5,'刘春','01067703993',4,2),(6,'刘伟','13381391850',4,2),(7,'办公室','01087393992',5,2),(8,'潘旭','13810168138',5,2),(9,'张静袆','13381035595',5,2),(10,'高飞','13520723376',4,1),(11,'高飞（座机）','01084641097',4,1),(12,'张春红','13552664976',4,1),(13,'高景红','13691505859',5,1),(14,'高利文','15810277809',5,1),(15,'高利文（座机）','01082071275',5,1);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='产品信息记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_record`
--

LOCK TABLES `prod_record` WRITE;
/*!40000 ALTER TABLE `prod_record` DISABLE KEYS */;
INSERT INTO `prod_record` VALUES (30,8,276,12,23,4,3),(31,8,276,12,23,5,3);
/*!40000 ALTER TABLE `prod_record` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (8,'huo-001','税收分类001','huo-001','huo-001','huo-001','pk',122,133,234,'含税','售卖价（含税）',8,'f32b0e2e-8f4c-46db-be66-755c27224fe1.jpg','8b1b87ba-cbec-40b8-9813-eca656e06634.jpg','78e9d0bc-4663-41b4-ba41-9559a9613705.jpg','huo-001'),(9,'huo002','收税分类002','huo002','huo002','huo002','瓶',12,23,43,'含税','售卖价（含税）',8,NULL,NULL,NULL,'huo002');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='报价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quote`
--

LOCK TABLES `quote` WRITE;
/*!40000 ALTER TABLE `quote` DISABLE KEYS */;
/*!40000 ALTER TABLE `quote` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `tax_category`
--

LOCK TABLES `tax_category` WRITE;
/*!40000 ALTER TABLE `tax_category` DISABLE KEYS */;
INSERT INTO `tax_category` VALUES (3,'税收分类001','001'),(4,'收税分类002','002');
/*!40000 ALTER TABLE `tax_category` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='进销项发票录入';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (4,8,'税收分类001','jinhuo',13,'ga002','增值税专用发票',0,'1',NULL),(5,NULL,'税收分类001','ka001',11,'ka001','增值税普通发票',0,'2',11);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='类型字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_dict`
--

LOCK TABLES `type_dict` WRITE;
/*!40000 ALTER TABLE `type_dict` DISABLE KEYS */;
INSERT INTO `type_dict` VALUES (3,5,'一级代理商'),(4,5,'二级代理商'),(5,6,'售卖价（不含税）'),(6,6,'市场价（含税）'),(9,7,'无票'),(10,7,'增值税普通发票'),(11,8,'未开'),(12,8,'已开'),(14,9,'取消'),(15,9,'转出库'),(16,10,'未送货'),(17,10,'已送'),(18,11,'非金属矿物制品'),(19,11,'分析仪器'),(20,12,'个'),(21,12,'张'),(35,5,'零售'),(36,6,'底价（不含税）'),(37,7,'增值税专用发票'),(38,8,'无票'),(39,9,'执行中'),(41,11,'化学试剂助剂'),(42,12,'袋'),(47,12,'盒'),(48,12,'瓶'),(52,4,'终端客户'),(53,4,'经销商'),(54,4,'个人'),(55,6,'底价（含税）'),(56,6,'市场价（不含税）'),(57,6,'售卖价（含税）'),(58,9,'完成'),(59,12,'pk'),(60,11,'泵'),(61,11,'电子计算机'),(62,11,'电子出版物'),(63,11,'电子工业设备'),(64,11,'电光源'),(65,11,'服装'),(67,11,'纺织产品'),(68,11,'工业仪表'),(69,11,'金属制品'),(70,11,'家具'),(71,11,'教具'),(72,11,'家用厨房电器具'),(73,11,'计算机配套产品'),(74,11,'计算器货币专用设备'),(75,11,'木制品'),(76,11,'其他机械设备'),(77,11,'其他仪器仪表机械'),(78,11,'日用杂品'),(79,11,'日用产品'),(80,11,'软饮料'),(81,11,'生物化学制品'),(82,11,'塑料制品'),(83,11,'试验检测机械'),(84,11,'通用设备'),(85,11,'涂料'),(86,11,'文具'),(87,11,'无机化学原料'),(88,11,'洗涤剂'),(89,11,'橡胶制品'),(90,11,'专项化学用品'),(91,11,'纸制品'),(120,1,'北京泓润泰丰贸易'),(121,2,'北京市顺义区大孙各庄镇大崔各庄村宽明街35号'),(122,3,'张延鹏');
/*!40000 ALTER TABLE `type_dict` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (8,'1001','1001供应商','1001地址',35,'1001');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-21 18:31:14
