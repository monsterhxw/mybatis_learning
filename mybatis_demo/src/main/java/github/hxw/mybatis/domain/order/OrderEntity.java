package github.hxw.mybatis.domain.order;

import github.hxw.mybatis.domain.customer.AddressEntity;
import github.hxw.mybatis.domain.customer.CustomerEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单实体类
 *
 * @author huangxuewei
 * @date 2021/11/14
 */
@Getter
@Setter
@ToString
public class OrderEntity {

    private Long id;

    /**
     * 用户
     */
    private CustomerEntity customer;

    /**
     * 送货地址
     */
    private AddressEntity deliveryAddress;

    /**
     * 订单条目列表
     */
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;
}
