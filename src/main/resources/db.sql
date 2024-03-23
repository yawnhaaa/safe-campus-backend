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

INSERT INTO info (title, author, author_id, info_date, info_like, info_collect, content)
VALUES ('新闻资讯 1', '阿豪', 1, '2024-3-18', 5222, 111,
        '这这里是正文内容l1. 这里是正文内容l这里是正文内容2.  l这里是正文内容l这里是正文3.   内容l这里是正文内容l这里4.    是正文内容l这里是正文内容l这里是正文5. \n内容l这里是正文内容l这里是正6. \\n文内容l这里是正7.\\n 文内容l这里是正文内容l这里是正文内容8. \\n l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 2', '阿豪2', 2, '2024-3-18', 0, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 3', '阿豪3', 3, '2024-3-18', 2542, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 4', '阿豪4', 4, '2024-3-18', 0, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 5', '阿豪5', 5, '2024-3-18', 22, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 6', '阿豪6', 6, '2024-3-18', 2, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 7', '阿豪7', 7, '2024-3-18', 22, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 8', '阿豪8', 8, '2024-3-18', 22, 111,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 9', '阿豪9', 9, '2024-3-18', 3, 0,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 10', '阿豪10', 10, '2024-3-18', 224, 211,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l'),
       ('新闻资讯 11', '阿豪11', 11, '2024-3-18', 2, 11,
        '这这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l这里是正文内容l里是正文内容l');

DROP TABLE IF EXISTS `info_user`;

CREATE TABLE `info_user`
(
    id           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_name    varchar(10)  NOT NULL COMMENT '用户昵称',
    info_id      bigint(20)   NOT NULL COMMENT '资讯id',
    info_user_id varchar(100) NOT NULL COMMENT '复合id',
    is_like      tinyint(1) DEFAULT 0 COMMENT '是否喜欢',
    like_time    datetime COMMENT '喜欢时间',
    is_collect   tinyint(1) DEFAULT 0 COMMENT '是否收藏',
    collect_time datetime COMMENT '收藏时间',
    UNIQUE KEY `unique_info_user_id` (`info_user_id`),
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment`
(
    id             bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    info_id        bigint(20)   NOT NULL COMMENT '资讯id',
    comment        varchar(100) NOT NULL COMMENT '评论内容',
    comment_id     bigint(20)   NOT NULL COMMENT '评论人id',
    comment_name   varchar(10)  NOT NULL COMMENT '评论人昵称',
    commented_id   bigint(20) COMMENT '被评论人id',
    commented_name varchar(10) COMMENT '被评论人昵称',
    top_id         bigint(20) COMMENT '顶级评论id',
    comment_time   datetime     NOT NULL COMMENT '评论时间',
    is_delete      tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material`
(
    id            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    src           varchar(100) NOT NULL COMMENT '素材路径',
    img_src       varchar(100) COMMENT '列表图片路径',
    title         varchar(20)  NOT NULL COMMENT '素材标题',
    author        varchar(10)  NOT NULL COMMENT '作者',
    author_id     bigint(20)   NOT NULL COMMENT '作者id',
    material_date datetime     NOT NULL COMMENT '发布日期',
    material_type tinyint(1)   NOT NULL COMMENT '素材类型：0图、1视频、2音频',
    download      int(10)    DEFAULT 0 COMMENT '下载次数',
    is_delete     tinyint(1) DEFAULT 0 COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
);

INSERT INTO material (src, title, author, author_id, material_date, material_type)
VALUES ('/audios/music.mp3', '枫', '周杰伦', '1', '2024-03-23 13:08:42', 2),
       ('/images/fanzha.jpg', '坚持反诈不动摇', '帅气程序员', '1', '2024-03-23 13:08:42', 0),
       ('/videos/video.mp4', '反诈工作贯彻到底', '帅气发布者', '1', '2024-03-23 13:08:42', 1);