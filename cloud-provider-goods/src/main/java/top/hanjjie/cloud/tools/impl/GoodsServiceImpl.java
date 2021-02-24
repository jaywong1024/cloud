package top.hanjjie.cloud.tools.impl;

import org.springframework.stereotype.Service;
import top.hanjjie.cloud.dao.GoodsDAO;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.tools.GoodsService;

import javax.annotation.Resource;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDAO goodsDAO;

    @Override
    public GoodsDTO add(GoodsDTO goodsDTO) {
        goodsDAO.add(goodsDTO);
        return goodsDTO;
    }

    @Override
    public Goods get(Long orderId) {
        return goodsDAO.get(orderId);
    }

}
