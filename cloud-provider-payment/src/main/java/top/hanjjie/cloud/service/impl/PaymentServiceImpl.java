package top.hanjjie.cloud.service.impl;

import org.springframework.stereotype.Service;
import top.hanjjie.cloud.dto.PaymentDTO;
import top.hanjjie.cloud.dao.PaymentDAO;
import top.hanjjie.cloud.entities.Payment;
import top.hanjjie.cloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDAO paymentDAO;

    @Override
    public int add(PaymentDTO paymentDTO) {
        if (Optional.ofNullable(paymentDAO.get(paymentDTO.getOrderId())).isPresent()) return -1;
        return paymentDAO.add(paymentDTO);
    }

    @Override
    public Payment get(Long orderId) {
        return paymentDAO.get(orderId);
    }

}
