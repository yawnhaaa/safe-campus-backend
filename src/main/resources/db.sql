CREATE DATABASE IF NOT EXISTS `safe_campus` DEFAULT CHARACTER SET utf8;

USE `safe_campus`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`      varchar(10) NOT NULL COMMENT '昵称',
    `email`     varchar(50) NOT NULL COMMENT '邮箱',
    `passwd`    varchar(64) NOT NULL COMMENT '密码',
    `salt`      varchar(64) NOT NULL COMMENT '盐',
    `intro`     varchar(200) COMMENT '个人介绍',
    `gender`    tinyint(1) COMMENT '性别',
    `school`    varchar(20) COMMENT '学校',
    `college`   varchar(20) COMMENT '学院',
    `stu_num`   varchar(20) COMMENT '学号',
    `is_delete` tinyint(1)  NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin`
(
    `id`       bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(10) NOT NULL COMMENT '用户名',
    `passwd`   varchar(64) NOT NULL COMMENT '密码',
    `salt`     varchar(64) NOT NULL COMMENT '盐',
    PRIMARY KEY (`id`)
);

INSERT INTO admin (username, passwd, salt)
VALUES ('admin',
        'f5e90050be7738876e6cdbf9d0e912a9fcbda9d15a9c2bb2f15292ca0855c6f4',
        'f716c58d2f7e8d3967b210be30f50946d8f4c05964d59ae4bf7c4aeabaa19001');