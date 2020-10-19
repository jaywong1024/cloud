package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.hanjjie.cloud.entities.Payment;
import top.hanjjie.cloud.service.PaymentService;
import top.hanjjie.cloud.utils.HttpResponse;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment")
    public HttpResponse payment(@RequestBody Payment payment) {
        try {
//            if (payment == null || StringUtils)
            int result = paymentService.add(payment);
            log.info("========== 插入订单结果：" + result);
            return HttpResponse.success().setData(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            return HttpResponse.serverError().setData(e.getMessage());
        }
    }

    @GetMapping("/payment/{id}")
    public HttpResponse payment(@PathVariable("id") Long id) {
        try {
            if (id == null || id <= 0) return HttpResponse.paramsError().setData("id is required");
            Payment payment = paymentService.get(id);
            log.info("========== 插入查询结果：" + payment.toString());
            return HttpResponse.success().setData(payment);
        } catch (Exception e) {
            log.error(e.getMessage());
            return HttpResponse.serverError().setData(e.getMessage());
        }
    }

}
