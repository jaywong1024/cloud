package top.hanjjie.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商品实体类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;

}
