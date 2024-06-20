CREATE TABLE `account_tbl`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id` varchar(255) not NULL COMMENT '用户 id',
    `money`   bigint(20) DEFAULT 0 COMMENT '用户余额',
    `create_time` TIMESTAMP NOT NULL default current_timestamp,
    `update_time` TIMESTAMP NOT NULL on update current_timestamp default current_timestamp,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id_key` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户账户余额表';