CREATE DATABASE bio_file DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
Use bio_file;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
DROP TABLE IF EXISTS `bio_user_images`;
CREATE TABLE `bio_user_images` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `object_name` varchar(50)  DEFAULT NULL COMMENT '文件名',
  `bucket_name` varchar(50)  DEFAULT NULL COMMENT '桶名',
  `url` varchar(255)  DEFAULT NULL COMMENT '资源URL',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户图片资源表';

-- ----------------------------
DROP TABLE IF EXISTS `bio_advert_resource`;
CREATE TABLE `bio_advert_resource` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `object_name` varchar(50)  DEFAULT NULL COMMENT '文件名',
  `bucket_name` varchar(50)  DEFAULT NULL COMMENT '桶名',
  `url` varchar(255)  DEFAULT NULL COMMENT '资源URL',
  `type` varchar(5)  DEFAULT NULL COMMENT '资源类型（0：图片 1：视频 2 其他 ）',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='广告文件和升级文件资源表';

-- ----------------------------
DROP TABLE IF EXISTS `bio_opendoor_images`;
CREATE TABLE `bio_opendoor_images` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `object_name` varchar(50)  DEFAULT NULL COMMENT '文件名',
  `bucket_name` varchar(50)  DEFAULT NULL COMMENT '桶名',
  `url` varchar(255)  DEFAULT NULL COMMENT '资源URL',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开门图片资源表';

SET FOREIGN_KEY_CHECKS = 1;
