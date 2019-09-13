-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS mp_page_user;

CREATE TABLE mp_page_user
(
  id      BIGINT (20) NOT NULL COMMENT '主键ID',
  name    VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  age     INT (11) NULL DEFAULT NULL COMMENT '年龄',
  email   VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mp_page_children;

CREATE TABLE mp_page_children
(
    id      BIGINT (20) NOT NULL COMMENT '主键ID',
    name    VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    mp_page_user_id BIGINT (20) NULL DEFAULT NULL COMMENT '上级ID',
    PRIMARY KEY (id)
);

-- 数据
DELETE FROM mp_page_children;
DELETE FROM mp_page_user;

INSERT INTO mp_page_children (id, name, mp_page_user_id)
VALUES (1, 'Jone', 1),
       (2, 'Jack', 1),
       (3, 'Jack2', 1),
       (4, 'Jack', 15),
       (5, 'Billie', 15);

INSERT INTO mp_page_user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Jack', 20, 'test2@baomidou.com'),
       (4, 'Jack', 20, 'test2@baomidou.com'),
       (5, 'Jack', 20, 'test2@baomidou.com'),
       (6, 'Jack', 20, 'test2@baomidou.com'),
       (7, 'Jack', 20, 'test2@baomidou.com'),
       (8, 'Jack', 20, 'test2@baomidou.com'),
       (9, 'Jack', 20, 'test2@baomidou.com'),
       (10, 'Jack', 20, 'test2@baomidou.com'),
       (11, 'Jack', 20, 'test2@baomidou.com'),
       (12, 'Jack', 20, 'test2@baomidou.com'),
       (13, 'Jack', 20, 'test2@baomidou.com'),
       (14, 'Jack', 20, 'test2@baomidou.com'),
       (15, 'Tom', 28, 'test3@baomidou.com'),
       (16, 'Sandy', 21, 'test4@baomidou.com'),
       (17, 'Billie', 24, 'test5@baomidou.com');
