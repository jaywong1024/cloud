package top.hanjjie.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.utils.ResultBean;

/**
 * Openfeign Goods 业务类
 */
@Component
@FeignClient(value = "${provider.goods}")
@RequestMapping("/api")
public interface OpGoodsService {

    @PostMapping("/goods")
    ResultBean<GoodsDTO> goods(@RequestBody GoodsDTO goodsDTO);

    @GetMapping("/goods/{id}")
    ResultBean<Goods> goods(@PathVariable("id") Long id);
}
