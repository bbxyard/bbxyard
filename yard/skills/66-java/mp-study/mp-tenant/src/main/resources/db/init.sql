DROP TABLE IF EXISTS mp_tenant_user;
CREATE TABLE mp_tenant_user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	tenant_id BIGINT(20) NOT NULL COMMENT '租户ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS mp_tenant_user_addr;
CREATE TABLE mp_tenant_user_addr
(
  id BIGINT(20) NOT NULL COMMENT '主键ID',
  user_id BIGINT(20) NOT NULL COMMENT 'mp_tenant_user.id',
  name VARCHAR(30) NULL DEFAULT NULL COMMENT '地址名称',
  PRIMARY KEY (id)
);

DELETE FROM mp_tenant_user;

INSERT INTO mp_tenant_user (id, tenant_id, name) VALUES
(1, 1, 'Jone'),(2, 1, 'Jack'),(3, 1, 'Tom'),
(4, 0, 'Sandy'),(5, 0, 'Billie');

INSERT INTO mp_tenant_user_addr (id, user_id, name) VALUES
(1, 1, 'addr1'),(2,1,'addr2');
