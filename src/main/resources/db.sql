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

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info`
(
    `id`           bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`        varchar(20) NOT NULL COMMENT '资讯标题',
    `author`       varchar(10) NOT NULL COMMENT '资讯作者',
    `author_id`    bigint(20)  NOT NULL COMMENT '资讯作者主键',
    `info_date`    DATE        NOT NULL COMMENT '资讯发布时间',
    `content`      TEXT        NOT NULL COMMENT '资讯内容',
    `img`          varchar(20) COMMENT '图片地址',
    `info_like`    int(10)     NOT NULL DEFAULT 0 COMMENT '点赞数',
    `info_collect` int(10)     NOT NULL DEFAULT 0 COMMENT '收藏数',
    `is_delete`    tinyint(1)  NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

INSERT INTO info (title, author, author_id, info_date, content)
VALUES ('新闻资讯 1', '阿豪', 1, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 2', '阿豪2', 2, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 3', '阿豪3', 3, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 4', '阿豪4', 4, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 5', '阿豪5', 5, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 6', '阿豪6', 6, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 7', '阿豪7', 7, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 8', '阿豪8', 8, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 9', '阿豪9', 9, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 10', '阿豪10', 10, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 11', '阿豪11', 11, '2024-3-18',
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l');
