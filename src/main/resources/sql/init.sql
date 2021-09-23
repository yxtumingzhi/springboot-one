CREATE TABLE `blade_notice`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `tenant_id`    varchar(500) DEFAULT NULL COMMENT '机器人ID',
    `title`        varchar(500) DEFAULT NULL COMMENT '任务id',
    `category`     int(11)      DEFAULT NULL COMMENT '城市名称',
    `release_time` datetime     DEFAULT NULL COMMENT '网点名称',
    `content`      varchar(200) DEFAULT NULL COMMENT '网点编码',
    `create_user`  bigint(20)   DEFAULT NULL COMMENT '创建时间',
    `create_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `update_user`  bigint(20)   DEFAULT NULL COMMENT '更新时间',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `status`       int(11)      DEFAULT NULL COMMENT '更新时间',
    `is_deleted`   int(11)      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='城市门店信息';




