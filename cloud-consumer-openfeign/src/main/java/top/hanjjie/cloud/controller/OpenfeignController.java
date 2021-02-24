package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.exception.ParamsException;
import top.hanjjie.cloud.tools.OpGoodsService;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

/**
 * 商品接口
 */
@Slf4j
@RestController
@RequestMapping("/api/openfeign")
public class OpenfeignController {

    @Resource
    private OpGoodsService opGoodsService;

    /**
     * 添加一条商品信息
     */
    @PostMapping("/goods")
    public ResultBean<GoodsDTO> goods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new ParamsException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        return opGoodsService.goods(goodsDTO);
    }

    /**
     * 获取一条商品信息
     */
    @GetMapping("/goods/{id}")
    public ResultBean<Goods> goods(@PathVariable("id") Long id) {
        return opGoodsService.goods(id);
    }

}
