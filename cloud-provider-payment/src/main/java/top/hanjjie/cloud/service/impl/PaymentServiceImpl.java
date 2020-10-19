package top.hanjjie.cloud.service.impl;

import org.springframework.stereotype.Service;
import top.hanjjie.cloud.dao.PaymentDAO;
import top.hanjjie.cloud.entities.Payment;
import top.hanjjie.cloud.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDAO paymentDAO;

    @Override
    public int add(Payment payment) {
        return paymentDAO.add(payment);
    }

    @Override
    public Payment get(Long id) {
        return paymentDAO.get(id);
    }

}
