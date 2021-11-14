package github.hxw.mybatis.domain.order;

import java.util.List;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public interface OrderRepository {

    // 根据订单Id查询
    OrderEntity find(long id);

    // 查询一个用户一段时间段内的订单列表
    List<OrderEntity> findByCustomerId(long customerId, long startTime, long endTime);

    // 保存一个订单
    long save(OrderEntity order);
}
