package top.hanjjie.cloud.service;


import top.hanjjie.cloud.entities.Payment;

/**
 * 支付业务
 */
public interface PaymentService {

    /**
     * 添加订单
     */
    int add(Payment payment);

    /**
     * 获取订单
     */
    Payment get(Long id);

}
