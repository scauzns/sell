/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-02-28 00:18:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_cover` varchar(255) DEFAULT NULL,
  `food_id` int(11) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `content` varchar(500) NOT NULL,
  `star` int(11) DEFAULT '1' COMMENT '评分，3以上为好评',
  `status` tinyint(4) DEFAULT '0' COMMENT '0 有效  1无效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id` int(11) NOT NULL COMMENT '类目id',
  `title` varchar(50) NOT NULL COMMENT '食物名称',
  `f_desc` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `price` decimal(10,2) NOT NULL COMMENT '售价',
  `cover` varchar(100) DEFAULT NULL COMMENT '食品封面图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for food_category
-- ----------------------------
DROP TABLE IF EXISTS `food_category`;
CREATE TABLE `food_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_name` varchar(50) DEFAULT NULL COMMENT '类目名称',
  `c_desc` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0有效 1无效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='食物分类';

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `send_cost` decimal(10,2) DEFAULT '0.00' COMMENT '运费',
  `total_amount` decimal(10,2) NOT NULL COMMENT '订单金额',
  `pay_money` decimal(10,2) DEFAULT '0.00' COMMENT '实付金额',
  `receiver` varchar(255) DEFAULT NULL COMMENT '收货人',
  `mobile` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '订单状态：1 未支付，2 已支付、待受理，3 已受理，待评论，4 已完成, 5失效',
  `expire_time` timestamp NULL DEFAULT NULL COMMENT '订单过期时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` varchar(255) NOT NULL,
  `order_id` varchar(255) NOT NULL,
  `food_id` int(11) NOT NULL,
  `food_title` varchar(255) DEFAULT NULL,
  `food_cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `sell_price` decimal(10,2) NOT NULL COMMENT '售价',
  `number` int(10) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sales_statistics
-- ----------------------------
DROP TABLE IF EXISTS `sales_statistics`;
CREATE TABLE `sales_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_id` varchar(255) NOT NULL COMMENT '商品id 或 订单统计id',
  `type` int(11) NOT NULL COMMENT '统计类型：1商品按日统计、2商品按月统计、3订单按日统计、4订单按月统计',
  `sales` varchar(4096) NOT NULL COMMENT '统计数据(Json格式)',
  `total` double(11,0) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '用户昵称',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别：0 男 1 女',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `user_type` tinyint(11) DEFAULT '1' COMMENT '1 普通用户  2 管理员',
  `cover` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `status` tinyint(4) DEFAULT '0' COMMENT '0 有效  1冻结',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `receiver` varchar(255) DEFAULT NULL COMMENT '接收者',
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `default_choose` int(11) NOT NULL DEFAULT '0' COMMENT '默认选择： 1是  0否',
  `status` int(11) DEFAULT '0' COMMENT '0 有效 1无效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for user_search
-- ----------------------------
DROP TABLE IF EXISTS `user_search`;
CREATE TABLE `user_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL COMMENT '搜索的内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
