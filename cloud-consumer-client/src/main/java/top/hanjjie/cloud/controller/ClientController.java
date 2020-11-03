package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.utils.HttpResponse;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@Slf4j
public class ClientController {

    @Value("${provider.goods}")
    private String goods;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 添加一条商品信息
     */
    @PostMapping("/goods")
    public HttpResponse goods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
                log.error("========== 添加一条商品信息.参数错误：" + defaultMessage);
                return HttpResponse.paramsError().setData(defaultMessage);
            }
            return restTemplate.postForObject(goods, goodsDTO, HttpResponse.class);
        } catch (Exception e) {
            log.error("========== 添加一条商品信息.异常：" + e);
            return HttpResponse.serverError().setData(e);
        }
    }

    /**
     * 获取一条商品信息
     */
    @GetMapping("/goods/{id}")
    public HttpResponse goods(@PathVariable("id") Long id) {
        try {
            if (!Optional.ofNullable(id).isPresent()) {
                log.error("========== 获取一条商品信息.参数错误：商品id不能为空");
                return HttpResponse.paramsError().setData("商品id不能为空");
            }
            return restTemplate.getForObject(goods + id, HttpResponse.class);
        } catch (Exception e) {
            log.error("========== 获取一条商品信息.异常：" + e);
            return HttpResponse.serverError().setData(e);
        }
    }

}
