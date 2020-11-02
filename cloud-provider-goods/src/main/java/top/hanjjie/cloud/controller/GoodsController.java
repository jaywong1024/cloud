package top.hanjjie.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.service.GoodsService;
import top.hanjjie.cloud.utils.HttpResponse;

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
    public HttpResponse goods(@RequestBody @Valid GoodsDTO goodsDTO, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
                log.error("========== 添加一条商品信息.参数错误：" + defaultMessage);
                return HttpResponse.paramsError().setData(defaultMessage);
            }
            Integer result = goodsService.add(goodsDTO);
            assert (Optional.ofNullable(result).isPresent());
            if (result > 0) log.info("========== 添加一条商品信息.成功：" + goodsDTO);
            else log.warn("========== 添加一条商品信息.失败");
            return HttpResponse.success().setData(goodsDTO);
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
            Goods goods = Optional.ofNullable(goodsService.get(id)).orElseGet(Goods::new);
            log.info("========== 获取一条商品信息.结果：" + goods);
            return HttpResponse.success().setData(goods);
        } catch (Exception e) {
            log.error("========== 获取一条商品信息.异常：" + e);
            return HttpResponse.serverError().setData(e);
        }
    }

}
