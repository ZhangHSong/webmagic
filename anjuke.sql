use webmagic;

#安居客
drop table main_information;
CREATE TABLE `main_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,#id
  `number` bigint unsigned NOT NULL ,#编号
  `site` varchar(255) NOT  NULL,	#网站来源
  `name` varchar(255) NOT  NULL,	#名称
  `city` varchar(255) ,	#城市
  `area` double NOT NULL,	#面积
  `house_type` varchar(255) NOT NULL,		#房型
  `rent_way` varchar(255) NOT NULL,		#出租方式
  `price` double NOT NULL,	#价格
  `payment_method` varchar(255) NOT NULL,	#付款方式
  `address` varchar(255) NOT NULL,	#地址
  `row_col` varchar(255),	#坐标
  `url` varchar(255),	#网址
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8;

#临时表
drop table main_score;
CREATE TABLE `main_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,#id
  `name` varchar(255) NOT  NULL,	#名称
  `area` double NOT NULL,	#面积
  `house_type` varchar(255) NOT NULL,		#房型
  `rent_way` varchar(255) NOT NULL,		#出租方式
  `price` double NOT NULL,	#价格
  `payment_method` varchar(255) NOT NULL,	#付款方式
  `address` varchar(255) NOT NULL,	#地址
  `row_col` varchar(255),	#坐标
  `url` varchar(255),	#网址
  `distance` double NOT NULL,		#距离
  `score` double NOT NULL,		#分数
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8;

ALTER TABLE main_score ADD INDEX score_index( score );	#创建分数索引

select * from main_information;
select count(*) from main_information;
select * from main_score;
select count(*) from main_score;

truncate table main_information;
truncate table main_score;






#链家

drop table lianjia_main_information;
CREATE TABLE `lianjia_main_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,#id
  `number` varchar(255)  NOT NULL ,#编号
  `name` varchar(255) NOT  NULL,	#名称
  `city` varchar(255) ,	#城市
  `address` varchar(255) ,	#地址+小区+楼层
  `area` double NOT NULL,		#面积
  `house_type` varchar(255) NOT NULL,		#房型
  `rent_way` varchar(255) NOT NULL,		#出租方式
  `price` double NOT NULL,	#价格
  `payment_method` varchar(255) NOT NULL,	#付款方式
  `contacts` varchar(255) NOT NULL,	#联系人
  `release_time` varchar(255) NOT NULL,	#发布时间
  `description` text ,	#描述
  `url` varchar(255) NOT NULL,	#网址
  `photo` text NOT  NULL,	#图片
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8;

select * from lianjia_main_information;

truncate table lianjia_main_information;

#自如

drop table ziru_main_information;
CREATE TABLE `ziru_main_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,#id
  `number` varchar(255)  NOT NULL ,#编号
  `name` varchar(255) NOT  NULL,	#名称
  `city` varchar(255) ,	#城市
  `address` varchar(255) ,	#地址+小区+楼层
  `area` double NOT NULL,		#面积
  `house_type` varchar(255) NOT NULL,		#房型
  `rent_way` varchar(255) NOT NULL,		#出租方式
  `price` double NOT NULL,	#价格
  `payment_method` varchar(255) NOT NULL,	#付款方式
  `contacts` varchar(255) NOT NULL,	#联系人
  `description` text ,	#描述
  `url` varchar(255) NOT NULL,	#网址
  `photo` text NOT  NULL,	#图片
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8;

select * from ziru_main_information;

truncate table ziru_main_information;


#58同城

drop table five8_main_information;
CREATE TABLE `five8_main_information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,#id
  `name` varchar(255) NOT  NULL,	#名称
  `city` varchar(255) ,	#城市
  `address` varchar(255) ,	#地址+小区+楼层
  `area` double NOT NULL,		#面积
  `house_type` varchar(255) NOT NULL,		#房型
  `rent_way` varchar(255) NOT NULL,		#出租方式
  `price` double NOT NULL,	#价格
  `payment_method` varchar(255) NOT NULL,	#付款方式
  `contacts` varchar(255) NOT NULL,	#联系人
  `release_time` varchar(255) NOT NULL,	#发布时间
  `description` text ,	#描述
  `url` varchar(255) NOT NULL,	#网址
  `photo` text NOT  NULL,	#图片
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8;

select * from five8_main_information;

truncate table five8_main_information;


