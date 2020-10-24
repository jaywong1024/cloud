/**
  支付表
 */
create table payment
(
    id bigint auto_increment,
    order_id bigint not null comment '订单id',
    type int not null comment '支付方式',
    money decimal not null comment '支付金额',
    time datetime not null comment '支付时间',
    constraint payment_pk
        primary key (id)
)
    comment '支付表';