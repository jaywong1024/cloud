/**
  商品表
 */
create table goods
(
    id bigint not null auto_increment,
    name varchar(32) not null comment '商品名称',
    price decimal not null comment '商品价格（元）',
    description varchar(128) not null comment '商品描述',
    constraint goods_pk
        primary key (id)
)
    comment '商品表';