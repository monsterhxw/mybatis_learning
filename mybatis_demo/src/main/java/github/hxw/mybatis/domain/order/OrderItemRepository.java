package github.hxw.mybatis.domain.order;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public interface OrderItemRepository {

    // 根据id查询OrderItem对象
    OrderItemEntity find(long id);

    // 查询指定的订单中的全部OrderItem
    List<OrderItemEntity> findByOrderId(long orderId);

    // 保存一个OrderItem信息
    long save(@Param("orderItem") OrderItemEntity orderItem, @Param("orderId") long orderId, @Param("price") BigDecimal price);
}
