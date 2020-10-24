package top.hanjjie.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 订单传输对象
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    @NotNull(message = "orderId（订单id）不能为空")
    private Long orderId;
    @NotNull(message = "paymentType（支付类型）不能为空")
    private Integer paymentType;

}
