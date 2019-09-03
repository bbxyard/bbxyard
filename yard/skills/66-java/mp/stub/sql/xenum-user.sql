DROP TABLE IF EXISTS mp_enum_user;

CREATE TABLE mp_enum_user
(
  id BIGINT(20) NOT NULL COMMENT '主键ID',
  name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  age INT(11) NULL DEFAULT NULL COMMENT '年龄',
  gender INT(2) NULL DEFAULT NULL COMMENT '性别,0:MALE,1:FEMALE',
  grade INT(3) NULL DEFAULT NULL COMMENT '年级',
  email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
  deleted INT(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  version INT(11) NOT NULL DEFAULT 1 COMMENT '乐观锁版本',
  operator VARCHAR(30) NULL DEFAULT NULL COMMENT '操作员',
  PRIMARY KEY (id)
);

DELETE FROM mp_enum_user;

INSERT INTO mp_enum_user (id, name, age, gender, grade, email) VALUES
  (1, 'Jone', 1, 0, 3, 'test1@baomidou.com'),
  (2, 'Jack', 2, 1, 5, 'test2@baomidou.com'),
  (3, 'Tom', 2, 0, 1, 'test3@baomidou.com'),
  (4, 'Sandy', 3, 0, 4,'test4@baomidou.com'),
  (5, 'Billie', 1, null, 3, 'test5@baomidou.com');
