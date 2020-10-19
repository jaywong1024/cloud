package top.hanjjie.cloud.dao;

import org.apache.ibatis.annotations.Param;
import top.hanjjie.cloud.entities.Payment;

/**
 * 支付持久层
 */
public interface PaymentDAO {

    /**
     * 添加订单
     */
    int add(Payment payment);

    /**
     * 获取订单
     */
    Payment get(@Param("id") Long id);

}
