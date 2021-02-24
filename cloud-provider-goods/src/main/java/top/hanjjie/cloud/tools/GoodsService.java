package top.hanjjie.cloud.tools;


import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;

/**
 * 商品业务
 */
public interface GoodsService {

    /**
     * 添加商品
     */
    GoodsDTO add(GoodsDTO goodsDTO);

    /**
     * 获取商品
     */
    Goods get(Long orderId);

}
