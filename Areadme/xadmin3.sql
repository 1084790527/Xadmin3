CREATE TABLE x_service(
  id VARCHAR(20),
  name VARCHAR(50) COMMENT '服务商',
  real_name VARCHAR(50) COMMENT '真实姓名',
  mobile_no VARCHAR(11) COMMENT '手机号/登入账号',
  state VARCHAR(2) COMMENT '1 启用  0 停用  2 删除',
  reg_date DATETIME COMMENT '注册日期',
  cre_oper_id VARCHAR(20) COMMENT '创建操作员id',
  cre_oper_date DATETIME COMMENT '创建操作员时间',
  last_oper_id VARCHAR(20) COMMENT '最后修改操作员id',
  last_oper_date DATETIME COMMENT '最后修改操作员时间',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='服务商 数据分离';

CREATE TABLE x_manager(
  id VARCHAR(20),
  service_id VARCHAR(20) COMMENT '服务商id',
  role_id VARCHAR(20) COMMENT '角色id',
  nickname VARCHAR(50) COMMENT '昵称',
  mobile_no VARCHAR(11) COMMENT '手机号/登入账号',
  password VARCHAR(100) COMMENT '密码',
  reg_date DATETIME COMMENT '注册日期',
  state VARCHAR(2) DEFAULT '1' COMMENT '用户状态 2删除 1启用 0停用',
  update_date DATETIME COMMENT '用户修改日期',
  last_login_date DATETIME COMMENT '最后登录日期',
  last_oper_id VARCHAR(20) COMMENT '最后修改操作员id',
  last_oper_date DATETIME COMMENT '最后修改操作员时间',
  last_login_ip VARCHAR(100) COMMENT '最后登录ip',
  defaults varchar(2) COMMENT '1 系统默认管理员不可更改  0 由机构操作员创建的管理员',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理员';
ALTER TABLE x_manager ADD INDEX (mobile_no);
ALTER TABLE x_manager ADD FULLTEXT (role_id);
ALTER TABLE x_manager ADD FULLTEXT (state);
ALTER TABLE x_manager ADD FULLTEXT (service_id);
ALTER TABLE x_manager ADD login_error VARCHAR(2) COMMENT '登入错误次数';  # 预计统计功能

CREATE TABLE x_manager_role(
  manager_id VARCHAR(20),
  role_id VARCHAR(20),
  cre_oper_id VARCHAR(20) COMMENT '创建操作员id',
  cre_oper_date DATETIME COMMENT '创建时间',
  PRIMARY KEY (manager_id,role_id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统管理员-角色';
CREATE TABLE x_role(
  id VARCHAR(20),
  name VARCHAR(50) COMMENT '角色名称',
  service_id VARCHAR(20),
  description VARCHAR(200) COMMENT '角色描述',
  state VARCHAR(2) COMMENT '1 启用  0 停用  2 删除',
  defaults VARCHAR(2) COMMENT '1 系统默认不可更改  0 由机管理员创建',
  cre_oper_id VARCHAR(20) COMMENT '创建操作员id',
  cre_oper_date DATETIME COMMENT '创建时间',
  last_oper_id VARCHAR(20) COMMENT '最后修改操作员id',
  last_oper_date DATETIME COMMENT '最后修改时间',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色';
ALTER TABLE x_role ADD FULLTEXT (state);
ALTER TABLE x_role ADD FULLTEXT (defaults);
ALTER TABLE x_role ADD FULLTEXT (service_id);

CREATE TABLE x_privileges(
  id VARCHAR(20),
  name VARCHAR(50) COMMENT '权限名称',
  description VARCHAR(200) COMMENT '权限描述',
  parent_id VARCHAR(20) COMMENT '权限父id 顶级权限 空',
  menu_level INTEGER DEFAULT 0 COMMENT '菜单层级',
  permission VARCHAR(50) COMMENT '权限名称',
  permission_type INTEGER default 0 COMMENT '1 菜单  0 功能',
  url VARCHAR(50) COMMENT '权限url',
  param VARCHAR(50) COMMENT 'url一样 参数判断',
  method_type VARCHAR(50) COMMENT '权限请求方式',
  state VARCHAR(2) COMMENT '1 启用  0 停用',
  PRIMARY KEY (id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';
ALTER TABLE x_privileges ADD FULLTEXT (parent_id);
ALTER TABLE x_privileges ADD INDEX (permission);
ALTER TABLE x_privileges ADD INDEX (url,param);
ALTER TABLE x_privileges ADD FULLTEXT (state);

CREATE TABLE x_role_privileges(
  role_id VARCHAR(20),
  privileges_id VARCHAR(20),
  cre_oper_id VARCHAR(20) COMMENT '创建操作员id',
  cre_oper_date DATETIME COMMENT '最后修改时间',
  PRIMARY KEY (role_id,privileges_id)
)
  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限';

CREATE TABLE x_system_log_202103(
  id VARCHAR(20),
  request_url VARCHAR(200) COMMENT '请求url',
  method VARCHAR(10) COMMENT 'url请求方式',
  ip VARCHAR(80) COMMENT '请求ip',
  class_method VARCHAR(100) COMMENT '类方法',
  args VARCHAR(8192) COMMENT '请求参数',
  cre_date DATETIME COMMENT '请求时间',
  manager_id VARCHAR(20) COMMENT '操作员id 可能空',
  type VARCHAR(2) DEFAULT '0' COMMENT '1 登入成功后操作有操作员id  0 还没有登入没有操作员id',
  PRIMARY KEY (id)
)
  ENGINE=MYISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='aop统计日志';
CREATE TABLE x_system_log_202104(
  id VARCHAR(20),
  request_url VARCHAR(200) COMMENT '请求url',
  method VARCHAR(10) COMMENT 'url请求方式',
  ip VARCHAR(80) COMMENT '请求ip',
  class_method VARCHAR(100) COMMENT '类方法',
  args VARCHAR(8192) COMMENT '请求参数',
  cre_date DATETIME COMMENT '请求时间',
  manager_id VARCHAR(20) COMMENT '操作员id 可能空',
  type VARCHAR(2) DEFAULT '0' COMMENT '1 登入成功后操作有操作员id  0 还没有登入没有操作员id',
  PRIMARY KEY (id)
)
  ENGINE=MYISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='aop统计日志';
CREATE TABLE x_system_log(
  id VARCHAR(20),
  request_url VARCHAR(200) COMMENT '请求url',
  method VARCHAR(10) COMMENT 'url请求方式',
  ip VARCHAR(80) COMMENT '请求ip',
  class_method VARCHAR(100) COMMENT '类方法',
  args VARCHAR(8192) COMMENT '请求参数',
  cre_date DATETIME COMMENT '请求时间',
  manager_id VARCHAR(20) COMMENT '操作员id 可能空',
  type VARCHAR(2) DEFAULT '0' COMMENT '1 登入成功后操作有操作员id  0 还没有登入没有操作员id',
  PRIMARY KEY (id)
)
  ENGINE = MERGE UNION = (x_system_log_202103,x_system_log_202104) CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci INSERT_METHOD=LAST;

ALTER TABLE x_system_log ENGINE = MERGE UNION = (x_system_log_202103,x_system_log_202104) CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci INSERT_METHOD=LAST;
停用  disable
启用  enable
删除  delete
  INSERT INTO `xadmin3`.`x_role_privileges`(`role_id`, `privileges_id`) VALUES ('0001', '92121');


