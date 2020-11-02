package top.hanjjie.cloud.dao;

import org.apache.ibatis.annotations.Param;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;

/**
 * 商品持久层
 */
public interface GoodsDAO {

    /**
     * 添加商品
     */
    int add(GoodsDTO goodsDTO);

    /**
     * 获取商品
     */
    Goods get(@Param("id") Long id);

}
