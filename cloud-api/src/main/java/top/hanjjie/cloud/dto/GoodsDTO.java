package top.hanjjie.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品传输对象
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO {

    private Long id;

    @NotNull(message = "name（商品名称）不能为空")
    private String name;

    @NotNull(message = "description（商品描述）不能为空")
    private String description;

    @NotNull(message = "price（商品价格）不能为空")
    @Min(value = 0, message = "price（商品价格）不能小于 0 元")
    private BigDecimal price;

}
