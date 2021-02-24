package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.exception.ParamsException;
import top.hanjjie.cloud.tools.GoodsService;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

/**
 * 商品接口
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 添加一条商品信息
     */
    @PostMapping("/goods")
    public ResultBean<GoodsDTO> goods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ParamsException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return new ResultBean<>(goodsService.add(goodsDTO));
    }

    /**
     * 获取一条商品信息
     */
    @GetMapping("/goods/{id}")
    public ResultBean<Goods> goods(@PathVariable("id") Long id) {
        return new ResultBean<>(Optional.ofNullable(goodsService.get(
                Optional.ofNullable(id).orElseThrow(() -> new ParamsException("id is required")))).orElseGet(Goods::new));
    }

}
