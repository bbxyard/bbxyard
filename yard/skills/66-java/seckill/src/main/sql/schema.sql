
-- 创建数据库
-- CREATE DATABASE demo_seckill DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 切换数据库
use demo_seckill;
use demo_seckill;
CREATE TABLE seckill(
    `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
    `name` varchar(120) NOT NULL COMMENT '商品名称',
    `number` int NOT NULL COMMENT '库存数量',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP	COMMENT '秒杀开始时间',
    `end_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
    PRIMARY KEY(seckill_id),
    key idx_start_time(start_time),
    key idx_end_time(end_time),
    key idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='秒杀库存表';

-- 初始化数据
INSERT into seckill(name,number,start_time,end_time)
VALUES
    ('6000元秒杀iPhoneX',100,'2019-08-26 00:00:00','2019-08-29 00:00:00'),
    ('3000元秒杀iPad Mini 4',100,'2019-08-26 00:00:00','2019-08-29 00:00:00'),
    ('8000元秒杀Mac Book Pro',100,'2019-08-26 00:00:00','2019-08-29 00:00:00'),
    ('18000元秒杀Mac Pro',100,'2019-08-26 00:00:00','2019-08-29 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关信息(简化为手机号
CREATE TABLE success_killed(
    `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
    `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
    `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY key(seckill_id,user_phone), /* 联合主键 */
    KEY idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀成功明细表';

