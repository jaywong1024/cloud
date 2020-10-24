/**
  订单表
 */
create table `order`
(
    id bigint auto_increment,
    status int not null default 0 comment '订单状态',
    money decimal not null comment '订单金额',
    discount decimal not null comment '折扣金额',
    time datetime not null comment '订单创建时间',
    remark varchar(200) null comment '订单备注',
    constraint order_pk
        primary key (id)
)
    comment '订单表';