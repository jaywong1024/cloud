package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public ResultBean<GoodsDTO> goods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            TODO 等待编写统一的参数异常管理
        }
        return restTemplate.postForObject(goods, goodsDTO, ResultBean.class);
    }

    /**
     * 获取一条商品信息
     */
    @GetMapping("/goods/{id}")
    public ResultBean<Goods> goods(@PathVariable("id") Long id) {
//        TODO 等待编写统一的参数异常管理
        return restTemplate.getForObject(goods + id, ResultBean.class);
    }

}
