package github.hxw.mybatis.application.order;

import com.google.common.base.Preconditions;
import github.hxw.mybatis.domain.customer.AddressEntity;
import github.hxw.mybatis.domain.customer.AddressRepository;
import github.hxw.mybatis.domain.order.OrderEntity;
import github.hxw.mybatis.domain.order.OrderItemEntity;
import github.hxw.mybatis.domain.order.OrderItemRepository;
import github.hxw.mybatis.domain.order.OrderRepository;
import github.hxw.mybatis.util.DaoUtils;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public class OrderApplicationService {

    // 创建订单
    public long createOrder(OrderEntity order) {
        Preconditions.checkArgument(order != null, "order is null");
        Preconditions.checkArgument(order.getOrderItems() != null
                && order.getOrderItems().size() > 0,
            "orderItems is empty");
        return DaoUtils.execute(sqlSession -> {
            OrderRepository orderMapper = sqlSession.getMapper(OrderRepository.class);
            OrderItemRepository orderItemMapper = sqlSession.getMapper(OrderItemRepository.class);
            // 调用OrderMapper.save()方法完成订单的持久化
            long affected = orderMapper.save(order);
            if (affected <= 0) {
                throw new RuntimeException("Save Order fail...");
            }
            long orderId = order.getId();
            for (OrderItemEntity orderItem : order.getOrderItems()) {
                // 通过OrderItemMapper完成OrderItem的持久化
                orderItemMapper.save(orderItem, orderId, orderItem.getPrice());
            }
            return orderId;
        });
    }

    // 根据订单id查询订单的全部信息
    public OrderEntity find(long orderId) {
        // 检查orderId参数是否合法
        Preconditions.checkArgument(orderId > 0, "orderId error");
        return DaoUtils.execute(sqlSession -> {
            // 查询该订单关联的全部OrderItem
            OrderItemRepository orderItemMapper = sqlSession.getMapper(OrderItemRepository.class);
            List<OrderItemEntity> orderItems = orderItemMapper.findByOrderId(orderId);
            // 查询订单本身的信息
            OrderRepository orderMapper = sqlSession.getMapper(OrderRepository.class);
            OrderEntity order = orderMapper.find(orderId);
            order.setOrderItems(orderItems);
            // 计算订单总额
            order.setTotalPrice(calculateTotalPrice(order));
            // 查询订单关联的Address
            AddressRepository addressMapper = sqlSession.getMapper(AddressRepository.class);
            AddressEntity address = addressMapper.find(order.getDeliveryAddress().getId());
            order.setDeliveryAddress(address);
            return order;
        });
    }

    private BigDecimal calculateTotalPrice(OrderEntity order) {
        List<OrderItemEntity> orderItems = order.getOrderItems();
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItemEntity orderItem : orderItems) {
            BigDecimal itemPrice = orderItem.getProduct().getPrice()
                .multiply(new BigDecimal(orderItem.getAmount()));
            orderItem.setPrice(itemPrice);
            totalPrice.add(itemPrice);
        }
        return totalPrice;
    }
}
