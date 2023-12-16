create table customer
(
    id                 bigint auto_increment,
    customer_id        varchar(20),
    member_id          varchar(20),
    global_id          varchar(20),
    customer_name      varchar(50),
    registered_capital bigint,
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id    BIGINT(20) NOT NULL COMMENT '主键ID',
    name  VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age   INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);
