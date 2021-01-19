package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.exception.ParamsException;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/api")
@RestController
@Slf4j
public class ClientController {

    private static final MultiValueMap<String, String> POST_HEADERS;
    static {
        POST_HEADERS = new HttpHeaders();
        POST_HEADERS.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    @Value("${provider.goods}")
    private String goods;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 添加一条商品信息
     */
    @PostMapping("/goods")
    public ResultBean<GoodsDTO> goods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ParamsException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return restTemplate.postForObject(goods, goodsDTO, ResultBean.class);
    }

    /**
     * 添加一条商品信息，使用 restTemplate.exchange() 方法
     */
    @PostMapping("/goodsForEntity")
    public ResultBean<GoodsDTO> goodsForEntity(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ParamsException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        ResponseEntity<ResultBean<GoodsDTO>> goodsForEntity = restTemplate.exchange(goods, HttpMethod.POST, new HttpEntity<>(goodsDTO, POST_HEADERS), new ParameterizedTypeReference<ResultBean<GoodsDTO>>() {});
        log.info("forEntity:{}", goodsForEntity);
        if (goodsForEntity.getStatusCode().is2xxSuccessful()) return goodsForEntity.getBody();
        return new ResultBean<>("添加商品信息失败");
    }

    /**
     * 获取一条商品信息
     */
    @GetMapping("/goods/{id}")
    public ResultBean<Goods> goods(@PathVariable("id") Long id) {
        return restTemplate.getForObject(goods + Optional.ofNullable(id).orElseThrow(() -> new ParamsException("id is required")), ResultBean.class);
    }

    /**
     * 获取一条商品信息，使用 restTemplate.exchange() 方法
     */
    @GetMapping("/goodsForEntity/{id}")
    public ResultBean<Goods> goodsForEntity(@PathVariable("id") Long id) {
        ResponseEntity<ResultBean<Goods>> goodsForEntity = restTemplate.exchange(goods + Optional.ofNullable(id).orElseThrow(() -> new ParamsException("id is required")), HttpMethod.GET, new HttpEntity<>(null), new ParameterizedTypeReference<ResultBean<Goods>>() {});
        log.info("forEntity:{}", goodsForEntity);
        if (goodsForEntity.getStatusCode().is2xxSuccessful()) return goodsForEntity.getBody();
        return new ResultBean<>("获取商品信息失败");
    }

}
