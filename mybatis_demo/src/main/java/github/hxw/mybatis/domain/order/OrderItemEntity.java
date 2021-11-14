package github.hxw.mybatis.domain.order;

import github.hxw.mybatis.domain.product.ProductEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单条目实体类
 *
 * @author huangxuewei
 * @date 2021/11/14
 */
@Getter
@Setter
@ToString
public class OrderItemEntity {

    private Long id;

    /**
     * 订单
     */
    private OrderEntity order;

    /**
     * 订单 ID
     */
    private Long orderId;

    /**
     * 商品
     */
    private ProductEntity product;

    /**
     * 购买商品的个数
     */
    private Integer amount;

    /**
     * OrderItem 的总金额 product.price * amount
     */
    private BigDecimal price;
}
