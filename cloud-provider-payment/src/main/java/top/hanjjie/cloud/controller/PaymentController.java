package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.hanjjie.cloud.dto.PaymentDTO;
import top.hanjjie.cloud.entities.Payment;
import top.hanjjie.cloud.service.PaymentService;
import top.hanjjie.cloud.utils.HttpResponse;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

/**
 * 支付接口
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     * 添加一条支付信息
     */
    @PostMapping("/payment")
    public HttpResponse payment(@RequestBody @Valid PaymentDTO paymentDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
                log.error("========== 添加一条支付信息.参数错误：" + defaultMessage);
                return HttpResponse.paramsError().setData(defaultMessage);
            }
            int result = paymentService.add(paymentDTO);
            log.info("========== 添加一条支付信息.结果：" + result);
            return HttpResponse.success().setData(result);
        } catch (Exception e) {
            log.error("========== 添加一条支付信息.异常：" + e);
            return HttpResponse.serverError().setData(e);
        }
    }

    /**
     * 获取支付信息
     */
    @GetMapping("/payment/{orderId}")
    public HttpResponse payment(@PathVariable("orderId") Long orderId) {
        try {
            if (!Optional.ofNullable(orderId).isPresent()) {
                log.error("========== 获取支付信息.参数错误：订单id不能为空");
                return HttpResponse.paramsError().setData("订单id不能为空");
            }
            Payment payment = paymentService.get(orderId);
            Optional.ofNullable(payment).ifPresent(p -> log.info("========== 获取支付信息.结果：" + p));
            return HttpResponse.success().setData(payment);
        } catch (Exception e) {
            log.error("========== 获取支付信息.异常：" + e);
            return HttpResponse.serverError().setData(e);
        }
    }

}
