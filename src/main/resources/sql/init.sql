CREATE TABLE `lcb_mars_misc_db`.`indicator_meter`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `indicator_code` varchar(500) DEFAULT NULL COMMENT '监控指标编码',
    `indicator_name` varchar(500) DEFAULT NULL COMMENT '监控指标名称',
    `type`           varchar(50)  DEFAULT NULL COMMENT '监控指标类型：counter，gauge，distribution_summary',
    `join_field`     varchar(500) DEFAULT NULL COMMENT '指标主键组合条件，逗号分隔',
    `match_rules`    json         DEFAULT NULL COMMENT '匹配规则，决定是否处理指标注册和更新',
    `sql`            varchar(500) DEFAULT NULL COMMENT '查询SQL',
    `group_id`       bigint(20) DEFAULT NULL COMMENT '店铺ID',
    `group_type`     tinyint(4) DEFAULT NULL COMMENT '店铺类型',
    `deleted`        tinyint(4) DEFAULT '0' COMMENT '是否删除 1删除 0未删除',
    `created_time`   bigint(20) DEFAULT NULL COMMENT '创建时间',
    `updated_time`   bigint(20) DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT='监控指标注册表';

INSERT INTO `lcb_mars_misc_db`.`indicator_meter`(`id`, `indicator_code`, `indicator_name`, `type`, `join_field`, `deleted`) VALUES (1, 'indicator_micro_add_car_nums_001', '添车数量', 'gauge', 'group_type,group_id,indicator_cycle', 0);
INSERT INTO `lcb_mars_misc_db`.`indicator_meter`(`id`, `indicator_code`, `indicator_name`, `type`, `join_field`, `deleted`) VALUES (2, 'indicator_micro_registers_users_003', '店铺_总用户数', 'gauge', 'group_type,group_id,indicator_cycle', 0);
INSERT INTO `lcb_mars_misc_db`.`indicator_meter`(`id`, `indicator_code`, `indicator_name`, `type`, `join_field`, `deleted`) VALUES (3, 'indicator_micro_track_uv_004', '店铺UV', 'gauge', 'group_type,group_id,indicator_cycle', 0);


