package top.hanjjie.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Integer status;
    private BigDecimal money;
    private BigDecimal discount;
    private Date time;
    private String remark;

}
