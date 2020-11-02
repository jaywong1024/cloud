package top.hanjjie.cloud.service.impl;

import org.springframework.stereotype.Service;
import top.hanjjie.cloud.dao.GoodsDAO;
import top.hanjjie.cloud.dto.GoodsDTO;
import top.hanjjie.cloud.entities.Goods;
import top.hanjjie.cloud.service.GoodsService;

import javax.annotation.Resource;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDAO goodsDAO;

    @Override
    public Integer add(GoodsDTO goodsDTO) {
        return goodsDAO.add(goodsDTO);
    }

    @Override
    public Goods get(Long orderId) {
        return goodsDAO.get(orderId);
    }

}
