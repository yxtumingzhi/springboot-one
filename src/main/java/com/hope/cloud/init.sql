CREATE TABLE `user`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `login_name`     varchar(100)                             DEFAULT NULL COMMENT '用户名',
    `passwd`         varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '密码',
    `email`          varchar(100) CHARACTER SET utf8          DEFAULT NULL COMMENT '电子邮件',
    `mobile`         varchar(20) CHARACTER SET utf8           DEFAULT NULL COMMENT '手机号码',
    `mobile_encrypt` varchar(255)                             DEFAULT NULL COMMENT '手机号密文',
    `encrypt_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否加密过手机号，0：否，1：是',
    `email_status`   tinyint(2) DEFAULT '0' COMMENT '邮件绑定状态 0未绑定 1绑定',
    `mobile_status`  tinyint(2) DEFAULT '0' COMMENT '手机绑定状态 0未绑定 1绑定',
    `register_ip`    varchar(50) CHARACTER SET utf8           DEFAULT NULL COMMENT '注册IP地址',
    `user_status`    tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户状态 0未激活/黑名单 1激活  2-已注销',
    `updated_time`   bigint(20) NOT NULL COMMENT '最后更新时间',
    `created_time`   bigint(20) NOT NULL COMMENT '注册时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `login_name_in` (`login_name`),
    UNIQUE KEY `email_in` (`email`),
    UNIQUE KEY `mobile_encry_in` (`mobile_encry`) USING BTREE,
    KEY              `updated_in` (`created_time`),
    KEY              `enc_status_in` (`encrypt_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='用户注册表';