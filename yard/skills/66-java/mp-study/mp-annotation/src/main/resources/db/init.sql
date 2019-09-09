DROP TABLE IF EXISTS mp_annotation_user;

CREATE TABLE mp_annotation_user
(
  user_id BIGINT(20) NOT NULL COMMENT '主键ID',
  user_name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  age INT(11) NULL DEFAULT NULL COMMENT '年龄',
  email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
  deleted INT(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  version INT(11) NOT NULL DEFAULT 1 COMMENT '乐观锁版本',
  PRIMARY KEY (user_id)
);

DELETE FROM mp_annotation_user;

INSERT INTO mp_annotation_user (user_id, user_name, age, email) VALUES
  (1, 'Jone', 18, 'test1@baomidou.com'),
  (2, 'Jack', 20, 'test2@baomidou.com'),
  (3, 'Tom', 28, 'test3@baomidou.com'),
  (4, 'Sandy', 21, 'test4@baomidou.com'),
  (5, 'Billie', 24, 'test5@baomidou.com');
